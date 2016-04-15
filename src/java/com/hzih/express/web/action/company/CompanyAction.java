package com.hzih.express.web.action.company;

import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.dao.UserDao;
import com.hzih.express.domain.Company;
import com.hzih.express.domain.ExpressLog;
import com.hzih.express.domain.User;
import com.hzih.express.service.CompanyService;
import com.hzih.express.service.LogService;
import com.hzih.express.web.SessionUtils;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CompanyAction extends ActionSupport {
    private static Logger logger = Logger.getLogger(CompanyAction.class);
    private CompanyService companyService;
    private LogService logService;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
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

    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    public String findCompany() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String queryName = request.getParameter("queryName");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String json = null;
        try {
            PageResult pageResult = companyService.findCompany(start, limit,queryName);
            if (pageResult != null) {
                List<Company> companyList = pageResult.getResults();
                int count = pageResult.getAllResultsAmount();
                if (companyList != null) {
                    json = build(companyList, count);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writer.write(json);
        writer.flush();
        writer.close();
        return null;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String name = request.getParameter("name");
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = null;
        try {
            PageResult pageResult = companyService.find(start, limit,name);
            if (pageResult != null) {
                List<Company> companyList = pageResult.getResults();
                int count = pageResult.getAllResultsAmount();
                if (companyList != null) {
                    json = build(companyList, count);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String findAll() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = null;
        try {
            List<Company> companyList = companyService.findAll();
                if (companyList != null) {
                    json = build(companyList);
                }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    private String build(List<Company> districts, int count) {
        StringBuilder json = new StringBuilder("{totalCount:" + count + ",root:[");
        Iterator<Company> iterator = districts.iterator();
        while (iterator.hasNext()) {
            Company company = iterator.next();
            if (iterator.hasNext()) {
                json.append("{");
                json.append("id:'" + company.getId() + "',");
                json.append("code:'" + company.getCode() + "',");
                json.append("name:'" + company.getName() + "'");
                json.append("},");
            } else {
                json.append("{");
                json.append("id:'" + company.getId() + "',");
                json.append("code:'" + company.getCode() + "',");
                json.append("name:'" + company.getName() + "'");
                json.append("}");
            }
        }
        json.append("]}");
        return json.toString();
    }

    private String build(List<Company> districts) {
        StringBuilder json = new StringBuilder("{totalCount:" + districts.size() + ",root:[");
        Iterator<Company> iterator = districts.iterator();
        while (iterator.hasNext()) {
            Company company = iterator.next();
            if (iterator.hasNext()) {
                json.append("{");
                json.append("id:'" + company.getId() + "',");
                json.append("code:'" + company.getCode() + "',");
                json.append("name:'" + company.getName() + "'");
                json.append("},");
            } else {
                json.append("{");
                json.append("id:'" + company.getId() + "',");
                json.append("code:'" + company.getCode() + "',");
                json.append("name:'" + company.getName() + "'");
                json.append("}");
            }
        }
        json.append("]}");
        return json.toString();
    }


    public String remove() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'删除失败'}";
        String msg = null;
        String id = request.getParameter("id");
        try {
            if(id!=null) {
                companyService.remove(Long.parseLong(id));
                msg = "删除快递公司信息成功"+id;
                json = "{success:true,msg:'删除快递公司成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递公司管理", msg);
            }
        }catch (Exception e){
            msg = "删除快递公司信息失败"+id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递公司管理", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String insert() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String msg = null;
        try {
            companyService.create(company);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递公司管理", "用户新增快递点" + company.getName()+ "信息成功");
            msg =  "<font color=\"green\">新增快递公司成功,点击[确定]返回列表!</font>";
        }catch (Exception e){
            logger.error("快递公司管理", e);
            logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "快递公司管理","用户新增快递点"+company.getName()+"信息失败");
            msg = "<font color=\"red\">新增快递点失败</font>";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }


    public String check() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String json = "{success:false,msg:'校验失败'}";
        try {
            String name = request.getParameter("name");
            json = companyService.checkName(name);
            logService.newLog("INFO",  SessionUtils.getAccount(request).getUserName(), "快递公司","检查快递公司是否存在成功");
        } catch (Exception e) {
            logger.error("快递公司", e);
            logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "快递公司","检查快递公司是否存在失败");
        }
        actionBase.actionEnd(response,json,result);
        return null;
    }


}
