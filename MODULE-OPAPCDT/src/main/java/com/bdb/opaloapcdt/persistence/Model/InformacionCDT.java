package com.bdb.opaloapcdt.persistence.Model;

import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InformacionCDT {

    private String mensaje;
    private boolean estado;
    private HisCDTSLargeEntity cdt;

}
