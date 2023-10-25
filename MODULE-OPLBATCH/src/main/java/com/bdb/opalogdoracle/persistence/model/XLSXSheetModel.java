package com.bdb.opalogdoracle.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class XLSXSheetModel {

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String password;

    @NotNull
    private List<String> headers;

    @NotNull
    private List<List<String>> cells;

}
