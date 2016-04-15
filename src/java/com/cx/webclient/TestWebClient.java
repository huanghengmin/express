package com.cx.webclient;

import net.sf.json.JSONObject;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Administrator on 16-4-1.
 */
public class TestWebClient {

    public static void main(String[] args) throws Exception {
//        URL wsdlUrl = new URL("http://172.16.2.9:80/Service/FindService?wsdl");
        URL wsdlUrl = new URL("http://127.0.0.1:80/Service/FindService?wsdl");
        Service s = Service.create(wsdlUrl, new QName("http://webservice.cx.com/", "FindServiceService"));
        FindService fs = s.getPort(new QName("http://webservice.cx.com/", "FindServicePort"), FindService.class);
//        String json = fs.getvalues(System.currentTimeMillis()+"", "430725199002093279");
        String json = fs.getvalues(System.currentTimeMillis()+"", "411426198709180619");

        JSONObject obj= new JSONObject().fromObject(json);
        String sessionid = (String) obj.get("sessionid");
        String code = (String) obj.get("code");
        String xq = (String) obj.get("xq");
        System.out.println(obj.toString());
        System.out.println(sessionid);
        System.out.println(code);
        System.out.println(xq);
    }
}
