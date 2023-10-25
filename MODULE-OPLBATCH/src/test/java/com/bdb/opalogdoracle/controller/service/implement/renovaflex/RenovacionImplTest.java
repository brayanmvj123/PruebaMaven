package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.EnvioCdtsDcvBtaService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.*;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RenovacionImplTest {

    private ObtenerRenovacionService obtenerRenovacionService;
    private RenovacionCondFlexService renovacionCondFlexService;
    private ControlEstadoService controlEstadoService;
    private EnvioCdtsDcvBtaService envioCdtsDcvBtaService;
    private TraductorRenovacionService traductorRenovacionService;
    private NotificacionDcvBtaService notificacionDcvBtaService;

    RenovacionImpl renovacion;

    @Before
    public void setup() {
        renovacion = new RenovacionImpl(obtenerRenovacionService,
                renovacionCondFlexService,
                controlEstadoService,
                envioCdtsDcvBtaService,
                traductorRenovacionService,
                notificacionDcvBtaService);
    }


}