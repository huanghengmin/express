package com.hzih.express.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.CompanyDao;
import com.hzih.express.domain.Company;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class CompanyDaoImpl extends MyDaoSupport implements CompanyDao {

    @Override
    public void setEntityClass() {
        this.entityClass = Company.class;
    }

    @Override
    public PageResult findCompany(int start, int limit,String queryName) {
        int pageIndex = start / limit + 1;
        String hql = " from Company where 1=1";

        List paramsList = new ArrayList();
        if (queryName != null && queryName.length() > 0) {
            hql += " and name like ?";
            paramsList.add("%" + queryName + "%");
        }
        hql += " order by id desc";
        String countHql = "select count(*) " + hql;

        PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
                pageIndex, limit);
        return ps;
    }

    @Override
    public Company findByCode(String express_company) {
        String hql = new String("from Company where code=?");
        List<Company> list = getHibernateTemplate().find(hql,new String[] { express_company });
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void remove(long l) {
        getHibernateTemplate().delete(new Company(l));
    }

    @Override
    public Company findByName(String name) {
        String hql = new String("from Company where name=?");
        List<Company> list = getHibernateTemplate().find(hql,new String[] { name });
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public PageResult find(int start, int limit, String name) {
        int pageIndex = start / limit + 1;
        String hql = " from Company where 1=1";

        List paramsList = new ArrayList();
        if (name != null && name.length() > 0) {
            hql += " and name like ?";
            paramsList.add("%" + name + "%");
        }

        hql += " order by id desc";

        String countHql = "select count(*) " + hql;

        PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),pageIndex, limit);
        return ps;
    }
}
