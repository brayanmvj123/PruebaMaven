import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { FtpUserModel } from '../models/ftp.user.model';

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
export class FtpService {

  constructor( private http: HttpClient ) {
  }

  /**
   * Register a new user to system
   * @param user User Object
   */
  registerUser( user: object ): Observable<{ result: string }> {
    return this.http.post<{ result: string }>( environment.api.create_ftp_user, user, {} );
  }

  /**
   * Load all FTP users...
   */
  loadUsers(): Observable<{ result: FtpUserModel[] }> {
    return this.http.post<{ result: FtpUserModel[] }>( environment.api.get_ftp_users, {}, {} );
  }

  /**
   * Create FTP Connection for a user
   * @param connection object
   */
  createFTPConnection( connection: object ): Observable<{ result: string }> {
    return this.http.post<{ result: string }>( environment.api.create_ftp_connection, connection, {} );
  }
}
