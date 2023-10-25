
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CommercialArea_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CommercialArea_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}SegmentId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}SubSegmentId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PriorizationCode" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}AgentId" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommercialArea_Type", propOrder = {
    "segmentId",
    "subSegmentId",
    "priorizationCode",
    "agentId"
})
public class CommercialAreaType {

    @XmlElement(name = "SegmentId")
    protected String segmentId;
    @XmlElement(name = "SubSegmentId")
    protected String subSegmentId;
    @XmlElement(name = "PriorizationCode")
    protected String priorizationCode;
    @XmlElement(name = "AgentId")
    protected String agentId;

    /**
     * En este campo se parametriza el Código del Segmento Comercial
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * Define el valor de la propiedad segmentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentId(String value) {
        this.segmentId = value;
    }

    /**
     * En este campo se parametriza el Código de Subsegmento Comercial
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubSegmentId() {
        return subSegmentId;
    }

    /**
     * Define el valor de la propiedad subSegmentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubSegmentId(String value) {
        this.subSegmentId = value;
    }

    /**
     * En este campo se parametriza el Código de Priorización Comercial
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriorizationCode() {
        return priorizationCode;
    }

    /**
     * Define el valor de la propiedad priorizationCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriorizationCode(String value) {
        this.priorizationCode = value;
    }

    /**
     * Obtiene el valor de la propiedad agentId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * Define el valor de la propiedad agentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentId(String value) {
        this.agentId = value;
    }

}
