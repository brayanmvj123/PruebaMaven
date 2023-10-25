package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ControlEstadoService;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@CommonsLog
public class ControlEstadoImpl implements ControlEstadoService {

    private final RepositoryTipVarentorno repositoryTipVarentorno;

    private static final String ESTADO = "estado";

    public ControlEstadoImpl(RepositoryTipVarentorno repositoryTipVarentorno) {
        this.repositoryTipVarentorno = repositoryTipVarentorno;
    }

    @Override
    public JsonObject getProcesoRenovacion() {
        return new Gson().fromJson(repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION")
                .getValVariable(), JsonObject.class);
    }

    @Override
    public int obtenerPaso() {
        JsonObject procesoRenovacion = getProcesoRenovacion();
        return procesoRenovacion.get("paso").getAsInt();
    }

    @Override
    public String obtenerEstado() {
        JsonObject procesoRenovacion = getProcesoRenovacion();
        return procesoRenovacion.get(ESTADO).getAsString();
    }

    @Override
    public boolean validacionEstado() {
        JsonObject procesoRenovacion = getProcesoRenovacion();
        String estado = procesoRenovacion.get(ESTADO).getAsString();
        LocalDate fecha = LocalDate.parse(procesoRenovacion.get("fecha").getAsString());
        if (estado.equals("COMPLETADO") && fecha.isBefore(LocalDate.now())) return true;
        if (estado.equals("FALLIDO") && fecha.isEqual(LocalDate.now())) return true;
        if (estado.equals("EJECUTANDOSE")) return false;
        return !estado.equals("COMPLETADO") || !fecha.isEqual(LocalDate.now());

    }

    @Override
    public VarentornoDownEntity actualizarControlRenovacion(String valorEstado, Integer valorPaso) {
        log.info("Metodo actualizarControlRenovacion iniciado, valorEstado: " + valorEstado + " - valorPaso: " + valorPaso);
        VarentornoDownEntity varentornoDownEntity = repositoryTipVarentorno.findByDescVariable("PROCESO_RENOVACION");
        JsonObject procesoRenovacion = new Gson().fromJson(varentornoDownEntity.getValVariable(), JsonObject.class);
        procesoRenovacion.addProperty(ESTADO, valorEstado);
        procesoRenovacion.addProperty("paso", validacionValorPaso(valorPaso, procesoRenovacion, 2));
        procesoRenovacion.addProperty("fecha", LocalDate.now().toString());
        varentornoDownEntity.setValVariable(procesoRenovacion.toString());
        return actualizarProcesoRenovacion(varentornoDownEntity);
    }

    @Override
    public void actualizarHistorialResultado(String paso) {
        getProcesoRenovacion().getAsJsonObject("resultado").addProperty(paso, "OK");
    }

    private VarentornoDownEntity actualizarProcesoRenovacion(VarentornoDownEntity varentornoDownEntity) {
        log.info("Se inicia el almacenamiento del estado del proceso de renovación.");
        return repositoryTipVarentorno.save(varentornoDownEntity);
    }

    public Integer validacionValorPaso(Integer valorPaso, JsonObject procesoRenovacion, Integer cantidadPasos) {
        if (valorPaso == 0) {
            if (procesoRenovacion.get("paso").getAsInt() == 1)
                reiniciarResultadosPasos(cantidadPasos, procesoRenovacion.getAsJsonObject("resultado"));
            return procesoRenovacion.get("paso").getAsInt();
        } else return valorPaso;
    }

    public void reiniciarResultadosPasos(Integer cantidadPasos, JsonObject procesoRenovacion) {
        for (int i = 1; i <= cantidadPasos; i++) {
            procesoRenovacion.addProperty("paso".concat(String.valueOf(i)), "");
        }
    }

}
