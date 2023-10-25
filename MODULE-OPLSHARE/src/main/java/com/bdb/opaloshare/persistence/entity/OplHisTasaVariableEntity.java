package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_HIS_TASASVARCON_MEDIUM_TBL")
public class OplHisTasaVariableEntity implements Serializable {

    @EmbeddedId
    private OplHisTasaVariableIdEntity id;

    @Column(name="VALOR")
    private Double valor;
}
