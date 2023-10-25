package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opaloshare.persistence.entity.TipPeriodParDownEntity;
import com.bdb.opaloshare.persistence.entity.TipbaseParDownEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryTipBase;
import com.bdb.opaloshare.persistence.repository.RepositoryTipPeriodicidad;
import com.bdb.opaloshare.util.Constants;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.IntPredicate;

@Service
@CommonsLog
public class CalculadoraServiceImpl implements CalculadoraService {

    @Autowired
    private RepositoryTipBase repositoryTipBase;

    @Autowired
    private RepositoryTipPeriodicidad repositoryTipPeriodicidad;

    DecimalFormat df2 = new DecimalFormat(Constants.DECIMAL_FORMAT_2);

    /**
     * @param valor
     * @return
     */
    public double entreCien(double valor) {
        return valor / 100F;
    }


    /**
     * Para calcular la <b><i><u>TASA NOMINAL</u></i></b> se debe utilizar la siguiente formula:
     * <p><center>Math.pow(( @tasaEfectiva / 100 ) + 1 , 1 / @periodicidad ) - 1</center></p>
     *
     * <p><i><u><b>Variables:</b></u></i></p>
     * <ul><li>@periodicidad: Periodicidad del periodo escogido dependiendo de la base.</li></ul>
     *
     * @param tasaFija Si la tasa: <ul>
     *                 <li>Es <u>Fija</u> se envia el valor enviado en el request del servicio.</li>
     *                 <li>Es diferente a la Fija se debe calcular la <u>TasaEfectivaAnual</u>.</li>
     *                 </ul>
     * @param periodo  Resultado de utilizar el metodo <u><i>periodo()</i></u>.
     * @return Tasa Nominal. De tipo {@link Double}.
     * @throws ParseException
     */
    public double calcularTasaNominal(double tasaFija, double periodo) throws ParseException {

        DecimalFormat df6 = new DecimalFormat(Constants.DECIMAL_FORMAT_6);
        df6.setRoundingMode(RoundingMode.HALF_UP);
        df2.setRoundingMode(RoundingMode.HALF_UP);

        double tasaFijaEntre100 = NumberFormat.getInstance().parse(df6.format(entreCien(tasaFija))).doubleValue();
        double primerTn = tasaFijaEntre100 + 1F;
        double tepv = Math.pow(primerTn, 1F / periodo) - 1;
        double tasaNominal = NumberFormat.getInstance().parse(df6.format((tepv * periodo) * 100)).doubleValue();
        log.info("TASA NOMINAL: " + tasaNominal);
        log.info("TASA NOMINAL CON FOMRATO 2 DECIMALES: " + NumberFormat.getInstance().parse(df2.format(tasaNominal)).doubleValue());
        return NumberFormat.getInstance().parse(df2.format(tasaNominal)).doubleValue();
    }

    /**
     * Calcula:
     * <ol>
     *     <li>Factor</li>
     *     <li>Intereses generados</li>
     * </ol>
     *
     * @param tasaNominal       Calculo generado a traves del metodo <i>calcularTasaNominal()</i>.
     * @param diasPlazo         Dias de plazo enviados.
     * @param baseDias          Valor dado por el metodo baseDias().
     * @param capital           Valor enviado en el request.
     * @param retencion         Valor enviado en el request.
     * @param numPeriodos       Calcul generado a traves del metodo <i>numerosPeriodos()</i>.
     * @param tasaEfectivaAnual Valor calculado en las tasas diferentes a la <i><u>Tasa Fija</u></i>. Si se calcula una
     *                          tasa fija se envia el valor enviado en el request.
     * @return JSONResultSimuladorCuota, objeto con la mayoria de resultado de las operaciones realizadas.
     * @throws ParseException
     */
    public JSONResultSimuladorCuota intereses(double tasaNominal, double diasPlazo, double baseDias, double capital,
                                              double retencion, double numPeriodos, double tasaEfectivaAnual,
                                              Integer tiptasa) throws ParseException {

        JSONResultSimuladorCuota resultOperations = new JSONResultSimuladorCuota();
        DecimalFormat df6 = new DecimalFormat(Constants.DECIMAL_FORMAT_6);
        df6.setRoundingMode(RoundingMode.HALF_UP);

        double tnomcienXplazo = (entreCien(tasaNominal)) * diasPlazo;

        double factorTotal = NumberFormat.getInstance().parse(df6.format(tnomcienXplazo / baseDias)).doubleValue();

        double factorDias = factorTotal / diasPlazo;
        double interesDiario = factorDias * capital;
        double retencionCien = entreCien(retencion);

        double interesBruto = operation(interesDiario, diasPlazo, 1);
        double retencionIntereses = operation(interesBruto, retencionCien, 1);
        double interesNeto = operation(interesBruto, retencionIntereses, 3);

        double interesBrutoPeriodo = operation(interesBruto, numPeriodos, 2);
        double retencionInteresesPeriodo = operation(retencionIntereses, numPeriodos, 2);
        double interesNetoPeriodo = operation(interesNeto, numPeriodos, 2);

        IntPredicate validate = x -> x == 1;

        resultOperations.setInteresBruto(validate.test(tiptasa) ? formatString(interesBruto) : "0");
        resultOperations.setRetencionIntereses(validate.test(tiptasa) ? formatString(retencionIntereses) : "0");
        resultOperations.setInteresNeto(validate.test(tiptasa) ? formatString(interesNeto) : "0");
        resultOperations.setInteresBrutoPeriodo(formatString(interesBrutoPeriodo));
        resultOperations.setRetencionInteresesPeriodo(formatString(retencionInteresesPeriodo));
        resultOperations.setInteresNetoPeriodo(formatString(interesNetoPeriodo));
        resultOperations.setFactor(factorTotal);
        resultOperations.setTasaNominal(tasaNominal);
        resultOperations.setTasaEfectiva(tasaEfectivaAnual);

        return resultOperations;
    }

