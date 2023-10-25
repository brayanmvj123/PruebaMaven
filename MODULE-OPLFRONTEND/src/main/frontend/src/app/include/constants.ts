/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * ACCIONESBF-FRONT was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */

// Storage for session data
export const SESSION_DATA: string = 'bb_session';
export const ROLES_DATA: string = 'bb_roles';
export const DOMAINS_DATA: string = 'bb_domains';
export const IS_MOBILE: string = 'bbsw';
export const WIME_DATA: string = 'wime';
export const UPS_ERROR: string = 'Ups! Los sentimos hubo un error inesperado.';
export const ID_USER_LIST_OFFICE: string = 'id';

export interface ListGeneric {
    code: number;
    name: string;
    extra1?: string;
  }

  export const IDS_CRM: ListGeneric[] = [
    { code: 1, name: 'C'},
    { code: 2, name: 'E'},
    { code: 3, name: 'I'},
    { code: 4, name: 'L'},
    { code: 5, name: 'N'},
    { code: 6, name: 'T'},
    { code: 7, name: 'P'},
    { code: 8, name: 'R'},
    { code: 9, name: 'Z'},
    { code: 10, name: 'U'},
    { code: 11, name: '9'}
  ];

  export const TYPES_BASE: ListGeneric[] = [
    { code: 1, name: '365'},
    { code: 2, name: '360'},
    { code: 3, name: 'Real'}
  ];

  export const TYPES_PERIOD: ListGeneric[] = [
    { code: 0, name: 'Al plazo'},
    { code: 1, name: 'Mensual'},
    { code: 2, name: 'Bimestral'},
    { code: 3, name: 'Trimestral'},
    { code: 4, name: 'Cuatrimestral'},
    { code: 5, name: 'Cada 5 meses'},
    { code: 6, name: 'Semestral'},
    { code: 7, name: 'Cada 7 meses'},
    { code: 8, name: 'Cada 8 meses'},
    { code: 10, name: 'Cada 10 meses'},
    { code: 12, name: 'Anual'},
    { code: 18, name: 'Cada 18 meses'},
    { code: 24, name: 'Cada 2 años'},
    { code: 30, name: 'Cada 30 meses (2 1/2 años)'},
    { code: 36, name: 'Cada 3 años'},
    { code: 42, name: 'Cada 42 meses (3 1/2 años)'},
    { code: 48, name: 'Cada 4 años'},
    { code: 60, name: 'Cada 5 años'},
    { code: 99, name: 'Sin periocidad'}
  ];

  export const TYPES_TASA: ListGeneric[] = [
    { code: 1, name: 'Fija'},
    { code: 2, name: 'DTF'},
    { code: 3, name: 'IPC'},
    { code: 6, name: 'IBR Un mes'},
    { code: 7, name: 'IBR Overnight'},
    { code: 8, name: 'IBR Tres meses'},
    { code: 9, name: 'IBR Seis meses'}
  ];

