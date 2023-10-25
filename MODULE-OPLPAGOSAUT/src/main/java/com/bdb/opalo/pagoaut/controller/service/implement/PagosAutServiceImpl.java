package com.bdb.opalo.pagoaut.controller.service.implement;

import com.bancodebogota.accounts.involvedparty.v1.CustInfoType;
import com.bancodebogota.customers.product.event.CDADelRqType;
import com.bancodebogota.customers.product.service.DelCDAFault;
import com.bancodebogota.customers.product.service.DelCDARequest;
import com.bancodebogota.customers.product.service.DelCDAResponse;
import com.bancodebogota.customers.product.service.DematerializedCertificateDepositManagement;
import com.bancodebogota.customers.product.v1.CDAInfoType;
import com.bancodebogota.ifx.base.v1.AcctBalListType;
import com.bancodebogota.ifx.base.v1.AcctBalType;
import com.bancodebogota.ifx.base.v1.BankInfoType;
import com.bancodebogota.ifx.base.v1.CurrencyAmountType;
import com.bancodebogota.ifx.base.v1.DepAcctIdType;
import com.bancodebogota.ifx.base.v1.NetworkTrnInfoType;
import com.bancodebogota.ifx.base.v1.PersonNameType;
import com.bancodebogota.ifx.base.v1.ProductType;
import com.bdb.opalo.pagoaut.controller.service.interfaces.PagosAutService;
import com.bdb.opalo.pagoaut.persistence.jsonschema.request.JSONCancelacionAut;
import com.bdb.opalo.pagoaut.persistence.jsonschema.request.TransaccionPago;
import com.bdb.opalo.pagoaut.persistence.jsonschema.response.ResponsePagCdt;
import com.bdb.opalo.pagoaut.persistence.jsonschema.response.ResponseTranpg;
import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationEntity;
import com.bdb.opaloshare.persistence.entity.HisCDTSCancelationPk;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCDTSCancelation;
import com.bdb.opaloshare.persistence.repository.RepositoryHisCtrCdts;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@CommonsLog
public class PagosAutServiceImpl implements PagosAutService {

    final DematerializedCertificateDepositManagement servicioBus;
    final RepositoryTipVarentorno repositoryTipVarentorno;
    final RepositoryHisCDTSCancelation repositoryHisCDTSCancelation;
    final RepositoryHisCtrCdts repositoryHisCtrCdts;

    public PagosAutServiceImpl(DematerializedCertificateDepositManagement servicioBus,
                               RepositoryTipVarentorno repositoryTipVarentorno,
                               RepositoryHisCDTSCancelation repositoryHisCDTSCancelation, RepositoryHisCtrCdts repositoryHisCtrCdts) {
        this.servicioBus = servicioBus;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
        this.repositoryHisCDTSCancelation = repositoryHisCDTSCancelation;
        this.repositoryHisCtrCdts = repositoryHisCtrCdts;
    }

    @Override
    public ResponsePagCdt consumoPagosAut(JSONCancelacionAut jsonCancelacionAut) throws DelCDAFault {
        List<ResponseTranpg> responseTranpgList = new ArrayList<>();
        // Consulto información en la tabla OPL_HIS_CTRCDTS_LARGE_TBL por número de CDT que viene de data
        List<HisCtrCdtsEntity> hisCtrCdtsEntity =  repositoryHisCtrCdts.findByNumCdt(jsonCancelacionAut.getNumCdt());

        if(hisCtrCdtsEntity == null || hisCtrCdtsEntity.isEmpty()) throw new IndexOutOfBoundsException("EL CDT NO EXTISTE");

        for (int i = 0; i < jsonCancelacionAut.getTransaccionPagoList().size(); i++) {
            responseTranpgList.add(getResponsePagCdt(jsonCancelacionAut,
                    jsonCancelacionAut.getTransaccionPagoList().get(i), hisCtrCdtsEntity));
            if (responseTranpgList.get(0).getResult().equals("0"))
                log.info("Capital pagado correctamente, continua el pago ...");
            else {
                log.error("El pago ha sido fallido");
                break;
            }
        }
        log.warn("Resultado: " + responseTranpgList.get(0).getTipPayment());
        return validateResponse(jsonCancelacionAut, responseTranpgList, hisCtrCdtsEntity.get(0).getNovedadV());
    }

    private ResponsePagCdt validateResponse(JSONCancelacionAut jsonCancelacionAut, List<ResponseTranpg> responseTranpgList, Integer novedadV) {
        JSONObject jsonObject = new JSONObject();
        if (responseTranpgList.stream().filter(pg -> pg.getResult().equals("0")).count() >= 1) {
            almacenarPago(jsonCancelacionAut, jsonObject.put("response", responseTranpgList).toString());
            return new ResponsePagCdt(HttpStatus.OK, LocalDateTime.now(), "EXITO: " + (novedadV.equals(2) ? "Se va a Cancelar -> 1" : "Se va a Renovar -> 2"), responseTranpgList);
        } else {
            almacenarPago(jsonCancelacionAut, jsonObject.put("response", responseTranpgList).toString());
            return new ResponsePagCdt(HttpStatus.NON_AUTHORITATIVE_INFORMATION, LocalDateTime.now(), "ERROR", responseTranpgList);
        }
    }

