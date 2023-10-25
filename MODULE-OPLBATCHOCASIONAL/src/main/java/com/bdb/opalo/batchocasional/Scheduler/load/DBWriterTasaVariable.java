package com.bdb.opalo.batchocasional.Scheduler.load;

import com.bdb.opaloshare.persistence.entity.OplHisTasaVariableEntity;
import com.bdb.opaloshare.persistence.entity.TipCiudEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryOplTasaVariable;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBWriterTasaVariable  implements ItemWriter<List<OplHisTasaVariableEntity>> {

    /*
     * REALIZA EL LLAMADO AL SERVICE PARA PODER IMPLEMENTAR EL ALMACENAMIENTO DE LOS DATOS OBTENIDOS A LA BASE DE DATOS.
     */

    @Autowired
    private RepositoryOplTasaVariable repositoryOplTasaVariable;

    @Override
    public void write(List<? extends List<OplHisTasaVariableEntity>> items) throws Exception {
        // TODO Auto-generated method stub
        for (List<OplHisTasaVariableEntity> elements:items) {
            System.out.println("tamaño datos: "+elements.size());
            repositoryOplTasaVariable.saveAll(elements);
        }
    }

}
