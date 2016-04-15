package com.hzih.express.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.AlertLog;


public interface AlertLogDao extends BaseDao {

	PageResult listByPage(String idCard, int pageIndex, int limit);

	public void save(AlertLog identityQueryLog);

	boolean read(String id);
}
