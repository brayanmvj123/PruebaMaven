package com.bdb.opaloshare.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_PAR_OFICINA_DOWN_TBL")
public class OficinaParWithRelationsDownEntity implements Serializable{

	public OficinaParWithRelationsDownEntity(Integer nroOficina, String descOficina, Integer oplTipoficinaTblTipOficina,
											 Integer oplOficinaTblnroOficina, Integer oplEstadosTblTipEstado) {
		this.nroOficina = nroOficina;
		this.descOficina = descOficina;
		this.oplTipoficinaTblTipOficina = oplTipoficinaTblTipOficina;
		this.oplOficinaTblnroOficina = oplOficinaTblnroOficina;
		this.oplEstadosTblTipEstado = oplEstadosTblTipEstado;
	}


	@Id
	@Column(name="NRO_OFICINA")
	@Schema(description = "Descripción: Número de oficina.", name = "nroOficina", type = "Integer", required = true,
			example = "4")
	private Integer nroOficina;

	@Column(name="DESC_OFICINA")
	@Schema(description = "Descripción: Descripción de oficina.", name = "descOficina", type = "String", required = true,
			example = "CALLE 53 EXITO")
	private String descOficina;

	@Column(name="OPL_TIPOFICINA_TBL_TIP_OFICINA")
	@Schema(description = "Descripción: Tipo de oficina: 1=OFICINA, 2=PYME, 3=CEO, 4=PREMIUM, 5=CENTRO DE SERVICIOS, " +
			"6=CENTRO DE COSTOS", name = "oplTipoficinaTblTipOficina", type = "Integer", required = true,
			example = "1")
	private Integer oplTipoficinaTblTipOficina;

	@ManyToOne(targetEntity = TipOficinaParDownEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "OPL_TIPOFICINA_TBL_TIP_OFICINA", insertable = false, updatable = false)
	@JsonIgnore
	private TipOficinaParDownEntity tipoficina;

	@Column(name="OPL_OFICINA_TBL_NRO_OFICINA")
	@Schema(description = "Descripción: Número de oficina.", name = "oplOficinaTblnroOficina", type = "Integer", required = true,
			example = "0")
	private Integer oplOficinaTblnroOficina;

	@Column(name = "OPL_ESTADOS_TBL_TIP_ESTADO")
	@Schema(description = "Descripción: Tipo de estado: Estados 1= procesado, 2=enviado, 3=finalizado, 4=oficina activa," +
			" 5= oficina cerrada", name = "oplEstadosTblTipEstado", type = "Integer", required = true,
			example = "4")
	private Integer oplEstadosTblTipEstado;

	@ManyToOne(targetEntity = TipEstadosEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "OPL_ESTADOS_TBL_TIP_ESTADO", insertable = false, updatable = false)
	@JsonIgnore
	private TipEstadosEntity tipoEstado;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "NRO_OFICINA")
	@JsonIgnore
	private List<HisUsuarioxOficinaEntity> usuarios;

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "OficinaParWithRelationsDownEntity{" +
				"nroOficina=" + nroOficina +
				", descOficina='" + descOficina + '\'' +
				", oplTipoficinaTblTipOficina=" + oplTipoficinaTblTipOficina +
				", oplOficinaTblnroOficina=" + oplOficinaTblnroOficina +
				", oplEstadosTblTipEstado=" + oplEstadosTblTipEstado +
				'}';
	}
}
