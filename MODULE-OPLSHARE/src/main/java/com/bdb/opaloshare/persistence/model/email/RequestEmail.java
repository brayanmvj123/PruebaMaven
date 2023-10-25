package com.bdb.opaloshare.persistence.model.email;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEmail implements Serializable {


    @Schema( description = "Descripción: Direccion de correo de origen", name = "from", type = "String", required = true,
            example = "opalo@bancodebogota.com.co"  )
    @NotBlank
    private String from;


    @Schema(description = "Descripción: Dirección de correo del destinatario", name = "to", type = "List<String>", required = true,
            example = "[\"amarles@bancodebogota.com.co\", \"amarles@bancodebogota.com.co\"]")
    @NotBlank
    private List<String> to;


    @Schema(description = "Descripción: Dirección de correo a la cual se envia una copia",
            name = "cc", type = "List<String>", required = false, example = "[\"amarles@bancodebogota.com.co\", \"amarles@bancodebogota.com.co\"]")
    @NotBlank
    private List<String> cc;


    @Schema(description = "Descripción: Dirección de correo a la cual se envia una copia oculta", name = "co", type = "List<String>",
            required = false, example = "[\"amarles@bancodebogota.com.co\", \"amarles@bancodebogota.com.co\"]")
    @NotBlank
    private List<String> co;


    @Schema(description = "Descripción: Asunto del correo.", name = "subject", type = "String", required = true,
            example = "Correo de prueba, no responder")
    @NotBlank(message = "Debe especificar el tipo de asunto que debe llevar el correo")
    private String subject;


    @Schema(description = "Descripción: Texto o contenido del cuerpo de mensaje que lleva el correo. Recibe texto plano o HTML", name = "message",
            type = "String", required = true, example = "<h4>Buenos dias</h4><br><div><p>Este es un mensaje de prueba," +
            " por favor no responder.</p><br><p style=\"color: red\">Atentamente,</p><p><em style=\"color: green\">Opalo</em></p></div>")
    @NotBlank(message = "Debe especificar el tipo de contenido o cuerpo de mensaje que debe llevar el correo")
    private String message;

    @Schema(description = "Descripción: Nombre del archivo con su extensión (debe estar guardado previamente en " +
            "la carpeta del dia de OPALO en el sito FTPS).", name = "files", type = "List<String>", required = false,
            example = "[\"test.xlsx\", \"test.pdf\"]")
    private List<String> files;

    @Schema(description = "Descripción: Nombre de la SubCarpeta contenida en la ubicación /OPALO/'carpetadeldia'/OUTPUT " +
            "en el FTPS, en esta carpeta deben estar ubicados los archivos escritos en el campo files, si el correo se " +
            "envia sin archivos adjuntos este campo puede ir vacío",
            name = "folderinOutputFTPS", type = "String", required = false, example = "REPORTE_OFICINA_SEMANAL")
    @NotBlank(message = "Debe especificar el tipo de contenido o cuerpo de mensaje que debe llevar el correo")
    private String folderinOutputFTPS;

    private static final long serialVersionUID = 1L;
}
