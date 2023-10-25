package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JSONCondCDT {

    @NotNull
    private String fecha;

    /**
     * Enviar el usuario NT quien radicó la creación del CDT.
     */
    @NotNull
    //@Pattern(regexp = "^([\\w]{10})$"
    @Size( max = 10 , message = "Ingrese un valor válido para el usuario NT.")
    private String usuarioNt;

    @NotNull
    private String canal;

    @NotNull
    @Size(min = 39 , message = "El codigo CUT debe tener una longitud de 39 caracteres")
    private String codCut;

    @NotNull
    @Pattern(regexp = "[0-9]\\d*", message = "Ingrese un valor NÚMERICO válido para el codigo de transacción.")
    private String codTransaccion;

    @NotNull
    @Size(min = 14 , message = "El número de CDT debe ser de 14 posiciones")
    private String numCdtDigital;

    @NotNull
    private String codProd;

    @NotNull
    @Pattern(regexp = "^([0-9])$", message = "Ingrese un valor válido para el mercado.")
    private String mercado;

    private String unidadNegocio;

    private String unidCEO;

    @NotNull
    @Pattern(regexp = "^([1-2])$", message = "Ingrese un valor válido para la forma de pago.")
    private String formaPago;

    // @Pattern(regexp = "^([\\w]{3})$", message = "")
    @NotNull
    private String depositante;

    /**
     * Ejemplo de plazo:
     * plazo: { tipo: "D", cantidad: 90 }
     */
    @Valid
    private JSONPlazoCDT plazo;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de fecha debe ser YYYY/MM/DD.")
    private String fechaEmision;

    @NotNull
    @Pattern(regexp = "^([0-9]{4}/[0-9]{2}/[0-9]{2})$", message = "El formato de fecha debe ser YYYY/MM/DD.")
    private String fechaVencimiento;

    @NotNull
    @Pattern(regexp = "^([1-3])$", message = "Ingrese un valor válido para la base.")
    private String base;

    @NotNull
    @Pattern(regexp = "^([1-2])$", message = "Ingrese un valor válido para la modalidad.")
    private String modalidad;

    @NotNull
    private Integer tipoTasa;

    @NotNull
    private Integer tipoPeriodicidad;

    /**
     * Si la tipo tasa es fijo enviar cero (0)
     */
    @NotNull
    @Digits(integer = 7, fraction = 4)
    private BigDecimal spread;

    @NotNull
    @Pattern(regexp = "[+|-]" , message = "El caracter aceptado es un + o -")
    private String signoSpread;

    @NotNull
    @Digits(integer = 7, fraction = 4)
    private BigDecimal tasaEfectiva;

    @NotNull
    @Digits(integer = 7, fraction = 4)
    private BigDecimal tasaNominal;

    // @Pattern(regexp = "^([1-2])$", message = "Ingrese un valor válido para la moneda.")
    @NotNull
    private BigDecimal moneda;

    @NotNull
    @Pattern(regexp = "[1|2]" , message = "Los caracteres aceptados son 1 o 2")
    private String unidadUVR;

    @NotNull
    private BigDecimal cantidadUnidad;

    /**
     * Se debe enviar solo si el tipo de moneda es UVR, de lo contrario enviar cero (0).
     */
    @NotNull
    @Digits(integer = 16, fraction = 4)
    private BigDecimal valor;

    @NotNull
    @Pattern(regexp = "^([1-3])$", message = "Ingrese un valor válido para la titularidad.")
    private String tipoTitularidad;

}
