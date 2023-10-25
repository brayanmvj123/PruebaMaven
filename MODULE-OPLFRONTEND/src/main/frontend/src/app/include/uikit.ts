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
import { Notification } from './notification';

declare var UIkit: any;

export class Uikit {

  /**
   * UIKit Notifications
   * @see https://getuikit.com/docs/notification
   */
  public static notification(): Notification {
    return new Notification();
  }

  /**
   * UIkit Modal
   * @see https://getuikit.com/docs/modal
   * @param selector element selector #demo
   * @param options options
   */
  public static modal( selector: string, options: { bgClose?: boolean, keyboard?: boolean } = {} ): Modal {
    return new Modal( selector, options );
  }

  /**
   * UIkit offcanvas
   * @see https://getuikit.com/docs/offcanvas
   * @param element native element
   * @param options options {}
   */
  public static offcanvas( element: string, options: {} = {} ): OffCanvas {
    return new OffCanvas( element, options );
  }
}

class OffCanvas {
  constructor( private element: string, private options: {} ) {
  }

  public show(): void {
    // noinspection TypeScriptValidateJSTypes
    if ( UIkit.modal( this.element, this.options ) !== undefined ) {
      UIkit.offcanvas( this.element, this.options ).show();
    }
  }

  public hide(): void {
    // noinspection TypeScriptValidateJSTypes
    if ( UIkit.modal( this.element, this.options ) !== undefined ) {
      UIkit.offcanvas( this.element, this.options ).hide();
    }
  }
}

class Modal {
  constructor( private selector: string, private options: {} ) {
  }

  public show(): void {
    // noinspection TypeScriptValidateJSTypes
    if ( UIkit.modal( this.selector, this.options ) !== undefined ) {
      UIkit.modal( this.selector, this.options ).show();
    }
  }

  public hide(): void {
    // noinspection TypeScriptValidateJSTypes
    if ( UIkit.modal( this.selector, this.options ) !== undefined ) {
      UIkit.modal( this.selector, this.options ).hide();
    }
  }
}