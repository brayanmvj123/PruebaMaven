package com.bdb.opalotasasfija.controller.service.interfaces;

import java.util.List;

public interface Calculadora {
	
	public double calcularTasaNominal(double tasaFija, double periodo);

	public double entreCien(double valor);
	
	public List<Double> calcularInteres(double tasaNominal, double diasPlazo, double baseDias, double capital, double retencion, double periodo);

}
