import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { JwtModel } from '../../models/jwt.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Subject, never } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { Uikit } from '../../include/uikit';
import { ViewUtil } from '../../include/view.util';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { ID_USER_LIST_OFFICE } from "../../include/constants";
/* Services */
import { OfficeListService } from '../../services/office-list.service';
/* Models */
import { DialogListModel } from '../../models/dialog.model';

@Component({
  selector: 'app-user-list-by-office',
  templateUrl: './user-list-by-office.component.html',
  styleUrls: ['./user-list-by-office.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class UserListByOfficeComponent implements OnInit, OnDestroy {

  [x: string]: any;
  dtOptions: any;
  public id!: string | null;
  public loading: boolean = true;
  public dataSource: OfficeListElement[] = [];
  public columnsToDisplay: string[] = ['oficina', 'subsegmento', 'zona', 'cargo', 'nombre', 'correo', 'action'];
  public expandedElement?: OfficeListElement | null;
  public determ: boolean = false;
  public dataSourcePag: any;
  public userPerPage: Subject<number> = new Subject<number>();
  public uPerPage: number = 10;

  @ViewChild(MatPaginator, {static: true }) paginator!: MatPaginator;

  constructor(
    private officeListServices: OfficeListService,
    private router: Router,
    private dialog: MatDialog,
    private route: ActivatedRoute
  ) { 
    this.dataSourcePag = new MatTableDataSource(this.dataSource);
    this.userPerPage.next(10);
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get(ID_USER_LIST_OFFICE);

    this.dataSourcePag.paginator = this.paginator;
    this.dataSourcePag.paginator._intl.itemsPerPageLabel = 'Usuarios por página';
    this.getData();
  }

  // When change items per page ============================================================================================================
  public changeIPP(): void {
    this.userPerPage.next(this.uPerPage);
  }

  public setColumnName(value: string): string {
    // @ts-ignore
    return {
      oficina: 'OFICINA',
      subsegmento: 'SUBSEGMENTO',
      zona: 'ZONA',
      cargo: 'CARGO',
      nombre: 'NOMBRE',
      correo: 'CORREO',
      action: ''
    }[ViewUtil.ifEmptySet(value, 'id')].toUpperCase();
  }

  private getData(): void {
    this.loading = true;
    this.officeListServices.getUserListByOfficeActive(this.id!, '6').subscribe(response => {
      console.log("Data recibida al consultar la lista usuarios x oficina: ", response);
      this.loading = false;
      if (response.result.length != undefined)
        response.result.forEach(item => {
          if (item.estadoUsuario === 6){
            this.dataSource.push({
              item: item.item.toString(),
              oficina: item.nroOficina,
              subsegmento: item.subsegmento,
              zona: item.zona,
              cargo: item.cargo,
              nombre: item.nombre,
              correo: item.correo,
              celular: item.celular,
              conmutador: item.conmutador,              
              ext: item.ext,
              ciudad: item.ciudad,
              csc: item.csc,
              estadoUsuario: item.estadoUsuario
            });
            this.dataSourcePag.data = this.dataSource;
            this.determ = true;
          }          
        });
    }, error => {
      this.loading = false;
      console.log("Información del error: ", error);
      if( error.status = 404){
        console.log("Entró al error 404.");
        Uikit.notification().danger(error.error.message || 'La oficina seleccionada no posee usuarios relacionados.');
      }else {
        Uikit.notification().danger(error.error.message || 'Hubo un error inesperado al intentar obtener la lista de usuarios de esta oficinas');
      }      
    });
  }

  public regUser(): void {
    this.router.navigate(['', 'c', 'officelist', 'registration', this.id]);
  }

  /* Al componente "officelist, item" se le envían tres elementos:
     - El item del usuario a consultar.
     - El valor de la acción a realizar (1 consulta, 2 moficiación de datos)
     - El id de la oficina para cuando se realizce el retorno a la actual vista se efecute la consulta por oficina */

  public goToItem(item: OfficeListElement): void {    
    item.item = item.item + "_1_" + this.id;
    this.router.navigate(['', 'c', 'officelist', item.item]); 
  }

  public modifyItem(item: OfficeListElement): void {
    item.item = item.item + "_2_" + this.id;
    this.router.navigate(['', 'c', 'officelist', item.item]); 
  }

  public deleteUser(item: OfficeListElement): void {
    
    const ls: DialogListModel[] = [
      { name: 'Eliminación de un usuario de la oficina', loading: 1 }
    ];
    const isSuccess = { show: false };
    const isError = { show: false };
    const tt = this;
    const loading = ViewUtil.dialog(this.dialog, {
      disableClose: true,
      data: {        
        title: '¿Está seguro de eliminar la información del usuario?',
        description: 'Al confirmar se eliminará la información del usuario en la lista de oficinas.',
        loadingContent: false,
        confirmText: 'Confirmar',
        cancelText: 'Volver',
        showCloseOnError: isError,
        showConfirmOnSuccess: isSuccess,
        loadingList: ls,
        startSending: () => {
          this.deleteUserListOffice(ls, isError, isSuccess, item);
        }
      }
    }).afterClosed().subscribe(result => {
      if (result.showConfirmOnSuccess.show) {
        Swal.fire({
          title: 'Muy bien',
          icon: 'success',
          html: `Usuario eliminado correctamente`,
          showConfirmButton: false,
          timer: 5000
        }).then(result => {
          tt.router.navigate(['', 'c', 'officelist']).then(res => {
            console.log('Redirecting...');   
          });
        });;
      }
    });
  }

  deleteUserListOffice(ls: DialogListModel[], isError: { show: boolean }, isSuccess: { show: boolean }, item: OfficeListElement): void {
    console.log("Usuario a eliminar de la lista de oficinas: ", item);
    let idUser = item.item;

    this.officeListServices.deleteUserOfficeList(idUser).subscribe(response => {
      isSuccess.show = true;
      ls[0] = { name: 'Usuario eliminado de la lista de oficinas.', loading: 2 };
    }, error => {
      isError.show = true;
      ls[0] = { name: error.error.message || 'Hubo un error inesperado al eliminar la información del usuario.', loading: 3 };
    });
  }

  /* Método para filtrar registros en la tabla. */
  applyFilter(filterValue: string) {
    this.dataSourcePag.filter = filterValue.trim().toLowerCase();

    if (this.dataSourcePag.paginator) {
      this.dataSourcePag.paginator.firstPage();
    }
  }

  processReturn(): void {
    this.router.navigate(['', 'c', 'officelist']);
  }

  ngOnDestroy(): void {
    this.userPerPage.complete();
  }
  
}

export interface OfficeListElement {
  item: string;
  oficina: number;
  subsegmento: string;
  zona: string;
  cargo: string;
  nombre: string;
  correo: string;
  celular: number;
  conmutador: string;
  ext: number;
  ciudad: string;
  csc: number;
  estadoUsuario: number;
}
