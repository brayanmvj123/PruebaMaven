package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OPL_CAR_CALENDARCON_DOWN_TBL")
public class OplCarCalendarconDownEntity {

    @Id
    @Column(name="ITEM")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long item;

    @Column(name="DIA")
    Integer dia;

    @Column(name="MES")
    Integer mes;

    @Column(name="VALOR")
    Integer valor;

}
