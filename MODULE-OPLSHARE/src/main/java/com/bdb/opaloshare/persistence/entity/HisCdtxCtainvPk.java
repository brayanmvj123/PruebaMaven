package com.bdb.opaloshare.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HisCdtxCtainvPk implements Serializable {

	private String codIsin;

	private String oplCtainvTblNumCta;

    private static final long serialVersionUID = 1L;

}
