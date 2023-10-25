
package com.bancodebogota.ifx.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para PersonClient_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PersonClient_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TypeId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ParticipantId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Role" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}PersonName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Gender" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}MaritalStatus" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}City" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}StateProv" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Country" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}BirthDt" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}DriversLicense" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ContactInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}FinantialInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ResidencePlace" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EducationalLevel" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}DeathCertificateInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonClient_Type", propOrder = {
    "typeId",
    "participantId",
    "role",
    "personName",
    "gender",
    "maritalStatus",
    "city",
    "stateProv",
    "country",
    "birthDt",
    "driversLicense",
    "contactInfo",
    "finantialInfo",
    "residencePlace",
    "educationalLevel",
    "deathCertificateInfo"
})
public class PersonClientType {

    @XmlElement(name = "TypeId")
    protected String typeId;
    @XmlElement(name = "ParticipantId")
    protected String participantId;
    @XmlElement(name = "Role")
    protected List<String> role;
    @XmlElement(name = "PersonName")
    protected PersonNameType personName;
    @XmlElement(name = "Gender")
    protected String gender;
    @XmlElement(name = "MaritalStatus")
    protected String maritalStatus;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "StateProv")
    protected String stateProv;
    @XmlElement(name = "Country")
    protected String country;
    @XmlElement(name = "BirthDt")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDt;
    @XmlElement(name = "DriversLicense")
    protected DriversLicenseType driversLicense;
    @XmlElement(name = "ContactInfo")
    protected ContactInfoType contactInfo;
    @XmlElement(name = "FinantialInfo")
    protected FinantialInfoType finantialInfo;
    @XmlElement(name = "ResidencePlace")
    protected ResidencePlaceType residencePlace;
    @XmlElement(name = "EducationalLevel")
    protected String educationalLevel;
    @XmlElement(name = "DeathCertificateInfo")
    protected DeathCertificateInfoType deathCertificateInfo;

    /**
     * 
     * 						En este campo se parametriza el tipo de
     * 						identificación, de cliente o persona natural o
     * 						jurídica.
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
     * 
     * 						En este campo se parametriza el número de
     * 						identificación.
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
     * 
     * 						En este campo se parametriza el identificador de
     * 						los roles en que participa.
     * 							Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRole() {
        if (role == null) {
            role = new ArrayList<String>();
        }
        return this.role;
    }

    /**
     * 
     * 						En este campo se parametriza el nombre de la
     * 						persona y opcionalmente el sobrenombre de ésta.
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
     * 
     * 						En este campo se parametriza el género.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Define el valor de la propiedad gender.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * 
     * 						En este campo se parametriza el estado civil.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Define el valor de la propiedad maritalStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatus(String value) {
        this.maritalStatus = value;
    }

    /**
     * 
     * 						En este campo se parametriza la ciudad
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
     * 						En este campo se parametriza el país, el cual se
     * 						basa en la norma ISO 3166, el tamaño es de tres
     * 						caracteres y aparece en las direcciones
     * 						postales.
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
     * 						En este campo se parametriza la fecha de
     * 						nacimiento.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDt() {
        return birthDt;
    }

    /**
     * Define el valor de la propiedad birthDt.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDt(XMLGregorianCalendar value) {
        this.birthDt = value;
    }

    /**
     * 
     * 						En este campo se parametriza la licencia de
     * 						conducción.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link DriversLicenseType }
     *     
     */
    public DriversLicenseType getDriversLicense() {
        return driversLicense;
    }

    /**
     * Define el valor de la propiedad driversLicense.
     * 
     * @param value
     *     allowed object is
     *     {@link DriversLicenseType }
     *     
     */
    public void setDriversLicense(DriversLicenseType value) {
        this.driversLicense = value;
    }

    /**
     * 
     * 						En este campo se parametriza la información de
     * 						contacto.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link ContactInfoType }
     *     
     */
    public ContactInfoType getContactInfo() {
        return contactInfo;
    }

    /**
     * Define el valor de la propiedad contactInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInfoType }
     *     
     */
    public void setContactInfo(ContactInfoType value) {
        this.contactInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad finantialInfo.
     * 
     * @return
     *     possible object is
     *     {@link FinantialInfoType }
     *     
     */
    public FinantialInfoType getFinantialInfo() {
        return finantialInfo;
    }

    /**
     * Define el valor de la propiedad finantialInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link FinantialInfoType }
     *     
     */
    public void setFinantialInfo(FinantialInfoType value) {
        this.finantialInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad residencePlace.
     * 
     * @return
     *     possible object is
     *     {@link ResidencePlaceType }
     *     
     */
    public ResidencePlaceType getResidencePlace() {
        return residencePlace;
    }

    /**
     * Define el valor de la propiedad residencePlace.
     * 
     * @param value
     *     allowed object is
     *     {@link ResidencePlaceType }
     *     
     */
    public void setResidencePlace(ResidencePlaceType value) {
        this.residencePlace = value;
    }

    /**
     * Obtiene el valor de la propiedad educationalLevel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducationalLevel() {
        return educationalLevel;
    }

    /**
     * Define el valor de la propiedad educationalLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducationalLevel(String value) {
        this.educationalLevel = value;
    }

    /**
     * 
     * 						Agrupa informacion relevante de acta defuncion de una persona.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link DeathCertificateInfoType }
     *     
     */
    public DeathCertificateInfoType getDeathCertificateInfo() {
        return deathCertificateInfo;
    }

    /**
     * Define el valor de la propiedad deathCertificateInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link DeathCertificateInfoType }
     *     
     */
    public void setDeathCertificateInfo(DeathCertificateInfoType value) {
        this.deathCertificateInfo = value;
    }

}
