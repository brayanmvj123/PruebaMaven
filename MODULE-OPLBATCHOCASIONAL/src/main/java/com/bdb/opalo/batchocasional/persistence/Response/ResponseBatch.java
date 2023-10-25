package com.bdb.opalo.batchocasional.persistence.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBatch implements Serializable {

    private String date;
    private String status;
    private String requestUrl;
    private String jobId;
    private String resultJob;
    private String possibleMistake;

}
