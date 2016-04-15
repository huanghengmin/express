package com.hzih.express.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.domain.ExpressLog;
import com.hzih.express.domain.Region;
import com.hzih.express.utils.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpressLogDaoImpl extends MyDaoSupport implements ExpressLogDao {

    @Override
    public void setEntityClass() {
        this.entityClass = ExpressLog.class;
    }

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageLength
     * @param startDate
     * @param endDate
     * @return
     */
    public PageResult listLogsByParams(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String idCard, String name, String shapeCode, String type, String phone, String express_code) {
        StringBuffer sb = new StringBuffer(" from ExpressLog s where 1=1");
        List params = new ArrayList();// 手动指定容量，避免多次扩容
        if (startDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
            params.add(startDate);
        }
        if (endDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
            params.add(endDate);
        }

        if (StringUtils.isNotBlank(idCard)) {
            sb.append(" and idCard like ?");
            params.add("%" + idCard + "%");
        }
        if (StringUtils.isNotBlank(name)) {
            sb.append(" and name like ?");
            params.add("%" + name + "%");
        }
        if (StringUtils.isNotBlank(shapeCode)) {
            sb.append(" and shapeCode like ?");
            params.add("%" + shapeCode + "%");
        }

        if (StringUtils.isNotBlank(type)) {
            sb.append(" and type = ?");
            params.add(type);
        }

        if (StringUtils.isNotBlank(phone)) {
            sb.append(" and phone like ?");
            params.add("%" + phone + "%");
        }

        if (StringUtils.isNotBlank(express_code)) {
            sb.append(" and s.user.companyPoint.company.code = ?");
            params.add(express_code);
        }
        sb.append(" order by id desc");

        String countString = "select count(*) " + sb.toString();
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), pageIndex, pageLength);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }

    @Override
    public ExpressLog findById(long id) {
        String hql = new String("from ExpressLog where id= " + id);
        List<ExpressLog> list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public PageResult listLogsByParamsIDCardDistinct(int pageIndex, int pageLength, Date startDate, Date endDate, String idCard, String name, String shapeCode, String type, String phone, String express_code) {
        StringBuffer sb = new StringBuffer(" from distinct(s.idCard) ExpressLog s where 1=1");
        List params = new ArrayList();// 手动指定容量，避免多次扩容
        if (startDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
            params.add(startDate);
        }
        if (endDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
            params.add(endDate);
        }

        if (StringUtils.isNotBlank(idCard)) {
            sb.append(" and idCard like ?");
            params.add("%" + idCard + "%");
        }
        if (StringUtils.isNotBlank(name)) {
            sb.append(" and name like ?");
            params.add("%" + name + "%");
        }
        if (StringUtils.isNotBlank(shapeCode)) {
            sb.append(" and shapeCode like ?");
            params.add("%" + shapeCode + "%");
        }

        if (StringUtils.isNotBlank(type)) {
            sb.append(" and type = ?");
            params.add(type);
        }

        if (StringUtils.isNotBlank(phone)) {
            sb.append(" and phone like ?");
            params.add("%" + phone + "%");
        }

        if (StringUtils.isNotBlank(express_code)) {
            sb.append(" and s.user.companyPoint.company.code = ?");
            params.add(express_code);
        }
        sb.append(" order by id desc");

        String countString = "select count(*) " + sb.toString();
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), pageIndex, pageLength);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }

    @Override
    public boolean modifyAll() {
        boolean flag = false;
        String s = "update ExpressLog i set i.status= " + 0 + " where i.status=1";
        Session session = super.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(s);
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public List<String> findByDistinctIdCard() {
        Session session = super.getSession();
        try {
            String hql = "Select distinct s.idCard from ExpressLog s order by s.idCard desc";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateByIdCard(int status,String code,String xq,String idCard) {
        boolean flag = false;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime=df.format(new Date());
        String s = " update ExpressLog i set " +
                "i.status= " + status + "," +
                "i.code='" + code + "'," +
                "i.compare_time= '"+currentTime+"'," +
                "i.xq='" + xq + "' where i.idCard='" + idCard+"'";
        Session session = super.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery(s);
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public PageResult listLogsByGAParams(int pageIndex, int pageLength, Date startDate, Date endDate, String idCard, String name, String shapeCode, String type, String phone, Region region, String express_code) {
        StringBuffer sb = new StringBuffer(" from ExpressLog s where 1=1");
        List params = new ArrayList(4);// 手动指定容量，避免多次扩容
        if (startDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
            params.add(startDate);
        }
        if (endDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
            params.add(endDate);
        }

        if (StringUtils.isNotBlank(idCard)) {
            sb.append(" and idCard like ?");
            params.add("%" + idCard + "%");
        }
        if (StringUtils.isNotBlank(name)) {
            sb.append(" and name like ?");
            params.add("%" + name + "%");
        }
        if (StringUtils.isNotBlank(shapeCode)) {
            sb.append(" and shapeCode like ?");
            params.add("%" + shapeCode + "%");
        }

        if (StringUtils.isNotBlank(type)) {
            sb.append(" and type = ?");
            params.add(type);
        }

        if (StringUtils.isNotBlank(phone)) {
            sb.append(" and phone like ?");
            params.add("%" + phone + "%");
        }

        if (StringUtils.isNotBlank(express_code)) {
            sb.append(" and s.user.companyPoint.company.code = ?");
            params.add(express_code);
        }

        String code = region.getCode();
        String first = code.substring(0,2);
        String content = code.substring(2,4);
        String end = code.substring(4,6);
        if("0000".endsWith(content+end)){
            sb.append(" and s.user.companyPoint.region.code like ?");
            params.add(first+"____");
        }else if("00".endsWith(end)&&(!"0000".endsWith(content+end))){
            sb.append(" and s.user.companyPoint.region.code like ?");
            params.add(first+content+"__");
        }else {
            sb.append(" and s.user.companyPoint.region.code = ?");
            params.add(first+content+end);
        }

        sb.append(" order by id desc");

        String countString = "select count(*) " + sb.toString();
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), pageIndex, pageLength);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }


    @Override
    public ExpressLog find(String type_ocr) {
        StringBuffer sb = new StringBuffer(" from ExpressLog s where 1=1 and type='" + type_ocr + "' order by id desc");
        Query query = getSession().createQuery(sb.toString());
        query.setMaxResults(1);
        List<ExpressLog> expressLogs = query.list();
        if (expressLogs != null && expressLogs.size() > 0) {
            return expressLogs.get(0);
        } else {
            return null;
        }
    }

    public PageResult listLogsByParams(int pageIndex, int pageLength, Date startDate, Date endDate, String idCard, String name, String shapeCode, String type, String phone, long id) {
        StringBuffer sb = new StringBuffer(" from ExpressLog s where 1=1");
        List params = new ArrayList(4);// 手动指定容量，避免多次扩容
        if (startDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
            params.add(startDate);
        }
        if (endDate != null) {
            sb.append(" and date_format(sendTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
            params.add(endDate);
        }

        if (StringUtils.isNotBlank(idCard)) {
            sb.append(" and idCard like ?");
            params.add("%" + idCard + "%");
        }
        if (StringUtils.isNotBlank(name)) {
            sb.append(" and name like ?");
            params.add("%" + name + "%");
        }
        if (StringUtils.isNotBlank(shapeCode)) {
            sb.append(" and shapeCode like ?");
            params.add("%" + shapeCode + "%");
        }

        if (StringUtils.isNotBlank(type)) {
            sb.append(" and type = ?");
            params.add(type);
        }

        if (StringUtils.isNotBlank(phone)) {
            sb.append(" and phone like ?");
            params.add("%" + phone + "%");
        }

        sb.append(" and s.user.companyPoint.id = ?");
        params.add(id);

        sb.append(" order by id desc");

        String countString = "select count(*) " + sb.toString();
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), pageIndex, pageLength);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }

    @Override
    public void add(ExpressLog expressLog) {
        getHibernateTemplate().save(expressLog);
    }

    @Override
    public void remove(ExpressLog expressLog) {
        getHibernateTemplate().delete(expressLog);
    }
}
