package com.hzih.express.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.Region;

public interface RegionDao extends BaseDao {

	PageResult findProvince(int start, int limit);

	Region findByCode(String code);

	Region findById(long id);

	PageResult findChild(int start, int limit, String parentCode);

}
