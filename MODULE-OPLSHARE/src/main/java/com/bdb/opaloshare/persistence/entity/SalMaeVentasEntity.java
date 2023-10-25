package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_SAL_MAEVENTAS_DOWN_TBL")
public class SalMaeVentasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "FECHA")
    private LocalDate fecha;

    @Column(name = "FECHA_EMI")
    private LocalDate fechaEmi;

    @Column(name = "COD_PROD")
    private String codProd;

    @Column(name = "NUM_CDT")
    private Long numCdt;

    @Column(name = "TIP_TRAN")
    private String proceso;

    @Column(name = "TIP_CANAL")
    private String tipCanal;

    @Column(name = "CANAL")
    private String canal;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "NRO_OFICINA")
    private Integer nroOficina;

    @Column(name = "COD_ID")
    private String codId;

    @Column(name = "NUM_TIT")
    private String numTit;

    public SalMaeVentasEntity(LocalDate fechaEmi, String codProd, String numCdt, String proceso, String tipCanal,
                              String canal, String usuario, Integer nroOficina, String codId, String numTit) {
        this.fecha = LocalDate.now();
        this.fechaEmi = fechaEmi;
        this.codProd = codProd;
        this.numCdt = Long.parseLong(numCdt);
        this.proceso = proceso;
        this.tipCanal = tipCanal;
        this.canal = canal.substring(5, 10);
        this.usuario = usuario;
        this.nroOficina = nroOficina;
        this.codId = codId;
        this.numTit = numTit;
    }

    public String reportTransFileCdtsDigBi() {
        return fecha + "|" + fechaEmi + "|" + codProd + "|" + numCdt + "|" + proceso + "|" + tipCanal + "|" + canal + "|" + usuario
                + "|" + nroOficina + "|" + codId + "|" + numTit;
    }

}
