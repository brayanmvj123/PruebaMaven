package com.bdb.oplbatchmensual.persistence.jsonschema.CalendarioCofnal;

import com.bdb.opaloshare.persistence.entity.OplCarCalendarconDownEntity;
import com.bdb.opaloshare.persistence.model.response.simuladorcuota.JSONResponseStatus;
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
    private List<OplCarCalendarconDownEntity> result;
}
