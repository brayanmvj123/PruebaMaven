package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoCdtTasaFijaService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@CommonsLog
public class CalculoCdtTasaFijaImpl implements CalculoCdtTasaFijaService {

    @Autowired
    private CalculadoraService calculadoraService;

    /**
     * <p>Este metodo realiza <u>solo</u> los calculos matematicos del tipo: <u><b><i>TASA FIJA</i></b></u>.</p>
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
     *      <li value="7"><p>Calcular los <i>Intereses</i> generados por la <b>Tasa Fija</b></p></li>
     * </ol>
     *
     * @param request Contiene los parametros enviados por el cliente a simlular la Tasa indicada.
     * @return Los resultados de las distintas operaciones y son almacenas en un objeto {@link JSONResultSimuladorCuota}.
     * @throws ParseException
     */
    @Override
    public JSONResultSimuladorCuota calculoTasaFija(JSONRequestSimuladorCuota request) throws ParseException {

        double tasaFija = calculadoraService.convertDouble(request.getParametros().getTasa());
        String periodicidad = request.getParametros().getPeriodicidad();
        String base = request.getParametros().getBase();
        double diasPlazo = calculadoraService.convertDouble(request.getParametros().getDiasPlazo());
        double capital = calculadoraService.convertDouble(request.getParametros().getCapital());
        double retencion = calculadoraService.convertDouble(request.getParametros().getRetencion());

        double baseDias = calculadoraService.baseDias(base);
        double periodicidadDias = calculadoraService.periodicidadDias(periodicidad, diasPlazo);
        double periodo = calculadoraService.periodo(baseDias, periodicidadDias);
        double tasaNominal = calculadoraService.calcularTasaNominal(tasaFija, periodo);
        double numPeriodos = calculadoraService.numerosPeriodos(diasPlazo, periodicidadDias);
        return calculadoraService.intereses(tasaNominal, diasPlazo, baseDias, capital, retencion, numPeriodos, tasaFija,
                request.getParametros().getTipoTasa());

    }
}
