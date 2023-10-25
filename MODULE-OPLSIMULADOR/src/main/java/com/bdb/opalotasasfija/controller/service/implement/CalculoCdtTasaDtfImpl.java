package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opaloshare.util.Constants;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoCdtTasaDtfService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

@Service
@CommonsLog
public class CalculoCdtTasaDtfImpl implements CalculoCdtTasaDtfService {

    @Autowired
    private CalculadoraService calculadoraService;

    DecimalFormat df2 = new DecimalFormat(Constants.DECIMAL_FORMAT_2);

    /**
     * <p>Este metodo realiza <u>solo</u> los calculos matematicos del tipo: <u><b><i>TASA DTF</i></b></u>.</p>
     * <ol>
     *     <li><p>Los valores numericos que son de tipo String son convertidos a <u><b><i>Doubles</i></b></u>.</p></li>
     *     <li><p>Se valida el valor enviado en la <u>base</u> exista en la BD de Opalo.</p></li>
     *     <li><p>Se valida el valor enviado en la <u>periodicidad</u>  Exista en la BD de Opalo.</p></li>
     *     <li><p>El periodo se obtiene:</p></li>
     *     <table style="width:20%;">
     *         <tr>
     *             <td style="text-align:center;">Base</td>
     *         </tr>
     *         <tr>
     *             <td style="border-top: 2px solid black; text-align:center;">Periodicidad</td>
     *         </tr>
     *     </table>
     * </ol>
     * <ol>
     *     <li value="5"><p>Calcular la <u><i><b>Tasa Nominal</b></i></u>.</p></li>
     *     <li><p>Para obtener el Numero de periodos: </p></li>
     *     <table style="width:20%;">
     *          <tr>
     *              <td style="text-align:center;">Plazo (en Dias)</td>
     *          </tr>
     *          <tr>
     *              <td style="border-top: 2px solid black; text-align:center;">Periodicidad</td>
     *          </tr>
     *      </table>
     * </ol>
     * <ol>
     *      <li value="7"><p>Calcular los <i>Intereses</i> generados por la <b>Tasa DTF</b></p></li>
     * </ol>
     *
     * @param request Contiene los parametros enviados por el cliente a simlular la Tasa indicada.
     * @return Los resultados de las distintas operaciones y son almacenas en un objeto {@link JSONResultSimuladorCuota}.
     * @throws ParseException
     */
    @Override
    public JSONResultSimuladorCuota calculoCdtDtf(JSONRequestSimuladorCuota request) throws ParseException {

        double tasaDTF = calculadoraService.convertDouble(request.getParametros().getTasa());// tasa dtf trimestre anticipado
        String periodicidad = request.getParametros().getPeriodicidad();
        String base = request.getParametros().getBase();
        double diasPlazo = calculadoraService.convertDouble(request.getParametros().getDiasPlazo());
        double capital = calculadoraService.convertDouble(request.getParametros().getCapital());
        double retencion = calculadoraService.convertDouble(request.getParametros().getRetencion());
        double spread = calculadoraService.convertDouble(request.getParametros().getSpread());

        double baseDias = calculadoraService.baseDias(base);
        double periodicidadDias = calculadoraService.periodicidadDias(periodicidad, diasPlazo);
        double periodo = calculadoraService.periodo(baseDias, periodicidadDias);

        double tasaEADTF = calcularTasaEfectivaDtf(tasaDTF, spread, periodicidadDias, baseDias);//TASA EFECTIVA ANUAL
        log.info("TASA EFECTIVA EFECTIVA ANUAL: " + tasaEADTF );
        double tasaNominal = calculadoraService.calcularTasaNominal(tasaEADTF, periodo);
        double numPeriodos = calculadoraService.numerosPeriodos(diasPlazo, periodicidadDias);

        return calculadoraService.intereses(tasaNominal, diasPlazo, baseDias, capital, retencion, numPeriodos, tasaEADTF,
                request.getParametros().getTipoTasa());

    }

    /**
     * Para calcular la <b><i><u>TASA Efectiva Anual DTF</u></i></b> se debe utilizar la siguiente formula:
     * <table style="width:20%;">
     *      <tr>
     *          <td style="text-align:center;">Tasa DTF Trimestre Anticipado</td>
     *      </tr>
     *      <tr>
     *        <td style="text-align:center;">Spread</td>
     *      </tr>
     *      <tr>
     *        <td style="border-top: 2px solid black; text-align:center;">Periodicidad</td>
     *      </tr>
     *      <tr>
     *          <td style="text-align:center;">Base</td>
     *      </tr>
     *
     * </table>
     *
     * @param tasaTA
     * @param spread
     * @param periodo
     * @param base
     * @return
     * @throws ParseException
     */
    @Override
    public double calcularTasaEfectivaDtf(double tasaTA, double spread, double periodo, double base) throws ParseException {

        DecimalFormat df4 = new DecimalFormat(Constants.DECIMAL_FORMAT_4);
        df4.setRoundingMode(RoundingMode.HALF_UP);
        df2.setRoundingMode(RoundingMode.HALF_UP);
        //TASA EFECTIVA ANUAl
        //=(1/(1-((+tasaTA%+spread%)/(basedias/periododias))))^(basedias/periododias)-1
        double tasaTA100 = NumberFormat.getInstance().parse(df4.format(calculadoraService.entreCien(tasaTA))).doubleValue();
        double spread100 = NumberFormat.getInstance().parse(df4.format(calculadoraService.entreCien(spread))).doubleValue();
        double primerTn = tasaTA100 + spread100;
        double segundoTn = primerTn / (base / periodo);
        double terceroTn = 1F - (segundoTn);
        double tepv = Math.pow(1F / terceroTn, base / periodo) - 1;
        double tasaEA = NumberFormat.getInstance().parse(df4.format((tepv) * 100)).doubleValue();
        log.info("TASA EFECTIVA ANUAl: " + tasaEA);
        log.info("TASA EFECTIVA ANUAl CON FOMRATO 2 DECIMALES: " + NumberFormat.getInstance().parse(df2.format(tasaEA)).doubleValue());
        return tasaEA;
    }
}
