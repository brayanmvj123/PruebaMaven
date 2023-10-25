package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.persistence.model.servicecancel.InfoCtaClienteModel;
import com.bdb.opalogdoracle.persistence.model.servicecancel.PaymentTransaction;
import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCDTSCancelation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbonoImplTest {

    private RepositoryHisCDTSCancelation repositoryHisCDTSCancelation;

    private final String RESPONSE = "\"{\"response\":[\"resultado\"]}\"";
    private final String RESULT = "\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"";
    private AbonoImpl abonoImpl;

    @Before
    public void init(){
        repositoryHisCDTSCancelation = mock(RepositoryHisCDTSCancelation.class);
        abonoImpl = new AbonoImpl(repositoryHisCDTSCancelation);
    }

    @Test
    public void almacenarNuevoPagoFallido(){
        HisCDTSCancelationEntity cancelationEntity = new HisCDTSCancelationEntity("COB01", 80000700000001L,
                "1090", "{\"response\":[{\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"K\"}]}",
                LocalDateTime.now());

        PaymentTransaction paymentTransaction = new PaymentTransaction(3, new BigDecimal(100000));
        List<PaymentTransaction> transaccionPagoList = new ArrayList<>();
        transaccionPagoList.add(paymentTransaction);

        InfoCtaClienteModel infoCtaClienteModel = new InfoCtaClienteModel("CC", "1090", "JUAN", 80000700000001L, "COB01",
                103, 103456L, 1, 103456L, 1, 103, transaccionPagoList);

        when(repositoryHisCDTSCancelation.findById(any())).thenReturn(Optional.empty());
        when(repositoryHisCDTSCancelation.save(any())).thenReturn(cancelationEntity);

        String response = "{\"response\":[{\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"K\"}]}";
        assertEquals(response, abonoImpl.almacenarPago(infoCtaClienteModel));
    }

    @Test
    public void almacenarExistentePagoFallido(){
        HisCDTSCancelationEntity cancelationEntity = new HisCDTSCancelationEntity("COB01", 80000700000001L,
                "1090", "{\"response\":[{\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"K\"}]}",
                LocalDateTime.now());

        PaymentTransaction paymentTransaction = new PaymentTransaction(4, new BigDecimal(10000));
        List<PaymentTransaction> transaccionPagoList = new ArrayList<>();
        transaccionPagoList.add(paymentTransaction);

        InfoCtaClienteModel infoCtaClienteModel = new InfoCtaClienteModel("CC", "1090", "JUAN", 80000700000001L, "COB01",
                103, 103456L, 1, 103456L, 1, 103, transaccionPagoList);

        when(repositoryHisCDTSCancelation.findById(any())).thenReturn(Optional.of(cancelationEntity));
        when(repositoryHisCDTSCancelation.save(any())).thenReturn(cancelationEntity);

        String response = "{\"response\":[{\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"K\"}," +
                "{\"result\":\"100\",\"notification\":\"TP en_US 100 General Error\",\"tipPayment\":\"I\"}]}";
        assertEquals(response, abonoImpl.almacenarPago(infoCtaClienteModel));
    }

}