
package com.bancodebogota.ifx.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ContactInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ContactInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ContactId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ContactPref" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PhoneNum" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EmailType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EmailAddr" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}DeliveryDestination" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PostAddr" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactInfo_Type", propOrder = {
    "contactId",
    "contactPref",
    "phoneNum",
    "emailType",
    "emailAddr",
    "deliveryDestination",
    "postAddr"
})
public class ContactInfoType {

    @XmlElement(name = "ContactId")
    protected String contactId;
    @XmlElement(name = "ContactPref")
    protected String contactPref;
    @XmlElement(name = "PhoneNum")
    protected List<PhoneNumType> phoneNum;
    @XmlElement(name = "EmailType")
    protected String emailType;
    @XmlElement(name = "EmailAddr")
    protected String emailAddr;
    @XmlElement(name = "DeliveryDestination")
    protected String deliveryDestination;
    @XmlElement(name = "PostAddr")
    protected List<PostAddrType> postAddr;

    /**
     * 
     * 						En este campo se parametriza el identificador de contacto.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Define el valor de la propiedad contactId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactId(String value) {
        this.contactId = value;
    }

    /**
     * 
     * 						En este campo se parametriza el método preferido para llegar a la entidad a la que el se refiere.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPref() {
        return contactPref;
    }

    /**
     * Define el valor de la propiedad contactPref.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPref(String value) {
        this.contactPref = value;
    }

    /**
     * 
     * 						En este campo se parametriza el número del teléfono.	
     * 							Gets the value of the phoneNum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneNum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneNum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNumType }
     * 
     * 
     */
    public List<PhoneNumType> getPhoneNum() {
        if (phoneNum == null) {
            phoneNum = new ArrayList<PhoneNumType>();
        }
        return this.phoneNum;
    }

    /**
     * 
     * 						En este campo se parametriza el proposito de la dirección de correo electrónica.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailType() {
        return emailType;
    }

    /**
     * Define el valor de la propiedad emailType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailType(String value) {
        this.emailType = value;
    }

    /**
     * 
     * 						En este campo se parametriza la dirección de correo electrónica.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * Define el valor de la propiedad emailAddr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddr(String value) {
        this.emailAddr = value;
    }

    /**
     * 
     * 						En este campo se parametriza donde entregar los cheques, tales como oficina,
     * 						cliente y dirección alternativa.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryDestination() {
        return deliveryDestination;
    }

    /**
     * Define el valor de la propiedad deliveryDestination.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryDestination(String value) {
        this.deliveryDestination = value;
    }

    /**
     * 
     * 						En este campo se parametriza la dirección postal.
     * 							Gets the value of the postAddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postAddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostAddr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PostAddrType }
     * 
     * 
     */
    public List<PostAddrType> getPostAddr() {
        if (postAddr == null) {
            postAddr = new ArrayList<PostAddrType>();
        }
        return this.postAddr;
    }

}
