
package com.bancodebogota.ifx.base.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.bancodebogota.customers.involvedparty.event.CustomerNoveltyModRqType;
import com.bancodebogota.ifx.base.v1.BaseEnvrType;
import com.bancodebogota.ifx.base.v1.CustIdType;
import com.bancodebogota.ifx.base.v2.NetworkTrnInfoType;


/**
 * <p>Clase Java para SvcRq_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SvcRq_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}RqUID"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}AsyncRqUID" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TrnSeqCntr" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}RevClientTrnSeq" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}CustId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}NextDay" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BaseEnvr" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ClientTerminalSeqId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TrnStatusType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v2/}NetworkTrnInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ClientDt" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SvcRq_Type", propOrder = {
    "rqUID",
    "asyncRqUID",
    "trnSeqCntr",
    "revClientTrnSeq",
    "custId",
    "nextDay",
    "baseEnvr",
    "clientTerminalSeqId",
    "trnStatusType",
    "networkTrnInfo",
    "clientDt"
})
@XmlSeeAlso({
    CustomerNoveltyModRqType.class
})
public class SvcRqType {

    @XmlElement(name = "RqUID", namespace = "urn://bancodebogota.com/ifx/base/v1/", required = true)
    protected String rqUID;
    @XmlElement(name = "AsyncRqUID", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String asyncRqUID;
    @XmlElement(name = "TrnSeqCntr", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String trnSeqCntr;
    @XmlElement(name = "RevClientTrnSeq", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String revClientTrnSeq;
    @XmlElement(name = "CustId", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected CustIdType custId;
    @XmlElement(name = "NextDay", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String nextDay;
    @XmlElement(name = "BaseEnvr", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected BaseEnvrType baseEnvr;
    @XmlElement(name = "ClientTerminalSeqId", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String clientTerminalSeqId;
    @XmlElement(name = "TrnStatusType", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String trnStatusType;
    @XmlElement(name = "NetworkTrnInfo", namespace = "urn://bancodebogota.com/ifx/base/v2/")
    protected NetworkTrnInfoType networkTrnInfo;
    @XmlElement(name = "ClientDt", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String clientDt;

    /**
     * 
     * 						Identificador único del mensaje que entrega la aplicación.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqUID() {
        return rqUID;
    }

    /**
     * Define el valor de la propiedad rqUID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqUID(String value) {
        this.rqUID = value;
    }

    /**
     * 
     * 						Identificador de solicitud asincrónica, enviado por un cliente para obtener una respuesta asíncrona 
     * 						generada por un servidor, por lo general, en el caso de que la respuesta ha tomado demasiado tiempo 
     * 						para construir y ser capaz de ser enviados de manera sincrónica.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsyncRqUID() {
        return asyncRqUID;
    }

    /**
     * Define el valor de la propiedad asyncRqUID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsyncRqUID(String value) {
        this.asyncRqUID = value;
    }

    /**
     * Obtiene el valor de la propiedad trnSeqCntr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrnSeqCntr() {
        return trnSeqCntr;
    }

    /**
     * Define el valor de la propiedad trnSeqCntr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrnSeqCntr(String value) {
        this.trnSeqCntr = value;
    }

    /**
     * 
     * 						Secuencia de transación del cliente para reversos.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevClientTrnSeq() {
        return revClientTrnSeq;
    }

    /**
     * Define el valor de la propiedad revClientTrnSeq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevClientTrnSeq(String value) {
        this.revClientTrnSeq = value;
    }

    /**
     * 
     * 						En este campo se parametriza la información del usuario que autentica el servicio.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CustIdType }
     *     
     */
    public CustIdType getCustId() {
        return custId;
    }

    /**
     * Define el valor de la propiedad custId.
     * 
     * @param value
     *     allowed object is
     *     {@link CustIdType }
     *     
     */
    public void setCustId(CustIdType value) {
        this.custId = value;
    }

    /**
     * 
     * 						El proveedor del pago indica que éste debe realizarse normalmente para el próximo día.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextDay() {
        return nextDay;
    }

    /**
     * Define el valor de la propiedad nextDay.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextDay(String value) {
        this.nextDay = value;
    }

    /**
     * 
     * 						En este campo se parametriza la información del ambiente que origina la invocación.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link BaseEnvrType }
     *     
     */
    public BaseEnvrType getBaseEnvr() {
        return baseEnvr;
    }

    /**
     * Define el valor de la propiedad baseEnvr.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseEnvrType }
     *     
     */
    public void setBaseEnvr(BaseEnvrType value) {
        this.baseEnvr = value;
    }

    /**
     * 
     * 						 Identificador de secuencia generado por la terminal del cliente 
     * 						 en un ambiente de oficina/CallCenter/cajero.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientTerminalSeqId() {
        return clientTerminalSeqId;
    }

    /**
     * Define el valor de la propiedad clientTerminalSeqId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientTerminalSeqId(String value) {
        this.clientTerminalSeqId = value;
    }

    /**
     * 
     * 						Tipo de estado de la transacción.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrnStatusType() {
        return trnStatusType;
    }

    /**
     * Define el valor de la propiedad trnStatusType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrnStatusType(String value) {
        this.trnStatusType = value;
    }

    /**
     * 
     * 						Información del banco originador de la transacción.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link NetworkTrnInfoType }
     *     
     */
    public NetworkTrnInfoType getNetworkTrnInfo() {
        return networkTrnInfo;
    }

    /**
     * Define el valor de la propiedad networkTrnInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkTrnInfoType }
     *     
     */
    public void setNetworkTrnInfo(NetworkTrnInfoType value) {
        this.networkTrnInfo = value;
    }

    /**
     * 
     * 						Tiempo de acuerdo con el cliente, se puede comparar con el tiempo del servidor
     * 						para determinar si hay una discrepancia.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientDt() {
        return clientDt;
    }

    /**
     * Define el valor de la propiedad clientDt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientDt(String value) {
        this.clientDt = value;
    }

}
