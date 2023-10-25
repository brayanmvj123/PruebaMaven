package com.bdb.oplbacthsemanal.schudeler.services;


import com.bdb.opaloshare.controller.service.errors.ErrorFtps;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OperationCreationFile {

    void creationFile(String host) throws Exception;
}
