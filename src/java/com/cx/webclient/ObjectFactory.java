
package com.cx.webclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cx.webclient package. 
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

    private final static QName _GetvaluesResponse_QNAME = new QName("http://webservice.cx.com/", "getvaluesResponse");
    private final static QName _Getvalues_QNAME = new QName("http://webservice.cx.com/", "getvalues");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cx.webclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link com.cx.webclient.Getvalues }
     *
     */
    public Getvalues createGetvalues() {
        return new Getvalues();
    }

    /**
     * Create an instance of {@link GetvaluesResponse }
     *
     */
    public GetvaluesResponse createGetvaluesResponse() {
        return new GetvaluesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetvaluesResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webservice.cx.com/", name = "getvaluesResponse")
    public JAXBElement<GetvaluesResponse> createGetvaluesResponse(GetvaluesResponse value) {
        return new JAXBElement<GetvaluesResponse>(_GetvaluesResponse_QNAME, GetvaluesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.cx.webclient.Getvalues }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cx.com/", name = "getvalues")
    public JAXBElement<Getvalues> createGetvalues(Getvalues value) {
        return new JAXBElement<Getvalues>(_Getvalues_QNAME, Getvalues.class, null, value);
    }

}
