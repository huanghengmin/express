package com.hzih.express.web.action.compare;

import com.hzih.express.utils.StringContext;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class CompareConfigXml {
    private static Logger logger=Logger.getLogger(CompareConfigXml.class);
    public  static final String config = "config";
    public  static final String compare_days = "compare_days";
    public  static final String compare_minutes = "compare_minutes";
    public  static final String ip = "ip";
    public  static final String port = "port";
    private static final String path = StringContext.systemPath+"/config/compare_config.xml";

    public static String getAttribute(String attributeName){
        SAXReader saxReader = new SAXReader();
        Document doc=null;
        try {
            doc =saxReader.read(new File(path));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        Element root = doc.getRootElement();
        String result = root.attributeValue(attributeName);
        return result;
    }

    public static void saveConfig(String compare_days,String compare_minutes,String ip,String port){
        Document doc=DocumentHelper.createDocument();
        Element root=doc.addElement(CompareConfigXml.config);
        root.addAttribute(CompareConfigXml.compare_days,compare_days);
        root.addAttribute(CompareConfigXml.compare_minutes,compare_minutes);
        root.addAttribute(CompareConfigXml.ip,ip);
        root.addAttribute(CompareConfigXml.port,port);
        OutputFormat outputFormat=new OutputFormat("",true);
        try {
            XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(new File(path)),outputFormat);
            try {
                xmlWriter.write(doc);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        } catch (UnsupportedEncodingException e) {
            logger.info(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        }
    }
}
