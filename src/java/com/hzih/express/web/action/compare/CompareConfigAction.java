package com.hzih.express.web.action.compare;

import com.hzih.express.service.LogService;
import com.hzih.express.web.SessionUtils;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hhm
 * Date: 12-11-8
 * Time: 下午11:11
 * To change this template use File | Settings | File Templates.
 */
public class CompareConfigAction extends ActionSupport {
    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    private LogService logService;


    private Logger logger  = Logger.getLogger(CompareConfigAction.class);

    public String save() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String json ="{success:false}";
        String compare_days = request.getParameter("compare_days");
        String compare_minutes = request.getParameter("compare_minutes");
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        CompareConfigXml.saveConfig(compare_days,compare_minutes,ip,port);
        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(),"对比配置","配置对比信息成功");
        json ="{success:true}";
        actionBase.actionEnd(response,json,result);
        return null;
    }

    public String find(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        int totalCount =0;
        StringBuilder stringBuilder = new StringBuilder();
        try{
        getData(stringBuilder);
        }catch (Exception e){
           logger.error(e.getMessage());
        }
        totalCount = totalCount+1;
        StringBuilder json=new StringBuilder("{total:"+totalCount+",rows:[");
        json.append(stringBuilder.toString().substring(0,stringBuilder.toString().length()-1));
        json.append("]}");
        try {
            actionBase.actionEnd(response,json.toString(),result);
        } catch (IOException e) {
          logger.error(e.getMessage());
        }
        return null;
    }

    private void getData(StringBuilder stringBuilder) {
        stringBuilder.append("{");
        stringBuilder.append("compare_days:'"+ CompareConfigXml.getAttribute(CompareConfigXml.compare_days)+"',");
        stringBuilder.append("compare_minutes:'"+ CompareConfigXml.getAttribute(CompareConfigXml.compare_minutes)+"',");
        stringBuilder.append("ip:'"+ CompareConfigXml.getAttribute(CompareConfigXml.ip)+"',");
        stringBuilder.append("port:'"+ CompareConfigXml.getAttribute(CompareConfigXml.port)+"'");
        stringBuilder.append("},");
    }
}
