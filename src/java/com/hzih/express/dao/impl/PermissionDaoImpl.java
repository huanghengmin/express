package com.hzih.express.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzih.express.dao.PermissionDao;
import com.hzih.express.domain.Permission;

public class PermissionDaoImpl extends MyDaoSupport implements PermissionDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Permission.class;
	}

}
