package com.bdb.opalogdoracle.Scheduler.transform;

import com.bdb.opaloshare.persistence.entity.TipDeparEntity;
import com.bdb.opaloshare.persistence.entity.TipPaisEntity;
import com.bdb.opaloshare.persistence.model.component.ModelTipDepPar;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPaisPar;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class TransformTipDep implements ItemProcessor<ModelTipDepPar, TipDeparEntity> {
	
	@Autowired
	private RepositoryTipPaisPar repoPais;
	
	@Override
	public TipDeparEntity process(ModelTipDepPar item) throws Exception {
		TipDeparEntity depEntity = new TipDeparEntity();
		TipPaisEntity paisEntity = repoPais.findByHomoCrm(item.getCodPais());
		
		depEntity.setCodDep(item.getLlave());
		depEntity.setOplTippaisTblCodPais(paisEntity.getCodPais());
		depEntity.setHomoCrm(item.getCodDep());
		depEntity.setDesDep(item.getDesDep());

		return depEntity;
	}

}
