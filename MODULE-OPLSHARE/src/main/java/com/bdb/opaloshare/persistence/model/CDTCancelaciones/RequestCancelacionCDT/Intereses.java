package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intereses implements Serializable
{
    @NotNull
    @ApiModelProperty(value = "Descripción: Tipo de proceso, puede tener los siguientes valores: 1=Apertura, 2=Renovación, 3=Pago capital, 4=Pago de intereses, 5=Cobro de retención",
            name = "tipProceso", dataType = "Integer", required = true, example = "4", notes = "Tipo de proceso 1=Apertura, 2=Renovación, 3=Pago capital, 4=Pago de intereses, 5=Cobro de retención")
    private Integer tipProceso;

    @NotNull
    @ApiModelProperty(value = "Descripción: Tipo de transacción o proceso, puede tener los siguientes valores: 1=Cuenta Corriente, " +
            "2=Cuenta Ahorros, 3=Cheque, 4=Efectivo, 5=Cebra, 6=Cuenta contable, 7=Apertura por renovación, 8=Cuenta contable retención", name = "tipPago", dataType = "Integer", required = true,
            example = "4", notes = "Nombre del canal de ejecución qque realiza la petición")
    private Integer tipPago;

    @NotNull
    @ApiModelProperty(value = "Descripción: Valor del cobro a capital, debe ser el valor exacto", name = "valor", dataType = "BigDecimal", required = true,
            example = "3807", notes = "Valor del cobro a capital, debe ser el valor exacto")
    private BigDecimal valor;

    @ApiModelProperty(value = "Descripción: Información de la cuenta en la cual se realizara el pago, puede ser cuenta corriente o de ahorros", name = "infoCta", dataType = "InfoCta",
            example = "3807", notes = "Información de la cuenta en la cual se realizara el pago, puede ser cuenta corriente o de ahorros")
    private InfoCta infoCta;

    private static final long serialVersionUID = 1L;
}
