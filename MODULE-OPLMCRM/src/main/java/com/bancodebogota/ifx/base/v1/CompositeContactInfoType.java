
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CompositeContactInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CompositeContactInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ContactInfoType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ContactInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeContactInfo_Type", propOrder = {
    "contactInfoType",
    "contactInfo"
})
public class CompositeContactInfoType {

    @XmlElement(name = "ContactInfoType")
    protected String contactInfoType;
    @XmlElement(name = "ContactInfo")
    protected ContactInfoType contactInfo;

    /**
     * 
     * 						En este campo se parametriza el tipo específico del contacto.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactInfoType() {
        return contactInfoType;
    }

    /**
     * Define el valor de la propiedad contactInfoType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactInfoType(String value) {
        this.contactInfoType = value;
    }

    /**
     * 
     * 						En este campo se parametriza la información necesaria de contacto de la organización o persona.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link ContactInfoType }
     *     
     */
    public ContactInfoType getContactInfo() {
        return contactInfo;
    }

    /**
     * Define el valor de la propiedad contactInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInfoType }
     *     
     */
    public void setContactInfo(ContactInfoType value) {
        this.contactInfo = value;
    }

}
