import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Uikit} from 'src/app/include/uikit';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import {SimulationFeesModel} from '../../models/simulation.fees.model';
import {OperationParametricTable} from '../../services/operation-parametric-table.service';
import {SimulationLiquidationService} from '../../services/simulation-liquidation.service';
import {ModalSimulationLiquidationCdtComponent} from '../../templates/modal-simulation-liquidation-cdt/modal-simulation-liquidation-cdt.component';
import {MatDialog} from '@angular/material/dialog';
import * as moment from 'moment';
import {CalculateStartDatePeriodModel} from "../../models/calculate.start.date.period.model";
import {GetRatesVariableModel} from "../../models/get.rates.variable.model";
import {ViewEncapsulation} from '@angular/core';
import { environment } from '../../../environments/environment';
import Swal from 'sweetalert2';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import { ViewUtil } from '../../include/view.util';
/* Models */
import { DialogListModel } from '../../models/dialog.model';
import { UpdateLiquidationModel } from '../../models/update.liquidation.model'


// See the Moment.js docs for the meaning of these formats:
// https://momentjs.com/docs/#/displaying/format/
export const MY_FORMATS = {
  parse: {
    dateInput: 'YYYY/MM/DD',
  },
  display: {
    dateInput: 'YYYY/MM/DD',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY'
  },
};
@Component({
  selector: 'app-simulator',
  templateUrl: './simulator.component.html',
  styleUrls: ['./simulator.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class SimulatorComponent implements OnInit {
  public simulador: FormGroup;
  public baseTypeList: {};
  public periodTypeList: {};
  public rateTypeList: {};
  public termTypeList: {};
  public termInDaysCdt: number;
  public opcionSeleccionada: number;
  public selDateAper: string;
  public selDateVen: string;
  public returnLiqui: boolean;
  public valorTasaFija: number;
  public fechaIniPeriod: string;
  public tasaVariable: number;
  public calendario_no_habiles=environment.simulador.calendario_no_habiles;
  public calendarYears: Array<number>=[];
  public notEnabledDays=[];
  date = moment();
  public updateData: boolean = false;
  public factorSelect;
  public factorLq;
  public enableFactor: boolean = false;
  public origenLiquid;
  public percentRete: number;
  public pItem: number;
  public pCtaInv: string;
  public pCodIsin: string;
  public pNumCdt : number;
  public pNumTit : number;
  public fechaProxPago : string;


  constructor(private parametricTableService: OperationParametricTable,
    private simulationService: SimulationLiquidationService,
    private formBuilder: FormBuilder,
    private router: Router,
    private dialog: MatDialog,) {
  }

  ngOnInit() {
    this.simulador = this.formBuilder.group({
      valor_nominal: ['', Validators.required],
      fecha_apertura: ['', Validators.required],
      fecha_vencimiento: ['', Validators.required],
      base: ['', Validators.required],
      periodicidad: ['', Validators.required],
      tipo_plazo: [''],
      plazo: [{ value: '', disabled: true }],
      tipo_tasa: ['', Validators.required],
      tasa_efectiva: ['', Validators.required],
      signo: [''],
      spread: [''],
      retencion: ['', Validators.required],
      tasa_nominal: [{ value: '', disabled: true }],
      capital: [{ value: '', disabled: true }],
      tasa_efectiva_sim: [{ value: '', disabled: true }],
      interes_bruto: [{ value: '', disabled: true }],
      interes_bruto_periodo: [{ value: '', disabled: true }],
      interes_neto: [{ value: '', disabled: true }],
      interes_neto_periodo: [{ value: '', disabled: true }],
      retencion_interes: [{ value: '', disabled: true }],
      retencion_interes_periodo: [{ value: '', disabled: true }],
      factor: [{value: '', disabled: true}],
      total_pagar: [{ value: '', disabled: true }]
    });
    // Get base Type
    this.getBaseType();
    // Get Period Type
    this.getPeriodType();
    // Get Rate Type
    this.getRateType();
    // Get Term Type
    this.getTermType();
    // Get not enabled days (actual year)
    this.getCalendarNotEnabledDays(new Date().getFullYear());
    this.simulador.controls['tipo_plazo'].setValue('2');
    this.returnLiqui = false;
    // validate existing liquidation
    if(history.state.liquidacionExistente){
      this.enableFactor = true;
      const liquidationData=history.state;
      console.log("Datos recibidos: ", liquidationData);
      /* Seteo de fecha Apertura y Vencimiento, con base a la data de Lq */
      this.selDateAper = liquidationData.valfechaemi.replace(/-/g,'/');
      this.selDateVen= liquidationData.valfechaven.replace(/-/g,'/');

      this.fechaProxPago = liquidationData.valfechaproxp.replace(/-/g,'/');
      this.origenLiquid = liquidationData.valLiquiOption;
      this.factorSelect = liquidationData.valFactorSelect;
      if (liquidationData.valFactorSelect === 1){
        this.factorSelect = "Opalo " + liquidationData.valFactorOpl;
        this.factorLq = liquidationData.valFactorOpl;
      }else {
        this.factorSelect = "Deceval " + liquidationData.valFactorDcvl;
        this.factorLq = liquidationData.valFactorDcvl;
      }
      this.pItem = liquidationData.valItem;
      this.pCtaInv = liquidationData.valCtaInv;
      this.pCodIsin = liquidationData.valCodIsin;
      this.pNumCdt = liquidationData.valNumCdt;
      this.pNumTit =liquidationData.valnumtit;

      this.simulador.controls['fecha_apertura'].disable();
      this.simulador.controls['fecha_vencimiento'].disable();
      this.simulador.controls['valor_nominal'].setValue(liquidationData.valvalornom);
      this.simulador.controls['fecha_apertura'].setValue(liquidationData.valfechaemi);
      this.simulador.controls['fecha_vencimiento'].setValue(liquidationData.valfechaven);
      this.simulador.controls['base'].setValue(liquidationData.valtipobase);
      this.simulador.controls['periodicidad'].setValue(liquidationData.valtipoper);
      this.simulador.controls['tipo_plazo'].setValue(liquidationData.valtipplazo);
      this.simulador.controls['plazo'].setValue(liquidationData.valplazo);
      this.simulador.controls['tipo_tasa'].setValue(liquidationData.valtipotasa);
      if (liquidationData.valtipotasa>1){
        let spread:number=liquidationData.valspread;
        spread>0?this.validateTipoTasa(liquidationData.valtipotasa,1,spread,''):this.validateTipoTasa(liquidationData.valtipotasa,2,spread*-1,'')
      }else{
        this.validateTipoTasa(liquidationData.valtipotasa,'','',liquidationData.valtasaefec)
      }
      /* Obtiene el porcentaje de retención a partir de dos datos recibidos de la SALPG. */
      this.percentRete = Number.parseFloat(((Number.parseFloat(liquidationData.valretencion) / Number.parseFloat(liquidationData.valInteBruto))*100).toFixed(2));
      console.log("Porcentaje de Retención: ", (Number.parseFloat(liquidationData.valretencion) / Number.parseFloat(liquidationData.valInteBruto))*100);
      this.simulador.controls['retencion'].setValue(this.percentRete);
    }
  }

  public preventType(form: AbstractControl, event, item: string): void {
    const p = {};
    p[item] = '';
    form.patchValue(p);
  }

  public validatePercent(field: string): void {
    if (!this.percentValidator(Number.parseFloat(this.simulador.get(field).value))) {
      const rr = {};
      rr[field] = '';
      this.simulador.patchValue(rr);
      this.simulador.get(field).markAsTouched();
      Uikit.notification().danger('El valor ingresado como porcentaje no es válido.');
    }
  }

  public percentValidator(value: number): boolean {
    return value <= 100.00;
  }

  getBaseType(): void {
    this.parametricTableService.getBaseType().subscribe(response => {
      this.baseTypeList = response.result;
    }, error => {
      Uikit.notification().danger(error.error.message || error.message);
    });
  }

  getPeriodType(): void {
    this.parametricTableService.getPeriodType().subscribe(response => {
      this.periodTypeList = response.result;
    }, error => {
      Uikit.notification().danger(error.error.message || error.message);
    });
  }

  getRateType(): void {
    this.parametricTableService.getRateType().subscribe(response => {
      this.rateTypeList = response.result;
    }, error => {
      Uikit.notification().danger(error.error.message || error.message);
    });
  }

  getTermType(): void {
    this.parametricTableService.getTermType().subscribe(response => {
      this.termTypeList = response.result;
    }, error => {
      Uikit.notification().danger(error.error.message || error.message);
    });
  }

  getCalendarNotEnabledDays(year:number): void {
    this.parametricTableService.getCalendarNotEnabledDays(year).subscribe(response => {
      this.calendarYears.push(year);
      response.result.forEach(element => {
        this.notEnabledDays.push(element.anno+"/"+element.mes+"/"+element.dia);
      });
    }, error => {
      Uikit.notification().danger(error.error.message || error.message);
    });
  }

  getTermInDaysCdt() {
    let base = this.simulador.get('base').value;
    let fecha_apertura = this.selDateAper;
    let fecha_vencimiento = this.selDateVen;

    console.log("Data a enviar a calculoDiasCDT. Base: ", base, "fecha apert: ", fecha_apertura, "fecha venci: ",fecha_vencimiento);

    this.parametricTableService.termDaysCdt(base, fecha_apertura, fecha_vencimiento).subscribe(response => {
      this.termInDaysCdt = response.result.plazoenDias;
      this.simulador.controls['plazo'].setValue(this.termInDaysCdt);
      this.periodTypeList = response.result.periodicidad;
      this.simulador.controls['periodicidad'].setValue(this.periodTypeList);
      this.simulador.controls['periodicidad'].setValue('');
    }, err => {
      Uikit.notification().danger(err.error.message || err.messagFe);
    });
  }

  getRateVariable(): void {

    if( !this.enableFactor ){
      this.fechaProxPago = this.selDateVen;
    }

    let parameterStartPeriod: CalculateStartDatePeriodModel = new CalculateStartDatePeriodModel();
    parameterStartPeriod.canal = "OPL766";
    parameterStartPeriod.parametros = {
      base : this.simulador.get('base').value,
      plazo : this.simulador.get('plazo').value,
      fechaVencimiento : this.selDateVen,
      fechaProxPago : this.fechaProxPago,
      periodicidad : Number.parseInt(this.simulador.get('periodicidad').value),
      tipoPlazo : this.simulador.get('tipo_plazo').value
    }
    console.log("data a enviar calculoFechaInicioPeriodo", parameterStartPeriod);

    this.simulationService.getStartDatePeriod(parameterStartPeriod).subscribe(response => {
      if(response.status.code === 200){
        this.fechaIniPeriod = response.result.fechaInicio;
        let parameterRates: GetRatesVariableModel = new GetRatesVariableModel();
        parameterRates.canal = "OPL766";
        parameterRates.parametros = {
          fecha: response.result.fechaInicio.replace('-','/').replace('-','/'),
          tipotasa: this.simulador.get('tipo_tasa').value
        }
        this.simulationService.getRateVariable(parameterRates).subscribe(res => {
          this.tasaVariable = res.result.valor;
          this.simuladorCuota();
        }, error => {
          Uikit.notification().danger(error.error.message || error.messagFe);
        });
      }else {
        Uikit.notification().danger("Se presenta error en el consumo de los servicios: Obtener fecha inicio de periodo " +
          "o Obtener la tasa.");
      }
    }, err => {
      Uikit.notification().danger(err.error.message || err.messagFe);
    });
  }

  serviceTermInDaysCdt() {
    if (this.simulador.get('base').value !== '' && this.simulador.get('fecha_apertura').value !== '' && this.simulador.get('fecha_vencimiento').value !== '') {
      this.getTermInDaysCdt();
    }
  }

  serviceGetRate(): void{
    if (this.simulador.get('base').value !== '' && this.simulador.get('tipo_plazo').value !== '' &&
      this.simulador.get('fecha_vencimiento').value !== '' && this.simulador.get('plazo').value !== ''
      && this.simulador.get('tipo_tasa').value !== '') {
      this.getRateVariable();
    }
  }

  onChange(valor) {
    this.validateTipoTasa(valor,'','','')
  }

  validateTipoTasa(valor,signo,spread,tasa_efectiva){
    this.opcionSeleccionada = valor;
    if (this.opcionSeleccionada > 1) {
      this.simulador.controls['signo'].setValidators([Validators.required]);
      this.simulador.controls['signo'].setValue(signo);
      this.simulador.controls['spread'].setValidators([Validators.required]);
      this.simulador.controls['spread'].setValue(spread);
      this.simulador.controls['tasa_efectiva'].clearValidators();
      this.simulador.controls["tasa_efectiva"].updateValueAndValidity();
      this.simulador.controls["tasa_efectiva"].setErrors(null);
    }
    else {
      this.simulador.controls['tasa_efectiva'].setValidators([Validators.required]);
      this.simulador.controls['tasa_efectiva'].setValue(tasa_efectiva);
      this.simulador.controls['signo'].clearValidators();
      this.simulador.controls["signo"].updateValueAndValidity();
      this.simulador.controls["signo"].setErrors(null);
      this.simulador.controls['spread'].clearValidators();
      this.simulador.controls["spread"].updateValueAndValidity();
      this.simulador.controls["spread"].setErrors(null);
    }
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date = moment(event.value);
    const cellDate:Date=moment(event.value).toDate();
    const weekday:number =cellDate.getDay();
    //días no habiles por defecto, semanales
    if (this.calendario_no_habiles.semana.includes(weekday) || this.notEnabledDays.includes(cellDate.getFullYear()+"/"+(cellDate.getMonth()+1)+"/"+cellDate.getDate())){
      const responseDate=cellDate.getFullYear()+"/"+(cellDate.getMonth()+1)+"/"+cellDate.getDate();
      //dias no habiles especificos, festivos
      Swal.fire({
        title: '¿Desea continuar?',
        text: "El día  _date_ es un día no habil.".replace("_date_",responseDate),
        icon: 'warning',
        confirmButtonColor: '#0f44a2',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Continuar',
        cancelButtonText: 'Cancelar',
        showCancelButton: true,
      }).then((result) => {
        if (result.value) {
          if (type == "changeAper") {
            this.selDateAper = this.date.format('YYYY') + "/" + this.date.format('MM') + "/" + this.date.format('DD');
          }else {
            this.selDateVen = this.date.format('YYYY') + "/" + this.date.format('MM') + "/" + this.date.format('DD');
          }
          this.serviceTermInDaysCdt();
        }else{
          if(type=="changeAper"){
            this.selDateAper = '';
            this.simulador.controls['fecha_apertura'].setValue('');
          }else{
            this.selDateVen = '';
            this.simulador.controls['fecha_vencimiento'].setValue('');
          }
        }
      });
    }else{
      if (type == "changeAper") {
        this.selDateAper = this.date.format('YYYY') + "/" + this.date.format('MM') + "/" + this.date.format('DD');
      }else {
        this.selDateVen = this.date.format('YYYY') + "/" + this.date.format('MM') + "/" + this.date.format('DD');
      }
      this.serviceTermInDaysCdt();
    }
  }

  /**
   * se verificar si el día es no hábil para cambiar el estilo
   * @param dateAnalisis
   * @returns string estilo del campo del día
   */
  selectedDateClass = (dateAnalisis) => {
    const cellDate=dateAnalisis.toDate();
    const day:number = cellDate.getDate();
    const month:number = cellDate.getMonth();
    const year:number = cellDate.getFullYear();
    const weekday:number = cellDate.getDay();
    if (!this.calendarYears.includes(year)){
      this.getCalendarNotEnabledDays(year);
    }
    //días no habiles por defecto, semanales
    if (this.calendario_no_habiles.semana.includes(weekday)){
      return 'not-enable-date';
    }
    //días no habiles especificos, festivos
    if(this.notEnabledDays.includes(year+"/"+(month+1)+"/"+day)){
      return 'not-enable-date';
    }
    return '';
  };

  validateTipTasa(): boolean{
    return this.opcionSeleccionada > 1;
  }

  simuladorCuota(){
    console.log("entró a simular cuota");
    let valTotPg: number;
    const sim: SimulationFeesModel = new SimulationFeesModel();
    sim.canal = "BDB";
    sim.parametros = {
      base: this.simulador.get('base').value,
      capital: this.simulador.get('valor_nominal').value,
      diasPlazo: this.simulador.get('plazo').value,
      periodicidad: this.simulador.get('periodicidad').value,
      retencion: this.simulador.get('retencion').value,
      tasa: this.validateTipTasa() ? this.tasaVariable : this.simulador.get('tasa_efectiva').value ,
      tipoPlazo: this.simulador.get('tipo_plazo').value,
      tipoTasa: this.simulador.get('tipo_tasa').value,
      spread: this.validateTipTasa() ? this.simulador.get('spread').value : 0
    };

    console.log("data a enviar a simulacionCuota: ", sim);
    this.simulationService.simulationFeesCdt(sim).subscribe(response => {

      console.log("Respuesta de simulacionCuota: ", response);
      this.returnLiqui = true;

      this.simulador.controls['valor_nominal'].disable();
      this.simulador.controls['fecha_apertura'].disable();
      this.simulador.controls['fecha_vencimiento'].disable();
      this.simulador.controls['base'].disable();
      this.simulador.controls['periodicidad'].disable();
      this.simulador.controls['tipo_plazo'].disable();
      this.simulador.controls['tipo_tasa'].disable();
      this.simulador.controls['tasa_efectiva'].disable();
      this.simulador.controls['signo'].disable();
      this.simulador.controls['spread'].disable();
      this.simulador.controls['retencion'].disable();

      this.simulador.controls['tasa_nominal'].setValue(response.result.tasaNominal);
      this.simulador.controls['capital'].setValue(this.simulador.get('valor_nominal').value);
      this.simulador.controls['tasa_efectiva_sim'].setValue(response.result.tasaEfectiva);
      this.simulador.controls['interes_bruto'].setValue(response.result.interesBruto);
      this.simulador.controls['interes_bruto_periodo'].setValue(response.result.interesBrutoPeriodo);
      this.simulador.controls['interes_neto'].setValue(response.result.interesNeto);
      this.simulador.controls['interes_neto_periodo'].setValue(response.result.interesNetoPeriodo);
      this.simulador.controls['retencion_interes'].setValue(response.result.retencionIntereses);
      this.simulador.controls['retencion_interes_periodo'].setValue(response.result.retencionInteresesPeriodo);
      this.simulador.controls['factor'].setValue(response.result.factor);
      valTotPg = parseFloat(this.simulador.get('valor_nominal').value) + parseFloat(response.result.interesNeto);
      this.simulador.controls['total_pagar'].setValue(valTotPg);
      if (this.opcionSeleccionada > 1) {
        this.simulador.controls['interes_bruto_periodo'].setValue(response.result.interesNeto);
        this.simulador.controls['interes_neto'].setValue(response.result.retencionIntereses);
      }
      if( this.enableFactor ){
        console.log("Factor del response Str: ", response.result.factor.toString());
        console.log("Factor seleccionado Str", this.factorLq.toString());
        /* Se valida que el factor generado sea igual al seleccionado para actualizar los datos en BD */
        if(response.result.factor.toString() === this.factorLq.toString()){
          this.updateData = true;
        }else {
          this.updateData = false;
        }
      }
      console.log("Factor del response: ", response.result.factor);
      console.log("Factor seleccionado", this.factorLq);
      /* Se valida que el factor generado sea igual al seleccionado para actualizar los datos en BD */
      if(response.result.factor === this.factorLq){
        this.updateData = true;
      }else {
        this.updateData = false;
      }
    }, error => {
      //err(error);
      Uikit.notification().danger(error.error.message || error.message);
    });
  }

  onSubmit(success?: () => void, err?: (error) => void): void {

    if (this.simulador.valid) {
      if(this.validateTipTasa()) {
        this.serviceGetRate();
        success();
      }else {
        this.simuladorCuota()
        success();
      }
    }
  }

  public doCreation(): void {
    const r = this.router;
    const tt = this;

    if (this.simulador.invalid) {
      console.error('Form invalid...');
      return;
    }

    console.log('Creating request...');

    // Request indicators for loaders
    const isError: { show: boolean } = { show: false };
    const isSuccess: { show: boolean } = { show: false };
    const theLoaders: { name: string, loading: 0 | 1 | 2 | 3 }[] = [{
      name: 'Recopilación datos del formulario.',
      loading: 0
    }, {
      name: 'Datos del formulario recopilados.',
      loading: 0
    }];
    const loading = tt.dialog.open(ModalSimulationLiquidationCdtComponent, {
      width: '600px',
      disableClose: true,
      data: {
        loadingContent: false,
        okText: 'Aceptar',
        confirmText: 'Aceptar',
        showCloseOnError: isError,
        showConfirmOnSuccess: false,
        closeText: 'Cerrar',
        loadingList: theLoaders
      }
    });
    if(this.opcionSeleccionada > 1 ) {
      theLoaders[0] = {name: 'Obtener fecha inicio periodo.', loading: 1};
      theLoaders[1] = {name: 'Obtener tasa variable.', loading: 0};
    }else{
      theLoaders[0] = { name: 'Recopilando datos del formulario.', loading: 1 };
    }
    setTimeout(() => {
      if(this.opcionSeleccionada > 1) {
        theLoaders[2] = {name: 'Recopilando datos del formulario.', loading: 0};
        theLoaders[3] = {name: 'Simulando Liquidación del CDT.', loading: 0};
      }else{
        theLoaders[0] = { name: 'Datos del formulario recopilados.', loading: 2 };
        theLoaders[1] = {name: 'Simulando Liquidación del CDT.', loading: 1};
      }
      this.onSubmit(() => {
        if(this.opcionSeleccionada > 1 ) {
          theLoaders[3] = { name: 'Simulando Liquidación del CDT.', loading: 1 };
          theLoaders[0] = {name: 'Se obtiene la fecha apertura.', loading: 2};
          theLoaders[1] = {name: 'Obteniendo tasa variable.', loading: 1};
          theLoaders[1] = {name: 'Tasa variable capturada.', loading: 2};
          theLoaders[2] = { name: 'Recopilando datos del formulario.', loading: 1 };
          theLoaders[2] = { name: 'Datos del formulario recopilados.', loading: 2 };
          theLoaders[3] = { name: 'Liquidación Terminada.', loading: 2 };
          setTimeout(() => loading.close(), 800);
        }else{
          theLoaders[1] = { name: 'Liquidación Terminada.', loading: 2 };
          setTimeout(() => loading.close(), 800);
        }
      }, error => {
        isError.show = true;
        theLoaders[1] = { name: error.error.message || error.message, loading: 3 };
      });
    }, 500);
  }

  editLiquidation(){
    this.returnLiqui = false;
    this.updateData = false;

    if( !this.enableFactor){
      this.simulador.controls['fecha_apertura'].enable();
      this.simulador.controls['fecha_vencimiento'].enable();
    }else {
      this.simulador.controls['fecha_apertura'].disable();
      this.simulador.controls['fecha_vencimiento'].disable();
    }
    this.simulador.controls['valor_nominal'].enable();
    this.simulador.controls['base'].enable();
    this.simulador.controls['periodicidad'].enable();
    this.simulador.controls['tipo_plazo'].enable();
    this.simulador.controls['tipo_tasa'].enable();
    this.simulador.controls['tasa_efectiva'].enable();
    this.simulador.controls['signo'].enable();
    this.simulador.controls['spread'].enable();
    this.simulador.controls['retencion'].enable();

  }

  confirmSend(): void{
    const ls: DialogListModel[] = [
      { name: 'Actualizando información', loading: 1 }
    ];
    const isSuccess = { show: false };
    const isError = { show: false };
    const tt = this;
    const loading = ViewUtil.dialog(this.dialog, {
      disableClose: true,
      data: {
        title: '¿Desea actualizar la información del CDT?',
        description: 'Recuerde que al actualizar la información del CDT también se modifican los datos de liquidación.',
        loadingContent: false,
        showCloseOnError: isError,
        showConfirmOnSuccess: isSuccess,
        loadingList: ls,
        startSending: () => {
          this.saveLiquidationMod(ls, isError, isSuccess);
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
          if (this.origenLiquid === 1){
            tt.router.navigate(['', 'c', 'liquidation', 'daily']).then(res => {
              console.log('Redirecting...');
            });
          }else{
            tt.router.navigate(['', 'c', 'liquidation', 'weekly']).then(res => {
              console.log('Redirecting...');
            });
          }
        });;
      }
    });
  }

  saveLiquidationMod(ls: DialogListModel[], isError: { show: boolean }, isSuccess: { show: boolean }): void {

    const dataUpdate: UpdateLiquidationModel = {

      item: this.pItem,
      ctaInv: this.pCtaInv,
      codIsin: this.pCodIsin,
      numCdt: this.pNumCdt,
      numTit: (this.pNumTit).toString(),
      valorNominal: this.simulador.get('valor_nominal').value,
      tipBase: this.simulador.get('base').value,
      tipPeriodicidad: this.simulador.get('periodicidad').value,
      tipPlazo: this.simulador.get('tipo_plazo').value,
      plazo: this.simulador.get('plazo').value,
      tipTasa: this.simulador.get('tipo_tasa').value,
      spread: this.validateTipTasa() ? this.simulador.get('spread').value : 0,
      tasaNom: this.simulador.get('tasa_nominal').value,
      capPg: this.simulador.get('capital').value,
      tasaEfe: this.simulador.get('tasa_efectiva_sim').value,
      intBruto: this.simulador.get('interes_bruto').value,
      intNeto: this.simulador.get('interes_neto').value,
      rteFte: this.simulador.get('retencion_interes').value,
      factorDcvsa: this.simulador.get('factor').value,
      factorOpl: this.simulador.get('factor').value,
      totalPagar: (parseFloat(this.simulador.get('valor_nominal').value) + parseFloat(this.simulador.get('interes_neto').value)).toString(),
    }

    console.log("data a guardar", dataUpdate);
    if (this.origenLiquid === 1){
      // Consumo servicio LQ Diaria
      this.simulationService.updateSalPg(dataUpdate).subscribe(response => {
        isSuccess.show = true;
        ls[0] = { name: 'Información actualizada exitosamente.', loading: 2 };
      }, error => {
        isError.show = true;
        ls[0] = { name: error.error.message || 'Hubo un error inesperado al realizar la actualización de los datos de Lq. Diaria.', loading: 3 };
      });
    } else{
      // Consumo servicio LQ Semanal
      this.simulationService.updateSalPgWeekly(dataUpdate).subscribe(response => {
        isSuccess.show = true;
        ls[0] = { name: 'Información actualizada exitosamente.', loading: 2 };
      }, error => {
        isError.show = true;
        ls[0] = { name: error.error.message || 'Hubo un error inesperado al realizar la actualización de los datos de Lq. Semanal.', loading: 3 };
      });
    }
  }

  processReturn(): void {
    // Retorna al componente de liquidacion con base al origen
    if (this.origenLiquid === 1){
      this.router.navigate(['', 'c', 'liquidation', 'daily']);
    }else {
      this.router.navigate(['', 'c', 'liquidation', 'weekly']);
    }
  }
}
