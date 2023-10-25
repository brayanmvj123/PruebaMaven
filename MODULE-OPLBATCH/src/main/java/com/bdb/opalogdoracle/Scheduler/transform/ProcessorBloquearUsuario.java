package com.bdb.opalogdoracle.Scheduler.transform;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import org.springframework.batch.item.ItemProcessor;

public class ProcessorBloquearUsuario implements ItemProcessor<HisLoginDownEntity,HisLoginDownEntity> {

    @Override
    public HisLoginDownEntity process(HisLoginDownEntity item) throws Exception {
        HisLoginDownEntity login = new HisLoginDownEntity();
        login.setItem(item.getItem());
        login.setUsuario(item.getUsuario());
        login.setNombres(item.getNombres());
        login.setApellidos(item.getApellidos());
        login.setFecha_conexion(item.getFecha_conexion());
        login.setEstado("2");
        login.setToken(item.getToken());
        login.setIdentificacion(item.getIdentificacion());
        return login;
    }
}
