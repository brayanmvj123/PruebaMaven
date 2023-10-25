package com.bdb.opalo.cofnal.persistance.JSONSchema.response.calendario;

import com.bdb.opalo.cofnal.persistance.JSONSchema.response.JSONResponseStatus;
import com.bdb.opalo.cofnal.persistance.model.entity.CalendarioEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JSONResponseCalendarioCofnal implements Serializable {

    private JSONResponseStatus status;
    private String requestUrl;
    private List<CalendarioEntity> result;

}
