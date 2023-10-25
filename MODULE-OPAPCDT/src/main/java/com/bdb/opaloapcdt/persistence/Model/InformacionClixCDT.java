package com.bdb.opaloapcdt.persistence.Model;

import com.bdb.opaloshare.persistence.entity.HisClixCDTLarge;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InformacionClixCDT {

    private String mensaje;
    private boolean estado;
    private List<HisClixCDTLarge> listClixCDT;

}