    /**
     * Es metodo calcula el periodo.
     *
     * @param base         Base de tipo Double, enviada en el request.
     * @param periodicidad Periodicidad de tipo Double, enviada en el rrequest.
     * @return El valor del periodo.
     */
    @Override
    public double periodo(double base, double periodicidad) {
        return base / periodicidad;
    }

    /**
     * Este metodo calcula el número de Periodos.
     *
     * @param plazo        Plazo en dias, enviado en el request.
     * @param periodicidad La periodicidad establecida.
     * @return El número de peridos de acuerdo al plazo.
     */
    @Override
    public double numerosPeriodos(double plazo, double periodicidad) {
        return plazo / periodicidad;
    }

    /**
     * Este metodo permite dar formato a los resultados de las operaciones.
     *
     * @param value Este valor debe ser de tipo Double.
     * @return Al formato <math><b>%.0f</b></math>
     */
    public String formatString(Double value) {
        return String.format(Constants.DECIMAL_FORMAT_AVOID_NOTATION, value);
    }

    /**
     * Realizar operaciones basicas, se deben enviar los dos valores a operar y el codigo de la respectiva operacion.
     * <table>
     *     <tr>
     *         <th>CODIGO</th>
     *         <th>OPERACIÓN</th>
     *     </tr>
     *     <tbody>
     *         <tr>
     *             <td>1</td>
     *             <td>MULTIPLICACIÓN</td>
     *         </tr>
     *         <tr>
     *            <td>1</td>
     *            <td>DIVISIÓN</td>
     *         </tr>
     *         <tr>
     *            <td>1</td>
     *            <td>RESTA</td>
     *         </tr>
     *     </tbody>
     * </table>
     *
     * @param valueOne  De tipo Double.
     * @param valueTwo  De tipo Double.
     * @param operation De tipo Integer, de acuerdo a lo operación deseada.
     * @return Resultado de la operación indicada.
     * @throws ParseException {@link ParseException}
     * @see DecimalFormat
     * @see NumberFormat
     */
    public Double operation(double valueOne, double valueTwo, Integer operation) throws ParseException {
        df2.setRoundingMode(RoundingMode.HALF_UP);
        double result = 0;
        switch (operation) {
            case 1:
                result = NumberFormat.getInstance().parse(df2.format(valueOne * valueTwo)).doubleValue();
                break;
            case 2:
                result = NumberFormat.getInstance().parse(df2.format(valueOne / valueTwo)).doubleValue();
                break;
            case 3:
                result = NumberFormat.getInstance().parse(df2.format(valueOne - valueTwo)).doubleValue();
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Convertir el valor de tipo String a Double.
     *
     * @param value Valor
     * @return Valor parseado a Double.
     */
    public Double convertDouble(String value) {
        return Double.parseDouble(value);
    }

    /**
     * Valida que la base sea correcta y devuelve la descripción de la base.
     *
     * @param base El codigo de la base, enviado por el request.
     * @return La descripcion de la base.
     */
    public double baseDias(String base) {
        Optional<TipbaseParDownEntity> tipbaseParDownEntity = repositoryTipBase.findById(Integer.parseInt(base));
        return tipbaseParDownEntity
                .map(parDownEntity -> {
                    if (parDownEntity.getDescBase().equals("Real")) {
                        LocalDate fecha = LocalDate.now();
                        return fecha.isLeapYear() ? Double.valueOf(366) : Double.valueOf(365);
                    } else {
                        return Double.parseDouble(parDownEntity.getDescBase());
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                        "El tipo de BASE no ha sido encontrado."));
    }

    /**
     * Se encarga validar la periodicidad enviada exista en la BD, para este caso la periodicidad se trabaja en dias.
     *
     * @param periodicidad Codigo enviado en el request.
     * @param diasPlazo    Número de dias enviado en el request.
     * @return La periodicidad en dias.
     */
    public double periodicidadDias(String periodicidad, double diasPlazo) {
        Optional<TipPeriodParDownEntity> codPeriodicidad = repositoryTipPeriodicidad.findById(Integer.parseInt(periodicidad));
        if (codPeriodicidad.isPresent()) {
            if (codPeriodicidad.get().getTipPeriodicidad() == 0)
                return diasPlazo;
            else
                return codPeriodicidad.get().getTipPeriodicidad() * 30F;
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de periodicidad no ha sido encontrado.");
    }

}
