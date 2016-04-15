package com.hzih.express.service;

import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.Company;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-7
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyService {
    PageResult findCompany(int start, int limit,String queryName);

    Company findByCode(String code);

    void remove(long l);

    void create(Company company);

    String checkName(String name);

    PageResult find(int start, int limit, String name);

    List<Company> findAll();
}
