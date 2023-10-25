package com.bdb.moduleoplcovtdsa.controller.service.control;

import com.bdb.moduleoplcovtdsa.controller.service.interfaces.CDTsBVBMService;
import com.bdb.moduleoplcovtdsa.persistence.JSONSchema.JSONConsultaBVBM;
import com.bdb.moduleoplcovtdsa.persistence.model.ResponseBVBM;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("CDTSDesmaterializado/v1/")
public class ControllerCDTsBVBM {

    @Autowired
    private CDTsBVBMService servicerCDTsBVBM;

    @PostMapping(value = "consCDTSDesBVBM", produces = {"application/json"})
    @ApiOperation(value = "Consulta de CDTS para Banca Movil/Virtual", produces = "application/json",
            response = ResponseBVBM.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta exitosa"),
            @ApiResponse(code = 400, message = "Request erroneo"),
            @ApiResponse(code = 404, message = "Servicio caido"),
            @ApiResponse(code = 500, message = "Error al consultar, por favor reportar")
    }
    )
    public ResponseEntity<ResponseBVBM> consCDTSDesBVBM(@Valid @RequestBody JSONConsultaBVBM request) {
        ResponseBVBM responseBVBM = servicerCDTsBVBM.consultaCDTsBVBM(request);
        return ResponseEntity.status(HttpStatus.OK).body(responseBVBM);
    }

}
