import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SimulationLiquidationService} from "../../services/simulation-liquidation.service";
import {Router} from "@angular/router";
import {SaveOfficeModel} from "../../models/save.office.model";

@Component({
  selector: 'app-info-office-dialog',
  templateUrl: './info-office-dialog.component.html',
  styleUrls: ['./info-office-dialog.component.scss']
})
export class InfoOfficeDialogComponent implements OnInit {

  tabs: number[] = [];
  public createOfficeForm: FormGroup;
  public nroOficina: number;
  selected = new FormControl(0);

  constructor(private liquidationService: SimulationLiquidationService,
              private formBuilder: FormBuilder,
              private router: Router
  ) {

  }

  ngOnInit() {
    console.log("info offices");
    this.validateOffices();
    this.createOfficeForm = this.formBuilder.group({
      num_oficina: [{value:'', disabled: true}, Validators.required],
      cargo: ['', Validators.required],
      nom_resp: ['', Validators.required],
      email: ['', Validators.required]
    });
    this.createOfficeForm.get('cargo').setValue('');
    this.createOfficeForm.get('email').setValue('');
    this.createOfficeForm.get('nom_resp').setValue('');
  }

  validateOffices() {
    this.liquidationService.validateOffice().subscribe(response => {
      if (response.status.code === 202) {
        response.result.forEach(x => console.log(x.nroOficina));
        response.result.forEach(x => this.tabs.push(x.nroOficina));
      }
    }, error => {
      console.log("Error al validar la información de las oficinas.")
    })
  }

  validateFields(): boolean {
    return this.createOfficeForm.get('cargo').value == '' ||
      this.createOfficeForm.get('nom_resp').value == '' ||
      this.createOfficeForm.get('email').value == '';
  }

  onSubmit(success?: () => void, err?: (error) => void): void {
    if (this.createOfficeForm.valid) {
      success();
    }
  }

  public doCreation(index: number, valueOffice: number): void {
    if (this.createOfficeForm.invalid) {
      console.error('Form invalid...');
      return;
    } else {
      this.onSubmit(() => {
        if (!this.validateFields()) {
          this.saveInfoOffice(index, valueOffice);
        }

        if (this.tabs.length == 1) {
          this.router.navigate(['', 'c', 'liquidation', 'weekly']).then(res => {
            console.log('Redirecting...');
            window.location.reload();
          });
        }
      }, error => {
        console.log("ERROR SUCEDIDO");
      })
    }
  }

  saveInfoOffice(index: number, valueOffice: number) {
    const data: SaveOfficeModel = {
      nroOficina: valueOffice,
      cargo: this.createOfficeForm.get('cargo').value,
      nombre: this.createOfficeForm.get('nom_resp').value,
      correo: (this.createOfficeForm.get('email').value) + "@bancodebogota.com.co",
      estadoUsuario : 6
    }
    this.liquidationService.saveOffices(data).subscribe(response => {
      console.log(response.result.status)
      if (response.result.status === 201) {
        this.tabs.splice(index, 1);
      }
    }, error => {
      console.log("ERROR")
    })
  }

}
