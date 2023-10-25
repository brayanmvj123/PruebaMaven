package com.bdb.platform.auth.controller.service.control;

import com.bancodebogota.wsldapppe.service.v1.AtributosLdap;
import com.bancodebogota.wsldapppe.service.v1.RespuestaAtributos;
import com.bancodebogota.wsldapppe.service.v1.RespuestaAutenticaUsuario;
import com.bancodebogota.wsldapppe.service.v1.RespuestaGruposLite;
import com.bancodebogota.wsldapppe.service.v1.V1_002fWebServiceLdapPpe;
import com.bancodebogota.wsldapppe.service.v1.V1_002fWebServiceLdapPpe_Service;
import com.bdb.opaloshare.persistence.entity.HisLoginDownEntity;
import com.bdb.opaloshare.persistence.entity.HisUsuarioxrolDownEntity;
import com.bdb.opaloshare.persistence.entity.ParRolesxgdaDownEntity;
import com.bdb.opaloshare.persistence.entity.ParVistasappDownEntity;
import com.bdb.opaloshare.persistence.entity.VarentornoDownEntity;
import com.bdb.opaloshare.persistence.model.jwt.JWTPayloadModel;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.util.Jwt;
import com.bdb.platform.auth.controller.service.interfaces.EnvironmentService;
import com.bdb.platform.auth.controller.service.interfaces.RoleByUserService;
import com.bdb.platform.auth.controller.service.interfaces.RolesService;
import com.bdb.platform.auth.controller.service.interfaces.UserLoginService;
import com.bdb.platform.auth.controller.service.interfaces.ViewsByRoleService;
import com.bdb.platform.auth.controller.service.interfaces.WsdlService;
import com.bdb.platform.auth.util.RolesFunctionsModel;
import com.bdb.platform.auth.util.UserStatus;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

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
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@CommonsLog
@RequestMapping("auth")
@CrossOrigin(value = "*", maxAge = 0)
public class SSOController {

    @Autowired
    private UserLoginService loginService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private RoleByUserService roleByUserService;

    @Autowired
    private ViewsByRoleService viewsByRoleService;

    @Autowired
    private WsdlService wsdlService;

    @Autowired
    private EnvironmentService environmentService;

    @Value("${session.token.durationInMinutes}")
    private Long sessionDuration;

    @Value("${bancodebogota.wsdl}")
    private Long wsdlID;

    @Value("${bancodebogota.auth_type_env}")
    private String authCodeEnv;

    @Value("${bancodebogota.auth_permanent_env}")
    private String authPermanentEnv;

    @Value("${bancodebogota.auth_domains_env}")
    private String authDomainEnv;

