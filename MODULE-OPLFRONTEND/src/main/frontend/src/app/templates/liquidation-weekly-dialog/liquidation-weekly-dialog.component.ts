import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { ROLES_DATA } from '../../include/constants';
import {Uikit} from 'src/app/include/uikit';

@Component({
    selector: 'app-liquidation-weekly-dialog',
    templateUrl: './liquidation-weekly-dialog.component.html',
    styleUrls: ['./liquidation-weekly-dialog.component.scss']
})
export class LiquidationWeeklyDialogComponent {

    public enableConfirm: boolean =  true;

    constructor(
        private router:Router,
        private storage: LocalStorageService,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private dialogRef: MatDialogRef<LiquidationWeeklyDialogComponent>) {
        this.dialogRef.updateSize('300vw', '300vw')
    }

    async simular(value: number){
        this.data.valFactorSelect = value;
        let permiso=false;
        let roles=JSON.parse(this.storage.get(ROLES_DATA));
        for await (const rol of roles) {
            for await (const _funcion of rol.functions) {
                if(_funcion.nombreFuncionalidad=="SIMULATOR"){
                    permiso=true;
                    break;
                }
            }
        }
        if(permiso){
            this.data["liquidacionExistente"]=true;
            this.dialogRef.close();
            this.router.navigateByUrl(
                'c/liquidation/simulator', 
                { state: this.data});
        }else{
            Uikit.notification().danger("No cuenta con los permisos necesarios para acceder al simulador.");
        }
    }
}
