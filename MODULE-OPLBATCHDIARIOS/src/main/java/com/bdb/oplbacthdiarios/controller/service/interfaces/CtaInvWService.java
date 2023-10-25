package com.bdb.oplbacthdiarios.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.HisInfoClienteEntity;
import com.bdb.opaloshare.persistence.model.CDTCancelaciones.BasicAccountInformationModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface CtaInvWService {

    /*
     * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (CUENTASSERVICEIMPL) , ESTA CLASE
     * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION ,
     * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS.
     */

    void actualizarTipoDocumento();

    void actualizarTipoDocumentoNIT();

    //HOMOLOGAR CODIGOS CREE
    void homologarCREENulos();

    void homologarCREE();

    void corregirCodCree();

    //HOMOLOGAR CODIGOS SECTOR
    void corregirCodSector();

    void corregirCodSectorNulos();

    void corregirCodSectorSec();

    void homologarSector();

    void homologarSectorSec();

    void guardarCuentas(ByteArrayOutputStream[] archivo);

    ByteArrayOutputStream[] cargarCuentas();

    ByteArrayOutputStream[] ExaminarArchivo(ByteArrayOutputStream resource);

    ByteArrayOutputStream accionistasCodsDesc();

    //HOMOLOGACIONES PAIES , DEPARTAMENTO , CIUDADES
    void homologarNulos();

    void homologarPaises();

    void homologarDepartamento();

    void homologarCiudades();

    void homologarIndExt();

    void verificarCodigosDep();

    boolean esNumero(String numero);

    //METODOS PARA CONOCER EL ERROR EXACTO DEL WRITER
    void conocerProblema(String errorEspecifico, List<? extends HisInfoClienteEntity> items);

    void guardarResultadoHomologacion(boolean resultado);

    void saveBasicAccountInf(List<BasicAccountInformationModel> basicAccInf);

    List<BasicAccountInformationModel> findAllBasicAccountInf();

}