    /**
     * Login Local and do validation to Active Directory to Single Sign On Flow.
     *
     * @param request  Http Request
     * @param username username
     * @param password passWord
     * @param domain   aval domain
     * @return unique user token validation
     */
    @SuppressWarnings("DuplicatedCode")
    @PostMapping(value = "sso",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Token> sso(
            HttpServletRequest request,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("domain") String domain) throws MalformedURLException {
        log.info("Logging user [" + username.toUpperCase() + "].");
        String passwordDecoded = "";
        try {
            passwordDecoded = URLDecoder.decode(password, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("Exception decoding password: " + e);
        }
        // Change to Uppercase
        username = username.toUpperCase();
        // SOAP PORT ===================================================================================================
        V1_002fWebServiceLdapPpe_Service service = new V1_002fWebServiceLdapPpe_Service(new URL(wsdlService.getById(wsdlID).getRuta()));
        V1_002fWebServiceLdapPpe ss = service.getWebServiceLdapPpeHttPort();
        // GET AUTHENTICATION TYPE =====================================================================================
        VarentornoDownEntity authType = environmentService.getByDesc(authCodeEnv);
        log.info("Authentication Type -> " + authType.getValVariable());
        // GET PERMANENT ROLES =========================================================================================
        VarentornoDownEntity permanentRoles = environmentService.getByDesc(authPermanentEnv);
        List<String> permanentUserRoles = new Gson().fromJson(permanentRoles.getValVariable(), ArrayList.class);
        // SOAP AUTHORIZATION ==========================================================================================
        log.info("Trying to authenticate at LDAP PPE.");
        RespuestaAutenticaUsuario auth = ss.autenticarUsuario(username, passwordDecoded, domain);
        // Check if user is authenticated, if value is different to 0 then user is not authenticated
        if (auth.getCodigo() != 0) {
            log.error(auth.getDescripcion());
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    String.format("%1$s. Verifica si el usuario y la contraseña son correctos.", auth.getDescripcion()));
        }
        // SOAP GET USER INFO ==========================================================================================
        log.info("Success authentication user at LDAP PPE, now getting user information.");
        RespuestaAtributos userAttr = ss.getAtributosLdapByNt(username);
        AtributosLdap ldapAttr = null;
        if (userAttr.getAtributos().get(0) != null) {
            ldapAttr = userAttr.getAtributos().get(0);
        } else {
            log.error(username + " hasn't Ldap attributes.");
        }
        // SOAP GET USER ROLES =========================================================================================
        RespuestaGruposLite userGroups = ss.getGruposLdapUsuario(username);
        List<String> groups = new ArrayList<>();
        userGroups.getGrupos().forEach(group -> {
            log.info("ROLE WSDL ROLE GROUPS -> " + group.getNombre());
            groups.add(group.getNombre());
        });
        List<ParRolesxgdaDownEntity> roles = rolesService.findRoles(groups);
        log.info("ROLES FOUND IN LOCAL DATABASE -> " + roles.size());
        // Verify if user exists in local repository ===================================================================
        if (loginService.userExists(username)) {
            log.info("User already exists in local repository for dynamic session.");
            // User logged data
            HisLoginDownEntity user = loginService.findUser(username);
            // Check authentication type
            if (authType.getValVariable().equals("0")) {
                log.info("Authentication configured in local mode, user roles cannot be deleted or created from AD. Can be assigned manually.");
            } else {
                // First delete all role relations
                log.info("First deleting user roles to do re-creation.");
                roleByUserService.deleteAll(user);
                // If roles found then save to locale relation
                if (roles.size() > 0) {
                    log.info("Now assigning roles to user " + username);
                    roles.forEach(role -> {
                        HisUsuarioxrolDownEntity roleByUser = new HisUsuarioxrolDownEntity();
                        roleByUser.setLogin(user);
                        roleByUser.setRol(role);
                        roleByUserService.save(roleByUser);
                    });
                }
            }
            // Check if user is active
            log.info("User [" + user.getUsuario() + "] logged in.");
            return ResponseEntity.status(HttpStatus.OK).body(new Token(getToken(user, domain)));
        } else { // If user is not exists ==============================================================================
            // Check if auth type permit this action
            if (authType.getValVariable().equals("0")) {
                log.info("Authentication type is configured in local model, cannot create a new user.");
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                        "El tipo de autenticación está configurado en modo local, no se puede crear nuevo usuario desde AD.");
            }
            // Create new user
            log.info("Creating Active Directory user [" + username + "] to local repository");
            // User entity
            assert ldapAttr != null;
            HisLoginDownEntity user = new HisLoginDownEntity();
            user.setUsuario(username);
            user.setNombres(ldapAttr.getNombre().toUpperCase());
            user.setApellidos(ldapAttr.getApellido().toUpperCase());
            user.setIdentificacion(ldapAttr.getIdentificacion());
            user.setToken("--token--");
            user.setFecha_conexion(new Date());
            user.setUsuarioXroles(new HashSet<>());
            // Setting state validation
            Set<String> _groups = new HashSet<>(groups);
            // Intersection
            _groups.retainAll(permanentUserRoles);
            if (_groups.size() > 0) {
                log.info("User created as permanent user.");
                user.setEstado(UserStatus.PERMANENT.getValue());
            } else {
                log.info("User created as active user.");
                user.setEstado(UserStatus.ACTIVE.getValue());
            }
            // If roles found then save to locale relation
            log.info("Creating logged user roles.");
            if (roles.size() > 0) {
                // Create and set user
                HisLoginDownEntity loggedUser = loginService.saveUser(user);
                //set Roles
                roles.forEach(role -> {
                    HisUsuarioxrolDownEntity roleByUser = new HisUsuarioxrolDownEntity();
                    roleByUser.setLogin(user);
                    roleByUser.setRol(role);
                    roleByUserService.save(roleByUser);
                });
                log.info(loggedUser);
                // Return token and update user
                String token = "--token--";
                log.info("Getting new user token.");
                if (loggedUser != null) token = getToken(loggedUser, domain);
                // Return token session
                return ResponseEntity.status(HttpStatus.CREATED).body(new Token(token));
            }
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario no tiene roles para acceder a esta aplicación.");
        }
    }

