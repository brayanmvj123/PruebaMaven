
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResidencePlace_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResidencePlace_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}SocialStratum" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}StatusDesc" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}DwellingType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResidencePlace_Type", propOrder = {
    "socialStratum",
    "statusDesc",
    "dwellingType"
})
public class ResidencePlaceType {

    @XmlElement(name = "SocialStratum")
    protected String socialStratum;
    @XmlElement(name = "StatusDesc")
    protected String statusDesc;
    @XmlElement(name = "DwellingType")
    protected String dwellingType;

    /**
     * 
     * 						En este campo se parametriza las ventas o
     * 						ingresos anuales.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialStratum() {
        return socialStratum;
    }

    /**
     * Define el valor de la propiedad socialStratum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialStratum(String value) {
        this.socialStratum = value;
    }

    /**
     * Obtiene el valor de la propiedad statusDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Define el valor de la propiedad statusDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad dwellingType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDwellingType() {
        return dwellingType;
    }

    /**
     * Define el valor de la propiedad dwellingType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDwellingType(String value) {
        this.dwellingType = value;
    }

}
