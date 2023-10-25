package com.bdb.oplbacthdiarios.schudeler.services;

import com.bdb.opaloshare.controller.service.errors.ErrorFtps;

public interface OperationBatchTransCdtsDigBi {

    void getData();

    void makeFile() throws ErrorFtps;

}