    /**
     * Refresh token session.
     *
     * @param request      Http Servlet request
     * @param currentToken Current token
     * @return Renewed token
     * @throws UnsupportedEncodingException Error decoding token
     */
    @SuppressWarnings("DuplicatedCode")
    @PostMapping(value = "sso/refresh",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Token> refresh(
            HttpServletRequest request,
            @RequestHeader("token") String currentToken) throws UnsupportedEncodingException, MalformedURLException {

        // Check if the request is sending by a valid user =============================================================
        Jwt jwt = new Jwt(currentToken);
        JWTPayloadModel data = jwt.getPayload();

        // First get user ==============================================================================================
        HisLoginDownEntity user = validateLoggedUser(currentToken);

        log.info("Refreshing user session for [" + user.getUsuario() + "].");

        // Check if token is the last token assigned
        if (user.getToken().equals(currentToken)) {
            log.info("Token is valid in first instance, check second instance.");

            // Check if token session has more than ${sessionDuration} minutes of duration
            if (System.currentTimeMillis() > (data.getExpiration() + TimeUnit.MINUTES.toMillis(sessionDuration))) {
                log.error("The current token can't refresh because expiration time is " + sessionDuration + " minutes lower than current time.");
                throw new ResponseStatusException(
                        HttpStatus.EXPECTATION_FAILED,
                        "El token no puede renovarse con más de " + sessionDuration + " minutos de expiración.");
            }

            // SOAP PORT ===========================================================================================
            V1_002fWebServiceLdapPpe_Service service = new V1_002fWebServiceLdapPpe_Service(new URL(wsdlService.getById(wsdlID).getRuta()));
            V1_002fWebServiceLdapPpe ss = service.getWebServiceLdapPpeHttPort();

            // SOAP GET USER ROLES =================================================================================
            RespuestaGruposLite userGroups = ss.getGruposLdapUsuario(user.getUsuario());
            List<String> groups = new ArrayList<>();
            userGroups.getGrupos().forEach(group -> {
                log.info("ROLE WSDL ROLE GROUPS -> " + group.getNombre());
                groups.add(group.getNombre());
            });

            List<ParRolesxgdaDownEntity> roles = rolesService.findRoles(groups);
            log.info("ROLES FOUND IN LOCAL DATABASE -> " + roles.size());

            // First delete all role relations
            roleByUserService.deleteAll(user);
            log.info("Renewing user groups in WSDL.");

            // If roles found then save to locale relation
            if (roles.size() > 0) {
                roles.forEach(role -> {
                    HisUsuarioxrolDownEntity roleByUser = new HisUsuarioxrolDownEntity();
                    roleByUser.setLogin(user);
                    roleByUser.setRol(role);

                    roleByUserService.save(roleByUser);
                });
            }

            // If is in time then refresh token
            String token = getToken(user, data.getDomain());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Token(token));
        } else {
            log.error("Invalid token " + currentToken);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El token enviado es inválido.");
        }
    }

