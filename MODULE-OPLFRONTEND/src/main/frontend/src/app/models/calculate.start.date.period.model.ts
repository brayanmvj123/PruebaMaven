export class CalculateStartDatePeriodModel {
  public canal: string;
  public parametros: ParametersCalStDateModel;
}

export class ParametersCalStDateModel {
  public base: number;
  public plazo: number;
  public fechaVencimiento: string;
  public fechaProxPago: string;
  public periodicidad: number; 
  public tipoPlazo: number;
}
