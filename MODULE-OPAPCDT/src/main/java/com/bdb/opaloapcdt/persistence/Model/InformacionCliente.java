package com.bdb.opaloapcdt.persistence.Model;

import com.bdb.opaloshare.persistence.entity.HisClientesLargeEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InformacionCliente {

    private String mensaje;
    private boolean estado;
    private List<HisClientesLargeEntity> listClients;

}
