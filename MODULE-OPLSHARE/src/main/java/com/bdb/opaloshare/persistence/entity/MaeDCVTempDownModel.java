package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MaeDCVTempDownModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long item;
    private Date fechaReg;
    private String codIsin;
    private String numCDT;
    private String numFol;
    private String ctaInv;
    private String idTit;
    private String nomTit;
    private String correo;
    private String fechaEmi;
    private String fechaVen;
    private String fechaProPago;
    private BigDecimal spread;
    private BigDecimal tasEfe;
    private BigDecimal tasNom;
    private BigDecimal vlrCDT;
    private String oficina;
    private String tipId;
    private String tipPlazo;
    private String base;
    private String periocidad;
    private String tipTasa;
    private String posicion;


    private String descBase;

    private String descPerodicidad;

    private String plazo;
    private String desPlazo;
    private String desTasa;

    private String estado;

    private String fraccionado;

    private String nomId;
    private String descPosicion;


	public MaeDCVTempDownModel(Long item, String numCDT, BigDecimal vlrCDT, String fechaVen, String fechaProxPg,
							   String oplTiptasaParDownTblTipTasa, String descTasa, String fechaEmi, BigDecimal tasEfe) {
		super();
		this.item = item;
		this.numCDT = numCDT;
		this.vlrCDT = vlrCDT;
		this.fechaVen = LocalDate.parse(fechaVen, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
		this.fechaProPago = LocalDate.parse(fechaProxPg, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
		this.tipTasa = oplTiptasaParDownTblTipTasa;
		this.desTasa = descTasa;
		this.fechaEmi = LocalDate.parse(fechaEmi, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
		this.tasEfe = tasEfe;
	}

    public MaeDCVTempDownModel(Long item, String codIsin, String numCDT, String idTit, String oplTipidParDownTblCodId, String nomId, String nomTit, String ctaInv,
                               String desPlazo, String oplTipplazoParDownTblTipPlazo, String plazo, String fechaEmi, String fechaVen, BigDecimal vlrCDT, String oplTipbaseParDownTblTipBase, String base, String oplTipperiodParDownTblTipPeriodicidad, String periocidad,
                               BigDecimal spread, BigDecimal tasNom, BigDecimal tasEfe, String oplOficinaParDownTblNroOficina, String oplTipposicionParDownTblTipPosicion, String posicion, String estado,
                               String oplTiptasaParDownTblTipTasa, String desTasa, String fechaProPago, String correo, String fraccionado) {
        super();
        this.item = item;
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
