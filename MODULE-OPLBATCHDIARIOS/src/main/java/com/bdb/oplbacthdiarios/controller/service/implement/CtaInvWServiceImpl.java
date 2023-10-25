package com.bdb.oplbacthdiarios.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.HisInfoClienteEntity;
import com.bdb.opaloshare.persistence.entity.CtaInvCarEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.BasicAccountInformationModel;
import com.bdb.opaloshare.persistence.repository.RepositoryInfoClienteHis;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvCar;
import com.bdb.opaloshare.persistence.repository.RepositoryCtaInvSecCar;
import com.bdb.opaloshare.persistence.repository.RepositoryTipCiudPar;
import com.bdb.oplbacthdiarios.controller.service.interfaces.CtaInvWService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service("serviceCuentas")
@CommonsLog
public class CtaInvWServiceImpl implements CtaInvWService {

    /*
     * ESTA CLASE ES LA ENCARGADA DE ALMACENAR LOS DIFERENTES ALGORITMOS PARA DAR FUNCIONAMIENTO A LOS DISTINTOS METODOS
     * DECLARADOS EN EL SERVICIO , LA CLASE SERVICEIMPLEMENTS SERA LA UNICA DONDE SE DIGITE CODIGO , ESTO CON EL FIN DE RESPETAR
     * LAS CAPAS DE LA APLICACIÓN.
     * CUANDO SE CREE UN SERVICEIMPLEMENTS SE DEBERA IMPLEMENTAR LA INTERFACE SERVICE CREADA , ADEMAS AÑADIR EL REPOSITORY PROPIO
     * DE LA ENTIDAD.
     *
     * ESTA CLASE CONTIENE LOS SIGUIENTES METODOS
     *
     * ***************************************************************************************************************************
     * actualizarTipoDocumento()
     * ESTE METODO CAMBIA EL TIPO DE DOCUMENTO "PAP","CC","CE","TI","NIP" POR EL ID UTILIZADO PARA EL RESPECTIVO TIPO EN LA TABLA
     * DE CARGA DE CUENTAS.
     * ***************************************************************************************************************************
     *
     * ***************************************************************************************************************************
     * actualizarTipoDocumentoNIT()
     * ESTE METODO CAMBIE EL TIPO DE DOCUMENTO ""NIT"  POR EL ID UTILIZADO PARA EL RESPECTIVO TIPO EN LA TABLA CARGA DE CUENTAS.
     * ***************************************************************************************************************************
     *
     * ***************************************************************************************************************************
     * guardarCuentas()
     * ESTE METODO HA SIDO CREADO PARA PODER GUARDAR LA INFORMACION OBTENIDA EN EL CONTROLLER Y PODER LLEVARLA A LA CLASE
     * (BATCHCONFIGURE...) ALMACENADA EN EL PACKAGE PROCESOSBATCH. EN RESUMEN ESTE METODO FUNCIONA COMO UN METODO SET().
     * ***************************************************************************************************************************
     *
     * ***************************************************************************************************************************
     * cargarCuentas()
     * ESTE METODO ES EL ENCARGADO DE CARGAR LA INFORMACION OBTENIDA EN EL METODO GUARDARCIIU. PRACTICAMENTE ACTUA COMO EL METODO
     * GET().
     * ***************************************************************************************************************************
     *
     * ***************************************************************************************************************************
     * ExaminarArchivo()
     * ESTE METODO PERMITE DIVIDIR EL ARCHIVO DE CUENTAS , ESTE ARCHIVO CUENTA CON DOS TIPOS DE ESTRUCTURAS:
     * 		- LA PRIMERA ESTRUCTURA : CUENTA CON UN TOTAL DE 241 CARACTERES Y SIEMPRE INICIA CON TIPO1.
     * 		- LA SEGUNDA ESTRUCTURA : CUENTA CON UN TOTAL DE 147 CARACTERES Y SIEMPRE INICIA CON TIPO2.
     * SI EL ARCHIVO NO SE DIVIDE SPRING BATCH GENERA UN PROBLEMA, PUES LAS LONGITUDES DE LAS DIFERENTES LINEAS SON DISTINTAS.
     * EL ARCHIVO AL FINAL DEL CONTENIDO CONTIENE UNA LINEA DE INFORMACION DEL ARCHIVO , EL METODO ELIMINA ESTA LINEA.
     * POR ULTIMO EL METODO AGREGA EL NUMERO DE CUENTA A LA ESTRUCTURA TIPO 2
     * ***************************************************************************************************************************
     *
     */

    @Autowired
    RepositoryCtaInvCar repoCuentas;

    @Autowired
    RepositoryInfoClienteHis repoAccionista;

    @Autowired
    RepositoryTipCiudPar repoCiudad;

    @Autowired
    RepositoryCtaInvSecCar repoCtaSec;

    @Autowired
    RepositoryCtaInvSecCar repoCtaInvSec;

    List<BasicAccountInformationModel> basicAccountInfList = new ArrayList<>();

    ByteArrayOutputStream[] archivosCuentas = new ByteArrayOutputStream[2];

    Boolean resultadoHomologaciones;

