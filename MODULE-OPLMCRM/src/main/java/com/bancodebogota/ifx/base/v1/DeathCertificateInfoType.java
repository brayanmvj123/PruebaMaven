
package com.bancodebogota.ifx.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DeathCertificateInfo_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DeathCertificateInfo_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}DeathCertificateId" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}EffDt" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}SelRangeDt" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}DeathInfoAddr" minOccurs="0"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}Memo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeathCertificateInfo_Type", propOrder = {
    "deathCertificateId",
    "effDt",
    "selRangeDt",
    "deathInfoAddr",
    "memo"
})
public class DeathCertificateInfoType {

    @XmlElement(name = "DeathCertificateId")
    protected String deathCertificateId;
    @XmlElement(name = "EffDt")
    protected String effDt;
    @XmlElement(name = "SelRangeDt")
    protected SelRangeDtType selRangeDt;
    @XmlElement(name = "DeathInfoAddr")
    protected PostAddrType deathInfoAddr;
    @XmlElement(name = "Memo")
    protected String memo;

    /**
     * Obtiene el valor de la propiedad deathCertificateId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeathCertificateId() {
        return deathCertificateId;
    }

    /**
     * Define el valor de la propiedad deathCertificateId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeathCertificateId(String value) {
        this.deathCertificateId = value;
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
     * Obtiene el valor de la propiedad selRangeDt.
     * 
     * @return
     *     possible object is
     *     {@link SelRangeDtType }
     *     
     */
    public SelRangeDtType getSelRangeDt() {
        return selRangeDt;
    }

    /**
     * Define el valor de la propiedad selRangeDt.
     * 
     * @param value
     *     allowed object is
     *     {@link SelRangeDtType }
     *     
     */
    public void setSelRangeDt(SelRangeDtType value) {
        this.selRangeDt = value;
    }

    /**
     * Obtiene el valor de la propiedad deathInfoAddr.
     * 
     * @return
     *     possible object is
     *     {@link PostAddrType }
     *     
     */
    public PostAddrType getDeathInfoAddr() {
        return deathInfoAddr;
    }

    /**
     * Define el valor de la propiedad deathInfoAddr.
     * 
     * @param value
     *     allowed object is
     *     {@link PostAddrType }
     *     
     */
    public void setDeathInfoAddr(PostAddrType value) {
        this.deathInfoAddr = value;
    }

    /**
     * Obtiene el valor de la propiedad memo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Define el valor de la propiedad memo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

}
