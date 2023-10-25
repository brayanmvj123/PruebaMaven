
package com.bancodebogota.customers.arrangement.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.bancodebogota.customers.involvedparty.event.CustomerNoveltyModRqType;


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
 *         &lt;element ref="{urn://bancodebogota.com/customers/involvedparty/event/}CustomerNoveltyModRq"/&gt;
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
    "customerNoveltyModRq"
})
@XmlRootElement(name = "modCustNoveltyRequest")
public class ModCustNoveltyRequest {

    @XmlElement(name = "CustomerNoveltyModRq", namespace = "urn://bancodebogota.com/customers/involvedparty/event/", required = true)
    protected CustomerNoveltyModRqType customerNoveltyModRq;

    /**
     * Obtiene el valor de la propiedad customerNoveltyModRq.
     * 
     * @return
     *     possible object is
     *     {@link CustomerNoveltyModRqType }
     *     
     */
    public CustomerNoveltyModRqType getCustomerNoveltyModRq() {
        return customerNoveltyModRq;
    }

    /**
     * Define el valor de la propiedad customerNoveltyModRq.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerNoveltyModRqType }
     *     
     */
    public void setCustomerNoveltyModRq(CustomerNoveltyModRqType value) {
        this.customerNoveltyModRq = value;
    }

}
