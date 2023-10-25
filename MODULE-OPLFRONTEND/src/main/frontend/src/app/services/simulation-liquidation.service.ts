import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {LiquidationWeeklyModel} from '../models/liquidation.weekly.model';
import {SimulationFeesModel} from '../models/simulation.fees.model';
import {SimulationFeesRsltModel} from '../models/simulation.fees.rslt.model';
import {CalculateStartDatePeriodModel} from "../models/calculate.start.date.period.model";
import {GetRatesVariableModel} from "../models/get.rates.variable.model";
import {CalculateStartDatePeriodRsltModel} from "../models/calculate.start.date.period.rslt.model";
import {getRatesVariableRsltModel} from "../models/get.rates.variable.rslt.model";
import {UpdateLiquidationModel} from '../models/update.liquidation.model'
import {ValidateOfficeModel} from "../models/validate.office.model";
import {ResponseSaveOffice, SaveOfficeModel} from "../models/save.office.model";
import {SendEmailModel} from "../models/send.email.model";

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
export class SimulationLiquidationService {

  constructor(private http: HttpClient) {
  }

  /**
   * Simulation fees of the cdt.
   */
  simulationFeesCdt(data: SimulationFeesModel): Observable<SimulationFeesRsltModel> {
    return this.http.post<SimulationFeesRsltModel>(environment.api.simulation_fees_cdt, data, {});
  }

  /**
   * Get pg weekly all list.
   */
  getSalPgSemanal(): Observable<{ result: LiquidationWeeklyModel[] }> {
    return this.http.get<{ result: LiquidationWeeklyModel[] }>(environment.api.get_pg_semanal);
  }

  /**
   * Get pg weekly all list.
   */
  getSalPg(): Observable<{ result: LiquidationWeeklyModel[] }> {
    return this.http.get<{ result: LiquidationWeeklyModel[] }>(environment.api.get_pg);
  }

  /**
   *
   * @param data
   */
  getStartDatePeriod(data: CalculateStartDatePeriodModel): Observable<CalculateStartDatePeriodRsltModel> {
    return this.http.post<CalculateStartDatePeriodRsltModel>(environment.api.get_start_date_period, data, {});
  }

  /**
   *
   * @param data
   */
  getRateVariable(data: GetRatesVariableModel): Observable<getRatesVariableRsltModel> {
    return this.http.post<getRatesVariableRsltModel>(environment.api.get_rates_variables, data, {});
  }

  /**
   * Close conciliation daily
   */
  closeConciliation(): Observable<any> {
    return this.http.get<{ result: string }>(environment.api.closeConciliation);
  }

  /**
   * Close conciliation weekly
   */
  closeConciliationWeekly(): Observable<any> {
    return this.http.get<{ result: string }>(environment.api.closeConciliationWeekly);
  }

  /**
   * Update data Salpg daily
   * @param data
   */
  updateSalPg(data: UpdateLiquidationModel): Observable<any> {
    return this.http.put<any>(environment.api.update_pg, data);
  }

  /**
   * Update data Salpg weekly
   * @param data
   */
  updateSalPgWeekly(data: UpdateLiquidationModel): Observable<any> {
    return this.http.put<any>(environment.api.update_pgWeekly, data);
  }

  /**
   * Validate offices
   */
  validateOffice(): Observable<ValidateOfficeModel> {
     return this.http.get<ValidateOfficeModel>(environment.api.validate_offices);
  }

  saveOffices(data: SaveOfficeModel): Observable<ResponseSaveOffice>{
    return this.http.post<ResponseSaveOffice>(environment.api.post_user_by_office_reg, data);
  }

  sendEmail(): Observable<SendEmailModel>{
    return this.http.get<SendEmailModel>(environment.api.send_email);
  }

}
