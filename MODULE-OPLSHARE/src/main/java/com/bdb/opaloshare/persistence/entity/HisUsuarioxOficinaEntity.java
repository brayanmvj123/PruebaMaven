package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPL_HIS_USUARIOXOFICINA_LARGE_TBL")
public class HisUsuarioxOficinaEntity implements Serializable {

    public HisUsuarioxOficinaEntity(Long item, Long nroOficina, String subsegmento, String zona, String cargo, String nombre,
                                    String correo, Long celular, String conmutador, Integer ext, String ciudad, Integer csc, Integer estadoUsuario) {
        this.item = item;
        this.nroOficina = nroOficina;
        this.subsegmento = subsegmento;
        this.zona = zona;
        this.cargo = cargo;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.conmutador = conmutador;
        this.ext = ext;
        this.ciudad = ciudad;
        this.csc = csc;
        this.estadoUsuario = estadoUsuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Long item;

    @Column(name = "NRO_OFICINA")
    @NotNull
    private Long nroOficina;

    @ManyToOne(targetEntity = OficinaParWithRelationsDownEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "NRO_OFICINA", insertable = false, updatable = false)
    @JsonIgnore
    private OficinaParWithRelationsDownEntity oficina;

    @Column(name = "SUBSEGMENTO")
    private String subsegmento;

    @Column(name = "ZONA")
    private String zona;

    @Column(name = "CARGO")
    @NotBlank
    private String cargo;

    @Column(name = "NOMBRE")
    @NotBlank
    private String nombre;

    @Column(name = "CORREO")
    @NotBlank
    private String correo;

    @Column(name = "CELULAR")
    private Long celular;

    @Column(name = "CONMUTADOR")
    private String conmutador;

    @Column(name = "EXT")
    private Integer ext;

    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "CSC")
    private Integer csc;

    @Column(name = "ESTADO_USUARIO")
    private Integer estadoUsuario;

    @ManyToOne(targetEntity = TipEstadosEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTADO_USUARIO", insertable = false, updatable = false)
    @JsonIgnore
    private TipEstadosEntity estado;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "HisUsuarioxOficinaEntity{" +
                "item=" + item +
                ", nroOficina=" + nroOficina +
                ", subsegmento='" + subsegmento + '\'' +
                ", zona='" + zona + '\'' +
                ", cargo='" + cargo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", celular=" + celular +
                ", conmutador='" + conmutador + '\'' +
                ", ext=" + ext +
                ", ciudad='" + ciudad + '\'' +
                ", csc=" + csc +
                ", estadoUsuario=" + estadoUsuario +
                '}';
    }
}
