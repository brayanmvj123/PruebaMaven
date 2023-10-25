package com.bdb.moduleoplcovtdsa.persistence.model;

import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONConsultaBVBM;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBVBM implements Serializable {

    @ApiModelProperty(name = "Aplicación" , value = "OPALO" , notes = "Aplicación")
    private String app;
    @ApiModelProperty(name = "Date" , value = "YYYY-MM-DD" , notes = "Fecha de respuesta")
    private LocalDateTime date;
    @ApiModelProperty(name = "Request" , notes = "Información a consultar")
    private transient JSONConsultaBVBM parametrosRequest;
    @ApiModelProperty(name = "Cantidad de CDTS Desmaterializados")
    private Integer countTitleCDTsDesma;
    @ApiModelProperty(name = "Cantidad de CDTS Digitales")
    private Integer countTitleCDTsDigital;
    @ApiModelProperty(name = "Servicio el cual responde")
    private String nameServiceResponse;
    @ApiModelProperty(name = "Codigo HTTP")
    private String status;

    @ApiModelProperty(notes = "Contiene los datos de los titulos (CDTS) pertenecientes al " +
            "cliente consultado", required = true)
    private List<ResponseHisCdts> titulo;

}
