import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NetworkUserModel } from '../../models/network.user.model';
import { AuthService } from '../../services/auth.service';
import { LocalStorageService } from '../../services/local-storage.service';
import Swal from 'sweetalert2';
import { Uikit } from '../../include/uikit';
import { JwtModel } from '../../models/jwt.model';
import { Jwt } from '../../include/jwt';
import { DatatableItem } from '../../models/datatable.item';
import { BdbDatatableOptions } from '../../templates/bdb-datatable/bdb-datatable.component';
import { FileService } from '../../services/file.service';
import { ExcelModel } from '../../models/excel.model';
import * as Moment from 'moment';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2/lib/swal.component';
import { Subject } from 'rxjs';
import { log } from 'util';
import * as moment from 'moment';
import * as $ from 'jquery';

@Component( {
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: [ './users.component.scss' ]
} )
export class UsersComponent implements OnInit, OnDestroy {

  public nuser: string = '';
  public loading = false;
  public tableLoading = false;
  public firstSearched = false;
  public nUsers: NetworkUserModel[] = [];
  public dataUsers: {}[] = [];
  public xlsxUserCells: any[] = [];
  public allUsersHeaders: DatatableItem[] = [];
  public errorMessage = '';
  public hasError = false;
  public userData: JwtModel;
  public filePassword: string = '';
  public userPerPage: Subject<number> = new Subject<number>();
  public uPerPage: number = 10;
  public nextinput: number;
  public searched: boolean = false;
  public eyeOpened: boolean = false;
  public prevSearch: string = '';
  public selectedUser: NetworkUserModel = new NetworkUserModel();
  public userListOptions: BdbDatatableOptions = {
    date_format: 'dd/MM/yyyy HH:mm',
    status_validation: [ {
      status: '0',
      name: 'Permanente',
      color: 'rgba(30,167,93,.65)'
    }, {
      status: '1',
      name: 'Activo',
      color: 'rgba(23,100,255,.65)'
    }, {
      status: '2',
      name: 'Inactivo',
      color: 'rgba(255,22,39,.65)'
    } ],
  };

  @ViewChild( 'downloadXlsxLink', { static: false } ) private downloadXlsxLink: ElementRef;
  @ViewChild( 'searchBanner', { static: false } ) private searchBanner: ElementRef;

  constructor(
    private authService: AuthService,
    private storage: LocalStorageService,
    private download: FileService
  ) {
    this.userPerPage.next( 10 );
  }

  ngOnInit() {

    // Set user data
    this.userData = Jwt.toObject( this.storage.get( 'bb_session' ) );

    // Get all users
    this.getAllUsers();

    // All Users headers
    this.allUsersHeaders = [ {
      type: 'text',
      title: 'nombre'
    }, {
      type: 'text',
      title: 'usuario'
    }, {
      type: 'text',
      title: 'identificación'
    }, {
      type: 'date',
      title: 'última conexión'
    }, {
      type: 'status',
      title: 'estado'
    } ];
  }

  // When change items per page ============================================================================================================
  public changeIPP(): void {
    this.userPerPage.next( this.uPerPage );
  }

  // User selection ========================================================================================================================
  public changeUserSelection( val: { index: number, item: {} } ): void {
    this.selectedUser = new NetworkUserModel();

    // Initialize the fields to visually add the roles that the selected user has ==========================================================
    this.initializeRoleField();

    this.selectedUser.nombres = val.item[0];
    $("#nombreusu").val(val.item[0]);

    this.selectedUser.usuario = val.item[1];
    $("#usuario").val(val.item[1]);

    this.selectedUser.identificacion = val.item[2];
    $("#identificacion").val(val.item[2]);

    this.selectedUser.fecha_conexion = val.item[3];
    $("#fechacon").val(moment(val.item[3]).format('DD/MM/YYYY HH:mm'));

    this.selectedUser.estado = val.item[4];
    $("#estado").val(this.stateCodeToVal(val.item[4]));

    this.selectedUser.usuarioXroles = [];
    let roles: { rol: { grupoDa: string } }[] = [];

    for (let item of this.xlsxUserCells) {
      if (item[2] === this.selectedUser.usuario) {
        item[6].split(", ").forEach(e => {
          if (e !== '') {
            roles.push({ rol: { grupoDa: e } });
            
            this.nextinput++;
            var rolUser = '<input type="text" size="20" id="rolAsociado' + this.nextinput + '"&nbsp; name="rolAsociado' + this.nextinput + '"&nbsp;  value="' + e + '"&nbsp; disabled>';
            $("#roleGroup").append(rolUser);

            // Apply the style for the role field associated with the user
            this.roleFieldStyle(this.nextinput);

          }
        })
      }
    }
    this.selectedUser.usuarioXroles = roles;
  }

  // Search network user ===================================================================================================================
  public searchNetworkUser(): void {
    this.loading = true;
    this.searched = this.nuser !== '';
    this.firstSearched = true;
    this.prevSearch = this.nuser;
    this.authService.searchNetworkUser( this.storage.get( 'bb_session' ), this.nuser ).subscribe( response => {
      this.nUsers = response.result;
      this.loading = false;
      this.hasError = false;
    }, error => {
      this.loading = false;
      this.hasError = true;
      this.errorMessage = error.error.message || error.message;
    } );
  }

