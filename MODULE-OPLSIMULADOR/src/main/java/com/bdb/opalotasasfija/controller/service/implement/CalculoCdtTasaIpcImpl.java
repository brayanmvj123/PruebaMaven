package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opaloshare.util.Constants;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoCdtTasaIpcService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

@Service
@CommonsLog
public class CalculoCdtTasaIpcImpl implements CalculoCdtTasaIpcService {

    @Autowired
    private CalculadoraService calculadoraService;

    DecimalFormat df2 = new DecimalFormat(Constants.DECIMAL_FORMAT_2);

    /**
     * <p>Este metodo realiza <u>solo</u> los calculos matematicos del tipo: <u><b><i>TASA IPC</i></b></u>.</p>
     * <ol>
     *     <li><p>Los valores numericos que son de tipo String son convertidos a <u><b><i>Doubles</i></b></u>.</p></li>
     *     <li><p>Se valida el valor enviado en la <u>base</u> exista en la BD de Opalo.</p></li>
     *     <li><p>Se valida el valor enviado en la <u>periodicidad</u> exista en la BD de Opalo.</p></li>
     *     <li><p>Se calcula la periodicidad del tipo de tasa para calculo de la tasa efectiva anual vencida.</p></li>
     *     <li>
     *         <p>Se calcula la <u>Tasa Efectiva Anual</u>:</p>
     *         <center><b>((( 1 + @tasa ) * ( 1 + @spread/100 )) -1 ) * 100</b></center>
     *         <p><u><b>Nota</b></u>: El <u>spread</u> debe ser dividido entre <u>100</u>.</p>
     *     </li>
     *     <li>
     *         <p>Se convierte la periodicidad a <u>dias</u>.</p>
     *         <p>Se la periodicidad es al plazo se toma el valor enviado.</p>
     *         <p>Si la periodicidad es diferente al plazo se multiplica por 30 dias (Cantidad de dias del mes).</p>
     *     </li>
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
     *      <li value="7"><p>Calcular los <i>Intereses</i> generados por la <b>Tasa IPC.</b></p></li>
     * </ol>
     *
     * @param request Contiene los parametros enviados por el cliente a simlular la Tasa indicada.
     * @return Los resultados de las distintas operaciones y son almacenas en un objeto {@link JSONResultSimuladorCuota}.
     * @throws ParseException
     */
    @Override
    public JSONResultSimuladorCuota calculoTasaIpc(JSONRequestSimuladorCuota request) throws ParseException {
        double tasa = calculadoraService.convertDouble(request.getParametros().getTasa());
        double baseDias = calculadoraService.baseDias(request.getParametros().getBase());
        double diasPlazo = calculadoraService.convertDouble(request.getParametros().getDiasPlazo());
        double capital = calculadoraService.convertDouble(request.getParametros().getCapital());
        double retencion = calculadoraService.convertDouble(request.getParametros().getRetencion());

        double periTipTasAVen = baseDias / 90;
        double tasaEfectiva = ((( 1 + calculadoraService.entreCien(tasa) ) *
                ( 1 + (calculadoraService.entreCien(calculadoraService.convertDouble(request.getParametros().getSpread())))) ) - 1 ) * 100;
        log.info("TASA EFECTIVA EFECTIVA ANUAL: " + tasaEfectiva);
        double periodicidadDias = calculadoraService.periodicidadDias(request.getParametros().getPeriodicidad(),
                calculadoraService.convertDouble(request.getParametros().getDiasPlazo()));
        double periodo = calculadoraService.periodo(baseDias, periodicidadDias);

        double tasaNominal = calculadoraService.calcularTasaNominal(tasaEfectiva, periodo);
        log.info("TASA NOMINAL: " + tasaNominal);
        double numPeriodos = calculadoraService.numerosPeriodos(diasPlazo, periodicidadDias);
        return calculadoraService.intereses(tasaNominal, diasPlazo, baseDias, capital, retencion, numPeriodos,
                NumberFormat.getInstance().parse(df2.format(tasaEfectiva)).doubleValue(), request.getParametros().getTipoTasa());
    }
}
