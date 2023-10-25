package com.bdb.opaloshare.persistence.model.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppData {
    private String nombre;
    private String version;
    private String descripcion;
}
