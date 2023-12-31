
package com.bancodebogota.ifx.base.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bancodebogota.ifx.base.v2 package. 
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

    private final static QName _NetworkTrnInfo_QNAME = new QName("urn://bancodebogota.com/ifx/base/v2/", "NetworkTrnInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bancodebogota.ifx.base.v2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NetworkTrnInfoType }
     * 
     */
    public NetworkTrnInfoType createNetworkTrnInfoType() {
        return new NetworkTrnInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NetworkTrnInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/base/v2/", name = "NetworkTrnInfo")
    public JAXBElement<NetworkTrnInfoType> createNetworkTrnInfo(NetworkTrnInfoType value) {
        return new JAXBElement<NetworkTrnInfoType>(_NetworkTrnInfo_QNAME, NetworkTrnInfoType.class, null, value);
    }

}
