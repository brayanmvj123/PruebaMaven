
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BankInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BankInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BankIdType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BankId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Name" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BranchId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BranchName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BranchIdType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PostAddr" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankInfo_Type", propOrder = {
    "bankIdType",
    "bankId",
    "name",
    "branchId",
    "branchName",
    "branchIdType",
    "postAddr"
})
public class BankInfoType {

    @XmlElement(name = "BankIdType")
    protected String bankIdType;
    @XmlElement(name = "BankId")
    protected String bankId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "BranchId")
    protected String branchId;
    @XmlElement(name = "BranchName")
    protected String branchName;
    @XmlElement(name = "BranchIdType")
    protected String branchIdType;
    @XmlElement(name = "PostAddr")
    protected PostAddrType postAddr;

    /**
     * 
     * 						En este campo se parametriza el tipo de identificador bancario como lo
     * 						son SWIFT, ABA, FedNet, CHIPS, CHAPS.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankIdType() {
        return bankIdType;
    }

    /**
     * Define el valor de la propiedad bankIdType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankIdType(String value) {
        this.bankIdType = value;
    }

    /**
     * 
     * 						En este campo se parametriza el código bancario el cual viene de CRM.
     * 	    				Indica cual banco dentro del holding esta asociado a la cuenta.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * Define el valor de la propiedad bankId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankId(String value) {
        this.bankId = value;
    }

    /**
     * 
     * 						En este campo se parametriza el nombre.
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
     * 						En este campo se parametriza la oficina de radicación, el cual es utilizado
     * 	    				para indicar la oficina en la cual se encuentra radicada la cuenta.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Define el valor de la propiedad branchId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchId(String value) {
        this.branchId = value;
    }

    /**
     * 
     * 						En este campo se parametriza el nombre de la sucursal bancaria, en ITA.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Define el valor de la propiedad branchName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

    /**
     * 
     * 						En este campo se parametriza el id de la sucursal bancaria.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchIdType() {
        return branchIdType;
    }

    /**
     * Define el valor de la propiedad branchIdType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchIdType(String value) {
        this.branchIdType = value;
    }

    /**
     * 
     * 						En este campo se parametriza el id de la sucursal bancaria.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link PostAddrType }
     *     
     */
    public PostAddrType getPostAddr() {
        return postAddr;
    }

    /**
     * Define el valor de la propiedad postAddr.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAddrType }
     *     
     */
    public void setPostAddr(PostAddrType value) {
        this.postAddr = value;
    }

}
