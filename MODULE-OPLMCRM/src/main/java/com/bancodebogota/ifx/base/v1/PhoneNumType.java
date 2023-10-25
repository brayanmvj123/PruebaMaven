
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PhoneNum_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PhoneNum_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PhoneType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Phone" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PhoneExt" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PhoneArea" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneNum_Type", propOrder = {
    "phoneType",
    "phone",
    "phoneExt",
    "phoneArea"
})
public class PhoneNumType {

    @XmlElement(name = "PhoneType")
    protected String phoneType;
    @XmlElement(name = "Phone")
    protected String phone;
    @XmlElement(name = "PhoneExt")
    protected String phoneExt;
    @XmlElement(name = "PhoneArea")
    protected String phoneArea;

    /**
     * 
     * 						En este campo se parametriza el tipo de
     * 						teléfono.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * Define el valor de la propiedad phoneType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneType(String value) {
        this.phoneType = value;
    }

    /**
     * 
     * 						En este campo se parametriza el teléfono.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Define el valor de la propiedad phone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * 
     * 						En este campo se parametriza la extensión.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneExt() {
        return phoneExt;
    }

    /**
     * Define el valor de la propiedad phoneExt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneExt(String value) {
        this.phoneExt = value;
    }

    /**
     * 
     * 						En este campo se parametriza el indicativo.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneArea() {
        return phoneArea;
    }

    /**
     * Define el valor de la propiedad phoneArea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneArea(String value) {
        this.phoneArea = value;
    }

}
