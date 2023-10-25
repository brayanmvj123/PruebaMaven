import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from "@angular/router";
import { ViewUtil } from '../../include/view.util';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { ID_USER_LIST_OFFICE } from "../../include/constants";
/* Models */
import { DialogListModel } from '../../models/dialog.model';
import { UserListByOfficeModel } from'../../models/user.list.by.office.model';
/* Services */
import { OfficeListService } from '../../services/office-list.service';

@Component({
  selector: 'app-office-list-registration',
  templateUrl: './office-list-registration.component.html',
  styleUrls: ['./office-list-registration.component.scss']
})
export class OfficeListRegistrationComponent implements OnInit {

  public id!: string | null;
  public officeListReg: FormGroup;
  public loading!: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private officeListServices: OfficeListService,
    private router: Router,
    private dialog: MatDialog,
    private route: ActivatedRoute
    ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get(ID_USER_LIST_OFFICE);
    this.formDatOfficeListReg();
  }

  formDatOfficeListReg(): void {
    this.officeListReg = this.formBuilder.group({
      val_oficina: [this.id, Validators.required],
      val_subsegmento: [''],
      val_zona: [''],
      val_cargo: ['', Validators.required],
      val_nombre: ['', Validators.required],
      val_correo: ['', Validators.required],
      val_celular: [''],
      val_conmutador: [''],
      val_ext: [''],
      val_ciudad: [''],
      val_csc: ['']
    });
    this.officeListReg.controls['val_oficina'].disable();
  }

  confirmSave(): void {
    const ls: DialogListModel[] = [
      { name: 'Registrando la información del usuario.', loading: 1 }
    ];
    const isSuccess = { show: false };
    const isError = { show: false };
    const tt = this;
    const loading = ViewUtil.dialog(this.dialog, {
      disableClose: true,
      data: {        
        title: '¿Está seguro de registrar la información del usuario?',
        description: 'Al confirmar se guardará la información.',
        loadingContent: false,
        confirmText: 'Confirmar',
        cancelText: 'Volver',
        showCloseOnError: isError,
        showConfirmOnSuccess: isSuccess,
        loadingList: ls,
        startSending: () => {
          this.saveUserOffice(ls, isError, isSuccess);
        }
      }
    }).afterClosed().subscribe(result => {
      if (result.showConfirmOnSuccess.show) {
        Swal.fire({
          title: 'Muy bien',
          icon: 'success',
          html: `Información almacenada.`,
          showConfirmButton: false,
          timer: 5000
        }).then(result => {
          tt.router.navigate(['', 'c', 'officelist', 'userlist', this.id]).then(res => {
            console.log('Redirecting...');
          });
        });;
      }
    });
  }

  saveUserOffice(ls: DialogListModel[], isError: { show: boolean }, isSuccess: { show: boolean }): void {

    const data: UserListByOfficeModel = {
      nroOficina:  Number.parseInt(this.officeListReg.get('val_oficina')!.value),
      cargo: this.officeListReg.get('val_cargo')!.value,
      nombre: this.officeListReg.get('val_nombre')!.value,
      correo: (this.officeListReg.get('val_correo')!.value) + "@bancodebogota.com.co",

      subsegmento: this.officeListReg.get('val_subsegmento')!.value,
      zona: this.officeListReg.get('val_zona')!.value,
      celular:  Number.parseInt(this.officeListReg.get('val_celular')!.value),
      conmutador: this.officeListReg.get('val_conmutador')!.value,
      ext:  Number.parseInt(this.officeListReg.get('val_ext')!.value),
      ciudad: this.officeListReg.get('val_ciudad')!.value,
      csc:  Number.parseInt(this.officeListReg.get('val_csc')!.value),
      estadoUsuario: 6
    };

    console.log("Data a guardar en Reg Officina: ", data);
    // Envío data
    this.officeListServices.registerUserByOffice(data).subscribe(response => {
      isSuccess.show = true;
      ls[0] = { name: 'Información del usuario guardada exitosamente.', loading: 2 };
    }, error => {
      isError.show = true;
      ls[0] = { name: error.error.message || 'Hubo un error inesperado al guardar la información.', loading: 3 };
    });
  }

  processReturn(): void {
    this.router.navigate(['', 'c', 'officelist', 'userlist', this.id])
  }

}
