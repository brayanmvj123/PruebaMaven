package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_PAR_CORREO_DOWN_TBL")
public class OplParCorreoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_PERFIL")
    private Integer idPerfil;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CUENTA_ORIGEN")
    private String cuentaOrigen;

    @Column(name = "CUENTA_DESTINO")
    private String cuentaDestino;

    @Column(name = "CUENTA_COPIA")
    private String cuentaCopia;

    @Column(name = "CUENTA_OCULTA")
    private String cuentaOculta;

    @Column(name = "ASUNTO")
    private String asunto;

    @Column(name = "CONTENIDO")
    private String contenido;

    @Column(name = "ADJUNTOS")
    private String adjuntos;

}
