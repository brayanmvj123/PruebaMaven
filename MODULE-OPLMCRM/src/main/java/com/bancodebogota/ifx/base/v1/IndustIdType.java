
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para IndustId_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="IndustId_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Org"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}IndustNum" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndustId_Type", propOrder = {
    "org",
    "industNum"
})
public class IndustIdType {

    @XmlElement(name = "Org", required = true)
    protected String org;
    @XmlElement(name = "IndustNum")
    protected String industNum;

    /**
     * 
     * 						En éste campo se parametriza la organización, identifica la organización asignando números a diferentes industrias.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrg() {
        return org;
    }

    /**
     * Define el valor de la propiedad org.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrg(String value) {
        this.org = value;
    }

    /**
     * 
     * 						En éste campo se parametriza el número que identifica la industria.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustNum() {
        return industNum;
    }

    /**
     * Define el valor de la propiedad industNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustNum(String value) {
        this.industNum = value;
    }

}
