package com.bdb.opaloshare.persistence.model.jsonschema.diaria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Objeto de respuesta para la consulta a la tabla de salida pgSemanal
 *
 * @author: Andres Marles
 * @version: 16/12/2020
 * @since: 16/12/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONGetSalPgDiaria {

    @Schema(description = "Descripción: Item.", name = "item", type = "String", required = true, example = "551")
    @NotBlank
    private String item;

    @Schema(description = "Descripción: número de oficina.", name = "nroOficina", type = "String", required = true,
            example = "1626")
    @NotBlank
    private String nroOficina;

    @Schema(description = "Descripción: Tipo de depositante.", name = "depositante", type = "String", required = true,
            example = "1")
    @NotBlank
    private String depositante;

    @Schema(description = "Descripción: Número del CDT.", name = "numCdt", type = "Long", required = true,
            example = "250186269")
    @NotNull
    private Long numCdt;

    @Schema(description = "Descripción: Código Isin del CDT.", name = "codIsin", type = "String", required = true,
            example = "COB01CD0EC16")
    @NotBlank
    private String codIsin;

    @Schema(description = "Descripción: Número de cuenta inversionista del titular del CDT.", name = "ctaInv",
            type = "String", required = true, example = "02076913")
    @NotBlank
    private String ctaInv;

    @Schema(description = "Descripción: Tipo de identificación del titular por código: 1=CC, 2=CE, 3=NIP, etc.",
            name = "codId", type = "String", required = true,  example = "5")
    @NotBlank
    private String codId;

    @Schema(description = "Descripción: Número de identificación del titular.", name = "numTit", type = "String",
            required = true, example = "9010888120")
    @NotBlank
    private String numTit;

    @Schema(description = "Descripción: Nombre del titular del CDT.", name = "nomTit", type = "String",
            required = true, example = "INVERTIP S A S")
    @NotBlank
    private String nomTit;

    @Schema(description = "Descripción: Fecha de Emisión del CDT.", name = "fechaEmi", type = "LocalDate",
            required = true, example = "2021-08-30")
    @NotNull
    private LocalDate fechaEmi;

    @Schema(description = "Descripción: Fecha de vencimiento del CDT.", name = "fechaVen", type = "LocalDate",
            required = true, example = "2021-11-29")
    @NotNull
    private LocalDate fechaVen;

    @Schema(description = "Descripción: Fecha de vencimiento de próximo pago del CDT.", name = "fechaProxPg",
            type = "String", required = true, example = "2021-11-29")
    @NotBlank
    private String fechaProxPg;

    @Schema(description = "Descripción: Tipo de plazo: 1=Día, 2=Mes.", name = "tipPlazo", type = "String",
            required = true, example = "1")
    @NotBlank
    private String tipPlazo;

    @Schema(description = "Descripción: Plazo en días o meses según el tipo de plazo escogido.", name = "plazo",
            type = "String", required = true, example = "91")
    @NotBlank
    private String plazo;

    @Schema(description = "Descripción: Tipo de base: 1=365, 2=360, 3=real.", name = "tipBase", type = "String",
            required = true, example = "1")
    @NotBlank
    private String tipBase;

    @Schema(description = "Descripción: Tipo de periodicidad: 0=Al plazo, 1= Mensual, 2=Bimestral, 3=Trimestral, etc.",
            name = "tipPeriodicidad", type = "String", required = true, example = "0")
    @NotBlank
    private String tipPeriodicidad;

    @Schema(description = "Descripción: Tipo de tasa por código: 1=Fija, 2=DTF, 3=IPC, 6=IBR Un mes, 7=IBR Overnight," +
            " 8=IBR Tres Meses, 9=IBR Seis Meses.", name = "tipTasa", type = "String", required = true, example = "1")
    @NotBlank
    private String tipTasa;

    @Schema(description = "Descripción: Tasa efectiva del CDT.", name = "tasaEfe", type = "String", required = true,
            example = "2.45")
    @NotBlank
    private String tasaEfe;

    @Schema(description = "Descripción: Tasa nominal del CDT.", name = "tasaNom", type = "String", required = true,
            example = "2.43")
    @NotBlank
    private String tasaNom;

    @Schema(description = "Descripción: Valor nominal del CDT.", name = "valorNominal", type = "String",
            required = true, example = "1258876089")
    @NotBlank
    private String valorNominal;

    @Schema(description = "Descripción: Valor de interés bruto del CDT.", name = "intBruto", type = "String",
            required = true, example = "7626271")
    @NotBlank
    private String intBruto;

    @Schema(description = "Descripción: Valor de la retención en la fuente del CDT.", name = "rteFte", type = "String",
            required = true, example = "305051")
    @NotBlank
    private String rteFte;

    @Schema(description = "Descripción: Valor de interés neto del CDT.", name = "intNeto", type = "String",
            required = true, example = "7321220")
    @NotBlank
    private String intNeto;

    @Schema(description = "Descripción: Valor del capital a pagar del CDT.", name = "capPg", type = "String",
            required = true, example = "1258876089")
    @NotBlank
    private String capPg;

    @Schema(description = "Descripción: Valor total neto a pagar del CDT, incluye valor a capital, intereses y retención " +
            "el la fuente.", name = "totalPagar", type = "String", required = true, example = "1266197309")
    @NotBlank
    private String totalPagar;

    @Schema(description = "Descripción: Tipo de posición.", name = "tipPosicion", type = "String", required = true,
            example = "1")
    @NotBlank
    private String tipPosicion;

    @Schema(description = "Descripción: Factor de liquidación provisto por DeceVal.", name = "factorDcvsa", type = "String",
            required = true, example = "0.06058")
    @NotBlank
    private String factorDcvsa;

    @Schema(description = "Descripción: Factor de liquidación calculado por OPALO.", name = "factorOpl", type = "String",
            required = true,  example = "0")
    @NotBlank
    private String factorOpl;

    @Schema(description = "Descripción: Valor del Spread.", name = "spread", type = "String", required = true,
            example = "0")
    @NotBlank
    private String spread;

    @Schema(description = "Descripción: Estado del CDT", name = "estado", type = "Integer", required = true,
            example = "4")
    @NotNull
    private Integer estado;

    @Schema(description = "Descripción: Tipo de posición: OFICINA, INMOVILIZADO, TESORERIA, DIGITAL,DESCONOCIDO.",
            name = "descPosicion", type = "String", required = true, example = "OFICINA")
    @NotBlank
    private String descPosicion;

    @Schema(description = "Descripción: Indica el tipo de conciliación: NO CONCILIADO, DECEVAL, OPLAO, CONCILIADO.",
            name = "conciliacion", type = "String", required = true, example = "NO CONCILIADO")
    @NotBlank
    private String conciliacion;

    @Schema(description = "Descripción: Tipo de código de producto.", name = "codProd", type = "Long", required = true,
            example = "0" )
    @NotNull
    private Long codProd;

    @Schema(description = "Descripción: Fecha de conciliación o creación del registro.", name = "fecha", type = "String",
            required = true, example = "2021-12-09 14:45:31.694")
    @NotBlank
    private String fecha;
}
