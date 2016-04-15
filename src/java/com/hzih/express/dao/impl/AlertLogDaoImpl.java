package com.hzih.express.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.AlertLogDao;
import com.hzih.express.domain.AlertLog;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class AlertLogDaoImpl extends MyDaoSupport implements AlertLogDao {

	@Override
	public void setEntityClass() {
		this.entityClass = AlertLog.class;
	}


	@Override
	public PageResult listByPage(String idCard, int pageIndex, int limit) {
		StringBuilder sb = new StringBuilder(" from AlertLog s where 1=1 ");
		List paramsList = new ArrayList();
		if (idCard != null && idCard.length() > 0) {
			sb.append(" and s.id_card like ?");
			paramsList.add("%" + idCard + "%");
		}
		sb.append(" and s.read_flag = 0");

		sb.append(" order by id desc");
		String countHql = "select count(*) " + sb.toString();

		PageResult ps = this.findByPage(sb.toString(), countHql, paramsList.toArray(),pageIndex, limit);
		return ps;
	}

	@Override
	public void save(AlertLog identityQueryLog) {
		getHibernateTemplate().save(identityQueryLog);
	}

	@Override
	public boolean read(String id) {
		boolean flag =false;
		String s= String.format("update AlertLog u set u.read_flag= %d where u.id=%s", 1, id);
		Session session = super.getSession();
		try{
			session.beginTransaction();
			Query query=session.createQuery(s);
			query.executeUpdate();
			session.getTransaction().commit();
			flag=true;

		} catch (Exception e){

		} finally {
			session.close();
		}
		return flag;
	}
}
