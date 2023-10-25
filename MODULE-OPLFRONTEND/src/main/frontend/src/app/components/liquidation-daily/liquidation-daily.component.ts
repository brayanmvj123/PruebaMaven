import { Component, ElementRef, OnInit, OnDestroy, ViewChild, AfterViewInit } from '@angular/core';
import { log } from 'util';
import Swal from 'sweetalert2';
import { Uikit } from '../../include/uikit';
import { Jwt } from '../../include/jwt';
import * as Moment from 'moment';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2/lib/swal.component';
import { Subject, never } from 'rxjs';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {MatTableModule} from '@angular/material/table';
import {formatCurrency, getCurrencySymbol} from '@angular/common';
import { VERSION, MatDialogRef, MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Router } from "@angular/router";
import { ViewUtil } from '../../include/view.util';
import * as moment from 'moment';
import * as $ from 'jquery';
import { LiquidationWeeklyDialogComponent } from '../../templates/liquidation-weekly-dialog/liquidation-weekly-dialog.component';
import { IS_MOBILE, IDS_CRM, ListGeneric, TYPES_BASE, TYPES_PERIOD, TYPES_TASA } from '../../include/constants';
// Models
import { NetworkUserModel } from '../../models/network.user.model';
import { JwtModel } from '../../models/jwt.model';
import { DialogListModel } from '../../models/dialog.model';
import { ExcelModel } from '../../models/excel.model';
// Services
import { AuthService } from '../../services/auth.service';
import { SimulationLiquidationService } from '../../services/simulation-liquidation.service';
import { LocalStorageService } from '../../services/local-storage.service';
import { FileService } from '../../services/file.service';

