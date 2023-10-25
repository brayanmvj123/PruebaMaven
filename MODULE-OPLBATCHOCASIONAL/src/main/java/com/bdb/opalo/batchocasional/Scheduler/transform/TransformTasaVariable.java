package com.bdb.opalo.batchocasional.Scheduler.transform;

import com.bdb.opalo.batchocasional.persistence.model.TasaVariableFileModel;
import com.bdb.opaloshare.persistence.entity.*;
import com.bdb.opaloshare.persistence.model.component.ModelTipCiudPar;
import com.bdb.opaloshare.persistence.repository.RepositoryTipDepar;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPaisPar;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransformTasaVariable implements ItemProcessor<TasaVariableFileModel, List<OplHisTasaVariableEntity>> {

    @Override
    public List<OplHisTasaVariableEntity> process(TasaVariableFileModel item){
        // TODO Auto-generated method stub
        int fecha= Integer.parseInt(item.getFecha().replace("-",""));
        List<OplHisTasaVariableEntity> ListTasasVariables= new ArrayList<>();
        //DTF
        OplHisTasaVariableEntity entity= new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity entityId= new OplHisTasaVariableIdEntity();
        entityId.setFecha(fecha);
        TiptasaParDownEntity tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(2);
        entityId.setTipotasa(tasa);
        entity.setId(entityId);
        entity.setValor(item.getDtf());
        ListTasasVariables.add(entity);
        //IPC
        OplHisTasaVariableEntity ipc=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ipcid=new OplHisTasaVariableIdEntity();
        ipcid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(3);
        ipcid.setTipotasa(tasa);
        ipc.setId(ipcid);
        ipc.setValor(item.getIpc());
        ListTasasVariables.add(ipc);
        //IBR un mes
        OplHisTasaVariableEntity ibr=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrid=new OplHisTasaVariableIdEntity();
        ibrid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(6);
        ibrid.setTipotasa(tasa);
        ibr.setId(ibrid);
        ibr.setValor(item.getIbrMensual());
        ListTasasVariables.add(ibr);
        //IBR Overnight
        OplHisTasaVariableEntity ibrover=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibroverid=new OplHisTasaVariableIdEntity();
        ibroverid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(7);
        ibroverid.setTipotasa(tasa);
        ibrover.setId(ibroverid);
        ibrover.setValor(item.getIbrDiaria());
        ListTasasVariables.add(ibrover);
        //IBR Tres Meses
        OplHisTasaVariableEntity ibrtrimestral=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrtrimestralid=new OplHisTasaVariableIdEntity();
        ibrtrimestralid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(8);
        ibrtrimestralid.setTipotasa(tasa);
        ibrtrimestral.setId(ibrtrimestralid);
        ibrtrimestral.setValor(item.getIbrTrimestral());
        ListTasasVariables.add(ibrtrimestral);
        //IBR seis Meses
        OplHisTasaVariableEntity ibrsemestral=new OplHisTasaVariableEntity();
        OplHisTasaVariableIdEntity ibrsemestralid=new OplHisTasaVariableIdEntity();
        ibrsemestralid.setFecha(fecha);
        tasa=new TiptasaParDownEntity();
        tasa.setTipTasa(9);
        ibrsemestralid.setTipotasa(tasa);
        ibrsemestral.setId(ibrsemestralid);
        ibrsemestral.setValor(item.getIbrSemestral());
        ListTasasVariables.add(ibrsemestral);
        return ListTasasVariables;
    }

}