package com.hzih.express.web.action.company;

import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.CompanyDao;
import com.hzih.express.dao.CompanyPointDao;
import com.hzih.express.dao.RegionDao;
import com.hzih.express.domain.Company;
import com.hzih.express.domain.CompanyPoint;
import com.hzih.express.domain.Region;
import com.hzih.express.service.LogService;
import com.hzih.express.web.SessionUtils;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CompanyPointAction extends ActionSupport {
    private static Logger logger = Logger.getLogger(CompanyPointAction.class);
    private CompanyPointDao companyPointDao;
    private LogService logService;
    private CompanyPoint companyPoint;
    private CompanyDao companyDao;
    private RegionDao regionDao;

    public RegionDao getRegionDao() {
        return regionDao;
    }

    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public CompanyPoint getCompanyPoint() {
        return companyPoint;
    }

    public void setCompanyPoint(CompanyPoint companyPoint) {
        this.companyPoint = companyPoint;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public CompanyPointDao getCompanyPointDao() {
        return companyPointDao;
    }

    public void setCompanyPointDao(CompanyPointDao companyPointDao) {
        this.companyPointDao = companyPointDao;
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


    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String json = null;
        try {
            PageResult pageResult = companyPointDao.findCompanyPoint(start, limit,code,name,address);
            if (pageResult != null) {
                List<CompanyPoint> companyList = pageResult.getResults();
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

    public String findByParent() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String code = request.getParameter("code");
        String json = null;
        try {
            List<CompanyPoint> companyList = companyPointDao.findCompanyCode(code);
                if (companyList != null) {
                    json = build(companyList, companyList.size());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writer.write(json);
        writer.flush();
        writer.close();
        return null;
    }

    public String check() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String json = "{success:true,flag:true,msg:'允许添加'}";
        try {
            String name = request.getParameter("name");
            String msg = companyPointDao.checkName(name);
            if(msg!=null){
                json = "{success:true,flag:false,msg:'"+msg+"'}";
            }
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递公司网点","检查快递公司网点是否存在成功");
        } catch (Exception e) {
            logger.error("快递公司网点", e);
            logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "快递公司网点","检查快递公司网点是否存在失败");
        }
        actionBase.actionEnd(response,json,result);
        return null;
    }

    private String build(List<CompanyPoint> districts, int count) {
        StringBuilder json = new StringBuilder("{totalCount:" + count + ",root:[");
        Iterator<CompanyPoint> iterator = districts.iterator();
        while (iterator.hasNext()) {
            CompanyPoint companyPoint = iterator.next();
            if (iterator.hasNext()) {
                json.append("{");
                json.append("id:'" + companyPoint.getId() + "',");
                json.append("name:'" + companyPoint.getName() + "',");
                json.append("address:'" + companyPoint.getAddress() + "',");
                json.append("phone:'" + companyPoint.getPhone() + "',");
                json.append("contacts:'" + companyPoint.getContacts() + "',");
                json.append("code:'" + companyPoint.getCompany().getCode() + "',");
                json.append("company:'" + companyPoint.getCompany().getName() + "'");
                json.append("},");
            } else {
                json.append("{");
                json.append("id:'" + companyPoint.getId() + "',");
                json.append("name:'" + companyPoint.getName() + "',");
                json.append("address:'" + companyPoint.getAddress() + "',");
                json.append("phone:'" + companyPoint.getPhone() + "',");
                json.append("contacts:'" + companyPoint.getContacts() + "',");
                json.append("code:'" + companyPoint.getCompany().getCode() + "',");
                json.append("company:'" + companyPoint.getCompany().getName() + "'");
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
                companyPointDao.remove(Long.parseLong(id));
                msg = "删除快递点信息成功"+id;
                json = "{success:true,msg:'删除快递点成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递点管理", msg);
            }
        }catch (Exception e){
            msg = "删除快递点信息失败"+id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递点管理", msg);
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
            String code = companyPoint.getCompany().getCode();
            Company company = companyDao.findByCode(code);
            String region_code = companyPoint.getRegion().getCode();
            Region region = regionDao.findByCode(region_code);
            companyPoint.setCompany(company);
            companyPoint.setRegion(region);
            companyPointDao.create(companyPoint);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递点管理", "用户新增快递点" + companyPoint.getName()+ "信息成功");
            msg =  "<font color=\"green\">新增快递点成功,点击[确定]返回列表!</font>";
        }catch (Exception e){
            logger.error("快递点管理", e);
            logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "快递点管理","用户新增快递点"+companyPoint.getName()+"信息失败");
            msg = "<font color=\"red\">新增快递点失败</font>";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String update() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result =	actionBase.actionBegin(request);
        String msg = null;
        try {
            CompanyPoint companyPoint = companyPointDao.findById(this.companyPoint.getId());
            companyPoint.setName(this.companyPoint.getName());
            companyPoint.setAddress(this.companyPoint.getAddress());
            companyPoint.setPhone(this.companyPoint.getPhone());
            companyPoint.setContacts(this.companyPoint.getContacts());
            companyPointDao.update(companyPoint);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递点管理", "用户修改快递点" + companyPoint.getName()+ "信息成功");
            msg =  "<font color=\"green\">修改快递点成功,点击[确定]返回列表!</font>";
        }catch (Exception e){
            logger.error("快递点管理", e);
            logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "快递点管理","用户修改快递点"+companyPoint.getName()+"信息失败");
            msg = "<font color=\"red\">修改快递点失败</font>";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        actionBase.actionEnd(response, json, result);
        return null;
    }

}
