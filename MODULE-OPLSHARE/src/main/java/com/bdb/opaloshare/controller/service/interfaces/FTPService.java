package com.bdb.opaloshare.controller.service.interfaces;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.List;

public interface FTPService {

	/*
	 * ESTA INTERFACE PERMITE CONSTRUIR LOS METODOS QUE SERAN PROPIOS DEL SERVICEIMPLEMENTS (FTPSSERVICEIMPL) , ESTA CLASE 
	 * SIEMPRE DEBERA SER LLAMADA PARA TRABAJAR CON LOS SERVICESIMPLEMENTS , ESTO SE REALIZA PARA MANTENER UNA ORGANIZACION , 
	 * PODER REUTILIZAR CODIGO Y MANTENER INTEGRIDAD ENTRE LAS DIFERENTES CAPAS. 
	 */
	
	void connectToFTP(String host, int port, String user, String pass) throws ErrorFtps;
    void uploadFileToFTP(File file, String ftpHostDir, String serverFilename) throws ErrorFtps;
    void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws ErrorFtps;
    void disconnectFTP() throws ErrorFtps;
    ByteArrayOutputStream archivoResource(String ftpRelativePath) throws ErrorFtps;
    void makeDirectoryDay(String ruta) throws ErrorFtps;
    void makeSubDirectorys() throws ErrorFtps;
    //void updateFile(ByteArrayInputStream linea) throws ErrorFtps;
    void updateFile(String rutaCrearArchivo, ByteArrayOutputStream file , String nombreArchivo) throws ErrorFtps;
    void makeFile(ByteArrayOutputStream archivo, String ftpHostDir, String serverFilename) throws ErrorFtps;
    String listFile(String inicioArchivo) throws ErrorFtps;
    String getFileFromSpecifiedPath(String startFile, String directory) throws ErrorFtps;
    String obtenerFechaActual();
    FTPFile[] listFileParameters(String inicioArchivo) throws ErrorFtps;
    List<String> getNameParameters() throws ErrorFtps;
    ParametersConnectionFTPS parametersConnection();
    String rutaEspecifica(String descripcionCarpeta, String fechaActual);
    Integer getNameFile(String inicio,String nombre) throws ErrorFtps;
    void renameFile(String Oldname , String Newname) throws ErrorFtps, IOException;
    Boolean verificarCarpetaDiaria(String ruta) throws IOException;

}
