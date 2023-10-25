package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ControlEstadosImpTest {

    private RepositoryTipVarentorno repositoryTipVarentorno;
    ControlEstadoImpl controlEstadosImp;

    @Before
    public void setup() {
        repositoryTipVarentorno = Mockito.mock(RepositoryTipVarentorno.class);
        controlEstadosImp = new ControlEstadoImpl(repositoryTipVarentorno);
    }

    @Test
    public void validarJsonGenerado(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"COMPLETADO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertEquals(new Gson().fromJson(varentornoDownEntity.getValVariable(), JsonObject.class), controlEstadosImp.getProcesoRenovacion());
    }

    @Test
    public void obtenerPaso_Ok(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_controlEstadosImp",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"COMPLETADO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertEquals(1, controlEstadosImp.obtenerPaso());
    }
    @Test
    public void obtenerPaso_not_Ok(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_controlEstadosImp",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"COMPLETADO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertNotEquals(2, controlEstadosImp.obtenerPaso());
    }

    @Test
    public void validacionEstado_estadoCompletado_fechaDiaAnterior(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-14\",\"estado\": \"COMPLETADO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertTrue(controlEstadosImp.validacionEstado());
    }

    @Test
    public void validacionEstado_estadoFallido_fechaDiaHoy(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": "+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +",\"estado\": \"FALLIDO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertTrue(controlEstadosImp.validacionEstado());
    }

    @Test
    public void validacionEstado_estadoComenzado(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"EJECUTANDOSE\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertFalse(controlEstadosImp.validacionEstado());
    }

    @Test
    public void validacionEstado_estadoCompletado_fechaDiaHoy(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": "+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +",\"estado\": \"COMPLETADO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertFalse(controlEstadosImp.validacionEstado());
    }

    @Test
    public void obtenerEstado(){
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"COMPLETADO\",\"paso\": 1}");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        assertEquals("COMPLETADO", controlEstadosImp.obtenerEstado());
    }

    @Test
    public void actualizarControlRenovacionPasoCero(){
        //Obtenemos informacion del proceso de renovacion
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"COMPLETADO\", \"paso\": 1, \"resultado\":{ \"paso1\":\"OK\", \"paso2\":\"OK\" } }");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        VarentornoDownEntity resultadoVarEntoron = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"EJECUTANDOSE\", \"paso\": 1, \"resultado\":{ \"paso1\":\"\", \"paso2\":\"\" }}");
        when(repositoryTipVarentorno.save(any())).thenReturn(resultadoVarEntoron);
        assertEquals("EJECUTANDOSE", new Gson().fromJson(controlEstadosImp
                .actualizarControlRenovacion("EJECUTANDOSE", 0).getValVariable(), JsonObject.class)
                .get("estado").getAsString());
        assertEquals("", new Gson().fromJson(controlEstadosImp
                .actualizarControlRenovacion("EJECUTANDOSE", 0).getValVariable(), JsonObject.class)
                .getAsJsonObject("resultado").get("paso1").getAsString());
        assertEquals(1, new Gson().fromJson(controlEstadosImp
                .actualizarControlRenovacion("EJECUTANDOSE", 0).getValVariable(), JsonObject.class)
                .get("paso").getAsInt());

    }

    @Test
    public void actualizarControlRenovacionPasoDiferenteCero(){
        //Obtenemos informacion del proceso de renovacion
        VarentornoDownEntity varentornoDownEntity = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"EJECUTANDOSE\", \"paso\": 1, \"resultado\":{ \"paso1\":\"\", \"paso2\":\"\" } }");
        when(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")).thenReturn(varentornoDownEntity);
        VarentornoDownEntity resultadoVarEntoron = new VarentornoDownEntity(40, "PROCESO_RENOVACION",
                "{ \"fecha\": \"2023-05-15\",\"estado\": \"EJECUTANDOSE\", \"paso\": 2, \"resultado\":{ \"paso1\":\"\", \"paso2\":\"\" }}");
        when(repositoryTipVarentorno.save(any())).thenReturn(resultadoVarEntoron);
        assertEquals("EJECUTANDOSE", new Gson().fromJson(controlEstadosImp
                .actualizarControlRenovacion("EJECUTANDOSE", 2).getValVariable(), JsonObject.class)
                .get("estado").getAsString());
        assertEquals("", new Gson().fromJson(controlEstadosImp
                .actualizarControlRenovacion("EJECUTANDOSE", 2).getValVariable(), JsonObject.class)
                .getAsJsonObject("resultado").get("paso1").getAsString());
        int paso = new Gson().fromJson(controlEstadosImp
                        .actualizarControlRenovacion("EJECUTANDOSE", 2).getValVariable(), JsonObject.class)
                .get("paso").getAsInt();
        assertEquals(2, paso);

    }

}