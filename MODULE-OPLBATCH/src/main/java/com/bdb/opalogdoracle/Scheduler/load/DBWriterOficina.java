package com.bdb.opalogdoracle.Scheduler.load;

import com.bdb.opalogdoracle.controller.service.interfaces.OficinaService;
import com.bdb.opaloshare.persistence.entity.OficinaParDownEntity;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DBWriterOficina implements ItemWriter<OficinaParDownEntity> {

    @Autowired
    OficinaService serviceOficina;

    @Override
    public void write(List<? extends OficinaParDownEntity> items) throws Exception {
        filtro(items);
    }

    public void filtro(List<? extends OficinaParDownEntity> items){
        for (int i = 1 ; i <= 4  ; i++){
            int finalI = i;
            System.out.println("VALOR DEL TIPO DE OFICINA: "+i);
            List<OficinaParDownEntity> carga = items.stream().filter(
                    tipo -> tipo.getOplTipoficinaTblTipOficina() == finalI).collect(Collectors.toList());
            serviceOficina.agregarOficina(carga);
        }
    }

}
