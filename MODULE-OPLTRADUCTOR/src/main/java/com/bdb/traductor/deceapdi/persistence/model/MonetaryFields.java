package com.bdb.traductor.deceapdi.persistence.model;

import com.bdb.opaloshare.persistence.model.trad.JSONMonetaryField;
import lombok.Data;

import java.util.List;

@Data
public class MonetaryFields {
    List<JSONMonetaryField> monetaryFieldList;
}
