package com.hzih.express.service.impl;

import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.CompanyDao;
import com.hzih.express.domain.Company;
import com.hzih.express.service.CompanyService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-7
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
public class CompanyServiceImpl implements CompanyService {
    private CompanyDao companyDao;

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }


    @Override
    public PageResult findCompany(int start, int limit,String queryName) {
        return companyDao.findCompany(start,limit,queryName);
    }

    @Override
    public Company findByCode(String code) {
        return companyDao.findByCode(code);
    }

    @Override
    public void remove(long l) {
        companyDao.remove(l);
    }

    @Override
    public void create(Company company) {
        companyDao.create(company);
    }

    @Override
    public String checkName(String name) {
        String msg = null;
        Company account = companyDao.findByName(name);
        if(account!=null){
            msg = "快递公司已经存在";
        } else {
            msg = "true";
        }
        return "{success:true,msg:'"+msg+"'}";
    }

    @Override
    public PageResult find(int start, int limit, String name) {
        return companyDao.find(start,limit,name);
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }
}
