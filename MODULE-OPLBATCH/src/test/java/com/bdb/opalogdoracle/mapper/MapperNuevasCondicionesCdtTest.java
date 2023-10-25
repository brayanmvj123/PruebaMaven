package com.bdb.opalogdoracle.mapper;

import com.bdb.opaloshare.persistence.entity.HisCondicionCdtsEntity;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import com.bdb.opaloshare.persistence.entity.ParControlesEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class MapperNuevasCondicionesCdtTest {

    private MapperNuevasCondicionesCdtImpl mapperNuevasCondicionesCdtImpl;

    @Before
    public void setup(){
        mapperNuevasCondicionesCdtImpl = new MapperNuevasCondicionesCdtImpl();
    }

    @Test
    public void validation_base_360(){
        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 360, new HisCtrCdtsEntity(80000700000001L, "PRUEBA UNITARIA",
                new ParControlesEntity(1L)));
        assertEquals(1L, mapperNuevasCondicionesCdtImpl.nuevasCondiciones(new BigDecimal("100000"),
                new BigDecimal("10000"), condicionCdts).getBase().longValue());
    }

    @Test
    public void validation_base_365(){
        HisCondicionCdtsEntity condicionCdts = new HisCondicionCdtsEntity(new BigDecimal("100000"), 1,
                3, 1, 365, new HisCtrCdtsEntity(80000700000001L, "PRUEBA UNITARIA",
                new ParControlesEntity(1L)));
        assertEquals(2L, mapperNuevasCondicionesCdtImpl.nuevasCondiciones(new BigDecimal("100000"),
                new BigDecimal("10000"), condicionCdts).getBase().longValue());
    }



}