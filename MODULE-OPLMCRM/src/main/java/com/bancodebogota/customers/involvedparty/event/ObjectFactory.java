
package com.bancodebogota.customers.involvedparty.event;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bancodebogota.customers.involvedparty.event package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CustomerNoveltyModRq_QNAME = new QName("urn://bancodebogota.com/customers/involvedparty/event/", "CustomerNoveltyModRq");
    private final static QName _CustomerNoveltyModRs_QNAME = new QName("urn://bancodebogota.com/customers/involvedparty/event/", "CustomerNoveltyModRs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bancodebogota.customers.involvedparty.event
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerNoveltyModRqType }
     * 
     */
    public CustomerNoveltyModRqType createCustomerNoveltyModRqType() {
        return new CustomerNoveltyModRqType();
    }

    /**
     * Create an instance of {@link CustomerNoveltyModRsType }
     * 
     */
    public CustomerNoveltyModRsType createCustomerNoveltyModRsType() {
        return new CustomerNoveltyModRsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNoveltyModRqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/customers/involvedparty/event/", name = "CustomerNoveltyModRq")
    public JAXBElement<CustomerNoveltyModRqType> createCustomerNoveltyModRq(CustomerNoveltyModRqType value) {
        return new JAXBElement<CustomerNoveltyModRqType>(_CustomerNoveltyModRq_QNAME, CustomerNoveltyModRqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNoveltyModRsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/customers/involvedparty/event/", name = "CustomerNoveltyModRs")
    public JAXBElement<CustomerNoveltyModRsType> createCustomerNoveltyModRs(CustomerNoveltyModRsType value) {
        return new JAXBElement<CustomerNoveltyModRsType>(_CustomerNoveltyModRs_QNAME, CustomerNoveltyModRsType.class, null, value);
    }

}
