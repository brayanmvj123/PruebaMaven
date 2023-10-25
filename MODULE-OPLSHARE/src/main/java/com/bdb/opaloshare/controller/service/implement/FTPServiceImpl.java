package com.bdb.opaloshare.controller.service.implement;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.errors.ErrorMessage;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ConexionesEntity;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.repository.RepositoryConexiones;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("serviceFTPS")
public class FTPServiceImpl implements FTPService {
	
	/*
	 * ESTA CLASE ES LA ENCARGADA DE ALMACENAR LOS DIFERENTES ALGORITMOS PARA DAR VIDA O FUNCIONAMIENTO A LOS DISTINTOS METODOS 
	 * DECLARADOS EN EL SERVICIO , LA CLASE SERVICEIMPLEMENTS SERA LA UNICA DONDE SE DIGITE CODIGO , ESTO CON EL FIN DE RESPETAR
	 * LAS CAPAS DE LA APLICACIÓN.
	 * CUANDO SE CREE UN SERVICEIMPLEMENTS SE DEBERA IMPLEMENTAR LA INTERFACE SERVICE CREADA , ADEMAS AÑADIR EL REPOSITORY PROPIO
	 * DE LA ENTIDAD.
	 * 
	 * ESTE SERVICE IMPLEMENTS CONTIENE TODA LA LOGICA PARA LA CONEXION AL SITIO FTPS , ADEMAS INCLUYE:
	 * *CONSULTA DE LA FECHA ACTUAL
	 * *FILTRAR POR EL ARCHIVO A TRABAJAR
	 * *DESCONEXION
	 * *SUBIR ARCHIVOS AL SITIO FTPS
	 * *DESCARGAR ALGUN ARCHIVO
	 * *LISTAR DE LOS ARCHIVOS ENCONTRADO EN LA RUTA INDICADA
	 */
	
	FTPSClient ftpconnection;
	//FTPClient ftpconnection;
	
	@Autowired
	private RepositoryConexiones repoConexiones;
	
	private Logger logger = LoggerFactory.getLogger(FTPService.class);
    
    //@Value("${texto.controller.service.control.username}")
    //@Value("gcelest")
	String username;
    
    //@Value("${texto.controller.service.control.password}")
    //@Value("Noviembre2019")
	String password;
    
    //@Value("${texto.controller.service.control.hostname}")
    //@Value("10.86.82.73")
	String hostname;
    
    //@Value("${texto.controller.service.control.puerto}")
    //@Value("1500")
	Integer puerto;

    /**
     * Method that implement FTP connection.
     * @param host IP of FTP server
     * @param user FTP valid user
     * @param pass FTP valid pass for user
     * @throws ErrorFtps Set of possible errors associated with connection process.
     */
    
