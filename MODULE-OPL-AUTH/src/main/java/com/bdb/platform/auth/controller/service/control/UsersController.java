package com.bdb.platform.auth.controller.service.control;

import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.HisUsuarioxrolDownEntity;
import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import com.bdb.opaloshare.persistence.model.jwt.JWTPayloadModel;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.util.Jwt;
import com.bdb.platform.auth.controller.service.interfaces.RoleByUserService;
import com.bdb.platform.auth.controller.service.interfaces.RolesService;
import com.bdb.platform.auth.controller.service.interfaces.UserLoginService;
import com.bdb.platform.auth.controller.service.interfaces.ViewsByRoleService;
import com.bdb.platform.auth.util.UserAction;
import com.bdb.platform.auth.util.UserModel;
import com.bdb.platform.auth.util.UserStatus;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@SuppressWarnings("ALL")
@RestController
@CommonsLog
@RequestMapping("users")
@CrossOrigin(value = "*", maxAge = 0)
public class UsersController {

    @Autowired
    private UserLoginService loginService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private RoleByUserService roleByUserService;

    @Autowired
    private ViewsByRoleService viewsByRoleService;

    @Value("${session.token.durationInMinutes}")
    private Long sessionDuration;

    /**
     * Get all session users, for user administration
     *
     * @param token get current logged user token
     * @param nuser get network username to search
     * @return network user
     */
    @PostMapping(value = "search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<List<HisLoginDownEntity>>> getUsers(
            HttpServletRequest request,
            @RequestHeader("token") String token,
            @RequestParam("network_user") String nuser
    ) throws UnsupportedEncodingException {
        nuser = nuser.toUpperCase();
        log.info("Searching network user ==> " + nuser);

        // Load logged user ============================================================================================
        HisLoginDownEntity user = validateLoggedUser(token);

        log.info("Getting users " + nuser);

        return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, loginService.getUsers(nuser)));
    }

    /**
     * Enable or disable user to do actions
     *
     * @param request Servlet request
     * @param nuser   Network user
     * @param action  Action to do
     * @param token   Current valid token
     * @return action result
     */
    @PostMapping(value = "availability",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<RequestResult<String>> disableEnable(
            HttpServletRequest request,
            @RequestParam("nuser") String nuser,
            @RequestParam("action") String action,
            @RequestHeader("token") String token
    ) throws UnsupportedEncodingException {
        nuser = nuser.toUpperCase();
        action = action.toUpperCase();
        log.info("Searching network user ==> " + nuser);

        // Load logged user ============================================================================================
        HisLoginDownEntity user = validateLoggedUser(token);

        // Disable or enable ===========================================================================================
        if (!action.equals(UserAction.DISABLE.getAction()) && !action.equals(UserAction.ENABLE.getAction())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "No se está enviando una acción válida tales como ENABLE: habilitar o DISABLE: deshabilitar");
        }

        // Change user status ==========================================================================================
        HisLoginDownEntity us = loginService.findUser(nuser);
        us.setEstado(UserAction.actionToValue(action));

        // Update user =================================================================================================
        loginService.updateUser(us);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new RequestResult<>(request, HttpStatus.ACCEPTED, "Estado cambiado"));
    }

    /**
     * Get All Users
     *
     * @param request Servlet Request
     * @return List of users
     */
    @PostMapping(value = "all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<List<HisLoginDownEntity>>> findAll(
            HttpServletRequest request,
            @RequestHeader("token") String token) throws UnsupportedEncodingException {

        // Load logged user ============================================================================================
        HisLoginDownEntity user = validateLoggedUser(token);

        return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, loginService.getAllUsers()));
    }

    /**
     * Do user validation
     *
     * @param token Token Logged user token
     * @return logged user object
     */
    private HisLoginDownEntity validateLoggedUser(String token) throws UnsupportedEncodingException {
        // Check if the request is sending by a valid user =============================================================
        Jwt jwt = new Jwt(token);
        JWTPayloadModel data = jwt.getPayload();

        // Username to do request ======================================================================================
        if (!loginService.userExists(data.getUsername())) {
            log.error("Logged user not exists in platform database.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Usuario que intenta hacer la petición no existe.");
        }

        // Load logged user ============================================================================================
        HisLoginDownEntity user = loginService.findUser(data.getUsername());

        // Check if user is valid ======================================================================================
        if (user.getEstado().equals(UserStatus.INACTIVE.getValue())) {
            log.error("Logged user is inactive, can not do anything.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario que intenta hacer la petición está inactivo.");
        }

        // Check if user token is valid ================================================================================
        if (user.getToken().equals(token)) {
            log.info("Checking token expiration time.");
            if (System.currentTimeMillis() > (data.getExpiration() + TimeUnit.MINUTES.toMillis(sessionDuration))) {
                log.error("Token for " + data.getUsername() + " is expired.");
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "El token de usuario expiró.");
            }
        } else {
            log.error("Token [" + token + " is invalid because is not equals to logged user.");
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "El token de usuario no es válido.");
        }

        return user;
    }
}
