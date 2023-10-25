package com.bdb.moduleoplcovtdsa.persistence.JSONSchema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosJSONPPE {
	
	@Size(max = 15, message = "Ingrese una identificacion válido de máximo 15 caracteres.")
	private String identificacion;
	
	@Size(max = 2, message = "Ingrese una tipo de documento válido de máximo 1 caracter.")
	private String tipoDocumento;

	@Size(max = 14, message = "Ingrese una numero de producto válida de máximo 14 caracteres.")
	private String numeroProducto;
}
