
package com.bancodebogota.ifx.base.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Tipo de dato que se utilizará para todos los datos de tipo valores monetarios.
 * 					
 * 
 * <p>Clase Java para CurrencyAmount_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CurrencyAmount_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Amt" type="{urn://bancodebogota.com/ifx/base/v1/}Decimal_Type" minOccurs="0"/&gt;
 *         &lt;element name="CurCode" type="{urn://bancodebogota.com/ifx/base/v1/}NC" minOccurs="0"/&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element name="CurRate" type="{urn://bancodebogota.com/ifx/base/v1/}Decimal_Type" minOccurs="0"/&gt;
 *           &lt;element name="CurConvertRule" type="{urn://bancodebogota.com/ifx/base/v1/}ClosedEnum_Type" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurrencyAmount_Type", propOrder = {
    "amt",
    "curCode",
    "curRate",
    "curConvertRule"
})
public class CurrencyAmountType {

    @XmlElement(name = "Amt")
    protected BigDecimal amt;
    @XmlElement(name = "CurCode")
    protected String curCode;
    @XmlElement(name = "CurRate")
    protected BigDecimal curRate;
    @XmlElement(name = "CurConvertRule")
    protected String curConvertRule;

    /**
     * Obtiene el valor de la propiedad amt.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * Define el valor de la propiedad amt.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmt(BigDecimal value) {
        this.amt = value;
    }

    /**
     * Obtiene el valor de la propiedad curCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurCode() {
        return curCode;
    }

    /**
     * Define el valor de la propiedad curCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurCode(String value) {
        this.curCode = value;
    }

    /**
     * Obtiene el valor de la propiedad curRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurRate() {
        return curRate;
    }

    /**
     * Define el valor de la propiedad curRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurRate(BigDecimal value) {
        this.curRate = value;
    }

    /**
     * Obtiene el valor de la propiedad curConvertRule.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurConvertRule() {
        return curConvertRule;
    }

    /**
     * Define el valor de la propiedad curConvertRule.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurConvertRule(String value) {
        this.curConvertRule = value;
    }

}
