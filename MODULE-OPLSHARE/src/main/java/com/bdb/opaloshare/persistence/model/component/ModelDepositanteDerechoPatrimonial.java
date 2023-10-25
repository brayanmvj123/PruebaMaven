/*
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */
package com.bdb.opaloshare.persistence.model.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model de datos encargado de tomar la informacion del archivo acumulado Semanal del modulo de depositantes
 * Derechos patrimoniales
 *
 * @author: Esteban Talero
 * @version: 26/11/2020
 * @since: 25/11/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelDepositanteDerechoPatrimonial {

    /**
     * Codigo Depositante
     */
    private String codDep;

    /**
     * Codigo Administrador
     */
    private String codAdmin;

    /**
     * Codigo Emisor
     */
    private String codEmisor;

    /**
     * Isin
     */
    private String isin;

    /**
     * Codigo del Deposito
     */
    private String codDeposito;

    /**
     * Tipo de Derecho
     */
    private String tipDer;

    /**
     * Fecha de Vencimiento
     */
    private String fechaVenc;

    /**
     * Cuenta Inversionista
     */
    private String ctaInv;

    /**
     * Tipo de Identificacion
     */
    private String tipId;

    /**
     * Numero de Identificacion
     */
    private String idTit;

    /**
     * Nombre del titular
     */
    private String nomTit;

    /**
     * Saldo Contable
     */
    private String salCont;

    /**
     * Cobro Dividendos en Efectivo
     */
    private String cobDivEfe;

    /**
     * Cobro Dividendos en Acciones
     */
    private String cobDivAcc;

    /**
     * Cobro Capital
     */
    private String cobCap;

    /**
     * Cobro rendimientos
     */
    private String cobRend;

    /**
     * Reinversiones
     */
    private String reinv;

    /**
     * Recaudo Capital
     */
    private String recCap;

    /**
     * Recaudo Dividendos en Acciones
     */
    private String recDivAcc;

    /**
     * Recaudo Rendimentos
     */
    private String recRend;

    /**
     * Retencion en la Fuente
     */
    private String rteFte;

    /**
     * Enajenacion
     */
    private String enajenacion;

    /**
     * Cuenta con Administracion DECEVAL
     */
    private String adminDcvl;

    /**
     * Preimpreso
     */
    private String preimpreso;

    /**
     * Fecha Inicia
     */
    private String fechaIni;

    /**
     * Fecha Fin
     */
    private String fechaFin;

    /**
     * Tasa Efectiva
     */
    private String tasEfe;

    /**
     * Factor Calculo Tasa
     */
    private String factor;

    /**
     * Pago CUD
     */
    private String pagoCud;

    /**
     * Pago PDI
     */
    private String pagoPdi;

    /**
     * Cobro Cheque
     */
    private String cobroCheque;

    /**
     * Cobro Consignacion
     */
    private String cobroConsig;

    /**
     * Gravamen
     */
    private String gravamen;

    /**
     * Banco
     */
    private String banco;

    /**
     * Numero de Cuenta
     */
    private String numCta;

    /**
     * Estado Pago PDI
     */
    private String estadoPdi;

    /**
     * Impuesto ICA
     */
    private String impIca;

    /**
     * Impuesto CREE
     */
    private String impCre;

    /**
     * Impuesto Adicional
     */
    private String impAdi;

    /**
     * Fecha de Consignacion
     */
    private String fechaConsig;

    /**
     * Estado Prorroga o Inversion
     */
    private String estadoProRe;

    /**
     * Monto Prorroga Automatica
     */
    private String montoProAuto;

    /**
     * Monto Prorroga Convenida
     */
    private String montoProCon;

    /**
     * Rendimiento Adicional
     */
    private String RendAdic;

    /**
     * Complemento Reinversion
     */
    private String complRein;

    /**
     * Total Cobro en Pesos
     */
    private String totalCoPesos;

    /**
     * Total Pago en Pesos
     */
    private String totalPaPesos;

}