    private ResponseTranpg getResponsePagCdt(JSONCancelacionAut jsonCancelacionAut, TransaccionPago transaccionPago, List<HisCtrCdtsEntity> hisCtrCdtsEntity)
            throws DelCDAFault {
        JSONObject getParams = new JSONObject(repositoryTipVarentorno.findByDescVariable("PARAM_DESMATERIALIZEDCERT")
                .getValVariable());

        String acctCur = hisCtrCdtsEntity.get(0).getNovedadV().equals(1) ? "RENOVAR POR ESO ES 2" : "CANCELAR POR ESO ES 1";
        log.info("El cliente va: "  + acctCur );

        CDADelRqType cdaDelRqType = new CDADelRqType();
        cdaDelRqType.setRqUID(generarUUID());
        cdaDelRqType.setTrnSeqCntr(getParams.get("TrnSeqCntr").toString());
        cdaDelRqType.setRevClientTrnSeq(getParams.get("RevClientTrnSeq").toString());

        cdaDelRqType.setNextDay(getParams.get("NextDay").toString());
        cdaDelRqType.setClientTerminalSeqId(getParams.get("ClientTerminalSeqId").toString());
        cdaDelRqType.setTrnStatusType(getParams.get("TrnStatusType").toString());

        NetworkTrnInfoType networkTrnInfoType = new NetworkTrnInfoType();
        networkTrnInfoType.setNetworkOwner(getParams.getJSONObject("NetworkTrnInfo").get("NetworkOwner").toString());
        networkTrnInfoType.setTerminalId(getParams.getJSONObject("NetworkTrnInfo").get("TerminalId").toString());
        networkTrnInfoType.setBankId(getParams.getJSONObject("NetworkTrnInfo").get("BankId").toString());
        networkTrnInfoType.setName(getParams.getJSONObject("NetworkTrnInfo").get("Name").toString());
        cdaDelRqType.setNetworkTrnInfo(networkTrnInfoType);

        cdaDelRqType.setClientDt(generarFecha());
        cdaDelRqType.setTrnType(getParams.get("TrnType").toString());

        CustInfoType custInfoType = new CustInfoType();
        custInfoType.setTypeId(jsonCancelacionAut.getTipoId());
        custInfoType.setParticipantId(completarValor(jsonCancelacionAut.getNumeroId(), 11));
        PersonNameType personNameType = new PersonNameType();
        personNameType.setFullName(validarLongitudNombre(jsonCancelacionAut.getNombreBeneficiario()));
        custInfoType.setPersonName(personNameType);
        cdaDelRqType.getCustInfo().add(custInfoType);

        ProductType productType = new ProductType();
        productType.setProductCode(jsonCancelacionAut.getNumCdt().toString());
        productType.setProductNum(jsonCancelacionAut.getCodIsin());
        productType.setAcctCur(hisCtrCdtsEntity.get(0).getNovedadV() >= 2 ? "1" : "2");
        cdaDelRqType.setProduct(productType);

        BankInfoType bankInfoType = new BankInfoType();
        bankInfoType.setBranchId(completarValorOficina(jsonCancelacionAut.getOficinaOrigen()));
        cdaDelRqType.setBankInfo(bankInfoType);

        cdaDelRqType.setBranchId(completarValorOficina(jsonCancelacionAut.getOficinaOrigen()));

        DepAcctIdType depAcctIdType = new DepAcctIdType();
        depAcctIdType.setAcctId(completarValor(jsonCancelacionAut.getNumCta().toString(), 9));
        depAcctIdType.setAcctType(validarTipoCta(jsonCancelacionAut.getTipoCtaInv()));
        BankInfoType bankInfoType1 = new BankInfoType();
        bankInfoType1.setBranchId(completarValorOficina(jsonCancelacionAut.getOficinaRadic()));
        depAcctIdType.setBankInfo(bankInfoType1);
        cdaDelRqType.setDepAcctIdTo(depAcctIdType);

        CDAInfoType cdaInfoType = new CDAInfoType();

        DepAcctIdType depAcctIdType1 = new DepAcctIdType();
        depAcctIdType1.setAcctId(completarValor(jsonCancelacionAut.getNumCta().toString(), 9));
        depAcctIdType1.setAcctType(validarTipoCta(jsonCancelacionAut.getTipoCta()));
        BankInfoType bankInfoType2 = new BankInfoType();
        bankInfoType2.setBranchId(completarValorOficina(jsonCancelacionAut.getOficinaRadic()));
        depAcctIdType1.setBankInfo(bankInfoType2);
        cdaInfoType.getDepAcctId().add(depAcctIdType1);

        AcctBalListType acctBalListType = new AcctBalListType();

        AcctBalType acctBalType = new AcctBalType();
        acctBalType.setBalType(validarTipoPago(transaccionPago.getTipoPago()));
        CurrencyAmountType currencyAmountType = new CurrencyAmountType();
        currencyAmountType.setAmt(new BigDecimal(transaccionPago.getValorAbono().toString()));
        acctBalType.getCurAmt().add(currencyAmountType);
        acctBalListType.getAcctBal().add(acctBalType);

        cdaInfoType.getAcctBalList().add(acctBalListType);

        cdaDelRqType.setCDAInterestInfo(cdaInfoType);

        DelCDARequest delCDARequest = new DelCDARequest();
        delCDARequest.setCDADelRq(cdaDelRqType);

        log.info("DelCDARequest test ");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DelCDARequest.class);
            Marshaller marshaller;
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.marshal(delCDARequest, new File("delCDARequest.xml"));
            marshaller.marshal(delCDARequest, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        DelCDAResponse delCDAResponse = servicioBus.delCDA(delCDARequest);
        log.info("STATUS SERVICE CODE RESPONSE BUS: " + delCDAResponse.getCDADelRs().getStatus().getStatusCode());
        log.info("DESCRIPTION SERVICE CODE RESPONSE BUS: " + delCDAResponse.getCDADelRs().getStatus().getStatusDesc());
        log.info("DESCRIPTION SERVICE CODE RESPONSE: " + delCDAResponse.getCDADelRs().getStatus().getServerStatusDesc());

        return messageResponse(delCDAResponse, delCDARequest, jsonCancelacionAut);
    }

