/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF-FRONT was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */

export class DistributionModel {
    public proyectoAcciones: {
        codEmi: number,
        fechaAsam: string,
        codIsinUk: string,
        cantAcc: number,
        valDiv: number,
        valNom: number,
        tipAcc: number,
        usrRadic: string,
        accTipUvtParTblYearUvt: string,
        valUvt: number
    };
    public distribucion: {
        idDist: number,
        valUtilidad: number,
        valNograv: number,
        porNograv: number,
        valGrav: number,
        porGrav: number,
        accRefretTblCodRef: number,
        accProdisaccTblCodEmi: number
    }[];
}
