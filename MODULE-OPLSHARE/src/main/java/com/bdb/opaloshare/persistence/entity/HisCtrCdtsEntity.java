package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPL_HIS_CTRCDTS_LARGE_TBL")
@IdClass(HisCtrCdtsPk.class)
public class HisCtrCdtsEntity implements Serializable {

    @Id
    @Column(name = "NUM_CDT")
    private Long numCdt;

    @Id
    @Column(name = "NUM_TIT")
    private String numTit;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "COD_CUT")
    private String codCut;

    @Column(name = "NOVEDAD_V")
    private Integer novedadV;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDateTime fechaModificacion;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Id
    @ManyToOne
    @JoinColumn(name = "OPL_CONTROLES_TBL_TIP_CONTROL")
    private ParControlesEntity parControlesEntity;

    @JsonIgnore
    @OneToMany(targetEntity = HisCondicionCdtsEntity.class,
            mappedBy = "hisCtrCdtsEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<HisCondicionCdtsEntity> hisCondicionCdtsEntity;

    @JsonIgnore
    @OneToMany(targetEntity = HisCtaCliEntity.class,
            mappedBy = "hisCtrCdtsEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<HisCtaCliEntity> hisCtaCliEntities;

    public HisCtrCdtsEntity(Long numCdt, String numTit, ParControlesEntity parControlesEntity) {
        this.numCdt = numCdt;
        this.numTit = numTit;
        this.parControlesEntity = parControlesEntity;
    }
}
