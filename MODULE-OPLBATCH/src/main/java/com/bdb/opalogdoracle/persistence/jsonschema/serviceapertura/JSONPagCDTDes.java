package com.bdb.opalogdoracle.persistence.jsonschema.serviceapertura;

import lombok.*;

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
public class JSONPagCDTDes {

    @NotNull
    @Size(max = 15, message = "Ingrese un número de identificación válido.")
    private String idCliente;

    @NotNull
    @Pattern(regexp = "^([1-4])$", message = "Ingrese un valor válido para el proceso. Codigos aceptados del 1 al 4 ... " +
            "Ver contrato de servicio para más información")
    private String proceso;

    @NotNull
    @Pattern(regexp = "^([1-2])$", message = "Ingrese un valor válido para el tipo de transacción.")
    private String tipTran;

    @NotNull
    //@Pattern(regexp = "^([0-9])$", message = "Ingrese un número de producto válido.")
    private String nroPordDestino;

    @NotNull
    @Digits(integer = 16, fraction = 4 , message = "Ingrese un valor valido.")
    private BigDecimal valor;

    @NotNull
    private Long idBeneficiario;

    @NotNull
    //@Pattern(regexp = "^([0-9]{5})$"
    @Size(max = 5 , message = "Ingrese una unidad de origen válida.")
    private String unidadOrigen;

    @NotNull
    //@Pattern(regexp = "^([0-9]{5})$"
    @Size(max = 5 , message = "Ingrese una unidad de negocio válida.")
    private String unidadDestino;

    @NotNull
    //@Size(max = 1, message = "Ingrese un número de tipo de transacción válido. Codgiso aceptado del 1 al 7 ... " +
    //        "Ver contrato de servicio para mas información")
    private Integer tipoTransaccion;

}
