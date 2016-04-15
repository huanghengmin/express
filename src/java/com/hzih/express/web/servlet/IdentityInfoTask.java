package com.hzih.express.web.servlet;

import com.cx.webclient.FindService;
import com.hzih.express.dao.AlertLogDao;
import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.domain.AlertLog;
import com.hzih.express.web.action.compare.CompareConfigXml;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class IdentityInfoTask extends TimerTask {

    private Logger logger = Logger.getLogger(IdentityInfoTask.class);

    private ExpressLogDao expressLogDao;
    private AlertLogDao alertLogDao;

    public IdentityInfoTask(ExpressLogDao expressLogDao,AlertLogDao alertLogDao) {
        this.expressLogDao = expressLogDao;
        this.alertLogDao = alertLogDao;
    }


    @Override
    public void run() {
        logger.info("*********************开始对比数据********************************");
        URL wsdlUrl = null;
        try {
            wsdlUrl = new URL("http://"+ CompareConfigXml.getAttribute(CompareConfigXml.ip)+":"+CompareConfigXml.getAttribute(CompareConfigXml.port)+"/Service/FindService?wsdl");
            Service s = Service.create(wsdlUrl, new QName("http://webservice.cx.com/", "FindServiceService"));
            FindService fs = s.getPort(new QName("http://webservice.cx.com/", "FindServicePort"), FindService.class);
            List<String> idCards = expressLogDao.findByDistinctIdCard();
            for (String idCard:idCards){
                if(idCard!=null&&idCard.length()>0) {
                    String json = fs.getvalues(System.currentTimeMillis()+"", idCard);
                    JSONObject obj= new JSONObject().fromObject(json);
                    String code = (String) obj.get("code");
                    String xq = (String) obj.get("xq");
                    //报警
                    if("001".equals(code)||"002".equals(code)){
                        AlertLog alertLog = new AlertLog();
                        alertLog.setId_card(idCard);
                        alertLog.setCode(code);
                        alertLog.setXq(xq);
                        alertLog.setLog_time(new Date());
                        alertLogDao.save(alertLog);
                    }
                    boolean flag = expressLogDao.updateByIdCard(1,code,xq,idCard);
                    if(flag){
                        logger.info("更新"+idCard+"对比状态完成！"+new Date());
                    }
                }
            }
        } catch (MalformedURLException e) {
            logger.error("对比出错",e);
        }
        logger.info("*********************结束对比数据********************************");
    }
}