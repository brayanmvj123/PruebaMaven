import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsersComponent } from './components/users/users.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ConnectionsComponent } from './components/connections/connections.component';
import { HomeComponent } from './components/home/home.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { environment } from '../environments/environment';
import { UserIdleModule } from 'angular-user-idle';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { NgxMaskModule } from 'ngx-mask';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { LocalStorageService } from './services/local-storage.service';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { BdbDatatableComponent } from './templates/bdb-datatable/bdb-datatable.component';
import { OrderModule } from 'ngx-order-pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatDialogConfigService } from './include/dialog.config';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule  } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatStepperModule } from '@angular/material/stepper';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { CapsLockDirective } from './directives/caps-lock.directive';
import { SimulatorComponent } from './components/simulator/simulator.component';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { ModalSimulationLiquidationCdtComponent } from './templates/modal-simulation-liquidation-cdt/modal-simulation-liquidation-cdt.component';
import { LiquidationWeeklyComponent } from './components/liquidation-weekly/liquidation-weekly.component';
import { LiquidationWeeklyDialogComponent } from './templates/liquidation-weekly-dialog/liquidation-weekly-dialog.component';
import { SidebarComponent } from './templates/sidebar/sidebar.component';
import { MomentDateModule } from '@angular/material-moment-adapter';
import { LiquidationDailyComponent } from './components/liquidation-daily/liquidation-daily.component';
import { ModalAuthprocessComponent } from './templates/modal-authprocess/modal-authprocess.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTableModule } from '@angular/material';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ModalProcessingComponent } from './templates/modal-processing/modal-processing.component';
import { InfoOfficeDialogComponent } from './templates/info-office-dialog/info-office-dialog.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTabsModule} from "@angular/material/tabs";
import { OfficeListComponent } from './components/office-list/office-list.component';
import { OfficeListRegistrationComponent } from './components/office-list-registration/office-list-registration.component';
import { OfficeListItemComponent } from './components/office-list-item/office-list-item.component';
import { UserListByOfficeComponent } from './components/user-list-by-office/user-list-by-office.component'; 

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    LoginComponent,
    DashboardComponent,
    ConnectionsComponent,
    HomeComponent,
    UnauthorizedComponent,
    BdbDatatableComponent,
    CapsLockDirective,
    SimulatorComponent,
    ModalSimulationLiquidationCdtComponent,
    LiquidationWeeklyComponent,
    LiquidationWeeklyDialogComponent,
    SidebarComponent,
    LiquidationDailyComponent,
    ModalAuthprocessComponent,
    ModalProcessingComponent,
    InfoOfficeDialogComponent,
    OfficeListComponent,
    OfficeListRegistrationComponent,
    OfficeListItemComponent,
    UserListByOfficeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    OrderModule,
    NgxPaginationModule,
    NgScrollbarModule,
    NgxMyDatePickerModule.forRoot(),
    NgxMaskModule.forRoot(),
    SweetAlert2Module.forRoot(),
    UserIdleModule.forRoot({idle: 5, timeout: environment.expiration_token * 60, ping: 3}),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatSlideToggleModule,
    MatStepperModule,
    MatDatepickerModule,
    MatRippleModule,
    MomentDateModule,
    MatExpansionModule,
    MatTableModule,
    MatPaginatorModule,
    MatSelectModule,
    MatMenuModule,
    MatCardModule,
    MatBottomSheetModule,
    MatInputModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatCheckboxModule,
    MatTabsModule
  ],
  entryComponents: [
    ModalProcessingComponent, ModalSimulationLiquidationCdtComponent, LiquidationWeeklyDialogComponent,ModalAuthprocessComponent, InfoOfficeDialogComponent
  ],
  providers: [CookieService, LocalStorageService, MatDialogConfigService,
    MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
