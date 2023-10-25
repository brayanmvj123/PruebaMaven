package com.bdb.opaloshare.persistence.model.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File implements Serializable {

    @NotBlank
    private String nameFile;

    private static final long serialVersionUID = 1L;
}
