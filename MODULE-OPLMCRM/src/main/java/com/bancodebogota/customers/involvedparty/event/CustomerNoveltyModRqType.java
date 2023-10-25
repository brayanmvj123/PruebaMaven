
package com.bancodebogota.customers.involvedparty.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.bancodebogota.ifx.base.v1.OtherIdentDocType;
import com.bancodebogota.ifx.base.v1.RefInfoType;
import com.bancodebogota.ifx.base.v3.SvcRqType;


/**
 * <p>Clase Java para CustomerNoveltyModRq_Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CustomerNoveltyModRq_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn://bancodebogota.com/ifx/base/v3/}SvcRq_Type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}OtherIdentDoc"/&gt;
 *         &lt;element ref="{urn://bancodebogota.com/ifx/base/v1/}RefInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerNoveltyModRq_Type", propOrder = {
    "otherIdentDoc",
    "refInfo"
})
public class CustomerNoveltyModRqType
    extends SvcRqType
{

    @XmlElement(name = "OtherIdentDoc", namespace = "urn://bancodebogota.com/ifx/base/v1/", required = true)
    protected OtherIdentDocType otherIdentDoc;
    @XmlElement(name = "RefInfo", namespace = "urn://bancodebogota.com/ifx/base/v1/")
    protected RefInfoType refInfo;

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
     * Obtiene el valor de la propiedad refInfo.
     * 
     * @return
     *     possible object is
     *     {@link RefInfoType }
     *     
     */
    public RefInfoType getRefInfo() {
        return refInfo;
    }

    /**
     * Define el valor de la propiedad refInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link RefInfoType }
     *     
     */
    public void setRefInfo(RefInfoType value) {
        this.refInfo = value;
    }

}
