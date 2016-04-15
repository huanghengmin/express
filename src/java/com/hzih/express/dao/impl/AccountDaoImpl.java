package com.hzih.express.dao.impl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.AccountDao;
import com.hzih.express.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends MyDaoSupport implements AccountDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Account.class;
	}

    /**
     * 分页查询用户列表
     *
     * @param userName
     * @param status
     * @param pageIndex
     * @param limit
     * @return
     */
	public PageResult listByPage(String userName,int userType, String status, int pageIndex, int limit) {
		StringBuilder sb = new StringBuilder("from Account where 1=1 ");
		List paramsList = new ArrayList();
		if (userName != null && userName.length() > 0) {
			sb.append(" and userName like ?");
			paramsList.add("%" + userName + "%");
		}

			sb.append( " and userType = ?");
			paramsList.add( userType );

		if (status != null && status.length() > 0
				&& !status.equalsIgnoreCase("全部")) {
			sb.append( " and status=?");
			paramsList.add(status);
		}
		sb.append(" order by id desc");
		String countHql = "select count(*) " + sb.toString();

		PageResult ps = this.findByPage(sb.toString(), countHql, paramsList.toArray(),
				pageIndex, limit);
		return ps;
	}

    /**
     * 通过用户名、密码和状态查找用户
     * @param name
     * @param pwd
     * @return
     */
	public Account findByNameAndPwd(String name, String pwd) {
		String hql = new String("from Account where userName=? and password=? and status=?");
		List list = getHibernateTemplate().find(hql,
				new String[] { name, pwd, "有效" });
		if (list != null && list.size() > 0) {
			return (Account) list.get(0);
		} else {
			return null;
		}
	}

    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    public Account findByName(String userName) {
        String hql = new String("from Account where userName=?");
		List<Account> list = getHibernateTemplate().find(hql,new String[] { userName });
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
    }

	@Override
	public Account findById(long aId) {
		String hql = new String("from Account where id="+aId);
		List<Account> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
