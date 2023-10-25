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
import { environment } from '../../environments/environment';
import { RolesModel } from '../models/roles.model';
import { log } from 'util';
import { MatDialogRef } from '@angular/material/dialog';
import { ModalProcessingComponent } from '../templates/modal-processing/modal-processing.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogModel } from '../models/dialog.model';

export class ViewUtil {

  /**
   * Validate if logged user can use view functionality
   * @param roles logged in user roles
   */
  public static enable( roles: RolesModel[] ): any {
    const result = environment.components;
    const env = Object.keys( environment.components );
    for ( let _rol of roles ) {
      for ( let _view of _rol.functions ) {
        if ( env.includes( _view.nombreFuncionalidad ) ) {
          result[ _view.nombreFuncionalidad ] = true;
        }
      }
    }

    return result;
  }

  public static isEmpty( item: string ): boolean {
    return item === '' || item === null || item === undefined;
  }

  public static dialog<R = any>( mod: MatDialog, config?: DialogModel ): MatDialogRef<ModalProcessingComponent, R> {
    const conf: DialogModel = {
      width: this.ifEmptySet( config.width!, '600px' ),
      disableClose: this.ifBoolEmptySet( config.disableClose, true ),
      data: {
        title: this.ifEmptySet( config.data.title! ),
        cancelText: this.ifEmptySet( config.data.cancelText!, 'Cancelar' ),
        description: this.ifEmptySet( config.data.description! ),
        icon: 'question',
        closeText: this.ifEmptySet( config.data.closeText!, 'Cerrar' ),
        confirmText: this.ifEmptySet( config.data.confirmText!, 'Aceptar' ),
        okText: this.ifEmptySet( config.data.okText!, 'Aceptar' ),
        loadingContent: this.ifBoolEmptySet( config.data.loadingContent, true ),
        loadingList: config!.data!.loadingList,
        showCloseOnError: config!.data!.showCloseOnError,
        showConfirmOnSuccess: config!.data!.showConfirmOnSuccess,
        startSending: config.data.startSending
      }
    };
    return mod.open( ModalProcessingComponent, conf );
  }

  public static ifEmptySet( value: string, def: string = '' ): string {
    return this.isEmpty( value ) ? def : value;
  }

  public static ifBoolEmptySet( value: boolean | undefined | null, def: boolean = false ): boolean {
    return value === undefined || value === null ? def : value;
  }

  public static urlEncodedBody( data: { [ item: string ]: any } ): string {
    const result: string[] = [];

    for ( let [ key, value ] of Object.entries( data ) ) {
      result.push( `${ encodeURIComponent( key ) }=${ encodeURIComponent( value ) }` );
    }

    return result.join( '&' );
  }
}
