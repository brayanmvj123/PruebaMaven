package com.bdb.opaloapcdt.controller.service.control;
//bra
import com.bdb.opaloapcdt.controller.service.interfaces.*;
import com.bdb.opaloapcdt.persistence.Model.InformacionCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionCliente;
import com.bdb.opaloapcdt.persistence.Model.InformacionClixCDT;
import com.bdb.opaloapcdt.persistence.Model.InformacionTranpg;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONAperCDT;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONAsignarNumCDT;
import com.bdb.opaloshare.persistence.model.response.RequestResult;
import com.bdb.opaloshare.persistence.model.response.ResultMessage;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*", maxAge = 0)
@RequestMapping("CDTSDesmaterializado/v1")
@CommonsLog
public class ControllerCDTsAPER {
	
	private final DatosCliente serviceDatosCliente;
	
	private final CondicionesCDTService serviceCondicionesCDT;
	
	private final TransaccionesPagosCDTService serviceTransPg;
	
	private final AperturaCDTDesService serviceAperturaCDT;
	
	private final CDTxClienteService serviceClixCDT;

	public ControllerCDTsAPER(DatosCliente serviceDatosCliente, CondicionesCDTService serviceCondicionesCDT,
							  TransaccionesPagosCDTService serviceTransPg, AperturaCDTDesService serviceAperturaCDT,
							  CDTxClienteService serviceClixCDT){
		this.serviceDatosCliente = serviceDatosCliente;
		this.serviceCondicionesCDT = serviceCondicionesCDT;
		this.serviceTransPg = serviceTransPg;
		this.serviceAperturaCDT = serviceAperturaCDT;
		this.serviceClixCDT = serviceClixCDT;
	}

    /**
     * Método de apertura de CDT Desmaterializado.
     *
     * @param request Cuerpo de la solicitud de CDT.
     * @return respuesta de la operación.
     * @throws SQLException 
     * @throws SQLDataException 
     */
    @PostMapping(value = "aperturaCDTsDes", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<RequestResult> aperCDTDes(@Valid @RequestBody JSONAperCDT request , HttpServletRequest JSONrequest) throws Exception {
		log.info("NUMERO DE CDT APERTURAR: "+request.getCdt().getNumCdtDigital());
		LocalDateTime horaInicio = LocalDateTime.now();
		log.info("HORA DE CONSUMO: " + LocalDateTime.parse(request.getCdt().getFecha()));
		log.info("Hora Inicio: " + horaInicio);
		if (!serviceAperturaCDT.saberSiExisteNumCdt(request.getCdt().getNumCdtDigital())) {
			InformacionCliente clientes = serviceDatosCliente.insertarDatosCliente(request.getCliente());
			InformacionCDT condicionesCDT = serviceCondicionesCDT.almacenarCondicionesCDT(request.getCdt(), clientes.isEstado());
			InformacionClixCDT cdtxCliente = serviceClixCDT.almacenarCDTxCliente(request.getCliente(), request.getCdt(), condicionesCDT.isEstado());
			InformacionTranpg transacPg = serviceTransPg.almacenarTransaccionesPagos(request.getTransaccionesPagoCdt(), cdtxCliente.isEstado() , request.getCdt().getNumCdtDigital());
			log.info("LAS VALIDACIONES FUERON EXITOSAS, SE CONTINUAN CON LA APERTURA DEL CDT: "+request.getCdt().getNumCdtDigital());
			if (clientes.isEstado() && condicionesCDT.isEstado() && cdtxCliente.isEstado() && transacPg.isEstado()) {
				log.info("Inicia la apertura del CDT Digital: "+request.getCdt().getNumCdtDigital());
				serviceAperturaCDT.crearCDTDes(clientes,condicionesCDT,cdtxCliente,transacPg, horaInicio,
						LocalDateTime.parse(request.getCdt().getFecha()));
				RequestResult<ResultMessage> result = new RequestResult<>(JSONrequest, HttpStatus.ACCEPTED);
				result.setResult(new ResultMessage("La apertura del CDT Desmaterialiazado ha sido exitoso"));
				log.info("CDT Digital aperturado.");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
			} else {
				RequestResult<ResultMessage> result = new RequestResult<>(JSONrequest, HttpStatus.BAD_GATEWAY);
				result.setResult(new ResultMessage(("Error !!! No se ha podido aperturar el CDT Desmaterializado")));
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(result);
			}
		}else{
			RequestResult<ResultMessage> result = new RequestResult<>(JSONrequest, HttpStatus.BAD_GATEWAY);
			result.setResult(new ResultMessage(("Error !!! El número de CDT Digital utilizado para esta apertura YA EXISTE")));
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(result);
		}
    }

    @PostMapping(value = "asignarNumCDTDigital" , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String asignarNumCDTDigital(@Valid @RequestBody JSONAsignarNumCDT request){
    	return serviceAperturaCDT.asignarNumCdtDigital();
	}
}
