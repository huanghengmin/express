package com.hzih.express.web.servlet;

import com.hzih.express.dao.ExpressLogDao;
import org.apache.log4j.Logger;

import java.util.TimerTask;

public class IdentityInfoStatusTask extends TimerTask {

    private Logger logger = Logger.getLogger(IdentityInfoStatusTask.class);

    private ExpressLogDao expressLogDao;

    public IdentityInfoStatusTask(ExpressLogDao expressLogDao) {
        this.expressLogDao = expressLogDao;
    }


    @Override
    public void run() {
        logger.info("*********************开始更改对比状态********************************");
        boolean flag = expressLogDao.modifyAll();
        if (flag) {
            logger.info("更改对比状态成功");
        } else {
            logger.error("更改对比状态失败");
        }
        logger.info("*********************结束更改对比状态********************************");
    }
}