    public ResponseTranpg messageResponse(DelCDAResponse delCDAResponse, DelCDARequest request, JSONCancelacionAut jsonCancelacionAut) {
        if (delCDAResponse.getCDADelRs().getStatus().getStatusCode().equals("0")) {
            log.info("Se realizo el pago de forma correcta.");
            return new ResponseTranpg(request.getCDADelRq().getCDAInterestInfo().getAcctBalList().get(0).getAcctBal().get(0).getBalType(),
                    delCDAResponse.getCDADelRs().getStatus().getServerStatusDesc(),
                    delCDAResponse.getCDADelRs().getStatus().getStatusCode());
        } else {
            log.info("Se rechazo el pago.");
            return new ResponseTranpg(request.getCDADelRq().getCDAInterestInfo().getAcctBalList().get(0).getAcctBal().get(0).getBalType(),
                    delCDAResponse.getCDADelRs().getStatus().getServerStatusDesc(),
                    delCDAResponse.getCDADelRs().getStatus().getStatusCode());
        }
    }

    public String generarUUID() {
        return UUID.randomUUID().toString();
    }

    public String generarFecha() {
        String formatoFecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String fecha = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(LocalDateTime.parse(formatoFecha).atZone(ZoneId.of("UTC-5")));
        log.info("FECHA ES: " + fecha);
        return fecha;
    }

    public String completarValorOficina(Integer valor) {
        return String.format("%04d", valor);
    }

    public String completarValor(String valor, int longitud) {
        int restante = longitud - valor.length();
        StringBuilder caracterAdicional = new StringBuilder();
        for (int i = 0; i < restante; i++) {
            caracterAdicional.append("0");
        }
        return caracterAdicional + valor;
    }

    public String validarTipoPago(Integer valor) {
        return valor == 3 ? "K" : "I";
    }

    public String validarTipoCta(Integer valor) {
        return valor == 1 ? "DDA" : "SDA";
    }

    private void almacenarPago(JSONCancelacionAut item, String resultadoPago) {
        if (item.getTransaccionPagoList().size() == 2) {
            guardarPago(item, resultadoPago);
        } else {
            Optional<HisCDTSCancelationEntity> cdtsCancelationById = repositoryHisCDTSCancelation
                    .findById(new HisCDTSCancelationPk(item.getCodIsin(), item.getNumCdt(), item.getNumeroId()));
            if (cdtsCancelationById.isPresent()) {
                String resultadoActualizado = anadirTipoPago(resultadoPago, cdtsCancelationById.get().getPago());
                guardarPago(item, resultadoActualizado);
            } else guardarPago(item, resultadoPago);
        }
    }

    private String anadirTipoPago(String resultadoPago, String resultadoAlmacenado) {
        JSONObject resultadoNuevo = new JSONObject(resultadoPago);

        JSONObject resultadoAntiguo = new JSONObject(resultadoAlmacenado);
        return resultadoAntiguo.getJSONArray("response").put(resultadoNuevo).toString();
    }

    private void guardarPago(JSONCancelacionAut item, String resultadoPago) {
        HisCDTSCancelationEntity hisCDTSCancelationEntity = new HisCDTSCancelationEntity(item.getCodIsin(),
                item.getNumCdt(),
                item.getNumeroId(),
                resultadoPago,
                LocalDateTime.now());
        repositoryHisCDTSCancelation.save(hisCDTSCancelationEntity);
    }


    private String validarLongitudNombre(String nombre) {
        return nombre.length() >= 40 ? nombre.substring(0, 39) : nombre;
    }
}
