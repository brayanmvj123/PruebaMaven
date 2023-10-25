package com.bdb.opaloshare.persistence.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONIdentificacion {

    @NotNull
    @Size( max=2 , message = "El tipo de documento debe tener una extensión de 2 carácter.")
    private String tipoId;
//regexp = "^([\\w]{4})$",
    @NotNull
    @Size(min = 1, max = 15, message = "Ingrese un número de identificación válido.")
    private String numId;
}
