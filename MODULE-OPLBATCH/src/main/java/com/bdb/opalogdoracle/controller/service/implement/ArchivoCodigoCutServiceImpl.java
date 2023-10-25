package com.bdb.opalogdoracle.controller.service.implement;

import com.bdb.opalogdoracle.controller.service.interfaces.ArchivoCodigoCutService;
import com.bdb.opaloshare.controller.service.errors.ErrorFtps;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationEntity;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.SalCutEntity;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSCancelacion;
import com.bdb.opaloshare.persistence.repository.RepositoryCDTSLarge;
import com.bdb.opaloshare.persistence.repository.RepositorySalCut;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivoCodigoCutServiceImpl implements ArchivoCodigoCutService {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    private RepositoryCDTSLarge repoCdts;

    @Autowired
    private RepositoryCDTSCancelacion repositoryCDTSCancelacion;

    @Autowired
    private RepositorySalCut repoSalCut;

    @Autowired
    private RepositoryTipVarentorno repoVarentorno;

    public String generarSecuenciaTransaccion() {
        VarentornoDownEntity var = repoVarentorno.findByDescVariable("COD_CUD");
        String maxSecuenTrans = var.getValVariable();
        List<Character> cadena = new ArrayList<>();
        String[] vector = new String[5];
        for (int i = 0; i < maxSecuenTrans.length(); i++) {
            System.out.println(maxSecuenTrans.charAt(i));
            cadena.add(maxSecuenTrans.charAt(i));
        }

        System.out.println("CANTIDAD DE CARACTERES: " + cadena.size());

        if (Integer.parseInt(cadena.get(3).toString()) == 9) {
            System.out.println("ENTRO");
            String posicionCuatro = adicionarLetra(cadena.get(4));
            vector[4] = posicionCuatro;
        }else{
            vector[4] = cadena.get(4).toString();
        }

        int posicionTres = adicionarUnidad(cadena.get(3).toString());
        vector[3] = String.valueOf(posicionTres);

        if (Integer.parseInt(cadena.get(3).toString()) == 9 && cadena.get(4).toString().equals("Z")) {
            String posicionDos = adicionarLetra(cadena.get(2));
            vector[2] = posicionDos;
        }else{
            vector[2] = cadena.get(2).toString();
        }

        if ( cadena.get(2).toString().equals("Z") && Integer.parseInt(cadena.get(3).toString()) == 9
                && cadena.get(4).toString().equals("Z") ){
            int posicionUno = adicionarUnidad(cadena.get(1).toString());
            vector[1] = String.valueOf(posicionUno);
        }else{
            vector[1] = cadena.get(1).toString();
        }

        if ( Integer.parseInt(cadena.get(1).toString()) == 9 && cadena.get(2).toString().equals("Z")
                && Integer.parseInt(cadena.get(3).toString()) == 9 && cadena.get(4).toString().equals("Z") ){
            String posicionCero = adicionarLetra(cadena.get(0));
            vector[0] = posicionCero;
        }else {
            vector[0] = cadena.get(0).toString();
        }

        System.out.println("SECUENCIA DE TRANSACCION MAXIMA: \n"+maxSecuenTrans);
        System.out.println("VECTOR: "+String.join("",vector));
        //repoVarentorno.actualizarNumCdtDesma(String.join("",vector),"COD_CUD");
        var.setValVariable(String.join("",vector));
        repoVarentorno.saveAndFlush(var);
        return String.join("",vector);
    }

    public String generarCutDestino(String codCutOrigen,LocalDateTime fecha) {
        return dividirCutOrigen(codCutOrigen)+obtenerCodigoAplicacion()+generarFecha(fecha)+generarSecuenciaTransaccion();
    }

    public String generarCutOrigenCancelacion(LocalDateTime fecha) {
        String codigoTransaccion = repoVarentorno.findByDescVariable("CODIGO_TRANSACCION").getValVariable();
        String codigoCanal = repoVarentorno.findByDescVariable("CODIGO_CANAL").getValVariable();
        String filler = "0000";
        return codigoTransaccion + codigoCanal + filler + obtenerCodigoAplicacion() + generarFecha(fecha) + generarSecuenciaTransaccion();
    }

    @Override
    public void generarInformacionCut() {
        LocalDateTime fechaInicioDiaAnterior = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIDNIGHT);
        LocalDateTime fechaFinDiaAnterior = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);

        System.out.println("FECHA ANTERIOR INICIO: "+fechaInicioDiaAnterior + " FECHA ANTERIOR FINAL: "+fechaFinDiaAnterior);
        List<SalCutEntity> saveCodCut = new ArrayList<>();

        List<HisCDTSLargeEntity> cdts = repoCdts.findByFechaBetween(fechaInicioDiaAnterior,fechaFinDiaAnterior);
        System.out.println("CANTIDAD: "+cdts.size());
        cdts.forEach(data -> {
            SalCutEntity salCut = new SalCutEntity();
            salCut.setCodOrigen(data.getCodCut());
            salCut.setCodDestino(generarCutDestino(data.getCodCut(), data.getFecha()));
            saveCodCut.add(salCut);
        });

        List<HisCDTSCancelationEntity> cdtsCancelation = repositoryCDTSCancelacion.findByFechaBetween(fechaInicioDiaAnterior,fechaFinDiaAnterior);
        System.out.println("CANTIDAD DE CDTS CANCELADOS: "+cdtsCancelation.size());

        cdtsCancelation.forEach(data -> {
            String cutOrigenCancelacion = generarCutOrigenCancelacion(data.getFecha());
            SalCutEntity salCut = new SalCutEntity();
            salCut.setCodOrigen(cutOrigenCancelacion);
            salCut.setCodDestino(generarCutDestino(cutOrigenCancelacion, data.getFecha()));
            saveCodCut.add(salCut);
        });
        repoSalCut.saveAll(saveCodCut);
    }

    public Integer adicionarUnidad(String caracter){
        int sumaPosicion = Integer.parseInt(caracter) + 1;
        int posicionActual = sumaPosicion > 9 ? 0 : sumaPosicion;
        System.out.println("POSICION NUMERICA ACTUAL: " + posicionActual);
        return posicionActual;
    }

    public String adicionarLetra(Character cadena){
        int codigoAsciiPos = (int) cadena;
        System.out.println(codigoAsciiPos);
        int sumaPosActual = codigoAsciiPos == 90 ? 65 : codigoAsciiPos + 1;
        System.out.println("POSICION LETRA ACTUAL: " + (char) sumaPosActual);
        return String.valueOf( (char) sumaPosActual );
    }

    public String generarFecha(LocalDateTime fechaLinea){
        String fecha = fechaLinea.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println("FECHA LINEA: "+fecha);
        return fecha;
    }

    public String dividirCutOrigen(String codCutOrigen){
        return codCutOrigen.substring(0,14);
    }

    public String obtenerCodigoAplicacion(){
        return repoVarentorno.findByDescVariable("CODIGO_OPALO").getValVariable();
    }

    @Override
    public void verificaGeneracionrArchivo() throws ErrorFtps, IOException {
        String nombreArchivo = "CUT.OPL";
        Integer cantidad = serviceFTP.getNameFile("%OUTPUT%",nombreArchivo);
        if (cantidad == 0){
            System.out.println("LA CANTIDAD ES: "+cantidad);
            File file = new File(nombreArchivo);
            ByteArrayOutputStream input = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(input);
            String nuevaLinea = "\n";
            String primeraLinea = "CUTORIGEN|CUTDESTINO";
            out.write(primeraLinea.getBytes());
            out.write(nuevaLinea.getBytes());
            String fechaArchivo = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String finalLinea = "0"+"|"+fechaArchivo+"|"+"CUT.OPL";
            out.write(finalLinea.getBytes());
            input.flush();
            input.close();
            serviceFTP.updateFile("%OUTPUT%", input , nombreArchivo);
        }
    }

    @Override
    public void limpiarTabla() {
        repoSalCut.deleteAll();
    }
}
