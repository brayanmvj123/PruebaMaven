package com.bdb.opalotasasfija.controller.service.implement;

import com.bdb.opalotasasfija.controller.service.interfaces.CalculadoraService;
import com.bdb.opalotasasfija.controller.service.interfaces.CalculoCdtTasaIbrService;
import com.bdb.opalotasasfija.controller.service.interfaces.SimuladorCuotaService;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.JSONRequestSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.request.simuladorcuota.ParametersSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseSimuladorCuota;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResponseStatus;
import com.bdb.opalotasasfija.persistence.JSONSchema.response.simuladorcuota.JSONResultSimuladorCuota;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Service
@CommonsLog
public class SimuladorCuotaServiceImpl implements SimuladorCuotaService {

    @Autowired
    private CalculoCdtTasaFijaImpl calculoTasaFija;
    @Autowired
    private CalculoCdtTasaDtfImpl calculoTasaDtf;
    @Autowired
    private CalculoCdtTasaIpcImpl calculoTasaIpc;
    @Autowired
    private CalculoCdtTasaIbrService calculoTasaIbr;
    /**
     * Este metodo permite realiza la verificación y consumo del metodo de acuerdo al <u>tipo de tasa</u> enviada en el
     * <u>request</u>.
     * De acuerdo al codigo enviado se valida en la tabla {@link com.bdb.opaloshare.persistence.entity.TiptasaParDownEntity}
     * que tipo de tasa es:
     * <table style="border: 1px solid black; width: 100%; text-align: center;">
     *     <tr>
     *         <th style="border: 1px solid black;">CODIGO</th>
     *         <th style="border: 1px solid black;">DESCRIPCIÓN</th>
     *     </tr>
     *     <tr>
     *         <td style="border: 1px solid black;">1</td>
     *         <td style="border: 1px solid black;">FIJA</td>
     *     </tr>
     *     <tr>
     *         <td style="border: 1px solid black;">2</td>
     *         <td style="border: 1px solid black;">DTF</td>
     *     </tr>
     *     <tr>
     *          <td style="border: 1px solid black;">3</td>
     *          <td style="border: 1px solid black;">IPC</td>
     *     </tr>
     *     <tr>
     *          <td style="border: 1px solid black;">6</td>
     *          <td style="border: 1px solid black;">IBR un mes</td>
     *     </tr>
     *     <tr>
     *          <td style="border: 1px solid black;">7</td>
     *          <td style="border: 1px solid black;">IBR Overnight</td>
     *     </tr>
     *     <tr>
     *          <td style="border: 1px solid black;">8</td>
     *          <td style="border: 1px solid black;">IBR Tres Meses</td>
     *     </tr>
     *     <tr>
     *          <td style="border: 1px solid black;">9</td>
     *          <td style="border: 1px solid black;">IBR seis Meses</td>
     *     </tr>
     * </table>
     * <p>Y con la ayuda de la funcion <pre>switch(tipoTasa){}</pre> se consume el metodo adecuado para la tasa.</p>
     *
     * @param request    Contiene todos los parametros enviados para realizar la simulación. ({@link JSONRequestSimuladorCuota})
     * @param urlRequest Contiene la URL del servicio.
     * @return {@link JSONResponseSimuladorCuota} , la respuesta se componene de:
     * <ul>
     *     <li>El <u>estado del servicio</u> al realizar la simulacion.</li>
     *     <li>El <u>request</u> enviado.</li>
     *     <li>Los <u>resultados</u> dados por la simulacion (calculos).</li>
     * </ul>
     * @throws ParseException Esta excepcion se puede obtener al momento de dar formato a un Double.
     * @see java.text.DecimalFormat
     * @see java.text.NumberFormat
     */
    @Override
    public JSONResponseSimuladorCuota simulador(JSONRequestSimuladorCuota request, HttpServletRequest urlRequest) throws ParseException {
        JSONResponseSimuladorCuota response = new JSONResponseSimuladorCuota();
        switch (request.getParametros().getTipoTasa()) {
            case 1:
                response = response(HttpStatus.OK,
                        urlRequest.getRequestURL().toString(),
                        request.getParametros(),
                        calculoTasaFija.calculoTasaFija(request));
                break;
            case 2:
                response = response(HttpStatus.OK,
                        urlRequest.getRequestURL().toString(),
                        request.getParametros(),
                        calculoTasaDtf.calculoCdtDtf(request));
                break;
            case 3:
                response = response(HttpStatus.OK,
                        urlRequest.getRequestURL().toString(),
                        request.getParametros(),
                        calculoTasaIpc.calculoTasaIpc(request));
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                response = response(HttpStatus.OK,
                        urlRequest.getRequestURL().toString(),
                        request.getParametros(),
                        calculoTasaIbr.calculoIbr(request));
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("El tipo de tasa [%s] no se encuentra entre los posibles cálculos establecidos.", request.getParametros().getTipoTasa()));
        }

        return response;
    }

    /**
     * Este metodo se encarga de dar formato al response. <u>Todos los parametros son <b>obligatorios</b>.</u>
     *
     * @param status     Es de tipo {@link HttpStatus}, con este parametro se obtiene el <u>codigo</u> y <u>mensaje</u>
     *                   del resultado de la simulación.
     * @param requestUrl Url del ambiente de donde se consumio el servicio.
     * @param parameters Es de tipo {@link ParametersSimuladorCuota}, contiene los parametros enviados en el request.
     * @param result     Es de tipo {@link JSONResultSimuladorCuota}, contienie el resultado de las operaciones de la
     *                   simulacion.
     * @return El resultado de la simulación, de tipo {@link JSONResponseSimuladorCuota}.
     * @see JSONResponseSimuladorCuota
     * @see HttpStatus
     * @see ParametersSimuladorCuota
     * @see JSONResultSimuladorCuota
     */
    public JSONResponseSimuladorCuota response(HttpStatus status, String requestUrl, ParametersSimuladorCuota parameters,
                                               JSONResultSimuladorCuota result) {
        JSONResponseStatus jsonResponseStatus = new JSONResponseStatus();
        jsonResponseStatus.setCode(status.value());
        jsonResponseStatus.setMessage(status.getReasonPhrase());
        return new JSONResponseSimuladorCuota(jsonResponseStatus, requestUrl, parameters, result);
    }

}
