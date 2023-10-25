package com.bdb.platform.servfront.controller.service.control;

import com.bdb.opaloshare.persistence.entity.ParUserconexDownEntity;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.platform.servfront.controller.service.interfaces.UserFTPService;
import com.bdb.platform.servfront.model.ftp.UserModel;
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
import java.util.List;

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
@RequestMapping("ftp/user")
@CrossOrigin(value = "*", maxAge = 0)
public class FTPUserController {

    @Autowired
    private UserFTPService userFTPService;

    /**
     * FTP User registration.
     *
     * @param data FTP User data
     * @return Creation result
     */
    @PostMapping(value = "registration",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<String>> create(HttpServletRequest request, @Valid @RequestBody UserModel data) {

        // Check By Id
        if (userFTPService.exists(data.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El Id ingresado corresponde a un usuario ya existente.");
        }

        // Start user creation
        ParUserconexDownEntity user = new ParUserconexDownEntity();
        user.setIdUsuario(data.getUserId());
        user.setContrasena(data.getPassword());
        user.setNombreUsuario(data.getUsername());

        // Execute creation
        userFTPService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RequestResult<>(request, HttpStatus.CREATED, "Usuario FTP Creado."));
    }

    /**
     * Get all FTP Users
     *
     * @param request Http Request
     * @return List of FTP Users
     */
    @PostMapping(value = "all",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<List<ParUserconexDownEntity>>> getAllUsers(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResult<>(request, HttpStatus.OK, userFTPService.findAll()));
    }
}
