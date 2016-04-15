package com.hzih.express.web.action.alert;

import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.AlertLogDao;
import com.hzih.express.domain.AlertLog;
import com.hzih.express.service.LogService;
import com.hzih.express.web.SessionUtils;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 16-4-6.
 */
public class AlertLogAction extends ActionSupport {
    private Logger logger = Logger.getLogger(AlertLogAction.class);
    private LogService logService;
    private AlertLogDao alertLogDao;

    public AlertLogDao getAlertLogDao() {
        return alertLogDao;
    }

    public void setAlertLogDao(AlertLogDao alertLogDao) {
        this.alertLogDao = alertLogDao;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String find() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String idCard = request.getParameter("idCard");
        String json = null;
        PageResult pageResult = alertLogDao.listByPage(idCard, start/limit+1,limit);
        if (pageResult != null) {
            List<AlertLog> companyList = pageResult.getResults();
            int count = pageResult.getAllResultsAmount();
            if (companyList != null) {
                json = build(companyList, count);
            }
        }

        actionBase.actionEnd(response,json,result);
        return null;
    }

    public String read()throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'已读报警失败'}";
        String msg = null;
        String id = request.getParameter("id");
        try {
            boolean flag = alertLogDao.read(id);
            if(flag){
                msg = "已读报警成功"+id;
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户信息", msg);
            }else {
                msg = "已读报警失败"+id;
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户信息", msg);
            }
        }catch (Exception e){
            msg = "已读报警失败"+id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户信息", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    private String build(List<AlertLog> districts, int count) {
        StringBuilder json = new StringBuilder("{total:" + count + ",rows:[");
        Iterator<AlertLog> iterator = districts.iterator();
        while (iterator.hasNext()) {
            AlertLog company = iterator.next();
            if (iterator.hasNext()) {
                json.append("{");
                json.append("id:'" + company.getId() + "',");
                json.append("idCard:'" + company.getId_card() + "',");
                json.append("read_flag:'" + company.getRead_flag() + "',");
                json.append("code:'" + company.getCode() + "',");
                json.append("xq:'" + company.getXq() + "',");
                json.append("log_time:'" + company.getLog_time() + "'");
                json.append("},");
            } else {
                json.append("{");
                json.append("id:'" + company.getId() + "',");
                json.append("idCard:'" + company.getId_card() + "',");
                json.append("read_flag:'" + company.getRead_flag() + "',");
                json.append("code:'" + company.getCode() + "',");
                json.append("xq:'" + company.getXq() + "',");
                json.append("log_time:'" + company.getLog_time() + "'");
                json.append("}");
            }
        }
        json.append("]}");
        return json.toString();
    }


}
