package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opaloshare.persistence.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class ValidarMontoMinimoImplTest {

    ValidarMontoMinimoImpl validarMontoMinimo;

    @Before
    public void init() {
        validarMontoMinimo = new ValidarMontoMinimoImpl();
    }

    @Test
    public void capitalMayorMontoMinimo() {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L,
                "CC", "1018425059", "JUAN", new BigDecimal("100000"), new BigDecimal("10000"),
                new BigDecimal("1000"), new BigDecimal("9000"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        assertTrue(validarMontoMinimo.montoMinimo(salRenautdigEntity, hisCtrCdtsEntity, new BigDecimal(100000)));

    }

    @Test
    public void capitalMasInteresesMontoMinimo() {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L,
                "CC", "1018425059", "JUAN", new BigDecimal("100000"), new BigDecimal("1000"),
                new BigDecimal("10"), new BigDecimal("10000"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("-10000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        assertTrue(validarMontoMinimo.montoMinimo(salRenautdigEntity, hisCtrCdtsEntity, new BigDecimal(100000)));

    }
    @Test
    public void capitalNoMayorMontoMinimo() {
        SalRenautdigEntity salRenautdigEntity = new SalRenautdigEntity(1L, "COB", 80000777000191L,
                "CC", "1018425059", "JUAN", new BigDecimal("100000"), new BigDecimal("1000"),
                new BigDecimal("10"), new BigDecimal("10000"), "1", "P");

        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("-100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(),
                new ParControlesEntity(1L)));
        HashSet<HisCondicionCdtsEntity> condicionCdtsEntities = new HashSet<>();
        condicionCdtsEntities.add(condicionCdts);

        HisCtaCliEntity hisCtaCliEntity = new HisCtaCliEntity("222", "dda", 444,
                new HisCtrCdtsEntity(salRenautdigEntity.getNumCdt(), salRenautdigEntity.getNumTit(), new ParControlesEntity(1L)));
        HashSet<HisCtaCliEntity> hisCtaCliEntities = new HashSet<>();
        hisCtaCliEntities.add(hisCtaCliEntity);

        HisCtrCdtsEntity hisCtrCdtsEntity = new HisCtrCdtsEntity(80000777000191L, "1018425059", LocalDateTime.now(), "123",
                1, LocalDateTime.now(), "RENOVACION", new ParControlesEntity(1L),
                condicionCdtsEntities, hisCtaCliEntities);

        assertFalse(validarMontoMinimo.montoMinimo(salRenautdigEntity, hisCtrCdtsEntity, new BigDecimal(100000)));

    }

}