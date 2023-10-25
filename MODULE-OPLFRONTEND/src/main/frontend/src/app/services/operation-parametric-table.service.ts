import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {BaseTypeModel} from '../models/base.type.model';
import {PeriodTypeModel} from '../models/period.type.model';
import {RateTypeModel} from '../models/rate.type.model';
import {TermTypeModel} from '../models/term.type.model';
import {CalculateDaysCdt} from '../models/calculate.days.cdt.model';
import { CalendarModel } from '../models/calendar.model';

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
@Injectable({
    providedIn: 'root'
})
export class OperationParametricTable {

    constructor(private http: HttpClient) {
    }

    /**
     * Get all base Type list.
     */
    getBaseType(): Observable<BaseTypeModel> {
        return this.http.get<BaseTypeModel>(environment.api.get_base_type);
    }

    /**
     * Get all period Type list.
     */
    getPeriodType(): Observable<PeriodTypeModel> {
        return this.http.get<PeriodTypeModel>(environment.api.get_period_type);
    }

    /**
     * Get all rate Type list.
     */
    getRateType(): Observable<RateTypeModel> {
        return this.http.get<RateTypeModel>(environment.api.get_rate_type);
    }

    /**
     * Get all term Type list.
     */
    getTermType(): Observable<TermTypeModel> {
        return this.http.get<TermTypeModel>(environment.api.get_term_type);
    }

     /**
     * Get days not enable in a year.
     */
    getCalendarNotEnabledDays(year:number): Observable<CalendarModel> {
        return this.http.get<CalendarModel>(environment.api.get_calendar_notenabled+"/"+year);
    }

    /**
     * Returns term in days of the cdt.
     */
    termDaysCdt(base: number, fechaApertura: string, fechaVencimiento: string): Observable<CalculateDaysCdt> {
        return this.http.post<CalculateDaysCdt>(environment.api.calculate_days_cdt, {
            base: base,
            fechaApertura: fechaApertura,
            fechaVencimiento: fechaVencimiento
        }, {});
    }

}
