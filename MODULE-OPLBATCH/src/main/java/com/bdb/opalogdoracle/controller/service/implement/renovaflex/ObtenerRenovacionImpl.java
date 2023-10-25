package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ControlEstadoService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.NotificacionDcvBtaService;
import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ObtenerRenovacionService;
import com.bdb.opaloshare.controller.service.interfaces.CrucePatrimonioRenautDTO;
import com.bdb.opaloshare.persistence.entity.HisCDTSLargeEntity;
import com.bdb.opaloshare.persistence.entity.HisTranpgEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import com.bdb.opaloshare.persistence.repository.OplHisTranpgDownTbl;
import com.bdb.opaloshare.persistence.repository.RepositoryHisRenovaCdt;
import com.bdb.opaloshare.persistence.repository.RepositorySalRenautdig;
import com.bdb.opaloshare.persistence.repository.RepositoryTransaccionesPago;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@CommonsLog
public class ObtenerRenovacionImpl implements ObtenerRenovacionService {

    private final RepositorySalRenautdig repositorySalRenautdig;

    private final ControlEstadoService controlEstadoService;

    private final NotificacionDcvBtaService notificacionDcvBtaService;



    private final RepositoryTransaccionesPago repositoryTransaccionesPago;
//
    private final RepositoryHisRenovaCdt repositoryHisRenovaCdt;

    public ObtenerRenovacionImpl(RepositorySalRenautdig repositorySalRenautdig,
                                 RepositoryTransaccionesPago repositoryTransaccionesPago,
                                 RepositoryHisRenovaCdt repositoryHisRenovaCdt,
                                 ControlEstadoService controlEstadoService,
                                 NotificacionDcvBtaService notificacionDcvBtaService, JdbcTemplate jdbcTemplate) {
        this.repositorySalRenautdig = repositorySalRenautdig;
        this.repositoryTransaccionesPago = repositoryTransaccionesPago;
        this.repositoryHisRenovaCdt = repositoryHisRenovaCdt;
        this.controlEstadoService = controlEstadoService;
        this.notificacionDcvBtaService = notificacionDcvBtaService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean obtenerRenovaciones() {
        try{
            log.info("start repositorySalRenautdig.findAll()...");
            List<SalRenautdigEntity> listSalRenautDig = repositorySalRenautdig.findAll();

            log.info("Busca los CDTs con estado P en la tabla de salidaRenaut ");
            List<SalRenautdigEntity> cdtEstadoC =
                    listSalRenautDig.stream().filter(name -> name.getEstadoV().equals("P")).collect(Collectors.toList());

            if (cdtEstadoC.isEmpty()) {
                log.info("start repositorySalRenautdig.deleteAll()...");
                repositorySalRenautdig.deleteAll(listSalRenautDig);
            }

            List<CrucePatrimonioRenautDTO> crucePatRenautList = repositorySalRenautdig.loadInfoFromCrossRenautData();
            log.info("crucePatRenautList: " + crucePatRenautList.toString());
            List<SalRenautdigEntity> salRenautdig = new ArrayList<>();

            crucePatRenautList.forEach(dataSalRenaut -> {
                log.info("start crucePatRenautList.forEach(dataSalRenaut ->)...");
                SalRenautdigEntity cdtExisteSalRenaut = repositorySalRenautdig.findByNumCdt(Long.valueOf(dataSalRenaut.getNumCdt()));
                log.info("Consulto exitosamente si el CDT existe o no en la tabla de salida de renautDig... ");
                if (cdtExisteSalRenaut == null) {
                    log.info("CDT SE GUARDA EN SALRENAUT DADO QUE NO EXISTIA EN ESTA TABLA... ");
                    SalRenautdigEntity salRenautDig = new SalRenautdigEntity();

                    salRenautDig.setCodIsin(dataSalRenaut.getCodIsin());
                    salRenautDig.setNumCdt(Long.valueOf(dataSalRenaut.getNumCdt()));
                    salRenautDig.setTipId(!dataSalRenaut.getTipId().equals("CC") ? "CC" : dataSalRenaut.getTipId());
                    salRenautDig.setNumTit(dataSalRenaut.getNumTit());
                    salRenautDig.setNomTit(dataSalRenaut.getNomTit());
                    salRenautDig.setCapPg(new BigDecimal(dataSalRenaut.getCapPg()));
                    salRenautDig.setIntBruto(new BigDecimal(dataSalRenaut.getIntBruto()));
                    salRenautDig.setRteFte(new BigDecimal(dataSalRenaut.getRteFte()));
                    salRenautDig.setIntNeto(new BigDecimal(dataSalRenaut.getIntNeto()));
                    salRenautDig.setFormaPago("RENOVACION_DIGITAL");
                    salRenautDig.setEstadoV("P");


                    log.info("salRenautdig: " + salRenautDig.toString());
                    salRenautdig.add(salRenautDig);

                    //AUN EN DESARROLLO
                    log.info("preparando Data para insertar en hisTranpg: ");
                    List<HisTranpgEntity> hisTranpg = new ArrayList<>();

                    String consultaSQL = "SELECT * FROM OPL_HIS_CONDICIONCDTS_LARGE_TBL WHERE OPL_CTRCDTS_TBL_NUM_CDT = ?";
                    List<Map<String, Object>> hisCondiciones = jdbcTemplate.queryForList(consultaSQL, dataSalRenaut.getNumCdt());
                    log.info("CONSULTA DE CONDICIONES: " + hisCondiciones);

                    BigDecimal totalCapital = new BigDecimal(dataSalRenaut.getCapPg());
                    BigDecimal totalRendimientos = new BigDecimal(dataSalRenaut.getIntNeto());

                    for (Map<String, Object> row : hisCondiciones) {
                        BigDecimal capital = new BigDecimal(row.get("CAPITAL").toString());
                        BigDecimal rendimientos = new BigDecimal(row.get("RENDIMIENTOS").toString());

                        // Realiza operaciones con los valores
                        totalCapital= totalCapital.add(capital);
                        totalRendimientos = rendimientos;
                    }



                    //BigDecimal capital = dataSalRenaut.getCapPg() - hisCondiciones.get()
                    if (totalCapital.compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " capital " + new BigDecimal(dataSalRenaut.getCapPg()));
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                totalCapital.toString(), "3", 7));
                    } else {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " capital 0");
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                "0", "3", 7));
                    }

