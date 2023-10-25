
package com.bancodebogota.ifx.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.bancodebogota.customers.classification.v1.ResidentAgentInfoType;


/**
 * <p>Clase Java para OrgClient_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OrgClient_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TypeId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}ParticipantId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Role" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}LegalName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}FullName" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EstablishDt" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}NumEmployees" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}NumBranchOffice" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}IndustId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}CompositeContactInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Income" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Outcome" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TotalLiabilities" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TotalAssets" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherAssets" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherIncome" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherIncomeDesc" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EffDt" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}CommercialArea" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}GroupHeaderInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/customers/classification/v1/}ResidentAgentInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}LargessTaxPayerInd" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}SelfTaxWithholderInd" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgClient_Type", propOrder = {
    "typeId",
    "participantId",
    "role",
    "legalName",
    "fullName",
    "establishDt",
    "numEmployees",
    "numBranchOffice",
    "industId",
    "compositeContactInfo",
    "income",
    "outcome",
    "totalLiabilities",
    "totalAssets",
    "otherAssets",
    "otherIncome",
    "otherIncomeDesc",
    "effDt",
    "commercialArea",
    "groupHeaderInfo",
    "residentAgentInfo",
    "largessTaxPayerInd",
    "selfTaxWithholderInd"
})
public class OrgClientType {

    @XmlElement(name = "TypeId")
    protected String typeId;
    @XmlElement(name = "ParticipantId")
    protected String participantId;
    @XmlElement(name = "Role")
    protected List<String> role;
    @XmlElement(name = "LegalName")
    protected String legalName;
    @XmlElement(name = "FullName")
    protected String fullName;
    @XmlElement(name = "EstablishDt")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar establishDt;
    @XmlElement(name = "NumEmployees")
    protected Long numEmployees;
    @XmlElement(name = "NumBranchOffice")
    protected Long numBranchOffice;
    @XmlElement(name = "IndustId")
    protected IndustIdType industId;
    @XmlElement(name = "CompositeContactInfo")
    protected CompositeContactInfoType compositeContactInfo;
    @XmlElement(name = "Income")
    protected CurrencyAmountType income;
    @XmlElement(name = "Outcome")
    protected CurrencyAmountType outcome;
    @XmlElement(name = "TotalLiabilities")
    protected CurrencyAmountType totalLiabilities;
    @XmlElement(name = "TotalAssets")
    protected CurrencyAmountType totalAssets;
    @XmlElement(name = "OtherAssets")
    protected CurrencyAmountType otherAssets;
    @XmlElement(name = "OtherIncome")
    protected CurrencyAmountType otherIncome;
    @XmlElement(name = "OtherIncomeDesc")
    protected String otherIncomeDesc;
    @XmlElement(name = "EffDt")
    protected String effDt;
    @XmlElement(name = "CommercialArea")
    protected CommercialAreaType commercialArea;
    @XmlElement(name = "GroupHeaderInfo")
    protected GroupHeaderInfoType groupHeaderInfo;
    @XmlElement(name = "ResidentAgentInfo", namespace = "urn://bancodebogota.com/customers/classification/v1/")
    protected ResidentAgentInfoType residentAgentInfo;
    @XmlElement(name = "LargessTaxPayerInd")
    protected String largessTaxPayerInd;
    @XmlElement(name = "SelfTaxWithholderInd")
    protected String selfTaxWithholderInd;

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
     * 						En este campo se parametriza la razón social o
     * 						nombre asignado al producto requerido para armar
     * 						el nombre de las relaciones comerciales.
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
     * Obtiene el valor de la propiedad fullName.
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

    /**
     * 
     * 						En este campo se parametriza la fecha de
     * 						constitución.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEstablishDt() {
        return establishDt;
    }

    /**
     * Define el valor de la propiedad establishDt.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEstablishDt(XMLGregorianCalendar value) {
        this.establishDt = value;
    }

    /**
     * Obtiene el valor de la propiedad numEmployees.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumEmployees() {
        return numEmployees;
    }

    /**
     * Define el valor de la propiedad numEmployees.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumEmployees(Long value) {
        this.numEmployees = value;
    }

    /**
     * Obtiene el valor de la propiedad numBranchOffice.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumBranchOffice() {
        return numBranchOffice;
    }

    /**
     * Define el valor de la propiedad numBranchOffice.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumBranchOffice(Long value) {
        this.numBranchOffice = value;
    }

    /**
     * 
     * 						En este campo se parametriza los codigos con los
     * 						cuales se identifica la industria o actividad
     * 						economica de un cliente(PN,PJ).
     * 							
     * 
     * @return
     *     possible object is
     *     {@link IndustIdType }
     *     
     */
    public IndustIdType getIndustId() {
        return industId;
    }

    /**
     * Define el valor de la propiedad industId.
     * 
     * @param value
     *     allowed object is
     *     {@link IndustIdType }
     *     
     */
    public void setIndustId(IndustIdType value) {
        this.industId = value;
    }

    /**
     * 
     * 						Información de Contacto CPP. La información
     * 						sobre cómo el cliente puede ponerse en contacto
     * 						con el CPP en relación con el conjunto de
     * 						transacciones asociadas con el tipo de acuse de
     * 						recibo. Información de contacto de remesas CPP.
     * 						En el caso de que se necesita información de
     * 						contacto, puede ser utilizado en el caso se
     * 						necesita la URL para enlazar el cliente al sitio
     * 						CPP para obtener información adicional acerca
     * 						del servicio o de la transacción. Remesas se
     * 						envía por separado del pago. En el caso de que
     * 						se necesita información de contacto, puede ser
     * 						utilizado en el caso no se necesita la URL
     * 						vincular el cliente al sitio CPP para obtener
     * 						información adicional acerca del servicio o de
     * 						la transacción.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CompositeContactInfoType }
     *     
     */
    public CompositeContactInfoType getCompositeContactInfo() {
        return compositeContactInfo;
    }

    /**
     * Define el valor de la propiedad compositeContactInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link CompositeContactInfoType }
     *     
     */
    public void setCompositeContactInfo(CompositeContactInfoType value) {
        this.compositeContactInfo = value;
    }

    /**
     * 
     * 						En este campo se parametriza las ventas o
     * 						ingresos anuales.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAmountType }
     *     
     */
    public CurrencyAmountType getIncome() {
        return income;
    }

    /**
     * Define el valor de la propiedad income.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAmountType }
     *     
     */
    public void setIncome(CurrencyAmountType value) {
        this.income = value;
    }

    /**
     * 
     * 						En este campo se parametriza los egresos
     * 						anuales.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAmountType }
     *     
     */
    public CurrencyAmountType getOutcome() {
        return outcome;
    }

    /**
     * Define el valor de la propiedad outcome.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAmountType }
     *     
     */
    public void setOutcome(CurrencyAmountType value) {
        this.outcome = value;
    }

    /**
     * 
     * 						En este campo se parametriza total pasivos.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAmountType }
     *     
     */
    public CurrencyAmountType getTotalLiabilities() {
        return totalLiabilities;
    }

    /**
     * Define el valor de la propiedad totalLiabilities.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAmountType }
     *     
     */
    public void setTotalLiabilities(CurrencyAmountType value) {
        this.totalLiabilities = value;
    }

    /**
     * 
     * 						En este campo se parametriza total activos.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAmountType }
     *     
     */
    public CurrencyAmountType getTotalAssets() {
        return totalAssets;
    }

    /**
     * Define el valor de la propiedad totalAssets.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAmountType }
     *     
     */
    public void setTotalAssets(CurrencyAmountType value) {
        this.totalAssets = value;
    }

    /**
     * Obtiene el valor de la propiedad otherAssets.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAmountType }
     *     
     */
    public CurrencyAmountType getOtherAssets() {
        return otherAssets;
    }

    /**
     * Define el valor de la propiedad otherAssets.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAmountType }
     *     
     */
    public void setOtherAssets(CurrencyAmountType value) {
        this.otherAssets = value;
    }

    /**
     * 
     * 						En este campo se parametriza otros ingresos.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link CurrencyAmountType }
     *     
     */
    public CurrencyAmountType getOtherIncome() {
        return otherIncome;
    }

    /**
     * Define el valor de la propiedad otherIncome.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyAmountType }
     *     
     */
    public void setOtherIncome(CurrencyAmountType value) {
        this.otherIncome = value;
    }

    /**
     * 
     * 						En este campo se parametriza la descripción de
     * 						otros ingresos.
     * 							
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherIncomeDesc() {
        return otherIncomeDesc;
    }

    /**
     * Define el valor de la propiedad otherIncomeDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherIncomeDesc(String value) {
        this.otherIncomeDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad effDt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffDt() {
        return effDt;
    }

    /**
     * Define el valor de la propiedad effDt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffDt(String value) {
        this.effDt = value;
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

    /**
     * Obtiene el valor de la propiedad groupHeaderInfo.
     * 
     * @return
     *     possible object is
     *     {@link GroupHeaderInfoType }
     *     
     */
    public GroupHeaderInfoType getGroupHeaderInfo() {
        return groupHeaderInfo;
    }

    /**
     * Define el valor de la propiedad groupHeaderInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupHeaderInfoType }
     *     
     */
    public void setGroupHeaderInfo(GroupHeaderInfoType value) {
        this.groupHeaderInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad residentAgentInfo.
     * 
     * @return
     *     possible object is
     *     {@link ResidentAgentInfoType }
     *     
     */
    public ResidentAgentInfoType getResidentAgentInfo() {
        return residentAgentInfo;
    }

    /**
     * Define el valor de la propiedad residentAgentInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ResidentAgentInfoType }
     *     
     */
    public void setResidentAgentInfo(ResidentAgentInfoType value) {
        this.residentAgentInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad largessTaxPayerInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLargessTaxPayerInd() {
        return largessTaxPayerInd;
    }

    /**
     * Define el valor de la propiedad largessTaxPayerInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLargessTaxPayerInd(String value) {
        this.largessTaxPayerInd = value;
    }

    /**
     * Obtiene el valor de la propiedad selfTaxWithholderInd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelfTaxWithholderInd() {
        return selfTaxWithholderInd;
    }

    /**
     * Define el valor de la propiedad selfTaxWithholderInd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelfTaxWithholderInd(String value) {
        this.selfTaxWithholderInd = value;
    }

}
