package com.cx.webclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FindService", targetNamespace = "http://webservice.cx.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FindService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getvalues", targetNamespace = "http://webservice.cx.com/", className = "com.cx.webclient.Getvalues")
    @ResponseWrapper(localName = "getvaluesResponse", targetNamespace = "http://webservice.cx.com/", className = "com.cx.webclient.GetvaluesResponse")
    @Action(input = "http://webservice.cx.com/FindService/getvaluesRequest", output = "http://webservice.cx.com/FindService/getvaluesResponse")
    public String getvalues(
            @WebParam(name = "arg0", targetNamespace = "")
            String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
            String arg1);

}
