package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import com.bdb.opaloshare.persistence.model.userdata.JSONIdentificacion;
import com.bdb.opaloshare.persistence.model.userdata.JSONTelefono;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONClientDatos {

    @NotNull
    @Valid
    private JSONIdentificacion identificacion;

    @NotNull
    @Size(min = 1, max = 100, message = "Ingrese un nombre o razón social válido de máximo 100 caracteres.")
    private String nombre;

    @Size(max = 100, message = "Ingrese una dirección válida de máximo 100 caracteres.")
    private String direccion;

    @Valid
    private JSONTelefono telefono;

    @Size(max = 100 , message = "El correo electronico tiene una longitud maxima de 100 caracteres")
    private String correoElectronico;
    
    //@Pattern(regexp = "^([1-2])$", message = "Ingrese un valor válido para clase persona.")
    @Size(max = 1 , message = "Ingrese un valor válido para clase persona , maximo 1 caracter")
    private String clasePersona;
    
    //@Pattern(regexp = "^([1-2])$", message = "Ingrese un valor válido para saber si el usuario declara renta.")
    @Size(max = 1 , message = "Ingrese un valor válido para saber si el usuario declara renta , maximo 1 caracter.")
    private String declaraRenta;

    //@Pattern(regexp = "^([0-9]{1})$", message = "Ingrese un codigo indicador Extranjero válido.")
    @Size(max = 1 , message = "Ingrese un codigo indicador Extranjero válido , maximo 1 caracter.")
    private String indicadorExtranjero;

    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de fecha debe ser YYYY/MM/DD.")
    private String fechaNacimiento;

    @NotNull
    @Size(max = 4)
    private String paisNacimiento;
    
    /**
     * Enviar actividad económica.
     */
    @NotNull
    @Pattern(regexp = "^([0-9]{4})$", message = "Ingrese un código CIUU válido.")
    private String codigoCIUU;
 
    @NotNull
    private String codigoPais;
    
    @NotNull
    private String codigoDepartamento;

    @NotNull
    private String codigoCiudad;
    
    @NotNull
    private String codigoSegmentoComercial;

    @Size(max = 1)
    private String retencion;

    private String cuentaInversionista;
    
    private Integer tipoTitular;

    @NotNull
    private String codigoDane;

   }
