
package com.bancodebogota.ifx.base.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.bancodebogota.ifx.base.v1.BankInfoType;


/**
 * <p>Clase Java para NetworkTrnInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NetworkTrnInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}NetworkOwner" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TerminalId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TerminalType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BankInfo" minOccurs="0"/&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Desc" minOccurs="0"/&gt;
 *           &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Name" minOccurs="0"/&gt;
 *           &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}NetworkRefId" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkTrnInfo_Type", propOrder = {
    "networkOwner",
    "terminalId",
    "terminalType",
    "bankInfo",
    "desc",
    "name",
    "networkRefId"
})
public class NetworkTrnInfoType {

    @XmlElement(name = "NetworkOwner", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String networkOwner;
    @XmlElement(name = "TerminalId", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String terminalId;
    @XmlElement(name = "TerminalType", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String terminalType;
    @XmlElement(name = "BankInfo", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected BankInfoType bankInfo;
    @XmlElement(name = "Desc", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String desc;
    @XmlElement(name = "Name", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String name;
    @XmlElement(name = "NetworkRefId", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String networkRefId;

    /**
     * 
     * 						En este campo se parametriza el propietario de Red, el nombre de la persona u organización 
     * 						que posee la red de origen, los valores definidos son ATM, POS, ACH, FedNet, SWIFT, Rama, 
     * 						CallCenter, Otros.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkOwner() {
        return networkOwner;
    }

    /**
     * Define el valor de la propiedad networkOwner.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkOwner(String value) {
        this.networkOwner = value;
    }

    /**
     * 
     * 						En este campo se parametriza el identificador de terminal, tal como el código de terminal o
     * 						número de terminal ATM.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Define el valor de la propiedad terminalId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * 
     * 						En este campo se parametriza el tipo de terminal, valores definidos AdminTerm, ATM, POS, 
     * 						CustomerDevice, ECR, DialCash, TravelerCheckDispenser, FuelPump, ScripTerm, CouponTerm, 
     * 						TicketTerm, POBTerm, Teller, Utility, Vending, Payment, VRU.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * Define el valor de la propiedad terminalType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalType(String value) {
        this.terminalType = value;
    }

    /**
     * 
     * 						En este agregado se parametriza la información del banco origen de la transacción.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link BankInfoType }
     *     
     */
    public BankInfoType getBankInfo() {
        return bankInfo;
    }

    /**
     * Define el valor de la propiedad bankInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link BankInfoType }
     *     
     */
    public void setBankInfo(BankInfoType value) {
        this.bankInfo = value;
    }

    /**
     * 
     * 							En este campo se parametriza la descripción.
     * 								
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Define el valor de la propiedad desc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * 
     * 							En este campo se parametriza el nombre.
     * 								
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 
     * 							En este campo se parametriza el Identificador de Red de Referencia.
     * 								
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkRefId() {
        return networkRefId;
    }

    /**
     * Define el valor de la propiedad networkRefId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkRefId(String value) {
        this.networkRefId = value;
    }

}
