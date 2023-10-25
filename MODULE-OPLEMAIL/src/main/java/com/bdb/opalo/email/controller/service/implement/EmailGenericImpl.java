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
import com.bdb.opalo.email.controller.service.interfaces.EmailGenericService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.model.email.RequestEmail;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.Base64.getDecoder;

/**
 * Servicio encargado de enviar correo electronico
 *
 * @author: Andres Marles
 * @version: 12/01/2022
 * @since: 12/01/2022
 */
@Service
@CommonsLog
public class EmailGenericImpl implements EmailGenericService {

    /**
     * Libreria que nos permite enviar el correo
     */
    @Autowired
    private JavaMailSenderImpl sender;

    /**
     * Interfaz que nos retorna todas las caracteristicas del correo a enviar
     * dependiendo el id de Perfil a consultar
     */
    @Autowired
    private EmailDataService emailDataService;

    /**
     * Interfaz que nos permite conectarnos al FTP para tomar los archivos
     * que iran adjuntos en el correo electronico
     */
    @Autowired
    @Qualifier("serviceFTPS")
    private FTPService serviceFTP;

    /**
     * Interfaz que nos permite conectarnos con la tabla de varEntorno de la Bd
     * para terminar si la funcionalidad para enviar correo esta habilitada o no
     */
    @Autowired
    private RepositoryTipVarentorno repoVarentorno;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private String emailHost;

    private String emailPort;

    @PostConstruct
    private void postConstruct(){
        emailHost = repoVarentorno.findByDescVariable("MAIL_HOST").getValVariable();
        emailPort = repoVarentorno.findByDescVariable("MAIL_PORT").getValVariable();
    }

    /**
     * Método encargado de consultar en la base de datos los datos del correo a enviar
     *
     * @param requestEmail el valor para consultar las caracteristicas del correo a enviar
     * @return Si el correo fue enviado exitosamente o no
     */
    @Override
    public boolean sendEmailGeneric(RequestEmail requestEmail) throws IOException, ErrorFtps, MessagingException, ExceptionHandling {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Map<String, Object> fieldHtml = new HashMap<>();
        Context context = new Context();

        //Establecemos la conexión con el sitio FTP
        log.info("ENVIO DE CORREO - Se inicia a establecer la conexion con FTP");
        ftpsSiteConnection();

        log.info("ENVIO DE CORREO - Se MAPEA objeto para envio de correo");
        //Mapeamos los datos del correo electronico
        sender.setDefaultEncoding("UTF-8");
        sender.setHost(emailHost);
        sender.setPort(Integer.parseInt(emailPort));

//        //Propiedades para enviar correos desde cualquier correo gmail
//        sender.setHost("smtp.gmail.com");
//        sender.setUsername("xxxxxx@gmail.com");
//        sender.setPassword("xxxxxxx");
//        sender.setPort(587);
//        sender.setProtocol("smtp");
//        Properties pros = new Properties();
//        pros.put("mail.smtp.starttls.enable", true);
//        sender.setJavaMailProperties(pros);

        //Mapeeamos el corro de origen
        helper.setFrom(requestEmail.getFrom());

        //Mapeamos listado de destinatarios
        helper.setTo(iterateEmail(requestEmail.getTo()));

        //Mapeamos listado de cuentas de correo en Copia Normal
        if(!(requestEmail.getCc() == null)) helper.setCc(iterateEmail(requestEmail.getCc()));

        //Mapeamos listado de cuentas de correo en Copia Oculta
        if(!(requestEmail.getCo() == null )) helper.setBcc(iterateEmail(requestEmail.getCo()));

        //Mapeamos el asunto del correo
        helper.setSubject(requestEmail.getSubject());

        //Mapeamos el mensaje del correo
        fieldHtml.put("message", requestEmail.getMessage());
        context.setVariables(fieldHtml);
        helper.addAttachment("logo", new ClassPathResource("templates/logo.jpg"));
        String html = templateEngine.process("template-generic", context);
        helper.setText(html, true);

        log.info("ENVIO DE CORREO - se verifica si el correo lleva archivos adjunto o no...");
        if (!(requestEmail.getFiles().isEmpty() || requestEmail.getFiles() == null)) {
            for (String file: requestEmail.getFiles()) {
                String currentDate = serviceFTP.obtenerFechaActual();
                String pathFtp = "/OPALO/" + currentDate + "/OUTPUT/"+ requestEmail.getFolderinOutputFTPS()+"/";
                InputStream in = new ByteArrayInputStream(serviceFTP.archivoResource(pathFtp + file).toByteArray());
//                InputStream in =  new BufferedInputStream(new FileInputStream("C:\\Users\\andre\\Documents\\"+pathFtp+"\\"+file));
                helper.addAttachment(file, new ByteArrayDataSource(in,"application/octet-stream"));
            }
        }

        log.info("ENVIO DE CORREO - enviando correo electronico...");
        sender.send(message);
        log.info("Mail enviado!");
        return true;
    }


