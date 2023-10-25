
package com.bancodebogota.customers.arrangement.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.bancodebogota.customers.involvedparty.event.CustomerNoveltyModRsType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn://bancodebogota.com/customers/involvedparty/event/}CustomerNoveltyModRs"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customerNoveltyModRs"
})
@XmlRootElement(name = "modCustNoveltyResponse")
public class ModCustNoveltyResponse {

    @XmlElement(name = "CustomerNoveltyModRs", namespace = "urn://bancodebogota.com/customers/involvedparty/event/", required = true)
    protected CustomerNoveltyModRsType customerNoveltyModRs;

    /**
     * Obtiene el valor de la propiedad customerNoveltyModRs.
     * 
     * @return
     *     possible object is
     *     {@link CustomerNoveltyModRsType }
     *     
     */
    public CustomerNoveltyModRsType getCustomerNoveltyModRs() {
        return customerNoveltyModRs;
    }

    /**
     * Define el valor de la propiedad customerNoveltyModRs.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerNoveltyModRsType }
     *     
     */
    public void setCustomerNoveltyModRs(CustomerNoveltyModRsType value) {
        this.customerNoveltyModRs = value;
    }

}
