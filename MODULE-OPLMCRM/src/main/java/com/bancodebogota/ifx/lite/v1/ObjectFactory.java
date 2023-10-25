
package com.bancodebogota.ifx.lite.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bancodebogota.ifx.lite.v1 package. 
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

    private final static QName _RefInfo_QNAME = new QName("urn://bancodebogota.com/ifx/lite/v1/", "RefInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bancodebogota.ifx.lite.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RefInfoType }
     * 
     */
    public RefInfoType createRefInfoType() {
        return new RefInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn://bancodebogota.com/ifx/lite/v1/", name = "RefInfo")
    public JAXBElement<RefInfoType> createRefInfo(RefInfoType value) {
        return new JAXBElement<RefInfoType>(_RefInfo_QNAME, RefInfoType.class, null, value);
    }

}
