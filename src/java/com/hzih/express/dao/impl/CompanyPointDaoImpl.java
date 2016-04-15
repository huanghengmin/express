package com.hzih.express.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.CompanyPointDao;
import com.hzih.express.domain.Company;
import com.hzih.express.domain.CompanyPoint;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class CompanyPointDaoImpl extends MyDaoSupport implements CompanyPointDao {

    @Override
    public void setEntityClass() {
        this.entityClass = CompanyPoint.class;
    }

    @Override
    public PageResult findCompanyPoint(int start, int limit,String code,String name,String address) {
        int pageIndex = start / limit + 1;
        StringBuilder sb = new StringBuilder("from CompanyPoint c where 1=1 ");
        List paramsList = new ArrayList();
        if (code != null && code.length() > 0) {
            sb.append(" and c.company.code = ?");
            paramsList.add( code );
        }

        if (name != null && name.length() > 0) {
            sb.append(" and c.name like ?");
            paramsList.add("%"+ name+"%" );
        }

        if (address != null && address.length() > 0) {
            sb.append(" and c.address like ?");
            paramsList.add("%"+ address+"%" );
        }
        sb.append(" order by id desc");

        String countHql = "select count(*) " + sb.toString();

        PageResult ps = this.findByPage(sb.toString(), countHql, paramsList.toArray(),pageIndex, limit);
        return ps;
    }

    @Override
    public List<CompanyPoint> findCompanyCode(String code) {
        StringBuilder sb = new StringBuilder("from CompanyPoint c where 1=1 ");
        List paramsList = new ArrayList();
        if (code != null && code.length() > 0) {
            sb.append(" and c.company.code = ?");
            paramsList.add( code );
        }
        sb.append(" order by id desc");

        Query query = getSession().createQuery(sb.toString());
        for (int i = 0; i < paramsList.toArray().length; i++) {
            query.setParameter(i, paramsList.toArray()[i]);
        }
        return query.list();

    }

    @Override
    public CompanyPoint findById(long id) {
        String hql = new String(" from CompanyPoint where id="+id);
        List<CompanyPoint> list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean add(CompanyPoint companyPoint) {
        try {
            getHibernateTemplate().saveOrUpdate(companyPoint);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean remove(long id) {
        try {
            getHibernateTemplate().delete(new CompanyPoint(id));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String checkName(String name) {
        String hql = new String("from CompanyPoint where name=?");
        List<Company> list = getHibernateTemplate().find(hql, new String[]{name});
        if (list != null && list.size() > 0) {
            return "快递公司已经存在";
        } else {
            return null;
        }
    }
}
