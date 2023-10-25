package com.bdb.opalotasasfija.controller.service.interfaces;

import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoDiasCDT;
import com.bdb.opalotasasfija.persistence.JSONSchema.ServiceCalculoFechaVen.JSONCalculoFechaVencimiento;

public interface CalculoDiasCDTService {

    String calculoFechaVencimiento(JSONCalculoDiasCDT request);

}
