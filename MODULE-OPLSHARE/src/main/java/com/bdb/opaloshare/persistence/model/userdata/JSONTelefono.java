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
public class JSONTelefono {

    /**
     * Teléfono del cliente (deben ser solo números).
     */
    @Size(max = 30 , message = "El telefono tiene una longitud maxima de 30 caracteres")
    private String telefono;

    @Size(max = 10 , message = "La extensión tiene una longitud maxima de 10 caracteres")
    private String extension;
    
    @Size(max= 20 , message = "El número de fax tiene una longitud maxima de 20 caracteres")
    private String numero_fax;
}
