package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OPL_PAR_CONTROLES_DOWN_TBL")
public class ParControlesEntity implements Serializable {

    @Id
    @Column(name = "TIP_CONTROL")
    private Long tipControl;

    @Column(name = "DESC_CONTROL")
    private String descControl;

    public ParControlesEntity(Long tipControl){
        this.tipControl = tipControl;
    }

}
