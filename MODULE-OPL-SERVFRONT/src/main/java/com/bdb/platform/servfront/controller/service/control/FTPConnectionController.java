package com.bdb.platform.servfront.controller.service.control;

import com.bdb.opaloshare.persistence.entity.ParConexionesDownEntity;
import com.bdb.opaloshare.persistence.entity.ParUserconexDownEntity;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.platform.servfront.controller.service.interfaces.FTPConnectionService;
import com.bdb.platform.servfront.controller.service.interfaces.UserFTPService;
import com.bdb.platform.servfront.model.ftp.ConnectionModel;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Copyright (c) 2019 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@RestController
@CommonsLog
@RequestMapping("ftp/connection")
@CrossOrigin(value = "*", maxAge = 0)
public class FTPConnectionController {

    @Autowired
    private UserFTPService userFTPService;

    @Autowired
    private FTPConnectionService ftpConnectionService;

    @PostMapping(value = "creation",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<String>> creation(HttpServletRequest request, @Valid @RequestBody ConnectionModel data) {

        // Check ID Exists
        if (ftpConnectionService.exists(data.getConnectionId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El Id de conexión ingresado corresponde a una conexión ya existente.");
        }

        // Start connection
        ParConexionesDownEntity connection = new ParConexionesDownEntity();
        connection.setIdConexion(data.getConnectionId());
        connection.setTipConexion(data.getConnectionType());
        connection.setNombreHost(data.getHostName());
        connection.setHostIp(data.getHostIp());
        connection.setPuerto(data.getPort());
        connection.setDescConex(data.getDescription());
        connection.setRuta(data.getPath());

        // Start User
        ParUserconexDownEntity user = userFTPService.getOne(data.getUserId());

        // Set user relation
        connection.setUsuario(user);

        // Save connection
        ftpConnectionService.save(connection);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RequestResult<>(request,
                        HttpStatus.CREATED,
                        "Conexión creada para el usuario " + user.getNombreUsuario() + "."));
    }
}
