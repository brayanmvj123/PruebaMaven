import { Inject, Injectable } from '@angular/core';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';

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
export class LocalStorageService<T = any> {

  constructor( @Inject( LOCAL_STORAGE ) private storage: StorageService ) {
  }

  /**
   * Save any data in local storage
   * @param key unique key
   * @param value any value
   */
  public save( key: string, value: T ): void {
    this.storage.set( key, value );
  }

  /**
   * Get local storage data
   * @param key unique key
   */
  public get( key: string ): T | undefined {
    return this.storage.get( key );
  }

  /**
   * Remove local storage data
   * @param key
   */
  public delete( key: string ): void {
    this.storage.remove( key );
  }
}