    @Override
    public void actualizarTipoDocumento() {
        log.info("Inicia actualizarTipoDocumento ");
        String[] tipo = {"PAP", "CC", "CE", "TI", "NIP"};
        for (String string : tipo) {
            log.info("bucle s ");
            //repoCuentas.actualizarPAP(string);
            //repoCtaInvSec.homologaTipIdCtaSec(string);
        }
        log.info("Termina actualizarTipoDocumento ");
    }

    @Override
    public void actualizarTipoDocumentoNIT() {
        String tipo = "NIT";
        for (int i = 1; i < 3; i++) {
            //repoCuentas.actualizarNIT(tipo, i);
            //repoCtaInvSec.homologaTipIdCtaSecNIT(tipo, i);
        }
    }

    @Override
    public void guardarCuentas(ByteArrayOutputStream[] archivo) {
        this.archivosCuentas = archivo;
    }

    @Override
    public ByteArrayOutputStream[] cargarCuentas() {
        return archivosCuentas;
    }

    @Override
    public ByteArrayOutputStream[] ExaminarArchivo(ByteArrayOutputStream resource) {

        log.info("ENTRO AL METODO EXAMINAR ARCHIVO, EL CUAL SEPARA LOS TIPOS DE CUENTAS");

        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setLineMapper(new PassThroughLineMapper());
        //reader.setResource(new ByteArrayResource(resource.toByteArray()));
        reader.setResource(new ClassPathResource("CUENTAS.txt"));
        reader.open(new ExecutionContext());

        ByteArrayOutputStream[] resultado = new ByteArrayOutputStream[3];

        ByteArrayOutputStream byteArrayOutputStreamTipo2 = new ByteArrayOutputStream();
        DataOutputStream outTipo2 = new DataOutputStream(byteArrayOutputStreamTipo2);

        ByteArrayOutputStream byteArrayOutputStreamTipo1 = new ByteArrayOutputStream();
        DataOutputStream outTipo1 = new DataOutputStream(byteArrayOutputStreamTipo1);

        ByteArrayOutputStream registroErrados = new ByteArrayOutputStream();
        DataOutputStream longitudErrada = new DataOutputStream(registroErrados);
        try {
            String line;
            String nuevaLinea = "\n";
            String numCta = "";
            String union;
            while ((line = reader.read()) != null) {

                if (line.length() == 147) {
                    union = line + numCta;
                    outTipo2.write(union.getBytes());
                    outTipo2.write(nuevaLinea.getBytes());
                    byteArrayOutputStreamTipo2.flush();
                    byteArrayOutputStreamTipo2.close();
                } else if (line.length() == 241) {
                    numCta = line.substring(2, 10);
                    outTipo1.write(line.getBytes());
                    outTipo1.write(nuevaLinea.getBytes());
                    byteArrayOutputStreamTipo1.flush();
                    byteArrayOutputStreamTipo1.close();
                } else {
                    longitudErrada.write(line.concat(nuevaLinea).getBytes());
                    registroErrados.flush();
                    registroErrados.close();
                }
            }
        } catch (Exception e) {
            log.fatal(e);
        } finally {
            reader.close();
        }

        resultado[0] = byteArrayOutputStreamTipo2;
        resultado[1] = byteArrayOutputStreamTipo1;
        resultado[2] = registroErrados;

        return resultado;
    }

    @Override
    public void homologarNulos() {
        repoCuentas.HomologarNulosCiud();
    }

    public void corregirCodCree() {
//        List<String> codigosCreeLista = repoCuentas.codigosCreeErroneos();
//        for (String codigoCree : codigosCreeLista) {
//            if (esNumero(codigoCree)) {
//                repoCuentas.ModificarCodigoCreeErroneos(Integer.parseInt(codigoCree));
//            }
//        }
    }

    public void corregirCodSector() {
//        List<String> codigosSectorLista = repoCuentas.codigosSectorErroneos();
//        for (String codigoSector : codigosSectorLista) {
//            if (esNumero(codigoSector)) {
//                repoCuentas.modificarCodSectorErroneos(Integer.parseInt(codigoSector));
//            }
//        }
    }

    @Override
    public void corregirCodSectorNulos() {
        repoCuentas.modificarCodSectorNulos();
        repoCtaInvSec.modificarCodSectorNulosSec();
    }

    @Override
    public void corregirCodSectorSec() {
//        List<String> codigosSectorSec = repoCtaInvSec.codigosSectorSecErroneos();
//        codigosSectorSec.stream().filter(this::esNumero).forEach(codDesSect -> repoCtaInvSec.modificarCodSectorErroneosSec(Integer.parseInt(codDesSect)));
    }

    @Override
    public void homologarSector() {
        List<String> codigosSector = repoCuentas.codigosSector();
//        codigosSector.forEach(codSector -> {
//            if (esNumero(codSector)) repoCuentas.homologarCodSector(Integer.parseInt(codSector));
//        });
    }

    @Override
    public void homologarSectorSec() {
        List<String> codigosSectorSec = repoCtaSec.codigosSectorSec();
//        codigosSectorSec.forEach(codSector -> {
//            if (esNumero(codSector)) repoCtaSec.homologarCodSectorSec(Integer.parseInt(codSector));
//        });
    }

