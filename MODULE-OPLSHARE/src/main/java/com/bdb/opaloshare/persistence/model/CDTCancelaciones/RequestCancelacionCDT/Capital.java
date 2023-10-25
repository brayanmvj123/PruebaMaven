package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Capital implements Serializable
{
    @NotNull
    @Range(min = 2, max = 4)
    @ApiModelProperty(value = "Descripción: Tipo de proceso, puede tener los siguientes valores: 1=Apertura, 2=Renovación, 3=Pago capital, 4=Pago de intereses, 5=Cobro de retención", name = "tipProceso", dataType = "Integer", required = true,
            example = "2", notes = "Tipo de proceso 1=Apertura, 2=Renovación, 3=Pago capital, 4=Pago de intereses, 5=Cobro de retención")
    private Integer tipProceso;

    @NotNull
    @Range(min = 1, max = 7)
    @ApiModelProperty(value = "Descripción: Tipo de transacción o proceso, puede tener los siguientes valores: 1=Cuenta Corriente, " +
            "2=Cuenta Ahorros, 3=Cheque, 4=Efectivo, 5=Cebra, 6=Cuenta contable, 7=Apertura por renovación, 8=Cuenta contable retención", name = "tipPago", dataType = "Integer", required = true,
            example = "7", notes = "Nombre del canal de ejecución qque realiza la petición")
    private Integer tipPago;

    @NotNull
    @Min(value = 1L)
    @ApiModelProperty(value = "Descripción: Valor del cobro a capital, debe ser el valor exacto", name = "valor", dataType = "BigDecimal", required = true,
            example = "800000", notes = "Valor del cobro a capital, debe ser el valor exacto")
    private BigDecimal valor;


    private InfoCta infoCta;

    private static final long serialVersionUID = 1L;
}
