
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DriversLicense_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DriversLicense_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}LicenseNum" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}StateProv" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Country" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}StatusCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DriversLicense_Type", propOrder = {
    "licenseNum",
    "stateProv",
    "country",
    "statusCode"
})
public class DriversLicenseType {

    @XmlElement(name = "LicenseNum")
    protected String licenseNum;
    @XmlElement(name = "StateProv")
    protected String stateProv;
    @XmlElement(name = "Country")
    protected String country;
    @XmlElement(name = "StatusCode")
    protected String statusCode;

    /**
     * 
     * 						En este campo se parametriza el número de licencia de conducción.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseNum() {
        return licenseNum;
    }

    /**
     * Define el valor de la propiedad licenseNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseNum(String value) {
        this.licenseNum = value;
    }

    /**
     * 
     * 						En este campo se parametriza el código de provincia o estado.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateProv() {
        return stateProv;
    }

    /**
     * Define el valor de la propiedad stateProv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateProv(String value) {
        this.stateProv = value;
    }

    /**
     * 
     * 						En este campo se parametriza el país, el cual se basa en la norma ISO 3166, el tamaño 
     * 	    				es de tres caracteres y aparece en las direcciones postales.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Define el valor de la propiedad country.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * 
     * 						En este campo estan paramentrizados todos los posibles estados en 
     * 	    				los cuales se puede encontrar una cuenta perteneciente a la entidad.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Define el valor de la propiedad statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

}
