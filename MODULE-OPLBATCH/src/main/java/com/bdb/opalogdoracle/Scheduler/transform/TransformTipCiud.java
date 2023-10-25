package com.bdb.opalogdoracle.Scheduler.transform;

import com.bdb.opaloshare.persistence.entity.TipCiudEntity;
import com.bdb.opaloshare.persistence.entity.TipDeparEntity;
import com.bdb.opaloshare.persistence.entity.TipPaisEntity;
import com.bdb.opaloshare.persistence.model.component.ModelTipCiudPar;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDepar;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPaisPar;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransformTipCiud implements ItemProcessor<ModelTipCiudPar, TipCiudEntity> {

	@Autowired
	private RepositoryTipPaisPar repoPais;
	
	@Autowired
	private RepositoryTipDepar repoDep;
	
	@Override
	public TipCiudEntity process(ModelTipCiudPar item) throws Exception {
		// TODO Auto-generated method stub
		TipCiudEntity entityCiudad = new TipCiudEntity();
		Integer codPais = repoDep.findByHomoCrmAndDesDep(item.getCodDep(),item.getDesDep()).getOplTippaisTblCodPais();
		//TipPaisEntity entityPais = repoPais.findByCodPais(codPais);
		List<TipDeparEntity> entityDepartamento = repoDep.findByHomoCrmAndOplTippaisTblCodPais(item.getCodDep(),codPais);

		entityCiudad.setCodCiud(item.getLlave());
		entityCiudad.setOplTippaisTblCodPais(codPais);
		entityCiudad.setOplTipdeparTblCodDep(entityDepartamento.get(0).getCodDep());
		entityCiudad.setHomoCrm(item.getCodCiud());
		entityCiudad.setDesCiud(item.getDesCiud());
		
		return entityCiudad;
	}

}
