package com.bdb.opalo.cofnal.persistance.JSONSchema.response.tasaVariable;

import com.bdb.opalo.cofnal.persistance.JSONSchema.response.JSONResponseStatus;
import com.bdb.opalo.cofnal.persistance.model.entity.TasaVariableCofnalMensualEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseTasaVariableCofnalMensual implements Serializable {

    private JSONResponseStatus status;
    private String requestUrl;
    private Object parameters;
    private List<TasaVariableCofnalMensualEntity> result;

}