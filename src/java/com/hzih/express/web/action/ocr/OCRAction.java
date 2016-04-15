package com.hzih.express.web.action.ocr;

import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.domain.CompanyPoint;
import com.hzih.express.domain.ExpressLog;
import com.hzih.express.service.LogService;
import com.hzih.express.utils.FileUtil;
import com.hzih.express.utils.ImageUtil;
import com.hzih.express.utils.StringContext;
import com.hzih.express.web.SessionUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 16-3-15.
 */
public class OCRAction extends ActionSupport {
    private static Logger logger = Logger.getLogger(OCRAction.class);

    private ExpressLogDao expressLogDao;
    private ExpressLog expressLog;
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public ExpressLogDao getExpressLogDao() {
        return expressLogDao;
    }

    public void setExpressLogDao(ExpressLogDao expressLogDao) {
        this.expressLogDao = expressLogDao;
    }

    public ExpressLog getExpressLog() {
        return expressLog;
    }

    public void setExpressLog(ExpressLog expressLog) {
        this.expressLog = expressLog;
    }


    public String loadPic() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
//        PrintWriter writer = response.getWriter();
//        String msg = null;
        String id = request.getParameter("id");
//        String str = request.getParameter("str");
//        ServletOutputStream output = null;
//        InputStream in = null;
//        try {
            ExpressLog expressLog = expressLogDao.findById(Long.parseLong(id));

            InputStream inputStream = expressLog.getBitmap().getBinaryStream();

            String dirStr = StringContext.systemPath + "/temp/";
            File dir = new File(dirStr);
            if (!dir.exists()) {
                dir.mkdirs();
            }
//            String path = dirStr + str + "-" + id + ".jpg";
//            String _path = dirStr + "thumb_450_300_" + str + "-" + id + ".jpg";
            FileUtil.copy(inputStream, response);
//            new ImageUtil().thumbnailImage(path, 450, 300);
//            new File(path).delete();
//            response.reset();
//            in = new FileInputStream(_path);
//            output = response.getOutputStream();
//            byte tmp[] = new byte[256];
//            int i = 0;
//            while ((i = in.read(tmp)) != -1) {
//                output.write(tmp, 0, i);
//            }
//            output.flush(); //强制清出缓冲区
//        } catch (FileNotFoundException e) {
//            logger.error(e.getMessage());
//        } catch (Exception e) {
//            logger.error("", e);
        /*} finally {
            try {
               *//* if (output != null) {
                    output.close();
                }*//*
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }*/
        return null;
    }

    public String saveOcr() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String json = "{success:false,msg:'保存人员信息失败'}";
        String msg = null;
        try {
//            String str = request.getParameter("str");
            ExpressLog l = expressLogDao.findById(expressLog.getId());
            l.setType("OTG");
            l.setName(expressLog.getName());
            l.setSex(expressLog.getSex());
            l.setNation(expressLog.getNation());
            l.setBirthday(expressLog.getBirthday());
            l.setAddress(expressLog.getAddress());
            l.setIdCard(expressLog.getIdCard());
            l.setValidTime(expressLog.getValidTime());
            l.setSignDepart(expressLog.getSignDepart());
            expressLogDao.update(l);
//            String dirStr = StringContext.systemPath + "/temp/";
//            String path = dirStr + "thumb_450_300_" + str + "-" + l.getId() + ".jpg";
//            new File(path).delete();
            json = "{success:true,msg:'保存人员信息成功'}";
        } catch (Exception e) {
            msg = "保存人员信息失败";
            json = "{success:true,msg:\"" + msg + "\"}";
        }
        writer.write(json.toString());
        writer.flush();
        writer.close();
        return null;
    }

    public String nextOcr() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");
        String json = "{success:false,msg:'跳到下一张失败'}";
        String msg = null;
        try {
            ExpressLog l = expressLogDao.findById(Long.parseLong(id));
            l.setType("NEXT");
            expressLogDao.update(l);
            json = "{success:true,flag:true,msg:'跳到下一张成功'}";
        } catch (Exception e) {
            msg = "跳到下一张失败";
            json = "{success:true,msg:\"" + msg + "\"}";
        }
        writer.write(json.toString());
        writer.flush();
        writer.close();
        return null;
    }

    public String getPic() throws Exception {
//        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String json = "{success:false,msg:'获取人员信息失败'}";
        String msg = null;
        String id = null;
        try {
            String type_ocr = "OCR";
            ExpressLog expressLog = expressLogDao.find(type_ocr);
            if(expressLog!=null) {
                id = String.valueOf(expressLog.getId());
                if (id == null) {
                    msg = "不存在需要处理的照片！";
                    json = "{success:false,msg:'" + msg + "'}";
                } else {
                    msg = "需要处理的数据Id:" + id;
                    json = "{success:true,id:" + id + ",msg:'" + msg + "'}";
                }
            }else {
                String type_next = "NEXT";
                ExpressLog log = expressLogDao.find(type_next);
                id = String.valueOf(log.getId());
                if (id == null) {
                    msg = "不存在需要处理的照片！";
                    json = "{success:false,msg:'" + msg + "'}";
                } else {
                    msg = "需要处理的数据Id:" + id;
                    json = "{success:true,id:" + id + ",msg:'" + msg + "'}";
                }
            }
        } catch (Exception e) {
            msg = "获取人员信息失败！";
            json = "{success:true,msg:\"" + msg + "\"}";
        } finally {
            id = null;
        }
        writer.write(json.toString());
        writer.flush();
        writer.close();
        return null;
    }
}
