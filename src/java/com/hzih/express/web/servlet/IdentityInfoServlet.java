package com.hzih.express.web.servlet;

import com.hzih.express.dao.AlertLogDao;
import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.web.action.compare.CompareConfigXml;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Administrator on 15-5-26.
 */
public class IdentityInfoServlet extends HttpServlet {

    private ExpressLogDao expressLogDao;
    private AlertLogDao alertLogDao;

    private Timer timer = null;

    public void destroy() {
        super.destroy();
        if (timer != null) {
            timer.cancel();
        }
        if (expressLogDao != null) {
            expressLogDao = null;
        }
        if (alertLogDao != null) {
            alertLogDao = null;
        }
    }

    /**
     * <p>
     * 在Servlet中注入对象的步骤:
     * 1.取得ServletContext
     * 2.利用Spring的工具类WebApplicationContextUtils得到WebApplicationContext
     * 3.WebApplicationContext就是一个BeanFactory,其中就有一个getBean方法
     * </p>
     */
    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext servletContext = this.getServletContext();

        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        expressLogDao = (ExpressLogDao) ctx.getBean("expressLogDao");
        alertLogDao = (AlertLogDao) ctx.getBean("alertLogDao");

        timer = new Timer(true);
        //设置任务计划，启动和间隔时间
        Calendar calendar = Calendar.getInstance();
        //表示什么时候执行
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        timer.schedule(new IdentityInfoTask(expressLogDao,alertLogDao), time, Long.parseLong(CompareConfigXml.getAttribute(CompareConfigXml.compare_minutes))* 1000L *60L);
//        timer.schedule(new IdentityInfoTask(expressLogDao), time, 1000L *5L);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
