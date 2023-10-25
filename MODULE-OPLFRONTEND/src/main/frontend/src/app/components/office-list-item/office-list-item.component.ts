import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from "@angular/router";
import { ID_USER_LIST_OFFICE } from "../../include/constants";
import { Uikit } from "../../include/uikit";
import { ViewUtil } from "../../include/view.util";
import { MatDialog } from "@angular/material/dialog";
import Swal from "sweetalert2";
/* Services */
import { OfficeListService } from '../../services/office-list.service';
/* Models */
import { DialogListModel } from "../../models/dialog.model";
import { UserListByOfficeModel } from'../../models/user.list.by.office.model';

@Component({
  selector: 'app-office-list-item',
  templateUrl: './office-list-item.component.html',
  styleUrls: ['./office-list-item.component.scss']
})
export class OfficeListItemComponent implements OnInit {

  public officeListReg: FormGroup;
  public loading: boolean = true;
  public id!: string | null;
  public updateStatus: boolean = false;
  public action: any;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private officeListServices: OfficeListService,
    private router: Router,
    private dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get(ID_USER_LIST_OFFICE);

    this.formDatOfficeListItem();
  }

  formDatOfficeListItem(): void {
    this.officeListReg = this.formBuilder.group({
      val_oficina: ['', Validators.required],
      val_subsegmento: [{ value: '', disabled: true }],
      val_zona: [{ value: '', disabled: true }],
      val_cargo: ['', Validators.required],
      val_nombre: ['', Validators.required],
      val_correo: ['', Validators.required],
      val_celular: [{ value: '', disabled: true }],
      val_conmutador: [{ value: '', disabled: true }],
      val_ext: [{ value: '', disabled: true }],
      val_ciudad: [{ value: '', disabled: true }],
      val_csc: [{ value: '', disabled: true }]
    });
    this.getDataItem();
  }

  public getDataItem(): void {
    this.action =  this.id.split("_");

    /* Obtener data */
    this.officeListServices.getUserItemListOffice(this.action[0]!).subscribe(response => {
      console.log("Data recibida al consulta usuario: ", response);
      this.loading = false;
      let email = response.result.correo.split("@"); 
      this.officeListReg.patchValue({  
        val_oficina: response.result.nroOficina,
        val_subsegmento: response.result.subsegmento,
        val_zona: response.result.zona,
        val_cargo: response.result.cargo,
        val_nombre: response.result.nombre,
        val_correo: email[0],
        val_celular: response.result.celular,
        val_conmutador: response.result.conmutador,
        val_ext: response.result.ext,
        val_ciudad: response.result.ciudad,
        val_csc: response.result.csc
      });
    }, error => {
      this.loading = false;
      Uikit.notification().danger(error.error.message || 'Hubo un error inesperado al intentar obtener los datos del usuario.');
    });

    if( this.action[1] === '1'){
      
      /* Consulta de información */
      this.officeListReg.controls['val_oficina'].disable();
      this.officeListReg.controls['val_subsegmento'].disable();
      this.officeListReg.controls['val_zona'].disable();
      this.officeListReg.controls['val_cargo'].disable();
      this.officeListReg.controls['val_nombre'].disable();
      this.officeListReg.controls['val_correo'].disable();
      this.officeListReg.controls['val_celular'].disable();
      this.officeListReg.controls['val_conmutador'].disable();
      this.officeListReg.controls['val_ext'].disable();
      this.officeListReg.controls['val_ciudad'].disable();
      this.officeListReg.controls['val_csc'].disable();

    }else {

      this.updateStatus = true;
      /* Modificación de información */
      this.officeListReg.controls['val_oficina'].enable();
      this.officeListReg.controls['val_subsegmento'].enable();
      this.officeListReg.controls['val_zona'].enable();
      this.officeListReg.controls['val_cargo'].enable();
      this.officeListReg.controls['val_nombre'].enable();
      this.officeListReg.controls['val_correo'].enable();
      this.officeListReg.controls['val_celular'].enable();
      this.officeListReg.controls['val_conmutador'].enable();
      this.officeListReg.controls['val_ext'].enable();
      this.officeListReg.controls['val_ciudad'].enable();
      this.officeListReg.controls['val_csc'].enable();
    }      
  }

  confirmSave(){
    // Confirmación y actualización de datos
    const ls: DialogListModel[] = [
      { name: 'Modificando datos del usuario...', loading: 1 }
    ];
    const isSuccess = { show: false };
    const isError = { show: false };
    const tt = this;
    const loading = ViewUtil.dialog(this.dialog, {
      disableClose: true,
      data: {
        title: '¿Está seguro de actualizar la información del usuario?',
        description: 'Al confirmar se actualizará la información en base de datos.',
        loadingContent: false,
        showCloseOnError: isError,
        showConfirmOnSuccess: isSuccess,
        loadingList: ls,
        startSending: () => {
          this.sendData(ls, isError, isSuccess);
        }
      }
    }).afterClosed().subscribe(result => {
      if (result.showConfirmOnSuccess.show) {
        Swal.fire({
          title: 'Muy bien',
          text: 'Información actualizada satisfactoriamente.',
          icon: 'success',
          showConfirmButton: false,
          timer: 5000
        }).then(result => {
          tt.router.navigate(['', 'c', 'officelist', 'userlist', this.action[2]]).then(res => {
            console.log('Redirecting...');
          });
        });
      }
    });
  }

  sendData(ls: DialogListModel[], isError: { show: boolean }, isSuccess: { show: boolean }): void {
    const dataUpdate: UserListByOfficeModel = {
      nroOficina: this.officeListReg.get('val_oficina')!.value,
      cargo: this.officeListReg.get('val_cargo')!.value,
      nombre: this.officeListReg.get('val_nombre')!.value,
      correo: (this.officeListReg.get('val_correo')!.value) + "@bancodebogota.com.co",

      subsegmento: this.officeListReg.get('val_subsegmento')!.value,
      zona: this.officeListReg.get('val_zona')!.value,
      celular: this.officeListReg.get('val_celular')!.value,
      conmutador: this.officeListReg.get('val_conmutador')!.value,
      ext: this.officeListReg.get('val_ext')!.value,
      ciudad: this.officeListReg.get('val_ciudad')!.value,
      csc: this.officeListReg.get('val_csc')!.value,
      estadoUsuario: 6
    };
    
    console.log("Data a guardar:", this.action[0], " - ", dataUpdate);
    this.officeListServices.editUserListOffice(this.action[0]!, dataUpdate).subscribe(response => {
      isSuccess.show = true;
      ls[0] = { name: 'Información modificada exitosamente.', loading: 2 };
    }, error => {
      isError.show = true;
      ls[0] = { name: error.error.message || 'Hubo un error inesperado al modificar la información.', loading: 3 };
    });
  }

  processReturn(): void {
    this.router.navigate(['', 'c', 'officelist' , 'userlist', this.action[2]]);
  }

}