                    if (totalRendimientos.equals(new BigDecimal(1))) {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " intereses " + new BigDecimal(dataSalRenaut.getIntNeto()));
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                dataSalRenaut.getIntNeto(), "4", 7));
                    } else {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " intereses 0");
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                "0", "4", 7));
                    }

                    if (new BigDecimal(dataSalRenaut.getRteFte()).compareTo(BigDecimal.ZERO) > 0) {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " rteFte " + new BigDecimal(dataSalRenaut.getRteFte()));
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                dataSalRenaut.getRteFte(), "5", 8));
                    } else {
                        System.out.println("cdt " + dataSalRenaut.getNumCdt() + " - " + " rteFte 0");
                        hisTranpg.add(guardarHisTranpg(dataSalRenaut.getNumTit(), "2", dataSalRenaut.getNumCdt(), dataSalRenaut.getNumTit(),
                                dataSalRenaut.getOficina(), dataSalRenaut.getOficina(), dataSalRenaut.getNumCdt(),
                                "0", "5", 8));
                    }
                }
            });
            if (!salRenautdig.isEmpty()) {
                log.info("repositorySalRenautdig.saveAll...");
                repositorySalRenautdig.saveAll(salRenautdig);
            }

            log.info("PASO: OBTENER RENOVACIONES COMPLETADO (PASO 1)");
            controlEstadoService.actualizarControlRenovacion("EJECUTANDOSE", 2);
            controlEstadoService.actualizarHistorialResultado("paso1");

            return true;
        }catch (Exception e){
            log.info("PASO: OBTENER RENOVACIONES, **FALLIDO** (PASO 1)");
            controlEstadoService.actualizarControlRenovacion("FALLIDO", 1);
            notificacionDcvBtaService.enviarNotificacion("RENAUT_FAIL");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se genero error al momento de consumir " +
                    "el servicio Obtener los CDTs Digitales a renovar.");
        }
    }

    /**
     * Metodo encargado de guardar en la tabla {@link HisTranpgEntity} los valores monetarios que seran usados
     * en el traductor CADI, los campos a guardar son
     * Capital
     * Intereses Netos
     * Rte Fuente
     *
     * @param idCliente     Identificador del Cliente
     * @param tipTran       Tipo de Transaccion
     * @param nroPord       Numero de Producto
     * @param idBen         Identificador del Beneficiario
     * @param unidadOrigen  Unidad Origen
     * @param unidadDestino Unidad Destino
     * @param numCdt        Numero de Cdt
     * @param valor         Valor en pesos
     * @param proceso       Proceso
     * @param tblTipTransa  Tipo Transaccion
     * @return hisTranpgEntity
     */

    @Autowired
    OplHisTranpgDownTbl oplHisTranpgDownTbl;

    private final JdbcTemplate jdbcTemplate;
    private HisTranpgEntity guardarHisTranpg(String idCliente, String tipTran, String nroPord, String idBen, String unidadOrigen,
                                             String unidadDestino, String numCdt, String valor, String proceso, Integer tblTipTransa) {
        log.info("VA A GUARDAR INFORMACION");
        HisTranpgEntity hisTranpgEntity = new HisTranpgEntity();
        HisCDTSLargeEntity hisCDTSLargeEntity = new HisCDTSLargeEntity();
        hisTranpgEntity.setIdCliente(Long.valueOf(idCliente));
        hisTranpgEntity.setTipTran(tipTran);
        hisTranpgEntity.setNroPordDestino(nroPord);
        hisTranpgEntity.setIdBeneficiario(Long.valueOf(idBen));
        hisTranpgEntity.setUnidOrigen(unidadOrigen);
        hisTranpgEntity.setUnidDestino(Integer.valueOf(unidadDestino));
        hisCDTSLargeEntity.setNumCdt(numCdt);
        hisTranpgEntity.setHisCDTSLargeEntity(hisCDTSLargeEntity);
        hisTranpgEntity.setValor(new BigDecimal(valor));
        hisTranpgEntity.setProceso(proceso);
        hisTranpgEntity.setOplTiptransTblTipTrasaccion(tblTipTransa);
        oplHisTranpgDownTbl.save(hisTranpgEntity);
        return hisTranpgEntity;
    }
}
