package com.bdb.opalogdoracle.persistence.jsonschema.servicesimuintereses;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONTasaEfectivaNominal {

	@NotNull
    @Valid
	private String canal;
	
	@NotNull
    @Valid
	private ParametrosJSONTasaEfectivaNominal parametros;

}