@Component({
  selector: 'app-liquidation-daily',
  templateUrl: './liquidation-daily.component.html',
  styleUrls: ['./liquidation-daily.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class LiquidationDailyComponent implements OnInit, OnDestroy {

  [x: string]: any;
  dtOptions: any;
  public nuser: string = '';
  public loading = false;
  public tableLoading = false;
  public determ: boolean = false;
  public nUsers: NetworkUserModel[] = [];
  public xlsxUserCells: any[] = [];
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
  public isMobileVersion: boolean;
  public valConciliacion: string;
  public valDepositante: string;
  // Table
  public dataSource: LiquidationDailyElement[] = [];
  public columnsToDisplay: string[] = ['factorOpl', 'factorDcvsa', 'codIsin', 'ctaInv','numCdt', 'intBruto', 'rteFte', 'intNeto', 'capPg', 'totalPagar', 'descPosicion', 'depositante', 'valConciliacion'];
  public expandedElement?: LiquidationDailyElement | null;
  public dataSourcePag: any;

  public enableSentConci: boolean = true;
  public blockConcili: boolean = false;
  public flag: number = 0;
  public selectedUser: NetworkUserModel = new NetworkUserModel();
  public userListOptions: any[] = [
    { status: 'Conciliado', name: 'Conciliado', color: 'rgba(30,167,93,.65)'},
    { status: 'Diferencia', name: 'Diferencia', color: 'rgba(255,22,39,.65)'},
    { status: 'MarcadoOPL', name: 'Opalo', color: '#fdc130'},
    { status: 'MarcadoDCV', name: 'Deceval', color: '#fdc130'},
  ];
  public idenfityTypes: ListGeneric[] = IDS_CRM;
  public baseTypes: ListGeneric[] = TYPES_BASE;
  public periodTypes: ListGeneric[] = TYPES_PERIOD;
  public tasaTypes: ListGeneric[] = TYPES_TASA;

  @ViewChild(MatPaginator, {static: true }) paginator!: MatPaginator;
  @ViewChild('downloadXlsxLink', { static: false }) private downloadXlsxLink: ElementRef;
  @ViewChild('searchBanner', { static: false }) private searchBanner: ElementRef;

  constructor(
    private authService: AuthService,
    private liquidationService: SimulationLiquidationService,
    private storage: LocalStorageService,
    private download: FileService,
    private dialog: MatDialog,
    private router: Router
  ) {
    this.dataSourcePag = new MatTableDataSource(this.dataSource);
    this.userPerPage.next(10);
    this.isMobileVersion = this.storage.get(IS_MOBILE);
  }

  ngOnInit() {
    this.dataSourcePag.paginator = this.paginator;
    this.dataSourcePag.paginator._intl.itemsPerPageLabel = 'Liquidación diaria por página';
    // Set user data
    this.userData = Jwt.toObject(this.storage.get('bb_session'));
    // Get data
    this.getAllUsers();
  }

  // When change items per page ============================================================================================================
  public changeIPP(): void {
    this.userPerPage.next(this.uPerPage);
  }

  public validateStatus(value: any, ret: string = 'color'): string {
    let color = '#fff';
    let name = 'Estado';
    this.userListOptions!.forEach(item => {
      if (item.status === value) {
        color = item.color;
        name = item.name;
      }
    });
    switch (ret) {
      default:
      case 'color':
        return color;
      case 'name':
        return name;
    }
  }

  public setColumnName(value: string): string {
    // @ts-ignore
    return {
      factorOpl: 'FACTOR OPL',
      factorDcvsa: 'FACTOR DCVAL' , 
      codIsin: 'ISIN',
      ctaInv: 'CTA INV',
      numCdt: 'CDT',
      intBruto: 'INT BRUTO',
      rteFte: 'RETENCIÓN',
      intNeto: 'INT NETO',
      capPg: 'CAP PAGAR',
      totalPagar: 'TOT PAGAR',
      descPosicion: 'POSICIÓN',
      depositante: 'DEPOSITANTE',
      valConciliacion: 'CONCILIACIÓN'
    }[ViewUtil.ifEmptySet(value, 'item')].toUpperCase();
  }

  // Get all logged users ==================================================================================================================
  public getAllUsers(): void {
    this.tableLoading = true;
    this.xlsxUserCells = [];
    this.liquidationService.getSalPg().subscribe(response => {
      this.tableLoading = false;
      response.result.forEach(item => {
        if(Number.parseInt(item.estado) === 4){
          this.enableSentConci = false;
          this.blockConcili = true;
        }
        /* Recibe el estado de la Conciliación */
        if(item.conciliacion === 'CONCILIADO'){
          this.valConciliacion = "Conciliado";
        } if(item.conciliacion === 'OPALO'){
          this.valConciliacion = "MarcadoOPL";
        } if(item.conciliacion === 'DECEVAL'){
          this.valConciliacion = "MarcadoDCV";
        } if(item.conciliacion === 'NO CONCILIADO'){
          this.valConciliacion = "Diferencia";
          /* No se validan los CDTs que tengan diferencias en el factor siendo de tipo TESORERIA  */
          if(parseFloat(item.tipPosicion) == 1){
            this.validateConsiliation(1);
          }
        }
        /* Resuelve el Depositante */
        if(Number.parseFloat(item.depositante) === 1){
          this.valDepositante = "DEPOS";
        } else{
          this.valDepositante = "NO DEPOS";
        }
        
        this.dataSource.push({
          item: item.item!.toString(),
          factorOpl: (this.ajusteDecimales(item.factorOpl, 6)).toString(),
          factorDcvsa: (this.ajusteDecimales(item.factorDcvsa, 6)).toString(),
          codIsin: item.codIsin,
          ctaInv: item.ctaInv,
          numCdt: item.numCdt,
          intBruto: (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item.intBruto, 0)).toString()!))),
          spread: item.spread,
          rteFte: (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item.rteFte, 0)).toString()!))),
          intNeto: (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item.intNeto, 0)).toString()!))),
          capPg: (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item.capPg, 0)).toString()!))),
          totalPagar: (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item.totalPagar, 0)).toString()!))),
          descPosicion: item.descPosicion,
          depositante: this.valDepositante,
          valConciliacion: this.valConciliacion,
          fechaEmi: item.fechaEmi,
          fechaVen: item.fechaVen,
          fechaProxPg: item.fechaProxPg,
          valorNominal: item.valorNominal,
          tipPlazo: item.tipPlazo,
          plazo: item.plazo,
          tipBase: item.tipBase,
          tipPeriodicidad: item.tipPeriodicidad,
          tipTasa: item.tipTasa,
          tasaEfe: item.tasaEfe,
          tasaNom: item.tasaNom,
          codId: item.codId,
          numTit: item.numTit,
          nomTit: item.nomTit,
          nroOficina: item.nroOficina,
          tipPosicion: item.tipPosicion,
          conciliacion: item.conciliacion,
          estado: item.estado,
          codProd: item.codProd,
          fecha: item.fecha
        });
        this.dataSourcePag.data = this.dataSource;
        this.determ = true;

        /*  Data para el archivo excel */
        this.xlsxUserCells.push([
          item.nroOficina,        //[0]
          this.valDepositante,    //[1]
          item.numCdt,            //[2]
          item.codIsin,           //[3]
          item.ctaInv,            //[4]
          item.codId,             //[5]
          item.numTit,            //[6]
          item.nomTit,            //[7]
          item.fechaEmi,          //[8]
          item.fechaVen,          //[9]
          item.fechaProxPg,       //[10]
          item.valorNominal,      //[11]
          item.tipPlazo,          //[12]
          item.plazo,             //[13]
          item.tipBase,           //[14]
          item.tipPeriodicidad,   //[15]
          item.tipTasa,           //[16]
          item.tasaEfe,           //[17]
          item.tasaNom,           //[18] 
          item.spread,            //[19]
          item.factorOpl,         //[20]
          item.factorDcvsa,       //[21]
          item.intBruto,          //[22]
          item.rteFte,            //[23]
          item.intNeto,           //[24]
          item.capPg,             //[25]
          item.totalPagar,        //[26]
          item.descPosicion,      //[27]
          this.valConciliacion    //[28]
        ]);
      });
    }, error => {
      this.tableLoading = false;
      Uikit.notification().danger(error.error.message || 'Hubo un error inesperado al intentar obtener la lista de los registros de liquidación diaria.');
    });
  }

  // Download excel file ===================================================================================================================
  public async downloadExcel(): Promise<void> {
    this.tableLoading = true;
    let data: ExcelModel = new ExcelModel();
    data.title = 'Reporte de vencimientos Liquidación Diaria';
    data.author = this.userData.username;
    data.password = this.filePassword;
    data.headers = ['Oficina','Depositante', 'Número cdt', 'Código isin', 'Cta Inv', 'Código id', 'Núm Tit', 'Nom Tit','Fecha Emi', 'Fecha Ven', 'Fecha Prox Pago', 'Valor Nom', 'Tip Plazo', 'Plazo', 'Tip Base', 'Tip periodicidad', 'Tip Tasa', 'Tasa Efec', 'Tasa Nom', 'Spread','Factor OPL','Factor Deceval', 'Int Bruto', 'Retención', 'Int Neto', 'Cap Pagar', 'Tot Pagar', 'Posición', 'Conciliación'];
    this.xlsxUserCells.forEach( item =>{
      item[5] = this.getNameTypeId(item[5].toString()); 
      item[11] = (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item[11], 0)).toString()!)));
      item[14] = this.getNameTypeBase(item[14].toString());
      item[15] = this.getNameTypePeriod(item[15].toString());
      item[16] = this.getNameTypeTasa(item[16].toString());    
      item[20] = (this.ajusteDecimales(item[20], 6));
      item[21] = (this.ajusteDecimales(item[21], 6));
      item[22] = (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item[22], 0)).toString()!)));
      item[23] = (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item[23], 0)).toString()!)));
      item[24] = (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item[24], 0)).toString()!)));
      item[25] = (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item[25], 0)).toString()!)));
      item[26] = (new Intl.NumberFormat("en-CO", {maximumFractionDigits: 0, style: "currency", currency: "USD"}).format(Number.parseFloat((this.ajusteDecimales(item[26], 0)).toString()!)));
    });
    data.cells = this.xlsxUserCells;
    console.log("Excel", data.cells);

    try {
      console.log("Data downloadExcel: ", data);
      const blob = await this.download.downloadExcel(data);

      const url = window.URL.createObjectURL(blob);

      const link = this.downloadXlsxLink.nativeElement;
      link.href = url;
      link.download = `Vencimientos_liquidacion_diaria_${Moment().format('YYYYMMDD')}.xlsx`;
      link.click();

      window.URL.revokeObjectURL(url);
    } catch (e) {
      setTimeout(() => Uikit.notification().danger('Ups! Hubo un error inesperado al descargar el archivo.'), 1500);
    } finally {
      setTimeout(() => {
        this.filePassword = '';
        this.tableLoading = false;
        this.eyeOpened = false;
        Uikit.modal('#password-modal').hide();
      }, 1500);
    }
  }

  public openPasswordDialog(): void {
    Uikit.modal('#password-modal').show();
  }

  public eye(): void {
    this.eyeOpened = !this.eyeOpened;
  }

  public initializeRoleField(): void {
    this.nextinput = 0;
  }

  openAlertDialog(val) {
    if ( val.factorDcvsa !== val.factorOpl && val.estado === 0){
      this.xlsxUserCells.forEach( item =>{
        if ( val.numCdt === item[2] && val.codIsin === item[3] && val.ctaInv === item[4]){
          console.log("Elemento seleccionado: ", item);
          const dialogRef = this.dialog.open(LiquidationWeeklyDialogComponent, {
            data: {
              valItem: val.item,
              valCodIsin: item[3],
              valCtaInv: item[4],
              valNumCdt: item[2],
              valfechaemi: item[8],
              valfechaven: item[9],
              valfechaproxp: item[10],
              valtipplazo: item[12],
              valplazo: item[13],
              valtipobase: item[14],
              valtipoper: item[15],
              valtipotasa: item[16],
              valtasaefec: item[17],
              valtasanom: item[18],
              valvalornom: item[11],
              valcodid: item[5],
              valnumtit: item[6],
              valnomtit: item[7],
              valFactorOpl: item[20],
              valFactorDcvl: item[21],
              valretencion: item[23],
              valspread: item[19],
              valFactorSelect: null,
              valInteBruto: item[22],
              // Permite determinar que corresponde a Liq diaria = 1
              valLiquiOption: 1,
              buttonText: {
                cancel: 'Done'
              }
            },
          });
        }
      });
    }
  }

  /* Confirmación para envío de data */
  confirmSend(): void{
    const ls: DialogListModel[] = [
      { name: 'Cerrando conciliación diaria', loading: 1 }
    ];
    const isSuccess = { show: false };
    const isError = { show: false };
    const tt = this;
    const loading = ViewUtil.dialog(this.dialog, {
      disableClose: true,
      data: {
        title: '¿Está seguro de finalizar el proceso de conciliación?',
        description: 'Recuerde que al cerrar este proceso se continuará con la renovación y cancelación de CDTs',
        loadingContent: false,
        showCloseOnError: isError,
        showConfirmOnSuccess: isSuccess,
        loadingList: ls,
        startSending: () => {
          this.closeConsiliation(ls, isError, isSuccess);
        }
      }
    }).afterClosed().subscribe(result => {
      if (result.showConfirmOnSuccess.show) {
        Swal.fire({
          title: 'Muy bien',
          icon: 'success',
          html: `Proceso terminado`,
          showConfirmButton: false,
          timer: 2000
        }).then(result => {
          tt.router.navigate(['', 'c', 'liquidation', 'daily']).then(res => {
              console.log('Redirecting...');
              window.location.reload();
          });
        });;
      }
    });
  }

  closeConsiliation(ls: DialogListModel[], isError: { show: boolean }, isSuccess: { show: boolean }): void {
    console.log("Servicio para generar el archivo plano");
    this.liquidationService.closeConciliation().subscribe(response => {
      console.log("resp", response);
      isSuccess.show = true;
      ls[0] = { name: 'Conciliación cerrada exitosamente.', loading: 2 };
    }, error => {
      isError.show = true;
      ls[0] = { name: error.error.message || 'Hubo un error inesperado al realizar el cierre de la conciliación.', loading: 3 };
    });
  }

  /* Método para habilitar/deshabilitar la opción de cerrar conciliación */
  validateConsiliation(item: number){
    this.flag = this.flag + item;
    if ( this.flag === 0){
      this.enableSentConci = true;
    }else {
      this.enableSentConci = false;
    }
  }

  /* Método para filtrar registros en la tabla. */
  applyFilter(filterValue: string) {
    this.dataSourcePag.filter = filterValue.trim().toLowerCase();

    if (this.dataSourcePag.paginator) {
      this.dataSourcePag.paginator.firstPage();
    }
  }

  /* Metodo para limitar la cantidad de cifras decimales. */
  ajusteDecimales (x:any, posiciones:number ): Number {
    var s = x.toString();
    var decimalLength = s.indexOf('.') + 1;    
    if( decimalLength > 0 ){
      let numDec= ((Number.parseFloat(s)).toFixed(posiciones));
      return Number(numDec);
    }
    else return Number(x);        
  }

  /* Get id type name */
  public getNameTypeId(idType: string): string {
    let nameId = idType;
    this.idenfityTypes.forEach(item => {
      if (item.code.toString() === idType) {
        nameId = item.name;
      }
    });    
    return nameId;    
  }

  /* Get base type name */
  public getNameTypeBase(baseType: string): string{
    let nameBase = baseType;
    this.baseTypes.forEach(element => {
      if (element.code.toString() === baseType){
        nameBase = element.name;
      }
    });
    return nameBase;
  }

  /* Get periodicity type name */
  public getNameTypePeriod(periodType: string): string{
    let namePeriod = periodType;
    this.periodTypes.forEach(element => {
      if (element.code.toString() === periodType){
        namePeriod = element.name;
      }
    });
    return namePeriod;
  }

  /* Get tasa type name */
  public getNameTypeTasa(tasaType: string): string{
    let nameTasa = tasaType;
    this.tasaTypes.forEach(element => {
      if (element.code.toString() === tasaType){
        nameTasa = element.name;
      }
    });
    return nameTasa;
  }

  ngOnDestroy(): void {
    this.userPerPage.complete();
  }

}

export interface LiquidationDailyElement {
  item: string;
  nroOficina: string;
  depositante: string;
  numCdt: string;
  codIsin: string;
  ctaInv: string;
  codId: string;
  numTit: string;
  nomTit: string;
  fechaEmi: string;
  fechaVen: string;
  fechaProxPg: string;
  tipPlazo: string;
  plazo: string;
  tipBase: string;
  tipPeriodicidad: string;
  tipTasa: string;
  tasaEfe: string;
  tasaNom: string;
  valorNominal: string;
  intBruto: string;
  rteFte: string;
  intNeto: string;
  capPg: string;
  totalPagar: string;
  tipPosicion: string;
  factorDcvsa: string;
  valConciliacion: string;
  factorOpl: string;
  spread: string;
  estado: string;
  conciliacion: string;
  descPosicion: string;
  codProd: string;
  fecha: string;
}