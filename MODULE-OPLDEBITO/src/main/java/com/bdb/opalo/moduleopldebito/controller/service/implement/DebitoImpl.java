package com.bdb.opalo.moduleopldebito.controller.service.implement;

import com.bancodebogota.customers.involvedparty.v1.OwnerInfoListType;
import com.bancodebogota.customers.involvedparty.v1.OwnerInfoType;
import com.bancodebogota.customers.product.event.CDAAcctPmtAddRqType;
import com.bancodebogota.customers.product.service.CustomerProductManagement;
import com.bancodebogota.customers.product.service.SetCreditDepositAcctPaymentFault;
import com.bancodebogota.customers.product.service.SetCreditDepositAcctPaymentRequest;
import com.bancodebogota.customers.product.service.SetCreditDepositAcctPaymentResponse;
import com.bancodebogota.ifx.base.v1.*;
import com.bdb.opalo.moduleopldebito.controller.service.interfaces.DebitoService;
import com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.request.DebitoRequest;
import com.bdb.opalo.moduleopldebito.controller.service.model.jsonschema.response.ResponseDebito;
import com.bdb.opaloshare.persistence.repository.RepositoryTipVarentorno;
import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@CommonsLog
public class DebitoImpl implements DebitoService {

    private final CustomerProductManagement customerProductManagement;
    final RepositoryTipVarentorno repositoryTipVarentorno;
    public DebitoImpl(CustomerProductManagement customerProductManagement, RepositoryTipVarentorno repositoryTipVarentorno) {
        this.customerProductManagement = customerProductManagement;
        this.repositoryTipVarentorno = repositoryTipVarentorno;
    }

    @Override
    public ResponseDebito debito(DebitoRequest request) throws SetCreditDepositAcctPaymentFault {
        SetCreditDepositAcctPaymentRequest setCreditDepositAcctPaymentRequest = new SetCreditDepositAcctPaymentRequest();
        CDAAcctPmtAddRqType cdaAcctPmtAddRqType = new CDAAcctPmtAddRqType();

        //CONSULTA DESDE LA TABLA VARENTORNO
        JSONObject getParams = new JSONObject(repositoryTipVarentorno.findByDescVariable("PARAM_CUSTOMERPRODUCTS").getValVariable());

        cdaAcctPmtAddRqType.setRqUID(generarUUID());
        NetworkTrnInfoType networkTrnInfoType = new NetworkTrnInfoType();
        networkTrnInfoType.setNetworkOwner(getParams.getJSONObject("NetworkTrnInfo").get("NetworkOwner").toString());
        networkTrnInfoType.setTerminalId(getParams.getJSONObject("NetworkTrnInfo").get("TerminalId").toString());
        networkTrnInfoType.setBankId(getParams.getJSONObject("NetworkTrnInfo").get("BankId").toString());
        networkTrnInfoType.setName(getParams.getJSONObject("NetworkTrnInfo").get("Name").toString());
        cdaAcctPmtAddRqType.setNetworkTrnInfo(networkTrnInfoType);
        cdaAcctPmtAddRqType.setTrnStatusType(getParams.get("TrnStatusType").toString());

        cdaAcctPmtAddRqType.setClientDt(generarFecha());
        cdaAcctPmtAddRqType.setBranchId(completarValorOficina(request.getOficinaOrigen()));

        cdaAcctPmtAddRqType.setProduct(productType(request.getNumCdt(), getParams.getJSONObject("Product")));

        BankInfoType bankInfoType = new BankInfoType();
        bankInfoType.setBranchId(completarValorOficina(request.getOficinaOrigen()));
        cdaAcctPmtAddRqType.setBankInfo(bankInfoType);

        cdaAcctPmtAddRqType.setOwnerInfoList(ownerInfoListType(request));

        DepAcctIdType depAcctIdType = new DepAcctIdType();
        depAcctIdType.setAcctType(request.getTipoCta());
        depAcctIdType.setAcctId(completarValor(request.getNumCta().toString(), 9));
        depAcctIdType.setBankInfo(bankInfoType);
        cdaAcctPmtAddRqType.setDepAcctId(depAcctIdType);

        cdaAcctPmtAddRqType.setExtAcctBalList(extAccBalListType(request));

        cdaAcctPmtAddRqType.getRefInfo().add(refInfoType("EspeSvcCode","01"));
        cdaAcctPmtAddRqType.getRefInfo().add(refInfoType("GenericAcct","01"));
        cdaAcctPmtAddRqType.getRefInfo().add(refInfoType("SvcCode", "KK058"));

        setCreditDepositAcctPaymentRequest.setCDAAcctPmtAddRq(cdaAcctPmtAddRqType);

        log.info("SetCreditDepositAcctPaymentRequest test ");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SetCreditDepositAcctPaymentRequest.class);
            Marshaller marshaller;
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.marshal(delCDARequest, new File("delCDARequest.xml"));
            marshaller.marshal(setCreditDepositAcctPaymentRequest, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        SetCreditDepositAcctPaymentResponse setCreditDepositAcctPaymentResponse = customerProductManagement
                .setCreditDepositAcctPayment(setCreditDepositAcctPaymentRequest);
        return resultado(setCreditDepositAcctPaymentResponse);
    }

    private ResponseDebito resultado(SetCreditDepositAcctPaymentResponse response){
        if (response.getCDAAcctPmtAddRs().getStatus().getStatusCode().equals("0"))
            return new ResponseDebito(HttpStatus.OK, LocalDateTime.now(), "Debito exitoso.");
        else
            return new ResponseDebito(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), "Error: "+
                    response.getCDAAcctPmtAddRs().getStatus().getStatusCode() + " - " +
                    response.getCDAAcctPmtAddRs().getStatus().getServerStatusDesc());
    }

    public String generarUUID() {
        return UUID.randomUUID().toString();
    }

    private RefInfoType refInfoType(String refType, String refId) {
        RefInfoType refInfoType = new RefInfoType();
        refInfoType.setRefType(refType);
        refInfoType.getRefId().add(refId);
        return refInfoType;
    }

    private ExtAcctBalListType extAccBalListType(DebitoRequest request) {
        ExtAcctBalListType extAcctBalListType = new ExtAcctBalListType();

        ExtAcctBalType extAcctBalType = new ExtAcctBalType();
        extAcctBalType.setExtBalType("CashChrg");

        CurrencyAmountType currencyAmountType = new CurrencyAmountType();
        currencyAmountType.setAmt(request.getValor());
        extAcctBalType.setCurAmt(currencyAmountType);

        extAcctBalListType.getExtAcctBal().add(extAcctBalType);
        extAcctBalType.setEffDt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return extAcctBalListType;
    }

    private OwnerInfoListType ownerInfoListType(DebitoRequest request) {
        OwnerInfoListType ownerInfoListType = new OwnerInfoListType();
        OwnerInfoType ownerInfoType = new OwnerInfoType();
        OtherIdentDocType otherIdentDocType = new OtherIdentDocType();
        otherIdentDocType.setTypeId(request.getTipoId());
        otherIdentDocType.setParticipantId(request.getNumeroId());
        ownerInfoType.setOtherIdentDoc(otherIdentDocType);
        ownerInfoListType.getOwnerInfo().add(ownerInfoType);
        return ownerInfoListType;
    }

    private ProductType productType(Long numCdt, JSONObject product){
        ProductType productType = new ProductType();
        productType.setProductId(numCdt.toString());
        productType.setAcctCur(product.get("AcctCur").toString());
        productType.setAcctDomain(product.get("AcctDomain").toString());
        return productType;
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

}
