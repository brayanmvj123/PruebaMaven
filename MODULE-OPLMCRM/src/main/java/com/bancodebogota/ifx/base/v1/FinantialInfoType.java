
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FinantialInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FinantialInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Income" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Outcome" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TotalLiabilities" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}TotalAssets" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherAssets" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherIncome" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherIncomeDesc" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EffDt" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinantialInfo_Type", propOrder = {
    "income",
    "outcome",
    "totalLiabilities",
    "totalAssets",
    "otherAssets",
    "otherIncome",
    "otherIncomeDesc",
    "effDt"
})
public class FinantialInfoType {

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

}