  // Erase search criteria =================================================================================================================
  public eraseSearch(): void {
    this.searched = false;
    this.nuser = '';
    this.nUsers = [];
    this.prevSearch = '';
    this.searchBanner.nativeElement.focus();
  }

  // Get all logged users ==================================================================================================================
  public getAllUsers(): void {
    this.tableLoading = true;
    this.dataUsers = [];
    this.xlsxUserCells = [];
    this.authService.getAllUsers( this.storage.get( 'bb_session' ) ).subscribe( response => {
      this.tableLoading = false;
      response.result.forEach( item => {

        // To show in table
        this.dataUsers.push( Object.assign( [
          `${ item.nombres } ${ item.apellidos }`,
          item.usuario,
          item.identificacion,
          item.fecha_conexion,
          item.estado
        ] ) );

        // To add to xlsx file
        item.usuarioXroles.forEach( i => {
          this.xlsxUserCells.push( [
            item.nombres,
            item.apellidos,
            item.usuario,
            item.identificacion,
            item.fecha_conexion,
            this.stateCodeToVal( item.estado ),
            i.rol.grupoDa
          ] );
        } );
      } );
    }, error => {
      this.tableLoading = false;
      Uikit.notification().danger( error.error.message || error.message );
    } );
  }

  // Get state to public person ============================================================================================================
  public stateCodeToVal( code: string ): string {
    switch ( code ) {
      case '0':
        return 'PERMANENTE';
      case '1':
        return 'ACTIVO';
      case '2':
        return 'INACTIVO';
    }
  }

  // Disable user ==========================================================================================================================
  public doDisableUser( nuser: NetworkUserModel ): void {
    log( 'Disabling user...' );
    this.authService.enableOrDisaleUser( this.storage.get( 'bb_session' ), this.nuser, 'disable' ).subscribe( response => {
      Swal.fire( {
        title: 'Deshabilitado!',
        text: `El usuario ${ nuser.nombres } fue deshabilitado exitosamente.`,
        icon: 'success'
      } ).then( () => {
        // Change user state to UNABLE
        this.nUsers[ this.nUsers.indexOf( nuser ) ].estado = '2';

        // Get all users again
        this.getAllUsers();
      } );
    }, error => {
      Uikit.notification().danger( error.error.message || error.message );
    } );
  }

  // Enable user ===========================================================================================================================
  public doEnableUser( nuser: NetworkUserModel ): void {
    log( 'Enabling user...' );
    this.authService.enableOrDisaleUser( this.storage.get( 'bb_session' ), this.nuser, 'enable' ).subscribe( response => {
      Swal.fire( {
        title: 'Habilitado!',
        text: `El usuario ${ nuser.nombres } ha sido habilitado exitosamente.`,
        icon: 'success'
      } ).then( () => {

        // Set user state to ACTIVE
        this.nUsers[ this.nUsers.indexOf( nuser ) ].estado = '1';

        // Get all users again
        this.getAllUsers();
      } );
    }, error => {
      Uikit.notification().danger( error.error.message || error.message );
    } );
  }

  // Download excel file ===================================================================================================================
  public async downloadExcel(): Promise<void> {
    this.tableLoading = true;
    let data: ExcelModel = new ExcelModel();
    data.title = 'Reporte de Usuarios OPALO Desmaterializado';
    data.author = this.userData.username;
    data.password = this.filePassword;
    data.headers = [ 'Nombres', 'Apellidos', 'Usuario', 'Identificación', 'Última conexión', 'Estado', 'Rol' ];
    data.cells = this.xlsxUserCells;

    const blob = await this.download.downloadExcel( data );
    const url = window.URL.createObjectURL( blob );

    const link = this.downloadXlsxLink.nativeElement;
    link.href = url;
    link.download = `reporte-de-usuario-opalo.${ Moment().format( 'YYYYMMDD' ) }.xlsx`;
    link.click();

    setTimeout( () => {
      this.filePassword = '';
      this.tableLoading = false;
      this.eyeOpened = false;
      Uikit.modal( '#password-modal' ).hide();
    }, 1500 );

    window.URL.revokeObjectURL( url );
  }

  public openPasswordDialog(): void {
    Uikit.modal( '#password-modal' ).show();
  }

  public validateModal( user: NetworkUserModel, { enableUser, disableUser, notAllowed, notAllowedPermanent } ): SwalComponent {
    if ( user.usuario === this.userData.username ) {
      return notAllowed;
    } else if ( user.estado === '2' ) {
      return enableUser;
    } else if ( user.estado === '0' ) {
      return notAllowedPermanent;
    } else {
      return disableUser;
    }
  }

  public eye(): void {
    this.eyeOpened = !this.eyeOpened;
  }

  public initializeRoleField(): void {
    $('#roleGroup').empty();
    this.nextinput = 0;
    var labelRol = '<label>Roles:</label>';
    $("#roleGroup").append(labelRol);
  }

  public roleFieldStyle(numberInput: number): void {
    $("#rolAsociado" + numberInput).css({
      "border": "0",
      "background-color": "white",
      "size": "10",
      "width": "250px",
      "font-family": "Roboto, 'Helvetica Neue', sans-serif",
      "font-size": "13px"
    });
  }

  ngOnDestroy(): void {
    this.userPerPage.complete();
  }
}
