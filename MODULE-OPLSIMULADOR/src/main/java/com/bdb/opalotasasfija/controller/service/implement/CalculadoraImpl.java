package com.bdb.opalotasasfija.controller.service.implement;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdb.opaloshare.util.Constants;
import com.bdb.opalotasasfija.controller.service.interfaces.Calculadora;

@Service
public class CalculadoraImpl implements Calculadora {

	public double calcularTasaNominal(double tasaFija, double periodo) {

		DecimalFormat df4 = new DecimalFormat(Constants.DECIMAL_FORMAT_4);
		df4.setRoundingMode(RoundingMode.HALF_UP);

		double tasaFijaEntre100 = Double.valueOf(df4.format(entreCien(tasaFija)));
		double primerTn = tasaFijaEntre100 + 1F;
		double tepv = (double) Math.pow(primerTn, 1F / periodo) - 1;
		double tasaNominal = Double.valueOf(df4.format((tepv * periodo) * 100));

		return tasaNominal;
	}

	public double entreCien(double valor) {
		double dividido = valor / 100F;
		return dividido;
	}

	public List<Double> calcularInteres(double tasaNominal, double diasPlazo, double baseDias, double capital,
			double retencion, double NumPeriodos) {

		List<Double> response = new ArrayList<>();

		DecimalFormat df6 = new DecimalFormat(Constants.DECIMAL_FORMAT_6);
		df6.setRoundingMode(RoundingMode.HALF_UP);

		DecimalFormat df2 = new DecimalFormat(Constants.DECIMAL_FORMAT_2);
		df2.setRoundingMode(RoundingMode.HALF_UP);

		double tNomEntreCien = entreCien(tasaNominal);
		double tnomcienXplazo = tNomEntreCien * diasPlazo;
		double factorTotal = Double.valueOf(df6.format(tnomcienXplazo / baseDias));
		double factorDias = factorTotal / diasPlazo;
		double interesDiario = factorDias * capital;
        double retencionCien = entreCien(retencion);

		double interesBruto = Double.valueOf(df2.format(interesDiario * diasPlazo));
		double retencionIntereses = Double.valueOf(df2.format(interesBruto * retencionCien));
		double interesNeto = Double.valueOf(df2.format(interesBruto - retencionIntereses));

		double interesBrutoPeriodo = Double.valueOf(df2.format(interesBruto / NumPeriodos));
		double retencionInteresesPeriodo = Double.valueOf(df2.format(retencionIntereses / NumPeriodos));
		double interesNetoPeriodo = Double.valueOf(df2.format(interesNeto / NumPeriodos));

		response.add(interesBruto);
		response.add(retencionIntereses);
		response.add(interesNeto);
		response.add(interesBrutoPeriodo);
		response.add(retencionInteresesPeriodo);
		response.add(interesNetoPeriodo);

		return response;
	}
}
