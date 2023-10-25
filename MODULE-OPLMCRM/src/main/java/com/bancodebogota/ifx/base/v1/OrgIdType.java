
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OrgId_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OrgId_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OrgIdType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OrgIdNum" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgId_Type", propOrder = {
    "orgIdType",
    "orgIdNum"
})
public class OrgIdType {

    @XmlElement(name = "OrgIdType")
    protected String orgIdType;
    @XmlElement(name = "OrgIdNum")
    protected String orgIdNum;

    /**
     * Obtiene el valor de la propiedad orgIdType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgIdType() {
        return orgIdType;
    }

    /**
     * Define el valor de la propiedad orgIdType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgIdType(String value) {
        this.orgIdType = value;
    }

    /**
     * Obtiene el valor de la propiedad orgIdNum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgIdNum() {
        return orgIdNum;
    }

    /**
     * Define el valor de la propiedad orgIdNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgIdNum(String value) {
        this.orgIdNum = value;
    }

}
