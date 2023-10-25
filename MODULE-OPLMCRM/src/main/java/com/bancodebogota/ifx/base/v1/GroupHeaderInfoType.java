
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para GroupHeaderInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="GroupHeaderInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}GroupMemberInd" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TypeId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ParticipantId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PersonName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}CommercialArea" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupHeaderInfo_Type", propOrder = {
    "groupMemberInd",
    "typeId",
    "participantId",
    "personName",
    "commercialArea"
})
public class GroupHeaderInfoType {

    @XmlElement(name = "GroupMemberInd")
    protected String groupMemberInd;
    @XmlElement(name = "TypeId")
    protected String typeId;
    @XmlElement(name = "ParticipantId")
    protected String participantId;
    @XmlElement(name = "PersonName")
    protected PersonNameType personName;
    @XmlElement(name = "CommercialArea")
    protected CommercialAreaType commercialArea;

    /**
     * Obtiene el valor de la propiedad groupMemberInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupMemberInd() {
        return groupMemberInd;
    }

    /**
     * Define el valor de la propiedad groupMemberInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupMemberInd(String value) {
        this.groupMemberInd = value;
    }

    /**
     * En este campo se parametriza  el Tipo documento cabeza de grupo económico
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * Define el valor de la propiedad typeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeId(String value) {
        this.typeId = value;
    }

    /**
     * En este campo se parametriza el Número de documento cabeza de grupo económico
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParticipantId() {
        return participantId;
    }

    /**
     * Define el valor de la propiedad participantId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParticipantId(String value) {
        this.participantId = value;
    }

    /**
     * En este campo se parametriza el Nombre cabeza grupo económico
     * 							
     * 
     * @return
     *     possible object is
     *     {@link PersonNameType }
     *     
     */
    public PersonNameType getPersonName() {
        return personName;
    }

    /**
     * Define el valor de la propiedad personName.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonNameType }
     *     
     */
    public void setPersonName(PersonNameType value) {
        this.personName = value;
    }

    /**
     * Obtiene el valor de la propiedad commercialArea.
     * 
     * @return
     *     possible object is
     *     {@link CommercialAreaType }
     *     
     */
    public CommercialAreaType getCommercialArea() {
        return commercialArea;
    }

    /**
     * Define el valor de la propiedad commercialArea.
     * 
     * @param value
     *     allowed object is
     *     {@link CommercialAreaType }
     *     
     */
    public void setCommercialArea(CommercialAreaType value) {
        this.commercialArea = value;
    }

}
