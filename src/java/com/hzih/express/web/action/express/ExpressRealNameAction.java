package com.hzih.express.web.action.express;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;
import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.dao.UserDao;
import com.hzih.express.domain.*;
import com.hzih.express.service.LogService;
import com.hzih.express.utils.FileUtil;
import com.hzih.express.utils.StringUtils;
import com.hzih.express.web.SessionUtils;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-12.
 */
public class ExpressRealNameAction extends ActionSupport {
    private Logger logger = Logger.getLogger(ExpressRealNameAction.class);
    private LogService logService;
    private ExpressLogDao expressLogDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;
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

    public ExpressLogDao getExpressLogDao() {
        return expressLogDao;
    }

    public void setExpressLogDao(ExpressLogDao expressLogDao) {
        this.expressLogDao = expressLogDao;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    public String upload() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        String json = "{success:false,msg:'添加出错'}";
        String msg = null;
        ExpressLog expressLog = new ExpressLog();

        String type = request.getParameter("type");
        expressLog.setType(type);
        if (type.equals("OCR")) {
            String shapeCode = request.getParameter("shapeCode");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            String sendTime = request.getParameter("sendTime");
            String phone = request.getParameter("phone");
            String contact = request.getParameter("contact");

            String idimg = request.getParameter("idimg");
            if (idimg != null) {
                byte[] bytes = Base64.decodeBase64(idimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setBitmap(photo);
            }

            String faceimg = request.getParameter("faceimg");
            if (faceimg != null) {
                byte[] bytes = Base64.decodeBase64(faceimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setFace_img(photo);
            }

            String senderimg = request.getParameter("senderimg");
            if (senderimg != null) {
                byte[] bytes = Base64.decodeBase64(senderimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setSender_img(photo);
            }

            String unpackimg = request.getParameter("unpackimg");
            if (unpackimg != null) {
                byte[] bytes = Base64.decodeBase64(unpackimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setUnpack_img(photo);
            }

            expressLog.setShapeCode(shapeCode);
            expressLog.setLatitude(latitude);
            expressLog.setLongitude(longitude);
            expressLog.setContact(contact);
            User user = userDao.find(phone);
            if (user != null)
                expressLog.setUser(user);
            if (sendTime != null)
                expressLog.setSendTime(format.parse(sendTime));
        } else if (type.equals("OTG")) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String dn = request.getParameter("dn");
            String idCard = request.getParameter("idCard");
            String nation = request.getParameter("nation");
            String sex = request.getParameter("sex");
            String shapeCode = request.getParameter("shapeCode");
            String signDepart = request.getParameter("signDepart");
            String validTime = request.getParameter("validTime");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            String sendTime = request.getParameter("sendTime");
            String phone = request.getParameter("phone");
            String contact = request.getParameter("contact");

            String idimg = request.getParameter("idimg");
            if (idimg != null) {
                byte[] bytes = Base64.decodeBase64(idimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setBitmap(photo);
            }

            String faceimg = request.getParameter("faceimg");
            if (faceimg != null) {
                byte[] bytes = Base64.decodeBase64(faceimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setFace_img(photo);
            }

            String senderimg = request.getParameter("senderimg");
            if (senderimg != null) {
                byte[] bytes = Base64.decodeBase64(senderimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setSender_img(photo);
            }

            String unpackimg = request.getParameter("unpackimg");
            if (unpackimg != null) {
                byte[] bytes = Base64.decodeBase64(unpackimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setUnpack_img(photo);
            }

            expressLog.setName(name);
            expressLog.setAddress(address);
            expressLog.setBirthday(birthday);
            expressLog.setDN(dn);
            expressLog.setIdCard(idCard);
            expressLog.setNation(nation);
            expressLog.setSex(sex);
            expressLog.setShapeCode(shapeCode);
            expressLog.setSignDepart(signDepart);
            expressLog.setValidTime(validTime);
            expressLog.setLatitude(latitude);
            expressLog.setLongitude(longitude);
            expressLog.setContact(contact);
            User user = userDao.find(phone);
            if (user != null)
                expressLog.setUser(user);
            if (sendTime != null)
                expressLog.setSendTime(format.parse(sendTime));
        }
        try {
            expressLogDao.add(expressLog);
            msg = "添加快递信息成功" + expressLog.getUser().getPhone() + "**********" + expressLog.getShapeCode();
            json = "{success:true,msg:'" + msg + "'}";
            logger.info("添加快递信息成功,操作时间:" + new Date() + ",操作信息:" + msg);
        } catch (Exception e) {
            msg = "添加快递信息失败" + expressLog.getUser().getPhone() + "**********" + expressLog.getShapeCode();
            json = "{success:false,msg:'" + msg + "'}";
            logger.info("添加快递信息失败,操作时间:" + new Date() + ",操作信息:" + msg);
        }
        response.getWriter().write(json);
        return null;
    }

    public String find() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{'success':true,'total':0,'rows':[]}";
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String idCard = request.getParameter("idCard");
            String name = request.getParameter("name");
            String shapeCode = request.getParameter("shapeCode");
            String express_code = request.getParameter("express_code");
            String type = request.getParameter("type");
            String phone = request.getParameter("phone");
            Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
                    .parse(startDateStr, "yyyy-MM-dd");
            Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
                    .parse(endDateStr, "yyyy-MM-dd");

            PageResult pageResult = expressLogDao.listLogsByParams(start / limit + 1, limit, startDate, endDate, idCard, name, shapeCode, type, phone, express_code);

            List<ExpressLog> expressLogs = pageResult.getResults();
            int total = pageResult.getAllResultsAmount();
            json = "{success:true,total:" + total + ",rows:[";
            for (ExpressLog u : expressLogs) {
                json += "{id:'" + u.getId() + "'" +
                        ",name:'" + u.getName() + "'" +
                        ",sex:'" + u.getSex() + "'" +
                        ",nation:'" + u.getNation() + "'" +
                        ",birthday:'" + u.getBirthday() + "'" +
                        ",address:'" + u.getAddress() + "'" +
                        ",idCard:'" + u.getIdCard() + "'" +
                        ",signDepart:'" + u.getSignDepart() + "'" +
                        ",validTime:'" + u.getValidTime() + "'" +
                        ",shapeCode:'" + u.getShapeCode() + "'" +
                        ",DN:'" + u.getDN() + "'" +
                        ",longitude:'" + u.getLongitude() + "'" +
                        ",type:'" + u.getType() + "'" +

                        ",phone:'" + u.getUser().getPhone() + "'" +
                        ",contact:'" + u.getContact() + "'" +
                        ",express_idCard:'" + u.getUser().getIdCard() + "'" +
                        ",express:'" + u.getUser().getExpress_name() + "'" +
                        ",express_company:'" + u.getUser().getCompanyPoint().getCompany().getName() + "'" +
                        ",express_number:'" + u.getUser().getExpress_number() + "'" +

                        ",latitude:'" + u.getLatitude() + "'";
                if (u.getSendTime() != null)
                    json += ",sendTime:'" + format.format(u.getSendTime()) + "'";
                json += "},";
            }
            json += "]}";

        } catch (Exception e) {
            logger.error("快递日志审计", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "快递日志审计", "用户读取快递日志审计信息失败 ");
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

   /* public String findByCompare() throws Exception {

       *//* 重点人员类别(7+x) 
        代码 名称
        01 涉恐人员
        02 涉稳人员
        03 在逃人员
        04 涉毒人员
        05 部前科人员
        06 精神病人
        07 上访人员
        25 个人极端行为人员
        26 社区矫正人员
        27 重口人员
        28 其他管控类人员
        29 经侦前科人员
        30 刑嫌人员
        31 省侵财前科人员
        32 脱失人员*//*

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{'success':true,'total':0,'rows':[]}";
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String idCard = request.getParameter("idCard");
            String name = request.getParameter("name");
            String shapeCode = request.getParameter("shapeCode");
            String express_code = request.getParameter("express_code");
            String type = request.getParameter("type");
            String phone = request.getParameter("phone");
            Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
                    .parse(startDateStr, "yyyy-MM-dd");
            Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
                    .parse(endDateStr, "yyyy-MM-dd");

            PageResult pageResult = expressLogDao.listLogsByParams(start / limit + 1, limit, startDate, endDate, idCard, name, shapeCode, type, phone, express_code);

            List<ExpressLog> expressLogs = pageResult.getResults();
            int total = pageResult.getAllResultsAmount();

            StringBuilder stringBuilder = new StringBuilder("{success:true,total:" + total + ",rows:[");
            for (ExpressLog u : expressLogs) {
                stringBuilder.append("{id:'" + u.getId() + "'");
                stringBuilder.append(",name:'" + u.getName() + "'");
                stringBuilder.append(",sex:'" + u.getSex() + "'");
                stringBuilder.append(",nation:'" + u.getNation() + "'");
                stringBuilder.append(",birthday:'" + u.getBirthday() + "'");
                stringBuilder.append(",address:'" + u.getAddress() + "'");
                stringBuilder.append(",idCard:'" + u.getIdCard() + "'");
                stringBuilder.append(",signDepart:'" + u.getSignDepart() + "'");
                stringBuilder.append(",validTime:'" + u.getValidTime() + "'");
                stringBuilder.append(",shapeCode:'" + u.getShapeCode() + "'");
                stringBuilder.append(",DN:'" + u.getDN() + "'");
                stringBuilder.append(",longitude:'" + u.getLongitude() + "'");
                stringBuilder.append(",type:'" + u.getType() + "'");
                stringBuilder.append(",status:'" + u.getStatus() + "'");
                stringBuilder.append(",code:'" + u.getCode() + "'");
                if (u.getCompare_time() != null)
                    stringBuilder.append(",compare_time:'" + format.format(u.getCompare_time()) + "'");
                else
                    stringBuilder.append(",compare_time:''");
                stringBuilder.append(",xq:'" + u.getXq() + "'");
                stringBuilder.append(",phone:'" + u.getUser().getPhone() + "'");
                stringBuilder.append(",contact:'" + u.getContact() + "'");
                stringBuilder.append(",express_idCard:'" + u.getUser().getIdCard() + "'");
                stringBuilder.append(",express:'" + u.getUser().getExpress_name() + "'");
                stringBuilder.append(",express_company:'" + u.getUser().getCompanyPoint().getCompany().getName() + "'");
                stringBuilder.append(",express_number:'" + u.getUser().getExpress_number() + "'");
                stringBuilder.append(",latitude:'" + u.getLatitude() + "'");
                if (u.getSendTime() != null)
                    stringBuilder.append(",sendTime:'" + format.format(u.getSendTime()) + "'");
                else
                    stringBuilder.append(",sendTime:''");
                stringBuilder.append("},");
            }
            stringBuilder.append("]}");
            json = stringBuilder.toString();
        } catch (Exception e) {
            logger.error("快递日志审计", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "快递日志审计", "用户读取快递日志审计信息失败 ");
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }*/

    public String findByPoint() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{'success':true,'total':0,'rows':[]}";
        try {
            Account account = SessionUtils.getAccount(request);
            CompanyPoint companyPoint = account.getCompanyPoint();
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String idCard = request.getParameter("idCard");
            String name = request.getParameter("name");
            String shapeCode = request.getParameter("shapeCode");
//            String express_code = request.getParameter("express_code");
            String type = request.getParameter("type");
            String phone = request.getParameter("phone");
            Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
                    .parse(startDateStr, "yyyy-MM-dd");
            Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
                    .parse(endDateStr, "yyyy-MM-dd");

            PageResult pageResult = expressLogDao.listLogsByParams(start / limit + 1, limit, startDate, endDate, idCard, name, shapeCode, type, phone, companyPoint.getId());

            List<ExpressLog> expressLogs = pageResult.getResults();
            int total = pageResult.getAllResultsAmount();
            json = "{success:true,total:" + total + ",rows:[";
            for (ExpressLog u : expressLogs) {
                json += "{id:'" + u.getId() + "'" +
                        ",name:'" + u.getName() + "'" +
                        ",sex:'" + u.getSex() + "'" +
                        ",nation:'" + u.getNation() + "'" +
                        ",birthday:'" + u.getBirthday() + "'" +
                        ",address:'" + u.getAddress() + "'" +
                        ",idCard:'" + u.getIdCard() + "'" +
                        ",signDepart:'" + u.getSignDepart() + "'" +
                        ",validTime:'" + u.getValidTime() + "'" +
                        ",shapeCode:'" + u.getShapeCode() + "'" +
                        ",DN:'" + u.getDN() + "'" +
                        ",longitude:'" + u.getLongitude() + "'" +
                        ",type:'" + u.getType() + "'" +

                        ",phone:'" + u.getUser().getPhone() + "'" +
                        ",contact:'" + u.getContact() + "'" +
                        ",express_idCard:'" + u.getUser().getIdCard() + "'" +
                        ",express:'" + u.getUser().getExpress_name() + "'" +
                        ",express_company:'" + u.getUser().getCompanyPoint().getCompany().getName() + "'" +
                        ",express_company_point:'" + u.getUser().getCompanyPoint().getName() + "'" +
                        ",express_number:'" + u.getUser().getExpress_number() + "'" +

                        ",latitude:'" + u.getLatitude() + "'";
                if (u.getSendTime() != null)
                    json += ",sendTime:'" + format.format(u.getSendTime()) + "'";
                json += "},";
            }
            json += "]}";

        } catch (Exception e) {
            logger.error("快递日志审计", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "快递日志审计", "用户读取快递日志审计信息失败 ");
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String findByGAAccount() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{'success':true,'total':0,'rows':[]}";
        try {
            Account account = SessionUtils.getAccount(request);
            Region region = account.getRegion();
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String idCard = request.getParameter("idCard");
            String name = request.getParameter("name");
            String shapeCode = request.getParameter("shapeCode");
            String express_code = request.getParameter("express_code");
            String type = request.getParameter("type");
            String phone = request.getParameter("phone");
            Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
                    .parse(startDateStr, "yyyy-MM-dd");
            Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
                    .parse(endDateStr, "yyyy-MM-dd");

            PageResult pageResult = expressLogDao.listLogsByGAParams(start / limit + 1, limit, startDate, endDate, idCard, name, shapeCode, type, phone, region,express_code);

            List<ExpressLog> expressLogs = pageResult.getResults();
            int total = pageResult.getAllResultsAmount();

            StringBuilder stringBuilder = new StringBuilder("{success:true,total:" + total + ",rows:[");
            for (ExpressLog u : expressLogs) {
                stringBuilder.append("{id:'" + u.getId() + "'");
                stringBuilder.append(",name:'" + u.getName() + "'");
                stringBuilder.append(",sex:'" + u.getSex() + "'");
                stringBuilder.append(",nation:'" + u.getNation() + "'");
                stringBuilder.append(",birthday:'" + u.getBirthday() + "'");
                stringBuilder.append(",address:'" + u.getAddress() + "'");
                stringBuilder.append(",idCard:'" + u.getIdCard() + "'");
                stringBuilder.append(",signDepart:'" + u.getSignDepart() + "'");
                stringBuilder.append(",validTime:'" + u.getValidTime() + "'");
                stringBuilder.append(",shapeCode:'" + u.getShapeCode() + "'");
                stringBuilder.append(",DN:'" + u.getDN() + "'");
                stringBuilder.append(",longitude:'" + u.getLongitude() + "'");
                stringBuilder.append(",type:'" + u.getType() + "'");
                stringBuilder.append(",status:'" + u.getStatus() + "'");
                stringBuilder.append(",code:'" + u.getCode() + "'");
                if (u.getCompare_time() != null)
                    stringBuilder.append(",compare_time:'" + format.format(u.getCompare_time()) + "'");
                else
                    stringBuilder.append(",compare_time:''");
                stringBuilder.append(",xq:'" + u.getXq() + "'");
                stringBuilder.append(",phone:'" + u.getUser().getPhone() + "'");
                stringBuilder.append(",contact:'" + u.getContact() + "'");
                stringBuilder.append(",express_idCard:'" + u.getUser().getIdCard() + "'");
                stringBuilder.append(",express:'" + u.getUser().getExpress_name() + "'");
                stringBuilder.append(",express_company:'" + u.getUser().getCompanyPoint().getCompany().getName() + "'");
                stringBuilder.append(",express_number:'" + u.getUser().getExpress_number() + "'");
                stringBuilder.append(",latitude:'" + u.getLatitude() + "'");
                if (u.getSendTime() != null)
                    stringBuilder.append(",sendTime:'" + format.format(u.getSendTime()) + "'");
                else
                    stringBuilder.append(",sendTime:''");
                stringBuilder.append("},");
            }
            stringBuilder.append("]}");
            json = stringBuilder.toString();
        } catch (Exception e) {
            logger.error("快递日志审计", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "快递日志审计", "用户读取快递日志审计信息失败 ");
        }
        actionBase.actionEnd(response, json, result);
        return null;
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
            if (id != null) {
                expressLogDao.remove(new ExpressLog(Long.parseLong(id)));
                msg = "删除快递信息成功" + id;
                json = "{success:true,msg:'删除成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
            }
        } catch (Exception e) {
            msg = "删除快递信息失败" + id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String loadBytes() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'加载失败'}";
        String msg = null;
        String id = request.getParameter("id");
        try {
            if (id != null) {
//                response.reset();
                ExpressLog expressLog = expressLogDao.findById(Long.parseLong(id));
                InputStream inputStream = expressLog.getBitmap().getBinaryStream();
                FileUtil.copy(inputStream, response);
                msg = "加载信息成功" + id;
                json = "{success:true,msg:'加载信息成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
            }
        } catch (Exception e) {
            msg = "加载信息失败" + id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String loadUnpackImg() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'加载失败'}";
        String msg = null;
        String id = request.getParameter("id");
        try {
            if (id != null) {
//                response.reset();
                ExpressLog expressLog = expressLogDao.findById(Long.parseLong(id));
                InputStream inputStream = expressLog.getUnpack_img().getBinaryStream();
                FileUtil.copy(inputStream, response);
                msg = "加载信息成功" + id;
                json = "{success:true,msg:'加载信息成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
            }
        } catch (Exception e) {
            msg = "加载信息失败" + id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String loadSenderImg() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'加载失败'}";
        String msg = null;
        String id = request.getParameter("id");
        try {
            if (id != null) {
//                response.reset();
                ExpressLog expressLog = expressLogDao.findById(Long.parseLong(id));
                InputStream inputStream = expressLog.getSender_img().getBinaryStream();
                FileUtil.copy(inputStream, response);
                msg = "加载信息成功" + id;
                json = "{success:true,msg:'加载信息成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
            }
        } catch (Exception e) {
            msg = "加载信息失败" + id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

    public String loadFaceImg() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ActionBase actionBase = new ActionBase();
        String result = actionBase.actionBegin(request);
        String json = "{success:false,msg:'加载失败'}";
        String msg = null;
        String id = request.getParameter("id");
        try {
            if (id != null) {
//                response.reset();
                ExpressLog expressLog = expressLogDao.findById(Long.parseLong(id));
                InputStream inputStream = expressLog.getFace_img().getBinaryStream();
                FileUtil.copy(inputStream, response);
                msg = "加载信息成功" + id;
                json = "{success:true,msg:'加载信息成功'}";
                logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
                logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
            }
        } catch (Exception e) {
            msg = "加载信息失败" + id;
            logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "快递信息", msg);
        }
        actionBase.actionEnd(response, json, result);
        return null;
    }

}