    @Override
    public boolean sendEmailGeneric(RequestEmail requestEmail, List<MultipartFile> files) throws IOException, MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Map<String, Object> fieldHtml = new HashMap<>();
        Context context = new Context();

        log.info("ENVIO DE CORREO - Se MAPEA objeto para envio de correo");
        //Mapeamos los datos del correo electronico
        sender.setDefaultEncoding("UTF-8");
        sender.setHost(emailHost);
        sender.setPort(Integer.parseInt(emailPort));

//        //Propiedades para enviar correos desde cualquier correo gmail
//        sender.setHost("smtp.gmail.com");
//        sender.setUsername("xxxxxx@gmail.com");
//        sender.setPassword("xxxxxxx");
//        sender.setPort(587);
//        sender.setProtocol("smtp");
//        Properties pros = new Properties();
//        pros.put("mail.smtp.starttls.enable", true);
//        sender.setJavaMailProperties(pros);

        //Mapeeamos el corro de origen
        helper.setFrom(requestEmail.getFrom());

        //Mapeamos listado de destinatarios
        helper.setTo(iterateEmail(requestEmail.getTo()));

        //Mapeamos listado de cuentas de correo en Copia Normal
        if(!(requestEmail.getCc() == null)) helper.setCc(iterateEmail(requestEmail.getCc()));

        //Mapeamos listado de cuentas de correo en Copia Oculta
        if(!(requestEmail.getCo() == null )) helper.setBcc(iterateEmail(requestEmail.getCo()));

        //Mapeamos el asunto del correo
        helper.setSubject(requestEmail.getSubject());

        //Mapeamos el mensaje del correo
        fieldHtml.put("message", requestEmail.getMessage());
        context.setVariables(fieldHtml);
        String html = templateEngine.process("template-generic", context);
        helper.setText(html, true);

        log.info("ENVIO DE CORREO - se verifica si el correo lleva archivos adjunto o no...");
        if (!(files.isEmpty() || files == null)) {
            for (MultipartFile file: files) {
                helper.addAttachment(file.getOriginalFilename(), new ByteArrayDataSource(file.getInputStream(),  "application/octet-stream"));
            }
        }

        log.info("ENVIO DE CORREO - enviando correo electronico...");
        sender.send(message);
        log.info("Mail enviado!");
        return true;
    }

    /**
     * Metodo encargado de establecer la conexión con el sitio FTPs
     */
    private void ftpsSiteConnection() throws ExceptionHandling {
        try {
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
        } catch (Exception e) {
            throw new ExceptionHandling(409, "Error al conectar al Sitio FTP");
        }
    }


    /**
     * Metodo encargado de Obtener las cuentas de correo que iran en copia Normal y en copiaOculta
     *
     * @param values values
     * @return InternetAddress[]
     * @throws AddressException listado de correos en copia Normal o en copia Oculta
     */
    private InternetAddress[] iterateEmail(List<String> values) throws AddressException, JsonProcessingException {

        StringJoiner joiner = new StringJoiner(", ");
        for ( String mail: values) {
            joiner.add(mail);
        }
        return InternetAddress.parse(joiner.toString());
    }

}
