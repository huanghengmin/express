package com.hzih.express.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.Company;

import java.util.List;

public interface CompanyDao extends BaseDao {

    PageResult findCompany(int start, int limit,String queryName);

    Company findByCode(String express_company);

    void remove(long l);

    Company findByName(String name);

    PageResult find(int start, int limit, String name);
}
