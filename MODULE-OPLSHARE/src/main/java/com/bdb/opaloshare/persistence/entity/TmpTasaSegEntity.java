package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPL_TMP_TASASEG_DOWN_TBL")
public class TmpTasaSegEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long identificador;

    @Column(name = "NOM_PROD")
    private String nomProd;

    private Integer segmento;

    @Column(name = "DES_SEGMENTO")
    private String desSegmento;

    private BigDecimal puntos;


}
