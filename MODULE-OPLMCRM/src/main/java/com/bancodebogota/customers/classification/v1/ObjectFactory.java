
package com.bancodebogota.customers.classification.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bancodebogota.customers.classification.v1 package. 
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

    private final static QName _ResidentAgentInfo_QNAME = new QName("urn://bancodebogota.com/customers/classification/v1/", "ResidentAgentInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bancodebogota.customers.classification.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResidentAgentInfoType }
     * 
     */
    public ResidentAgentInfoType createResidentAgentInfoType() {
        return new ResidentAgentInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResidentAgentInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/customers/classification/v1/", name = "ResidentAgentInfo")
    public JAXBElement<ResidentAgentInfoType> createResidentAgentInfo(ResidentAgentInfoType value) {
        return new JAXBElement<ResidentAgentInfoType>(_ResidentAgentInfo_QNAME, ResidentAgentInfoType.class, null, value);
    }

}
