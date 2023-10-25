package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HisCDTSLargeModel {

    private Date fechaReg;
    private String codIsin;
    private String numCDT;
    private String numFol;
    private String ctaInv;
    private String idTit;
    private String nomTit;
    private String correo;
    private LocalDate fechaEmi;
    private LocalDate fechaVen;
    private LocalDate fechaProPago;
    private BigDecimal spread;
    private BigDecimal tasEfe;
    private BigDecimal tasNom;
    private BigDecimal vlrCDT;
    private Integer oficina;
    private Integer tipId;
    private Integer tipPlazo;
    private Integer base;
    private Integer periocidad;
    private Integer tipTasa;
    private Integer posicion;


    private String descBase;

    private String descPerodicidad;

    private Integer plazo;
    private String desPlazo;
    private String desTasa;

    private String estado;

    private String fraccionado;

    private String nomId;
    private String descPosicion;
    private Long item;

    public HisCDTSLargeModel(String numCDT, BigDecimal vlrCDT, LocalDate fechaVen, LocalDate fechaProPago, Integer oplTiptasaParDownTblTipTasa,
                             String desTasa, LocalDate fechaEmi, BigDecimal tasEfe) {
        this.numCDT = numCDT;
        this.vlrCDT = vlrCDT;
        this.fechaVen = fechaVen;
        this.fechaProPago = fechaProPago;
        this.tipTasa = oplTiptasaParDownTblTipTasa;
        this.desTasa = desTasa;
        this.fechaEmi = fechaEmi;
		this.tasEfe = tasEfe;
    }

    public HisCDTSLargeModel(String codIsin, String numCDT, String idTit, Integer oplTipidParDownTblCodId, String nomId, String nomTit,
                             String ctaInv, String desPlazo, Integer oplTipplazoParDownTblTipPlazo, Integer plazo, LocalDate fechaEmi,
                             LocalDate fechaVen, BigDecimal vlrCDT, Integer oplTipbaseParDownTblTipBase, String base, Integer oplTipperiodParDownTblTipPeriodicidad,
                             String periocidad, BigDecimal spread, BigDecimal tasNom, BigDecimal tasEfe, Integer oplOficinaParDownTblNroOficina,
                             Integer oplTipposicionParDownTblTipPosicion, String posicion, String estado, Integer oplTiptasaParDownTblTipTasa,
                             String desTasa, LocalDate fechaProPago, String correo, String fraccionado) {
        super();
        this.codIsin = codIsin;
        this.numCDT = numCDT;
        this.idTit = idTit;
        this.ctaInv = ctaInv;
        this.tipId = oplTipidParDownTblCodId;
        this.nomId = nomId;
        this.nomTit = nomTit;
        this.fechaEmi = fechaEmi;
        this.fechaVen = fechaVen;
        this.tipPlazo = oplTipplazoParDownTblTipPlazo;
        this.plazo = plazo;
        this.desPlazo = desPlazo;
        this.descBase = base;
        this.base = oplTipbaseParDownTblTipBase;
        this.descPerodicidad = periocidad;
        this.periocidad = oplTipperiodParDownTblTipPeriodicidad;
        this.spread = spread;
        this.tasNom = tasNom;
        this.tasEfe = tasEfe;
        this.posicion = oplTipposicionParDownTblTipPosicion;
        this.descPosicion = posicion;
        this.oficina = oplOficinaParDownTblNroOficina;
        this.vlrCDT = vlrCDT;
        this.estado = estado;
        this.tipTasa = oplTiptasaParDownTblTipTasa;
        this.desTasa = desTasa;
        this.correo = correo;
        this.fechaProPago = fechaProPago;
        this.fraccionado = fraccionado;
    }

}
