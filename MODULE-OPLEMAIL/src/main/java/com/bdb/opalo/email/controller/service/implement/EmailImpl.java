/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opalo.email.controller.service.implement;

import com.bdb.opalo.email.controller.service.exception.ExceptionHandling;
import com.bdb.opalo.email.controller.service.interfaces.EmailDataService;
import com.bdb.opalo.email.controller.service.interfaces.EmailService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.OplParCorreoEntity;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Servicio encargado de enviar correo electronico
 *
 * @author: Esteban Talero
 * @version: 26/07/2020
 * @since: 26/07/2020
 */
@Service
public class EmailImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    /**
     * Libreria que nos permite enviar el correo
     */
    @Autowired
    JavaMailSenderImpl sender;

    /**
     * Interfaz que nos retorna todas las caracteristicas del correo a enviar
     * dependiendo el id de Perfil a consultar
     */
    @Autowired
    EmailDataService emailDataService;

    /**
     * Interfaz que nos permite conectarnos al FTP para tomar los archivos
     * que iran adjuntos en el correo electronico
     */
    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    /**
     * Interfaz que nos permite conectarnos con la tabla de varEntorno de la Bd
     * para terminar si la funcionalidad para enviar correo esta habilitada o no
     */
    @Autowired
    RepositoryTipVarentorno repoVarentorno;

    /**
     * Método encargado de consultar en la base de datos los datos del correo a enviar
     *
     * @param idPerfil el valor para consultar las caracteristicas del correo a enviar
     * @return Si el correo fue enviado exitosamente o no
     */
    @Override
    public boolean sendEmail(Integer idPerfil, String emailSubjectType, Boolean attached, String emailContentType)
            throws ExceptionHandling {

        LOGGER.info("EmailBody: {}", idPerfil);
        //Consultamos la tabla varEntorno de la BD para validar si la funcionalidad de correo esta habilitada
        //o no, si esta en 0 quiere decir que esta inactiva si esta en 1 significa que esta activa
        VarentornoDownEntity varEntorno = repoVarentorno.findByDescVariable("MAIL_ENABLED");
        if (emailSubjectType.equals("0") || varEntorno.getValVariable().equals("0")) {
            return false;
        } else {
            VarentornoDownEntity emailHost = repoVarentorno.findByDescVariable("MAIL_HOST");
            VarentornoDownEntity emailPort = repoVarentorno.findByDescVariable("MAIL_PORT");
            OplParCorreoEntity oplParCorreoEntity = emailDataService.getByIdPerfil(idPerfil);
            return sendEmailTool(idPerfil, emailSubjectType, emailContentType,
                    oplParCorreoEntity.getContenido(), oplParCorreoEntity.getCuentaOrigen(), oplParCorreoEntity.getCuentaDestino(), oplParCorreoEntity.getAsunto(), oplParCorreoEntity.getCuentaCopia(), oplParCorreoEntity.getCuentaOculta(), oplParCorreoEntity.getAdjuntos(), attached, emailHost.getValVariable(), emailPort.getValVariable());
        }
    }

    /**
     * Metodo encargado de establecer la conexión con el sitio FTPs
     */
    private void ftpsSiteConnection() throws ExceptionHandling {
        try {
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(),
                    parameters.getPassword());
        } catch (Exception e) {
            throw new ExceptionHandling(409, "Error al conectar al Sitio FTP");
        }
    }

    /**
     * Metodo encargado de enviar el correo Electronico con los datos consultados de la base de datos
     * y los archivos adjuntos(si es el caso)
     *
     * @param idPerfil         id plantilla del correo BD
     * @param emailSubjectType tipo de asunto del correo
     * @param emailContentType cuerpo del mensaje del correo
     * @param textMessage      Cuerpo del mensaje
     * @param originMail       Cuenta origen
     * @param email            Cuenta destino
     * @param subject          Asunto del correo
     * @param emailCC          Cuentas que van en copia
     * @param emailCCO         Cuentas que van en copia Oculta
     * @param attachedFile     Archivos adjuntos
     * @return boolean si el correo fue enviado o no
     */
    private boolean sendEmailTool(Integer idPerfil, String emailSubjectType, String emailContentType, String textMessage,
                                  String originMail, String email, String subject, String emailCC, String emailCCO,
                                  String attachedFile, Boolean attached, String emailHost, String emailPort)
            throws ExceptionHandling {
        try {
            //Establecemos la conexión con el sitio FTP
            LOGGER.info("ENVIO DE CORREO - Se inicia a establecer la conexion con FTP");
            ftpsSiteConnection();
            String textMessageEmail = "";

            LOGGER.info("ENVIO DE CORREO - Se MAPEA objeto para envio de correo");
            //Mapeamos los datos del correo electronico
            sender.setHost(emailHost);
            sender.setPort(Integer.parseInt(emailPort));
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(originMail);
            if (jsonObjectValid(textMessage)) {
                textMessageEmail = emailSubjectType(textMessage, emailContentType);
                helper.setText("<html> <body><span style='color: black;'>" + textMessageEmail + "<br/><br/></span><br/><br/><br/> </body></html>", true);
            }
            helper.setSubject(emailSubjectType(subject, emailSubjectType));
            //Mapeamos listado de destinatarios
            if (jsonObjectValid(email)) {
                helper.setTo(iterateEmail(email));
            }
            //Mapeamos listado de cuentas de correo en Copia Normal
            if (jsonObjectValid(emailCC)) {
                helper.setCc(iterateEmail(emailCC));
            }
            //Mapeamos listado de cuentas de correo en Copia Oculta
            if (jsonObjectValid(emailCCO)) {
                helper.setBcc(iterateEmail(emailCCO));
            }
            LOGGER.info("ENVIO DE CORREO - se verifica si el correo lleva archivos adjunto o no...");
            if (attached) {
                generateAttachmentObject(textMessageEmail, attachedFile, message, idPerfil);
            }
            LOGGER.info("ENVIO DE CORREO - enviando correo electronico...");
            sender.send(message);
            LOGGER.info("Mail enviado!");
        } catch (IOException | MessagingException | ErrorFtps e) {
            throw new ExceptionHandling(503, "Error al enviar Correo");
        }
        return true;
    }

    /**
     * Metodo encargado de Obtener las cuentas de correo que iran en copia Normal y en copiaOculta
     *
     * @param values values
     * @return InternetAddress[]
     * @throws AddressException listado de correos en copia Normal o en copia Oculta
     */
    private InternetAddress[] iterateEmail(String values) throws AddressException, JsonProcessingException {
        StringJoiner joiner = new StringJoiner(", ");

        Iterator<Map.Entry<String, JsonNode>> iter = convertStringToJson(values).fields();
        while (iter.hasNext()) {
            Map.Entry<String, JsonNode> entry = iter.next();
            joiner.add(entry.getValue().textValue());
        }

        return InternetAddress.parse(joiner.toString());
    }

    /**
     * Metodo que determina la extension que tendra el archivo, el tipo de archivo de octet-stream es generico
     * y se podria usar para manipular o crear cualquier tipo de archivo
     *
     * @param idPerfil Integer
     * @return typeFile String
     */
    private String typeFile(Integer idPerfil) {
        String typeFile = "application/octet-stream";
        if (idPerfil == 1) {
            typeFile = "application/vnd.ms-excel";
        }
        return typeFile;
    }

    /**
     * Metodo que convierte el String que retorna la base de datos y lo convierte en String
     *
     * @param value String
     * @return ObjectNose
     * @throws JsonProcessingException excepcion
     */
    private ObjectNode convertStringToJson(String value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(value);
        return (ObjectNode) actualObj;
    }

    /**
     * Metodo que valida si el valor que retorna la base de datos convertido a Json es un objeto valido o no
     * evitando excepciones a la hora de armar el objeto de correo
     *
     * @param jsonString String
     * @return boolean
     * @throws JsonProcessingException excepcion
     */
    private boolean jsonObjectValid(String jsonString) throws JsonProcessingException {
        if (Optional.ofNullable(jsonString).isPresent() && !jsonString.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode objectJson = mapper.readTree(jsonString);
            return objectJson.isObject();
        }
        return false;
    }

    /**
     * Metodo que determina el asunto que se va a enviar en el correo según el json recibido
     * de la base de Datos
     *
     * @param subject          String
     * @param emailSubjectType String
     * @return String asunto
     * @throws ExceptionHandling exception
     */
    private String emailSubjectType(String subject, String emailSubjectType) throws ExceptionHandling {
        JsonNode jsonNode1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            JsonNode actualObj = mapper.readTree(subject);
            jsonNode1 = actualObj.get(emailSubjectType);
        } catch (JsonProcessingException e) {
            throw new ExceptionHandling(400,
                    "En la base de datos en el campo ASUNTO el id del asunto recibido esta duplicado, por favor " +
                            "valide");
        }
        return jsonNode1.textValue();
    }

    /**
     * Metodo que verifica si el correo tiene o no adjunto y si tiene adjunto los mapea en el correo para enviarlos
     *
     * @param textMessage  String
     * @param attachedFile String
     * @param message      MimeMessage
     * @param idPerfil     Integer
     * @throws MessagingException exception tipo message
     * @throws IOException        exception
     * @throws ErrorFtps          exception del FTP
     */
    private void generateAttachmentObject(String textMessage, String attachedFile, MimeMessage message, Integer idPerfil)
            throws MessagingException, IOException, ErrorFtps {
        String valueFile;
        String currentDate;
        Multipart mp = new MimeMultipart();
        BodyPart textEmail = new MimeBodyPart();
        textEmail.setText("<html> <body><span style='color: black;'>" + textMessage + "<br/><br/></span><br/><br/><br/> </body></html>");
        textEmail.setContent("<html> <body><span style='color: black;'>" + textMessage + "<br/><br/></span><br/><br/><br/> </body></html>", "text/html; charset=utf-8");
        mp.addBodyPart(textEmail);
        if (jsonObjectValid(attachedFile)) {
            Iterator<Map.Entry<String, JsonNode>> iter = convertStringToJson(attachedFile).fields();
            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                currentDate = serviceFTP.obtenerFechaActual();
                String pathFtp = "/OPALO/" + currentDate + "/OUTPUT/";
                if (idPerfil > 0) {
                    valueFile = entry.getValue().textValue() + "_" + currentDate + ".xlsx";
                } else {
                    valueFile = entry.getValue().textValue() + "_" + currentDate;
                }
                InputStream in = new ByteArrayInputStream(serviceFTP.archivoResource(pathFtp + valueFile)
                        .toByteArray());
                DataSource ds = new ByteArrayDataSource(in, typeFile(idPerfil));
                DataHandler dh = new DataHandler(ds);
                messageBodyPart.setDataHandler(dh);
                messageBodyPart.setFileName(valueFile);
                mp.addBodyPart(messageBodyPart);
                message.setContent(mp);
            }
        }
    }
}
