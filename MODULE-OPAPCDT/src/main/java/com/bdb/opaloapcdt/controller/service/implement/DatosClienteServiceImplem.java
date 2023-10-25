package com.bdb.opaloapcdt.controller.service.implement;

import com.bdb.opaloapcdt.controller.service.interfaces.DatosCliente;
import com.bdb.opaloapcdt.persistence.JSONSchema.JSONClientDatos;
import com.bdb.opaloapcdt.persistence.Model.InformacionCliente;
import com.bdb.opaloshare.controller.service.interfaces.SharedService;
import com.bdb.opaloshare.persistence.entity.HisClientesLargeEntity;
import com.bdb.opaloshare.persistence.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatosClienteServiceImplem implements DatosCliente {
	
	private final SharedService serviceShared;

	private final RepositoryDatosCliente repoDatosCliente;

	private final RepositoryTipCiud repoCiud;

	private final RepositoryTipDepar repoDepar;

	private final RepositoryTipDane repoDane;

	private final RepositoryTipCIUU repoCiiu;

	private final RepositoryTipSegmento repoSegmento;
	
	private static final Logger LOG = LoggerFactory.getLogger(DatosClienteServiceImplem.class); 
	
	public DatosClienteServiceImplem(SharedService serviceShared, RepositoryDatosCliente repoDatosCliente, RepositoryTipCiud repoCiud, RepositoryTipDepar repoDepar, RepositoryTipDane repoDane, RepositoryTipCIUU repoCiiu, RepositoryTipSegmento repoSegmento) {
		this.serviceShared = serviceShared;
		this.repoDatosCliente = repoDatosCliente;
		this.repoCiud = repoCiud;
		this.repoDepar = repoDepar;
		this.repoDane = repoDane;
		this.repoCiiu = repoCiiu;
		this.repoSegmento = repoSegmento;
	}

	@Override
	public InformacionCliente insertarDatosCliente(List<JSONClientDatos> requestList) {
		LOG.info("Se ingresa a generar el objeto del cliente para ser almacenado.");
		InformacionCliente infoClientes = new InformacionCliente();
		try {
			List<HisClientesLargeEntity> informacionCliente = new ArrayList<>();
			for(JSONClientDatos request : requestList) {
				HisClientesLargeEntity datosCliente = new HisClientesLargeEntity();
				datosCliente.setNumTit(request.getIdentificacion().getNumId());
				datosCliente.setOplTipidTblCodId(Integer.parseInt(request.getIdentificacion().getTipoId()));
				datosCliente.setNomTit(request.getNombre());
				datosCliente.setDirTit(request.getDireccion().trim().isEmpty() ? "NO INFORMADA" : request.getDireccion());
				datosCliente.setTelTit(request.getTelefono().getTelefono().trim().isEmpty() ? "0000000000" : request.getTelefono().getTelefono());
				datosCliente.setFaxTit(request.getTelefono().getNumero_fax());
				datosCliente.setExtension(request.getTelefono().getExtension());
				datosCliente.setCorreo(request.getCorreoElectronico());
				datosCliente.setClaPer(request.getClasePersona());
				datosCliente.setDeclaRenta(request.getDeclaraRenta());
				datosCliente.setIndExtra(request.getIndicadorExtranjero());
				datosCliente.setFechaNacimiento(serviceShared.formatoFechaSQL(request.getFechaNacimiento()));
				datosCliente.setPaisNacimiento(Integer.parseInt(repoDatosCliente.codigoPaisObtenido(request.getPaisNacimiento())));
				LOG.info("Codigo pais obtenido.");
				datosCliente.setOplTipciiuTblCodCiiu(Integer.parseInt(repoCiiu.codigoObtenidoCIIU(request.getCodigoCIUU())));
				LOG.info("Codigo CIIU obtenido.");
				datosCliente.setOplTipciudTblCodCiud(Integer.parseInt(repoCiud.codigoObtenidoCiud(request.getCodigoCiudad(),request.getCodigoDepartamento(),request.getCodigoPais())));
				LOG.info("Codigo Ciudad obtenido.");
				datosCliente.setOplTipdeparTblCodDep(Integer.parseInt(repoDepar.codigoObtenidoDep(request.getCodigoDepartamento(),request.getCodigoPais())));
				LOG.info("Codigo Departamento obtenido.");
				datosCliente.setOplTippaisTblCodPais(Integer.parseInt(repoDatosCliente.codigoPaisObtenido(request.getCodigoPais())));
				LOG.info("Codigo pais obtenido.");
				datosCliente.setOplTipsegmentoTblCodSegmento(Integer.parseInt(repoSegmento.codigoObtenidoSegmento(request.getCodigoSegmentoComercial())));
				LOG.info("Codigo segmento obtenido.");
				datosCliente.setRetencion(request.getRetencion());
				datosCliente.setOplParTipdaneTblCodDane(repoDane.codigoObtenidoDane(request.getCodigoDane()));
				LOG.info("Codigo DANE obtenido.");
				informacionCliente.add(datosCliente);
			}
			infoClientes.setEstado(true);
			infoClientes.setListClients(informacionCliente);
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage());
			infoClientes.setEstado(false);
		}
		LOG.info("Se termina de generar el objeto del cliente para ser almacenado.");
		return infoClientes;
	}

}
