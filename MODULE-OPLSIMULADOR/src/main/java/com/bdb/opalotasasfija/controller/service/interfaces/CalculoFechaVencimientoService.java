package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoFechaVencimiento;

import java.time.LocalDate;
import java.util.List;

public interface CalculoFechaVencimientoService {

    String calculoFechaVencimiento(JSONCalculoFechaVencimiento request);

    Integer verificarMeses31Dias(LocalDate fecha,Long adicional);

    int corregirFebrero(LocalDate fechaApertura, LocalDate fechaVencimiento);

    List<TipPeriodParDownEntity> periodicidadAdecuadasParaMeses(Long plazo);

    long convertirDiasMeses360(Long plazo);

    Long convertirDiasaMeses365(LocalDate fechaApertura , LocalDate fechaVencimiento);

    int verificarBisiesto(LocalDate fechaApertura, LocalDate fechaVencimiento);

}
