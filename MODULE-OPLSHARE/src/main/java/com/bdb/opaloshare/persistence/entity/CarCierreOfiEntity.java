package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_CAR_CIERREOFI_DOWN_TBL")
public class CarCierreOfiEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;
    @Column(name = "TIP_CTA")
    private String tipCta;
    @Column(name = "NUM_CTA")
    private String numCta;
    @Column(name = "TIP_ID")
    private String tipId;
    @Column(name = "NUM_ID")
    private String numId;
    @Column(name = "CEO")
    private String ceo;
    @Column(name = "OFI_ORI")
    private String ofiOri;
    @Column(name = "OFI_DES")
    private String ofiDes;
    @Column(name = "SEGMENTO")
    private String segmento;
    @Column(name = "FECHA_CIE")
    private String fechaCie;
    @Column(name = "FILLER")
    private String filler;

    public CarCierreOfiEntity(String ofiOri, String ofiDes){
        this.ofiOri = ofiOri;
        this.ofiDes = ofiDes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarCierreOfiEntity that = (CarCierreOfiEntity) o;
        return Objects.equals(ofiOri, that.ofiOri) &&
                Objects.equals(ofiDes, that.ofiDes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ofiOri, ofiDes);
    }
}