    public void connectToFTP(String host, int port, String user, String pass) throws ErrorFtps {

        //ftpconnection = new FTPClient();
        ftpconnection = new FTPSClient();
        ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;

        try {
            ftpconnection.connect(host, port);
        } catch (IOException e) {
            System.out.println(e);
            ErrorMessage errorMessage = new ErrorMessage(-1, "No fue posible conectarse al FTP a través del host=" + host);
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }

        reply = ftpconnection.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {

            try {
            	logger.error("DESCONECTADO");
                ftpconnection.disconnect();
            } catch (IOException e) {
                ErrorMessage errorMessage = new ErrorMessage(-2, "No fue posible conectarse al FTP, el host=" + host + " entregó la respuesta=" + reply);
                logger.error(errorMessage.toString());
                throw new ErrorFtps(errorMessage);
            }
        }

        try {
        	logger.error("ENTROOOOOOOOOOOOOOOO");
            ftpconnection.login(user, pass);
            ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
            ftpconnection.execPBSZ(0);  // Set protection buffer size
            ftpconnection.execPROT("P"); // Set data channel protection to private
            ftpconnection.enterLocalPassiveMode();
            //System.out.print(ftpconnection.listFiles());
            //ftpconnection.changeWorkingDirectory("");
            //System.out.print("LA DIRECCION ES:" + ftpconnection.printWorkingDirectory());
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-3, "El usuario=" + user + ", y el pass=**** no fueron válidos para la autenticación.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }

        try {
            ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-4, "El tipo de dato para la transferencia no es válido.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }

        ftpconnection.enterLocalPassiveMode();
    }

    /**
     * Method that allow upload file to FTP
     * @param file File object of file to upload
     * @param ftpHostDir FTP host internal directory to save file
     * @param serverFilename Name to put the file in FTP server.
     * @throws ErrorFtps Set of possible errors associated with upload process.
     */
    
    public void uploadFileToFTP(File file, String ftpHostDir , String serverFilename) throws ErrorFtps {

        try {
            InputStream input = new FileInputStream(file);
            this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }

    }

    /**
     * Method for download files from FTP.
     * @param ftpRelativePath Relative path of file to download into FTP server.
     * @param copytoPath Path to copy the file in download process.
     * @throws ErrorFtps Set of errors associated with download process.
     */

    
    public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws ErrorFtps {

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(copytoPath);
        } catch (FileNotFoundException e) {
            ErrorMessage errorMessage = new ErrorMessage(-6, "No se pudo obtener la referencia a la carpeta relativa donde guardar, verifique la ruta y los permisos.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }

        try {
            this.ftpconnection.retrieveFile(ftpRelativePath, fos);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo descargar el archivo.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }
    }
    
    /* 
     * ESTE METODO PERMITE OBTENER LOS DATOS DE ALGUN ARCHIVO DEL SITIO FTPS Y CARGARLOS EN UN RESOURCE PARA SER
     * UTILIZADO POR SPRING BATCH
     */
    public ByteArrayOutputStream archivoResource(String ftpRelativePath) throws ErrorFtps {
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	      
        try {
            this.ftpconnection.retrieveFile(ftpRelativePath, outputStream);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo descargar el archivo.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }
        
        return outputStream;
    }
    
    public void makeDirectoryDay(String ruta) throws ErrorFtps {
    	String fechaActual = obtenerFechaActual();
    	try {
    		ruta = reemplazarFechaRuta(ruta,fechaActual);
            this.ftpconnection.makeDirectory(ruta);
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo crear la carpeta.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
		}
    }

    public Boolean verificarCarpetaDiaria(String ruta) throws IOException {
        return this.ftpconnection.listDirectories(reemplazarFechaRuta(ruta,obtenerFechaActual())).length == 0;
    }
    
    /*
     * OBTIENE LA FECHA ACTUAL DEL SISTEMA , LA FECHA ACTUAL DEL SISTEMA SERA EL NOMBRE DE LA CARPETA DIARIA QUE
     * SE CREARA EN EL SERVIDOR FTPS, CON LA SIGUIENTE ESTRUCTURA DD (DIA) MM (MES) YYYY (AÑO) (17072019). 
     */
    public String obtenerFechaActual() {
    	Date fechaActual = new Date();
    	String formato = "yyyyMMdd";
    	SimpleDateFormat simpleFormato = new SimpleDateFormat(formato);
    	String obtenerFecha = simpleFormato.format(fechaActual);
    	System.out.println("LA FECHA ES: " + obtenerFecha);
    	return obtenerFecha;
    }
    
    /*
     *  ESTE METODO CREAR LAS SUBCARPETAS INDISPENSABLES PARA EL FUNCIONAMIENTO DE LA APLICACION, LA ESTRUCTURA SE DEFINE
     *  DE LA SIGUIENTE MANERA:
     *  INPUT: ESTA CARPETA SERA DEDICADA PARA QUE EL USUARIO DE FORMA MANUAL ALOJA LOS ARCHIVOS DE CARGA (CUENTAS,SALDOS,ETC...).
     *  OUPUT: ESTA CARPETA SERA DEDICADA ALOJAR LOS ARCHIVOS DE SALIDA DE LA APLICACION (REPORTES.ETC...). 
     *  CONFIG: ESTA CARPETA SERA DEDICADA ALOJAR LOS DIFERENTES ARCHIVOS PROCESADOS NECESARIOS PARA ORGANIZAR,ESTRUCTURAR LA
     *  		INFORMACIÓN. 
     */
    public void makeSubDirectorys() throws ErrorFtps {
    	String fechaActual = obtenerFechaActual();
    	try {
    		List<ConexionesEntity> rutas = repoConexiones.listaRutas();
    		rutas.stream().forEach(ruta -> {
    			String reemplazo = reemplazarFechaRuta(ruta.getRuta(),fechaActual);
    			try {
					this.ftpconnection.makeDirectory(reemplazo);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		});
			/*this.ftpconnection.makeDirectory("/PRUEBA_ACCIONES/"+fechaActual+"/INPUT");
			this.ftpconnection.makeDirectory("/PRUEBA_ACCIONES/"+fechaActual+"/OUTPUT");
			this.ftpconnection.makeDirectory("/PRUEBA_ACCIONES/"+fechaActual+"/CONFIGURATION");*/
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo crear las subcarpetas para el dia: " + fechaActual);
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
		}	
    }
    
    public void makeFile(ByteArrayOutputStream archivo, String ftpHostDir, String serverFilename) throws ErrorFtps {
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(archivo.toByteArray());
            //InputStream input = new FileInputStream(file);
            this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }
    }

    public void makeFilePrueba(String ftpHostDir, InputStream input) throws ErrorFtps {
        try {

            //InputStream input = new FileInputStream(file);
            this.ftpconnection.storeFile(ftpHostDir, input);
        } catch (IOException e) {
            ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }
    }
    
    /*public void updateFile(ByteArrayInputStream linea) throws ErrorFtps {
    	String fechaActual = obtenerFechaActual();
    	try {
    		this.ftpconnection.appendFile("/PRUEBA_ACCIONES/"+fechaActual+"/CONFIGURATION/prueba.txt", linea);
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo actualzar el archivo del servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
		}
    }*/

    public void updateFile(String rutaCrearArchivo,ByteArrayOutputStream file,String nombreArchivo) throws ErrorFtps {
        String fechaActual = obtenerFechaActual();
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(file.toByteArray());
            String pathname = rutaEspecifica(rutaCrearArchivo,fechaActual);
            this.ftpconnection.storeFile(pathname+nombreArchivo, input);
        } catch (Exception e) {
            // TODO: handle exception
            ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo actualzar el archivo del servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }
    }
    
    /*
     * ESTE METODO PERMITE LISTAR LOS ARCHIVOS.
     */
    public String listFile(String inicioArchivo) throws ErrorFtps {
    	String fechaActual = obtenerFechaActual();
    	String leerArchivo = null;
    	try {
    		String pathname = rutaEspecifica("%INPUT%",fechaActual);
            System.out.println("INICIO DE ARCHIVO : "+inicioArchivo);
    		FTPFileFilter filter = file -> file.getName().toUpperCase().startsWith(inicioArchivo);
			FTPFile[] archivos = this.ftpconnection.listFiles(pathname, filter);
			System.out.println("cantidad de archivos coincidentes: " + archivos.length);
			leerArchivo = getFileMax(archivos);
			
		} catch (Exception e) {
    	    ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo listar los archivos del servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
		}
    	return leerArchivo;
    }

    /**
     * ESTE METODO PERMITE LISTAR LOS ARCHIVOS.
     */
    @Override
    public String getFileFromSpecifiedPath(String startFile, String directory) throws ErrorFtps {
        try {
            String pathname = rutaEspecifica("%"+directory+"%",obtenerFechaActual());
            logger.info("INICIO DE ARCHIVO : {}", startFile);
            FTPFileFilter filter = file -> file.getName().toUpperCase().startsWith(startFile);
            FTPFile[] archivos = this.ftpconnection.listFiles(pathname, filter);
            logger.info("CANTIDAD DE ARCHIVOS ENCONTRADOS: {}", archivos.length);
            return getFileMax(archivos);
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo listar los archivos del servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
        }
    }
    
   
    public String getFileMax(FTPFile[] archivos) {
    	int cantidadVector = archivos.length;
    	return archivos[cantidadVector-1].getName();
    	/*for(FTPFile archivo : archivos) {
    		System.out.println(archivo.getName());
    	}*/
    }
    
    public FTPFile[] listFileParameters(String inicioArchivo) throws ErrorFtps {
    	String fechaActual = obtenerFechaActual();
    	FTPFile[] leerArchivos = null;
    	try {
    		String pathname = rutaEspecifica("%OCASIONAL%",fechaActual);
    		FTPFileFilter filter = file -> file.getName().toUpperCase().startsWith(inicioArchivo);
			leerArchivos = this.ftpconnection.listFiles(pathname, filter);
			
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo listar los archivos del servidor.");
            logger.error(errorMessage.toString());
            throw new ErrorFtps(errorMessage);
		}
    	return leerArchivos;
    }
    
    public List<String> getNameParameters() throws ErrorFtps{
    	List<String> nombres = new ArrayList<>();
    	FTPFile[] archivos = listFileParameters("OPL_");
		for(int i = 0 ; i < archivos.length ; i++) {
			String[] cadena = archivos[i].getName().split("\\.");
			nombres.add(cadena[0]);
		}
		Collections.reverse(nombres);
		for (int i = 0; i < nombres.size(); i++) {
			System.out.println("PRIMER ARCHIVO A LEER: " + nombres.get(i));
		}

		return nombres;	
    }

    public Integer getNameFile(String archivosSalida,String nombreArchivo) throws ErrorFtps{
        FTPFile[] leerArchivos = null;
        try {
            System.out.println("entrooooooooooooo a buscar TC.OPL");
            System.out.println(rutaEspecifica(archivosSalida,obtenerFechaActual())+nombreArchivo);
            leerArchivos = this.ftpconnection.listFiles(rutaEspecifica(archivosSalida,obtenerFechaActual())+nombreArchivo);
            System.out.println("CANTIDAD DE ARCHIVOS: "+leerArchivos.length);
        }catch (Exception e){
            System.out.println(e.getCause());
        }
        return leerArchivos.length;
    }

    @Override
    public void renameFile(String Oldname, String Newname) throws ErrorFtps, IOException {
        ftpconnection.rename(Oldname, Newname);
    }

    public ParametersConnectionFTPS parametersConnection(){
    	ConexionesEntity parametros = repoConexiones.parametrosConexion(1);
    	logger.info("parametros: {}", parametros.toString());
    	ParametersConnectionFTPS parameters = new ParametersConnectionFTPS();
    	parameters.setUsername(parametros.getUser());
    	logger.info("user: {}", parameters.getUsername());
        logger.info("CONTRASEÑA: {}", new String(Base64.getDecoder().decode(parametros.getPass().getBytes())));
    	parameters.setPassword(new String(Base64.getDecoder().decode(parametros.getPass().getBytes())));
    	parameters.setHostname(parametros.getHostIp());
    	parameters.setPuerto(parametros.getPuerto());
    	parameters.setRuta(parametros.getRuta());
    	return parameters;
    }

    @Override
    public String rutaEspecifica(String descripcionCarpeta,String fechaActual) {
        ConexionesEntity ruta = repoConexiones.rutaEspecifica(descripcionCarpeta);
        return reemplazarFechaRuta(ruta.getRuta(),fechaActual);
    }

    /**
     * Method for release the FTP connection.
     * @throws ErrorFtps Error if unplugged process failed.
     */
    
    public void disconnectFTP() throws ErrorFtps {
        if (this.ftpconnection.isConnected()) {
            try {
                this.ftpconnection.logout();
                this.ftpconnection.disconnect();
            } catch (IOException f) {
               throw new ErrorFtps( new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexión del servidor FTP"));
            }
        }
    }
    
    private String reemplazarFechaRuta(String ruta,String fecha) {
    	return ruta.replace("fechaActual", fecha);
    }

}
