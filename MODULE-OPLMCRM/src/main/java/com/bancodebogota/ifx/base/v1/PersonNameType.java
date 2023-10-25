
package com.bancodebogota.ifx.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PersonName_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PersonName_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}LastName" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}FirstName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}MiddleName" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TitlePrefix" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}NameSuffix" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Nickname" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}LegalName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}FullName" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonName_Type", propOrder = {
    "lastName",
    "firstName",
    "middleName",
    "titlePrefix",
    "nameSuffix",
    "nickname",
    "legalName",
    "fullName"
})
public class PersonNameType {

    @XmlElement(name = "LastName")
    protected List<String> lastName;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "MiddleName")
    protected List<String> middleName;
    @XmlElement(name = "TitlePrefix")
    protected String titlePrefix;
    @XmlElement(name = "NameSuffix")
    protected String nameSuffix;
    @XmlElement(name = "Nickname")
    protected String nickname;
    @XmlElement(name = "LegalName")
    protected String legalName;
    @XmlElement(name = "FullName")
    protected String fullName;

    /**
     * 
     * 						En este campo se parametriza el apellido del cliente.
     * 							Gets the value of the lastName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lastName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLastName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLastName() {
        if (lastName == null) {
            lastName = new ArrayList<String>();
        }
        return this.lastName;
    }

    /**
     * 
     * 						En este campo se parametriza el nombre del cliente.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Define el valor de la propiedad firstName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * 
     * 						En este campo se parametriza el segundo nombre del cliente.
     * 							Gets the value of the middleName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the middleName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiddleName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMiddleName() {
        if (middleName == null) {
            middleName = new ArrayList<String>();
        }
        return this.middleName;
    }

    /**
     * 
     * 						En este campo se parametriza el prefijo del título.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitlePrefix() {
        return titlePrefix;
    }

    /**
     * Define el valor de la propiedad titlePrefix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitlePrefix(String value) {
        this.titlePrefix = value;
    }

    /**
     * 
     * 						En este campo se parametriza el sufijo del nombre del cliente.
     * 	    				Por ejemplo "Jr.".
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Define el valor de la propiedad nameSuffix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameSuffix(String value) {
        this.nameSuffix = value;
    }

    /**
     * 
     * 						En este campo se parametriza el sobrenombre.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Define el valor de la propiedad nickname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickname(String value) {
        this.nickname = value;
    }

    /**
     * 
     * 						En este campo se parametriza la razón social o nombre asignado al producto 
     * 	    				requerido para armar el nombre de las relaciones comerciales.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * Define el valor de la propiedad legalName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalName(String value) {
        this.legalName = value;
    }

    /**
     * 
     * 						En este campo se parametriza el nombre completo del cliente.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Define el valor de la propiedad fullName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

}
