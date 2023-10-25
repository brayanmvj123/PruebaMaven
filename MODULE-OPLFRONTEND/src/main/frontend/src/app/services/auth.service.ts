import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Bbforms } from '../include/bbforms';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { NetworkUserModel } from '../models/network.user.model';
import { RolesModel } from '../models/roles.model';
import { TokenModel } from '../models/token.model';

export type ActionProp = 'disable' | 'enable';

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF-FRONT was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@Injectable( {
  providedIn: 'root'
} )
export class AuthService {

  constructor( private http: HttpClient ) {
  }

  /**
   * Login user and get token
   * @param username login name
   * @param password login password
   * @param domain login domain
   */
  doLogin( username: string, pwd: string, domain: string ): Observable<TokenModel> {

    let password = encodeURIComponent(pwd);
  
    return this.http.post<TokenModel>( environment.api.auth_url, new HttpParams( {
      fromObject: { username, password, domain }
    } ), {
      headers: new HttpHeaders( { ...Bbforms.X_WWW_FORM_URL_ENCODED } )
    } );
  }

  /**
   * Refresh current secure session
   * @param token current token
   */
  refresh( token: string ): Observable<TokenModel> {
    return this.http.post<TokenModel>( environment.api.auth_refresh_url, new HttpParams( {
      fromObject: {}
    } ), {
      headers: new HttpHeaders( { token, ...Bbforms.X_WWW_FORM_URL_ENCODED } )
    } );
  }

  /**
   * Get logged user roles
   * @param token valid current token
   */
  roles( token: string ): Observable<{ result: RolesModel[] }> {
    return this.http.post<{ result: RolesModel[] }>( environment.api.user_roles, new HttpParams( {
      fromObject: {}
    } ), {
      headers: new HttpHeaders( { token, ...Bbforms.X_WWW_FORM_URL_ENCODED } )
    } );
  }

  /**
   * Search network user for enabling and disabling
   * @param token
   * @param network_user
   */
  searchNetworkUser( token: string, network_user: string ): Observable<{ result: NetworkUserModel[] }> {
    return this.http.post<{ result: NetworkUserModel[] }>( environment.api.search_nuser, new HttpParams( {
      fromObject: { network_user }
    } ), {
      headers: new HttpHeaders( { token, ...Bbforms.X_WWW_FORM_URL_ENCODED } )
    } );
  }

  /**
   * Enable or disable user
   * @param token
   * @param nuser
   * @param action
   */
  enableOrDisaleUser( token: string, nuser: string, action: ActionProp ): Observable<{ result: string }> {
    return this.http.post<{ result: string }>( environment.api.change_user_state, new HttpParams( {
      fromObject: { action, nuser }
    } ), {
      headers: new HttpHeaders( { token, ...Bbforms.X_WWW_FORM_URL_ENCODED } )
    } );
  }

  /**
   * Get all users that have been logged in this platform.
   * @param token
   */
  getAllUsers( token: string ): Observable<{ result: NetworkUserModel[] }> {
    return this.http.post<{ result: NetworkUserModel[] }>( environment.api.get_users, new HttpParams( {
      fromObject: {}
    } ), {
      headers: new HttpHeaders( { token, ...Bbforms.X_WWW_FORM_URL_ENCODED } )
    } );
  }

  /**
   * Get all domain list.
   */
  getDomains(): Observable<{ result: {} }> {
    return this.http.post<{ result: {} }>( environment.api.get_domains, {}, {} );
  }

  /**
   * Get authentication type.
   */
  getAuthType(): Observable<{ result: string, status: { code: number } }> {
    return this.http.post<{ result: string, status: { code: number } }>( environment.api.get_auth_type, {}, {} );
  }
}
