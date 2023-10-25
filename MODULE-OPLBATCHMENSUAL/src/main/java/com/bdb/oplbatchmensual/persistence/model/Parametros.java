package com.bdb.oplbatchmensual.persistence.model;

import lombok.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Parametros {

    LocalDate fechaInicio;
    LocalDate fechaFin;

}