    /**
     * Get authentication type.
     *
     * @param request Servlet request
     * @return authentication type string message
     */
    @PostMapping(value = "type", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<String>> getAuthType(HttpServletRequest request) {
        VarentornoDownEntity authType = environmentService.getByDesc(authCodeEnv);

        if (authType.getValVariable().equals("0")) {
            return ResponseEntity
                    .status(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
                    .body(new RequestResult<>(request, HttpStatus.NON_AUTHORITATIVE_INFORMATION,
                            "El método de autenticación está configurado en modo local."));
        } else {
            return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, "Método de autenticación completa."));
        }
    }

    /**
     * Get domain list.
     *
     * @param request Servlet request.
     * @return domain list.
     */
    @PostMapping(value = "domain/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<Map<String, String>>> getDomainList(HttpServletRequest request) {
        return ResponseEntity.ok(new RequestResult<Map<String, String>>(request,
                HttpStatus.OK,
                new Gson().fromJson(environmentService.getByDesc(authDomainEnv).getValVariable(), HashMap.class)));
    }

    /**
     * Get Roles and Functions for user to enable front end views
     *
     * @param request http Servlet request
     * @param token   current valid token for security response
     * @return List of roles and functions
     */
    @PostMapping(value = "user/roles",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RequestResult<List<RolesFunctionsModel>>> getRolesAndFunctions(
            HttpServletRequest request,
            @RequestHeader("token") String token) throws UnsupportedEncodingException {

        // First get user ==============================================================================================
        HisLoginDownEntity user = validateLoggedUser(token);

        // Username to do request ======================================================================================
        String username = user.getUsuario();
        log.info("getting roles and functions for " + username);

        // Now search relations between user and roles using user id ===================================================
        List<HisUsuarioxrolDownEntity> rolesByUser = roleByUserService.rolesByUser(user);
        log.info("FOUND " + rolesByUser.size() + " ROLES BY USER LOGIN" + username);

        // Now get roles and redirect to views to make a list of role with their functions =============================
        List<RolesFunctionsModel> result = new ArrayList<>();
        rolesByUser.forEach(role -> {

            // Starting to make a model
            RolesFunctionsModel rfm = new RolesFunctionsModel();
            rfm.setId(role.getRol().getIdRol().toString());
            rfm.setRole(role.getRol().getNombreRol());
            rfm.setDescription(role.getRol().getGrupoDa());

            // Set view function to role in a list
            List<ParVistasappDownEntity> function = new ArrayList<>();
            viewsByRoleService.findByRole(role.getRol()).forEach(view -> function.add(view.getVista()));
            rfm.setFunctions(function);

            // Add to result list
            result.add(rfm);
        });

        return ResponseEntity.ok(new RequestResult<>(request, HttpStatus.OK, result));
    }

    /**
     * Get token for exists user
     *
     * @param user   User entity
     * @param domain Domain
     * @return Token
     */
    private String getToken(HisLoginDownEntity user, String domain) {
        // JWT Payload generation
        JWTPayloadModel payload = new JWTPayloadModel();
        payload.setUsername(user.getUsuario());
        payload.setId("" + user.getItem());
        payload.setName(user.getNombres());
        payload.setSurname(user.getApellidos());
        payload.setIdentification(user.getIdentificacion());
        payload.setExpiration(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(sessionDuration));
        payload.setDomain(domain);

        // Token generator
        Jwt.Generator<JWTPayloadModel> tokenGen = new Jwt.Generator<>();
        tokenGen.setPayload(payload);

        // Set user token to local repository
        user.setFecha_conexion(new Date());
        user.setToken(tokenGen.getToken());

        // Update user
        loginService.updateUser(user);

        return user.getToken();
    }

    // Token Model =====================================================================================================
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Token {
        private String token;
    }

    /**
     * Do user validation
     *
     * @param token Token Logged user token
     * @return logged user object
     */
    @SuppressWarnings("DuplicatedCode")
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
