package com.bdb.opalomcrm.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bdb.opalomcrm.common.Constants;
import com.bdb.opalomcrm.interfaces.NovedadesLoaderService;
import com.bdb.opaloshare.persistence.entity.SalNovCrmCliEntity;
import com.bdb.opaloshare.persistence.repository.OplMaecdtsCarDownTblRepository;
import com.bdb.opaloshare.persistence.repository.OplMaedcvTmpDownTblRepository;
import com.bdb.opaloshare.persistence.repository.OplNovcrmcliSalDownTblRepository;

@Service
public class NovedadesLoaderServiceImpl implements NovedadesLoaderService {

	@Autowired
	private OplMaecdtsCarDownTblRepository RepoCar;

	@Autowired
	private OplMaedcvTmpDownTblRepository RepoTmp;

	@Autowired
	private OplNovcrmcliSalDownTblRepository RepoNov;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void novedadesLoader() {
		
		RepoNov.deleteAll();
		
		try {

		List<List<String>> CarDif = new ArrayList<>();
		List<List<String>> TmpDif = new ArrayList<>();
		
		List<SalNovCrmCliEntity> novList = new ArrayList<>();

		CarDif = RepoCar.findDif();
		TmpDif = RepoTmp.findDif(); 
		
        if(!TmpDif.isEmpty()) {
        	
        	System.out.println("*****TmpDif: ");
			
			for (List<String> item : TmpDif) {

				SalNovCrmCliEntity salNovCrmCliEntity = new SalNovCrmCliEntity();

				salNovCrmCliEntity.setTipId(item.get(0));
				salNovCrmCliEntity.setIdTit(item.get(1));
				salNovCrmCliEntity.setTipNovedad(Constants.TIP_NOV);
				salNovCrmCliEntity.setInfNovedad(Constants.NOV_INF_N);
				salNovCrmCliEntity.setStatus(Constants.NOV_MARCA_BUS_SOAP_N);

				novList.add(salNovCrmCliEntity);

			}
			
    	}
		
		if(!CarDif.isEmpty()) {
			
        	System.out.println("*****CarDif: ");

			
			for (List<String> item : CarDif) {

				SalNovCrmCliEntity salNovCrmCliEntity = new SalNovCrmCliEntity();

				salNovCrmCliEntity.setTipId(item.get(0));
				salNovCrmCliEntity.setIdTit(item.get(1));
				salNovCrmCliEntity.setTipNovedad(Constants.TIP_NOV);
				salNovCrmCliEntity.setInfNovedad(Constants.NOV_INF_Y);
				salNovCrmCliEntity.setStatus(Constants.NOV_MARCA_BUS_SOAP_N);

				novList.add(salNovCrmCliEntity);

			}
    		
    	}
		
		
		if(!novList.isEmpty()) {
			RepoNov.saveAll(novList);
		}
      

	}
		catch (Exception e) {
        	logger.error("ERROR IN novedadesLoader: "+e.getMessage());
        }
	
}

}