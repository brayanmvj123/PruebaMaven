import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { JwtModel } from '../../models/jwt.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Subject, never } from 'rxjs';
import { Router } from '@angular/router';
import { Uikit } from '../../include/uikit';
import { ViewUtil } from '../../include/view.util';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
/* Services */
import { OfficeListService } from '../../services/office-list.service';

@Component({
  selector: 'app-office-list',
  templateUrl: './office-list.component.html',
  styleUrls: ['./office-list.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class OfficeListComponent implements OnInit, OnDestroy {

  [x: string]: any;
  dtOptions: any;
  public loading: boolean = true;
  public dataSource: OfficeListElement[] = [];
  public columnsToDisplay: string[] = ['oficina', 'descripOficina', 'oplDescTipoOficina'];
  public expandedElement?: OfficeListElement | null;
  public determ: boolean = false;
  public dataSourcePag: any;
  public userPerPage: Subject<number> = new Subject<number>();
  public uPerPage: number = 10;

  @ViewChild(MatPaginator, {static: true }) paginator!: MatPaginator;

  constructor(
    private officeListServices: OfficeListService,
    private router: Router,
    private dialog: MatDialog
  ) { 
    this.dataSourcePag = new MatTableDataSource(this.dataSource);
    this.userPerPage.next(10);
  }

  ngOnInit() {
    this.dataSourcePag.paginator = this.paginator;
    this.dataSourcePag.paginator._intl.itemsPerPageLabel = 'Oficinas por página';
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
      descripOficina: 'NOMBRE DE OFICINA',
      oplDescTipoOficina: 'TIPO DE OFICINA'
    }[ViewUtil.ifEmptySet(value, 'id')].toUpperCase();
  }

  private getData(): void {
    this.loading = true;
    /* Estado de oficina a consultar: 4 - Abierta */
    const statusOffice: string = "4";
    this.officeListServices.getOfficeList(statusOffice).subscribe(response => {
      console.log("Data recibida al consultar la lista de oficinas, state/:id -> ", statusOffice, ": ", response);
      this.loading = false;

      if (response.result.length != undefined)
        response.result.forEach(item => {
            this.dataSource.push({
              oficina: item.nroOficina,
              descripOficina: item.descOficina,
              tipOficin: item.tipoficina.tipOficina,
              oplDescTipoOficina: item.tipoficina.descOficina
            });
            this.dataSourcePag.data = this.dataSource;
            this.determ = true;
        });
    }, error => {
      this.loading = false;
      Uikit.notification().danger(error.error.message || 'Hubo un error inesperado al intentar obtener la lista de oficinas');
    });
  }

  public getListUser(item: OfficeListElement): void {
    console.log("oficina a consultar: ", item.oficina);
    this.router.navigate(['', 'c', 'officelist', 'userlist', item.oficina]); 
  }

  /* Método para filtrar registros en la tabla. */
  applyFilter(filterValue: string) {
    this.dataSourcePag.filter = filterValue.trim().toLowerCase();

    if (this.dataSourcePag.paginator) {
      this.dataSourcePag.paginator.firstPage();
    }
  }

  ngOnDestroy(): void {
    this.userPerPage.complete();
  }
  
}

export interface OfficeListElement {
  oficina: number;
  descripOficina: string;
  tipOficin: number;
  oplDescTipoOficina: string;
}
