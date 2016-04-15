package com.hzih.express.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.CompanyPoint;

import java.util.List;

public interface CompanyPointDao extends BaseDao {

    PageResult findCompanyPoint(int start, int limit,String code,String name,String address);

    List<CompanyPoint> findCompanyCode(String code);

    CompanyPoint findById(long id);

    boolean add(CompanyPoint companyPoint);

    boolean remove(long id);

    String checkName(String name);
}
