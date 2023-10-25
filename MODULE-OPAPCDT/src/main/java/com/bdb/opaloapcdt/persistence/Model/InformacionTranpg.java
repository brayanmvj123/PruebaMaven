package com.bdb.opaloapcdt.persistence.Model;

import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InformacionTranpg {

	private String mensaje;
	private boolean estado;
	private List<HisTranpgEntity> listTranPag;
		
}
