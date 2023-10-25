import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { LoginComponent } from './components/login/login.component';
import { ConnectionsComponent } from './components/connections/connections.component';
import { SimulatorComponent } from './components/simulator/simulator.component';
import { LiquidationWeeklyComponent } from './components/liquidation-weekly/liquidation-weekly.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { LiquidationDailyComponent } from './components/liquidation-daily/liquidation-daily.component';
import { OfficeListComponent } from './components/office-list/office-list.component';
import { OfficeListRegistrationComponent } from './components/office-list-registration/office-list-registration.component';
import { OfficeListItemComponent } from './components/office-list-item/office-list-item.component';
import { UserListByOfficeComponent } from './components/user-list-by-office/user-list-by-office.component';

const routes: Routes = [ {
  path: '',
  redirectTo: 'c/dashboard',
  pathMatch: 'full'
}, {
  path: 'c',
  component: DashboardComponent,
  data: { title: 'Bienvenido' },
  children: [ {
    path: 'dashboard',
    component: HomeComponent,
    data: { title: 'Bienvenido', id: 'DASHBOARD_HOME' }
  }, {
    path: 'admin/connections',
    component: ConnectionsComponent,
    data: { title: 'Conexiones', id: 'ADMIN_CONNECTIONS' }
  }, {
    path: 'admin/users',
    component: UsersComponent,
    data: { title: 'Usuarios', id: 'ADMIN_USERS' }
  }, {
    path: 'liquidation/simulator',
    component: SimulatorComponent,
    data: { title: 'Simulador', id: 'SIMULATOR' }
  }, {
    path: 'admin/users',
    component: UsersComponent,
    data: { title: 'Usuarios', id: 'DAILY_SETTLEMENT' }
  }, {
    path: 'liquidation/weekly',
    component: LiquidationWeeklyComponent,
    data: { title: 'Usuarios', id: 'WEEKLY_SETTLEMENT' }
  }, {
    path: 'liquidation/daily',
    component: LiquidationDailyComponent,
    data: { title: 'Usuarios', id: 'DAILY_SETTLEMENT' }
  }, {
    path: 'officelist',
    component: OfficeListComponent,
    data: { title: 'Lista de oficinas/usuarios', id: 'OFFICELIST' }
  }, {
    path: 'officelist/userlist/:id',
    component: UserListByOfficeComponent,
    data: { title: 'Lista de usuarios por oficina', id: 'USERLIST_OFFICE' }
  }, {
    path: 'officelist/registration/:id',
    component: OfficeListRegistrationComponent,
    data: { tittle: 'Registro de usuario por oficina', id: 'OFFICELIST_REG' }
  }, {
    path: 'officelist/:id',
    component: OfficeListItemComponent,
    data: { tittle: 'Consulta de usuario por oficina', id: 'OFFICELIST_ITEM' }
  } ]
}, {
  path: 'account/login',
  component: LoginComponent,
  data: { title: 'Iniciar sesión' }
}, {
  path: 'unauthorized',
  component: UnauthorizedComponent,
  data: { title: 'Acceso no autorizado' }
} ];

@NgModule( {
  imports: [ RouterModule.forRoot( routes ) ],
  exports: [ RouterModule ]
} )
export class AppRoutingModule {
}
