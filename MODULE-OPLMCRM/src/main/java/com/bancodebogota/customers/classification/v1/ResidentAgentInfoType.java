
package com.bancodebogota.customers.classification.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.bancodebogota.ifx.base.v1.OtherIdentDocType;
import com.bancodebogota.ifx.base.v1.PersonClientType;


/**
 * <p>Clase Java para ResidentAgentInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResidentAgentInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherIdentDoc" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PersonClient" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}FACTAInd" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResidentAgentInfo_Type", propOrder = {
    "otherIdentDoc",
    "personClient",
    "factaInd"
})
public class ResidentAgentInfoType {

    @XmlElement(name = "OtherIdentDoc", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected OtherIdentDocType otherIdentDoc;
    @XmlElement(name = "PersonClient", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected PersonClientType personClient;
    @XmlElement(name = "FACTAInd", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected String factaInd;

    /**
     * Obtiene el valor de la propiedad otherIdentDoc.
     * 
     * @return
     *     possible object is
     *     {@link OtherIdentDocType }
     *     
     */
    public OtherIdentDocType getOtherIdentDoc() {
        return otherIdentDoc;
    }

    /**
     * Define el valor de la propiedad otherIdentDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherIdentDocType }
     *     
     */
    public void setOtherIdentDoc(OtherIdentDocType value) {
        this.otherIdentDoc = value;
    }

    /**
     * 
     * 						En este campo se parametriza la identificación
     * 						de una venta potencial.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link PersonClientType }
     *     
     */
    public PersonClientType getPersonClient() {
        return personClient;
    }

    /**
     * Define el valor de la propiedad personClient.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonClientType }
     *     
     */
    public void setPersonClient(PersonClientType value) {
        this.personClient = value;
    }

    /**
     * Obtiene el valor de la propiedad factaInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFACTAInd() {
        return factaInd;
    }

    /**
     * Define el valor de la propiedad factaInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFACTAInd(String value) {
        this.factaInd = value;
    }

}
