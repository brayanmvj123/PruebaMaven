/**
 * Copyright (c) 2021 Banco de Bogotá. All Rights Reserved.
 * <p>
 * MODULE-ACCFRONTEND was developed by Core Banking BDB.
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
 export class DialogModel {
    public width?: string = '600px';
    public disableClose?: boolean = true;
    public data?: {
      title?: string,
      description?: string,
      icon?: 'question',
      cancelText?: string,
      loadingContent?: boolean,
      okText?: string,
      confirmText?: string,
      showCloseOnError: { show: boolean },
      showConfirmOnSuccess: { show: boolean },
      closeText?: string,
      loadingList: DialogListModel[];
      startSending?: () => void
    };
  }
  
  export class DialogListModel {
    public name!: string;
    public loading!: 0 | 1 | 2 | 3 | 4;
  }
  