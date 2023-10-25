
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PostAddr_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PostAddr_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;sequence&gt;
 *           &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Addr1" minOccurs="0"/&gt;
 *           &lt;sequence minOccurs="0"&gt;
 *             &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Addr2" minOccurs="0"/&gt;
 *             &lt;sequence minOccurs="0"&gt;
 *               &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Addr3" minOccurs="0"/&gt;
 *               &lt;sequence minOccurs="0"&gt;
 *                 &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Addr4" minOccurs="0"/&gt;
 *               &lt;/sequence&gt;
 *             &lt;/sequence&gt;
 *           &lt;/sequence&gt;
 *         &lt;/sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}City" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}StateProv" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PostalCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Country" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}AddrType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ZipCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Coordenate" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostAddr_Type", propOrder = {
    "addr1",
    "addr2",
    "addr3",
    "addr4",
    "city",
    "stateProv",
    "postalCode",
    "country",
    "addrType",
    "zipCode",
    "coordenate"
})
public class PostAddrType {

    @XmlElement(name = "Addr1")
    protected String addr1;
    @XmlElement(name = "Addr2")
    protected String addr2;
    @XmlElement(name = "Addr3")
    protected String addr3;
    @XmlElement(name = "Addr4")
    protected String addr4;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "StateProv")
    protected String stateProv;
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    @XmlElement(name = "Country")
    protected String country;
    @XmlElement(name = "AddrType")
    protected String addrType;
    @XmlElement(name = "ZipCode")
    protected String zipCode;
    @XmlElement(name = "Coordenate")
    protected String coordenate;

    /**
     * 
     * 							En este campo se parametriza la oficina de entrega o línea de dirección 1.
     * 								
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * Define el valor de la propiedad addr1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr1(String value) {
        this.addr1 = value;
    }

    /**
     * 
     * 								En este campo se parametriza la oficina próxima entrega o línea de dirección 2.
     * 									
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * Define el valor de la propiedad addr2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr2(String value) {
        this.addr2 = value;
    }

    /**
     * 
     * 									En este campo se parametriza la línea de dirección 3.
     * 										
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr3() {
        return addr3;
    }

    /**
     * Define el valor de la propiedad addr3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr3(String value) {
        this.addr3 = value;
    }

    /**
     * 
     * 										En este campo se parametriza la línea de dirección 4.
     * 											
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr4() {
        return addr4;
    }

    /**
     * Define el valor de la propiedad addr4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr4(String value) {
        this.addr4 = value;
    }

    /**
     * 
     * 						En este campo se parametriza la ciudad dentro de la dirección postal.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Define el valor de la propiedad city.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
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
     * 						En este campo se parametriza el código postal.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Define el valor de la propiedad postalCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
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
     * 						En este campo se parametriza el tipo de dirección física o electrónica.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrType() {
        return addrType;
    }

    /**
     * Define el valor de la propiedad addrType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrType(String value) {
        this.addrType = value;
    }

    /**
     * 
     * 						En este campo se parametriza el código de zip.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Define el valor de la propiedad zipCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * 
     * 						En este campo se parametriza la coordenada para determinar la posición de una dirección postal.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordenate() {
        return coordenate;
    }

    /**
     * Define el valor de la propiedad coordenate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordenate(String value) {
        this.coordenate = value;
    }

}
