package com.bdb.opaloshare.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HisCtaInvxCliPk implements Serializable {

    private String oplCtainvTblNumCta;

    private String oplInfoclienteTblNumTit;

    private static final long serialVersionUID = 1L;
}
