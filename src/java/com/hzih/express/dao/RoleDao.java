package com.hzih.express.dao;

import cn.collin.commons.dao.BaseDao;
import com.hzih.express.domain.Role;

public interface RoleDao extends BaseDao {

    public Role findByName(String name) throws Exception;
}
