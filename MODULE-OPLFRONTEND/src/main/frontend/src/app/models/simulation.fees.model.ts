/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF-FRONT was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Esteban Talero <etalero@bancodebogota.com.co>.
 */

export class SimulationFeesModel {
    public canal: string;
    public parametros: SimulationItemFeesModel;
}

export class SimulationItemFeesModel {
    public base: number;
    public capital: number;
    public diasPlazo: number;
    public periodicidad: number;
    public retencion: number;
    public tasa: number;
    public tipoPlazo: number;
    public tipoTasa: number;
    public spread: number;
}
