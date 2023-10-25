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
import { SidebarModel } from '../app/models/sidebar.model';

export const sidebar: SidebarModel = {
  _main: [ {
    id: 'DASHBOARD_HOME',
    title: 'Inicio',
    routerLink: [ '', 'c', 'dashboard' ],
    icon: [ 'fas', 'home' ],
    submenu: []
  } ],
  _admin: [ {
    id: 'SHOW_DIST',
    title: 'Proyecto distribución',
    routerLink: [ '', 'c', 'project', 'distribution' ],
    icon: [ 'fas', 'file' ],
    submenu: [ {
      id: 'REGISTER_DIST',
      title: 'Registrar distribución',
      routerLink: [ '', 'c', 'project', 'distribution', 'registration' ]
    } ]
  }, {
    id: 'SIMULATOR|DAILY_SETTLEMENT|WEEKLY_SETTLEMENT',
    title: 'Liquidación',
    routerLink: null, // Type null when it has no router link
    icon: [ 'fas', 'money-check-alt' ],
    submenu: [ {
      id: 'SIMULATOR',
      title: 'Simulador',
      routerLink: [ '', 'c', 'liquidation', 'simulator' ]
    }, {
      id: 'DAILY_SETTLEMENT',
      title: 'Liquidación Diaria',
      routerLink: [ '', 'c', 'liquidation', 'daily' ],
    }, {
      id: 'WEEKLY_SETTLEMENT',
      title: 'Liquidación Semanal',
      routerLink: [ '', 'c', 'liquidation', 'weekly' ]
    } ]
  }, {
    id: 'OFFICELIST',
    title: 'Lista de oficinas/usuarios',
    routerLink: [ '', 'c', 'officelist' ],
    icon: [ 'fas', 'list' ],
    submenu: []
  }, {
    id: 'ADMIN_CONNECTIONS|ADMIN_USERS',
    title: 'Administrador',
    routerLink: null, // Type null when it has no router link
    icon: [ 'fas', 'users' ],
    submenu: [ {
      id: 'ADMIN_CONNECTIONS',
      title: 'Conexiones',
      routerLink: [ '', 'c', 'admin', 'connections' ]
    }, {
      id: 'ADMIN_USERS',
      title: 'Usuarios',
      routerLink: [ '', 'c', 'admin', 'users' ]
    } ]
  },  ]
};