    public boolean esNumero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void conocerProblema(String errorEspecifico, List<? extends HisInfoClienteEntity> items) {
        String[] error = errorEspecifico.split(";");
        String[] tablaError = error[2].split("PAR_");
        switch (tablaError[1].replace("]", "")) {
            case "TIPCIUD_DOWN_TBL":
                log.fatal("TIPO DE CIUDAD");
                errorTipCiud(items);
                break;
            case "TIPCIUU_DOWN_TBL":
                log.fatal("TIPO DE CIUU");
                errorTipCiud(items);
                break;
            case "TIPID_DOWN_TBL":
                log.fatal("TIPO DE IDENTIFICACION");
                errorTipCiud(items);
                break;
            case "TIPSECT_DOWN_TBL":
                log.fatal("TIPO DE SECTOR");
                errorTipCiud(items);
                break;
            default:
                log.fatal("NO HAY COINCIDENCIAS");
                break;
        }
    }

    @Override
    public void guardarResultadoHomologacion(boolean resultado) {
        this.resultadoHomologaciones = resultado;
    }

    @Override
    public void homologarPaises() {
//        repoCuentas.conocerPaisesDesconocidos();
//        List<CtaInvCarEntity> paises = repoCuentas.codigosPais();
//        paises.forEach(pais -> repoCuentas.HomologarPaises(pais.getCodPais()));
    }

    @Override
    public void homologarDepartamento() {
        List<CtaInvCarEntity> departamentos = repoCuentas.codigosDepartamento();
//        departamentos.forEach(item ->
//                repoCuentas.HomologarDepartamentos(Long.parseLong(item.getCodPais()), Integer.parseInt(item.getCodDep())));
    }

    @Override
    public void homologarCiudades() {
        repoCuentas.corregirPais();
        repoCuentas.corregirDepartamento();
        repoCuentas.corregirCiudades();
        List<CtaInvCarEntity> ciudades = repoCuentas.codigosCiudad();
//        ciudades.forEach(item ->
//                repoCuentas.HomologarCiudades(item.getCodCiud(), Long.parseLong(item.getCodPais()), Long.parseLong(item.getCodDep()),
//                        item.getCodDep(), item.getCodPais(), item.getCodCiud())
//        );
    }

    @Autowired
    public void homologarCREENulos() {
        //repoCuentas.modificarCodCreeNulos();
    }


    @Override
    public void homologarCREE() {
        List<CtaInvCarEntity> codCree = repoCuentas.codigosCree();
        //codCree.forEach(item -> repoCuentas.HomologarCodigosCREE(item.getCodCree()));
    }

    @Override
    public void verificarCodigosDep() {
//        repoCuentas.codigosDepErroneos();
    }

    public void errorTipCiud(List<? extends HisInfoClienteEntity> items) {
        items.forEach(item -> {
            /*if (!repoCiudad.existsByIdAndAccTipdepTblIdAndAccTippaisTblId(item.getAccTipciudTblId().longValue(),
                    item.getAccTipdepTblId().longValue(), Long.parseLong(item.getAccTippaisTblId()))) {
                System.out.println(item.getIdAcc());
            }*/
        });
    }

    @Override
    public void homologarIndExt() {
        repoCuentas.HomologarNulosIndExt();
    }

    public ByteArrayOutputStream accionistasCodsDesc() {

        log.info("ENTRO AL METODO PARA CREAR ARCHIVO ACCIONISTAS CON CODIGOS DESCONOCIDOS");

        List<CtaInvCarEntity> lista = repoCuentas.findByCodPaisOrCodDepOrCodCiudOrCodSectOrCodCree("9999", "9999", 9999, 9999L, 9999);

        ByteArrayOutputStream resultado = new ByteArrayOutputStream();

        ByteArrayOutputStream byteArrayOutputStreamTipo1 = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStreamTipo1);

        try {

            long count = 0;
            String nuevaLinea = "\n";
            String num_cta = "";
            String union;
            for (CtaInvCarEntity line : lista) {

                try {
                    out.write(line.toString().getBytes());
                    out.write(nuevaLinea.getBytes());
                    byteArrayOutputStreamTipo1.flush();
                    byteArrayOutputStreamTipo1.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    log.error("Error escribiendo ARCHIVO ACCIONISTAS CON CODIGOS DESCONOCIDOS "+e);
                }

                count++;
            }
        } catch (Exception e) {
            log.error("Error creando ARCHIVO ACCIONISTAS CON CODIGOS DESCONOCIDOS "+e);
        }

        resultado = byteArrayOutputStreamTipo1;

        return resultado;
    }

   // @Override
    public void saveBasicAccountInf(List<BasicAccountInformationModel> basicAccInf){
        log.info("setValues basic "+basicAccInf.isEmpty());
        this.basicAccountInfList = basicAccInf;
    }

    //@Override
    public List<BasicAccountInformationModel> findAllBasicAccountInf(){
        log.info("getValues getNomCta "+basicAccountInfList);
        return basicAccountInfList;
    }

}
