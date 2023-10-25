package com.bdb.opaloshare.util;

import com.bdb.opaloshare.persistence.model.jwt.JWTHeaderModel;
import com.bdb.opaloshare.persistence.model.jwt.JWTPayloadModel;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.regex.Pattern;

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
@Getter
@CommonsLog
@NoArgsConstructor
public class Jwt {
    private String token;

    public Jwt(String token) {
        this.token = token;
    }

    /**
     * JWT Code to JSON String decoder
     *
     * @return JSON String
     */
    public String getRawPayload() throws UnsupportedEncodingException {
        String[] jwtParts = token.split(Pattern.quote(".")); // Split period - Second part of jwt code.
        String result = jwtParts[1];
        result = result.replaceAll("-", "+");
        result = result.replaceAll("_", "/");

        switch (result.length() % 4) {
            case 0:
                break;
            case 2:
                result += "==";
                break;
            case 3:
                result += "=";
                break;
            default:
                log.warn("Invalid request token [" + this.token + "]");
                break;
        }

        result = (result + "===").substring(0, result.length() + (result.length() % 4));
        result = result.replace("-", "+");
        result = result.replace("_", "/");
        result = new String(Base64.getDecoder().decode(result));
        result = URLDecoder.decode(result, "UTF-8");

        return result;
    }

    /**
     * Decode jwt token to custom object model
     *
     * @return jwt data as selected
     */
    public JWTPayloadModel getPayload() throws UnsupportedEncodingException {
        return new Gson().fromJson(getRawPayload(), JWTPayloadModel.class);
    }

    /**
     * Get Jwt header
     *
     * @return Jwt Header Object model
     */
    public JWTHeaderModel getHeader() {
        String[] jwtParts = token.split(Pattern.quote(".")); // Split period - Second part of jwt code.
        String result = jwtParts[0];
        String header = new String(Base64.getDecoder().decode(result));
        return new Gson().fromJson(header, JWTHeaderModel.class);
    }

    /**
     * Get signature validations
     *
     * @return jwt signature object
     */
    public JWTSignature signature() {
        return new JWTSignature(token.split(Pattern.quote("."))[2]);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JWTSignature {
        private String signature;

        public boolean isValid() {
            return false;
        }
    }

    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Generator<T> {
        private T payload;

        /**
         * Generate a Token for sso session
         *
         * @return String token
         */
        public String getToken() {
            return Jwts.builder()
                    .setPayload(new Gson().toJson(payload))
                    .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .compact();
        }
    }
}
