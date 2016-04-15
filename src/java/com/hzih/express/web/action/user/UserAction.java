package com.hzih.express.web.action.user;

import cn.collin.commons.domain.PageResult;
import com.hzih.express.dao.CompanyPointDao;
import com.hzih.express.dao.UserDao;
import com.hzih.express.dao.UserOperLogDao;
import com.hzih.express.domain.Account;
import com.hzih.express.domain.CompanyPoint;
import com.hzih.express.domain.User;
import com.hzih.express.service.LogService;
import com.hzih.express.utils.Md5Key;
import com.hzih.express.utils.ResultObj;
import com.hzih.express.web.SessionUtils;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(UserAction.class);
	private LogService logService;
	private UserDao userDao;
	private CompanyPointDao companyPointDao;
	private User user;
	private UserOperLogDao userOperLogDao;
	private int start;
	private int limit;

	public CompanyPointDao getCompanyPointDao() {
		return companyPointDao;
	}

	public void setCompanyPointDao(CompanyPointDao companyPointDao) {
		this.companyPointDao = companyPointDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public UserOperLogDao getUserOperLogDao() {
		return userOperLogDao;
	}

	public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
		this.userOperLogDao = userOperLogDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*检查*/
	public String check() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		boolean flag = userDao.check(phone);
		if(flag == true){
			String msg = "用户检测手机号已注册";
			out.print("{success:false,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户检测手机号已注册", phone);
		}else {
			String msg = "用户检测手机号未注册";
			out.print("{success:true,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户检测手机号未注册", phone);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	public String checkIdCard() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String idCard = request.getParameter("idCard");
		boolean flag = userDao.checkIdCard(idCard);
		if(flag == true){
			String msg = "用户身份证号码已被使用";
			out.print("{success:false,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户身份证号码已被使用", idCard);
		}else {
			String msg = "用户身份证号码已被使用";
			out.print("{success:true,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户身份证号码已被使用", idCard);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	/*注册*/
	public String register() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		boolean flag = userDao.register(phone,password);
		if(flag == true){
			out.print("{success:true}");
			userOperLogDao.newLog("用户注册成功", phone);
		}else {
			out.print("{success:false}");
			userOperLogDao.newLog("用户注册失败", phone);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	/*修改密码*/
	public String modifyPassword() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String oldPwd = request.getParameter("oldPwd");
		String password = request.getParameter("password");
		if(oldPwd!=null) {
			ResultObj obj = userDao.modifyPassword(phone,oldPwd, password);
			if (obj.isFlag()) {
				out.print("{success:true,msg:\""+obj.getMsg()+"\"}");
				userOperLogDao.newLog(obj.getMsg(), phone);
			} else {
				out.print("{success:false,msg:\""+obj.getMsg()+"\"}");
				userOperLogDao.newLog(obj.getMsg(), phone);
			}
		}else {
			boolean flag = userDao.modifyPassword(phone, password);
			if (flag == true) {
				out.print("{success:true}");
				userOperLogDao.newLog("用户修改密码成功", phone);
			} else {
				out.print("{success:false}");
				userOperLogDao.newLog("用户修改密码失败", phone);
			}
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	/*修改密码*/
	public String resetPsw() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String json = null;
		String msg = null;
		if(password!=null) {
			password = Md5Key.changeMd5Psd(password);
			boolean flag = userDao.modifyPassword(phone, password);
			if (flag == true) {
				msg = "用户修改密码成功";
				json = "{success:true,msg:'"+msg+"'}";
				userOperLogDao.newLog(msg, phone);
			} else {
				msg = "用户修改密码失败";
				json = "{success:false,msg:'"+msg+"'}";
				userOperLogDao.newLog(msg, phone);
			}
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	/*登录*/
	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		User user = userDao.login(phone, password);
		if(user != null){
			if(user.getStatus()==1){
				out.print("{success:true}");
				userOperLogDao.newLog("用户登陆成功", phone);
			}else {
				out.print("{success:false}");
				userOperLogDao.newLog("用户已被禁用", phone);
			}
		} else {
			out.print("{success:false}");
			userOperLogDao.newLog("用户登陆失败", phone);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();

		return null;
	}

	public String enable()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String json = null;
		String msg = null;
		String id = request.getParameter("id");
		boolean flag = userDao.setStatus(Long.parseLong(id), 1);
		if(flag){
			msg = "启用用户成功";
			json = "{success:true,msg:'"+msg+"'}";
		}else {
			msg = "启用用户失败";
			json = "{success:false,msg:'"+msg+"'}";
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String disable()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String json = null;
		String msg = null;
		String id = request.getParameter("id");
		boolean flag = userDao.setStatus(Long.parseLong(id), 0);
		if(flag){
			msg = "禁用用户成功";
			json = "{success:true,msg:'"+msg+"'}";
		}else {
			msg = "禁用用户失败";
			json = "{success:false,msg:'"+msg+"'}";
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String findUser()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		User user = userDao.find(phone);
		if(user != null){
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("success:"+true).append(",");
			builder.append("idCard:"+"\""+user.getIdCard()+"\"").append(",");
			builder.append("express_name:" + "\"" + user.getExpress_name() + "\"").append(",");
			builder.append("express_company:"+"\""+user.getCompanyPoint().getCompany().getName()+"\"").append(",");
			builder.append("express_number:"+"\""+user.getExpress_number()+"\"").append(",");
			builder.append("phone:"+"\""+user.getPhone()+"\"").append(",");
			builder.append("register_time:"+"\""+user.getRegister_time()+"\"").append(",");
			builder.append("modify_time:"+"\""+user.getModify_time() + "\"").append(",");
			builder.append("status:"+"\""+user.getStatus() + "\"");
			builder.append("}");
			out.print(builder.toString());
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("success:"+false).append(",");
			builder.append("}");
			out.print(builder.toString());
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();

		return null;
	}

	public String modifyUser()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String idCard = request.getParameter("idCard");
		String express_name = request.getParameter("express_name");
		String express_number = request.getParameter("express_number");
//		String id = request.getParameter("id");
		User user = userDao.find(phone);
		if(user != null){
			user.setIdCard(idCard);
			user.setExpress_name(express_name);
			user.setExpress_number(express_number);
//			CompanyPoint companyPoint = companyPointDao.findById(Long.parseLong(id));
//			user.setCompanyPoint(companyPoint);
			user.setModify_time(new Date());
			boolean modify = userDao.modify(user);
			if(modify){
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				builder.append("success:"+true);
				builder.append("}");
				out.print(builder.toString());
			}else {
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				builder.append("success:"+false);
				builder.append("}");
				out.print(builder.toString());
			}
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("success:"+false);
			builder.append("}");
			out.print(builder.toString());
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();

		return null;
	}

	public String find()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String json =  "{'success':true,'total':0,'rows':[]}";
		try {
			String idCard = request.getParameter("idCard");
			String express_name = request.getParameter("express_name");
			String express_number = request.getParameter("express_number");
			String company_code = request.getParameter("company_code");
			String company_point_id = request.getParameter("company_point_id");
			String phone = request.getParameter("phone");

			PageResult pageResult = userDao.listByParams(start / limit + 1, limit, idCard, express_name, express_number,company_code,company_point_id, phone);

			List<User> userList = pageResult.getResults();
			int total = pageResult.getAllResultsAmount();
			json = "{success:true,total:"+ total + ",rows:[";
			for (User u : userList) {
				json +="{id:'"+u.getId()+"'" +
						",idCard:'"+u.getIdCard()+"'" +
						",express_name:'"+u.getExpress_name()+"'" +
						",express_number:'"+u.getExpress_number()+"'" +
						",express_company:'"+u.getCompanyPoint().getCompany().getName() +"'" +
						",express_company_point_name:'"+u.getCompanyPoint().getName() +"'" +
						",phone:'"+u.getPhone()+"'" +
						",register_time:'"+u.getRegister_time() +"'" +
						",modify_time:'"+u.getModify_time() +"'" +
						",status:'"+ u.getStatus() +"'" ;
				json+= "},";
			}
			json += "]}";

		} catch (Exception e) {
			logger.error("用户信息查询", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "用户信息","用户信息查询失败 ");
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String findByAccount()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String json =  "{'success':true,'total':0,'rows':[]}";
		try {
			String idCard = request.getParameter("idCard");
			String express_name = request.getParameter("express_name");
			String express_number = request.getParameter("express_number");
			String phone = request.getParameter("phone");
			Account account = SessionUtils.getAccount(request);

			PageResult pageResult = userDao.listByParams(start / limit + 1, limit, idCard, express_name, express_number,account.getCompanyPoint().getId(), phone);

			List<User> userList = pageResult.getResults();
			int total = pageResult.getAllResultsAmount();
			json = "{success:true,total:"+ total + ",rows:[";
			for (User u : userList) {
				json +="{id:'"+u.getId()+"'" +
						",idCard:'"+u.getIdCard()+"'" +
						",express_name:'"+u.getExpress_name()+"'" +
						",express_number:'"+u.getExpress_number()+"'" +
						",express_company:'"+u.getCompanyPoint().getCompany().getName() +"'" +
						",express_company_point_name:'"+u.getCompanyPoint().getName() +"'" +
						",phone:'"+u.getPhone()+"'" +
						",register_time:'"+u.getRegister_time() +"'" +
						",modify_time:'"+u.getModify_time() +"'" +
						",status:'"+ u.getStatus() +"'" ;
				json+= "},";
			}
			json += "]}";

		} catch (Exception e) {
			logger.error("用户信息查询", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "用户信息","用户信息查询失败 ");
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String findByPoint()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String json =  "{'success':true,'total':0,'rows':[]}";
		try {
			Account account = SessionUtils.getAccount(request);
			CompanyPoint companyPoint = account.getCompanyPoint();
			String idCard = request.getParameter("idCard");
			String express_name = request.getParameter("express_name");
			String express_number = request.getParameter("express_number");
			String phone = request.getParameter("phone");

			PageResult pageResult = userDao.listByParams(start / limit + 1, limit, idCard, express_name, express_number, companyPoint.getId(), phone);

			List<User> userList = pageResult.getResults();
			int total = pageResult.getAllResultsAmount();
			json = "{success:true,total:"+ total + ",rows:[";
			for (User u : userList) {
				json +="{id:'"+u.getId()+"'" +
						",idCard:'"+u.getIdCard()+"'" +
						",express_name:'"+u.getExpress_name()+"'" +
						",express_number:'"+u.getExpress_number()+"'" +
						",express_company:'"+u.getCompanyPoint().getCompany().getName() +"'" +
						",express_company_point_name:'"+u.getCompanyPoint().getName() +"'" +
						",phone:'"+u.getPhone()+"'" +
						",register_time:'"+u.getRegister_time() +"'" +
						",modify_time:'"+u.getModify_time() +"'" +
						",status:'"+ u.getStatus() +"'" ;
				json+= "},";
			}
			json += "]}";

		} catch (Exception e) {
			logger.error("用户信息查询", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "用户信息","用户信息查询失败 ");
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String remove() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result = actionBase.actionBegin(request);
		String json = "{success:false,msg:'删除失败'}";
		String msg = null;
		String id = request.getParameter("id");
		try {
			if(id!=null) {
				userDao.remove(new User(Long.parseLong(id)));
				msg = "删除用户信息成功"+id;
				json = "{success:true,msg:'删除成功'}";
				logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
				logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户信息", msg);
			}
		}catch (Exception e){
			msg = "删除用户信息失败"+id;
			logger.info("管理员" + SessionUtils.getAccount(request).getUserName() + ",操作时间:" + new Date() + ",操作信息:" + msg);
			logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户信息", msg);
		}
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String insert() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String msg = null;
		//进行md5加密
		String md5_pwd = Md5Key.changeMd5Psd(user.getPassword());
		CompanyPoint companyPoint = user.getCompanyPoint();
		CompanyPoint companyP  = companyPointDao.findById(companyPoint.getId());
		user.setStatus(1);
		user.setCompanyPoint(companyP);
		user.setPassword(md5_pwd);
		user.setRegister_time(new Date());
		user.setModify_time(new Date());
		try {
			userDao.create(user);
			logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户管理", "用户新增账户" + user.getExpress_name() + "信息成功");
			msg =  "<font color=\"green\">注册成功,点击[确定]返回列表!</font>";
		}catch (Exception e){
			logger.error("用户管理", e);
			logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "用户管理","用户新增账户"+user.getExpress_name()+"信息失败");
			msg = "<font color=\"red\">注册失败</font>";
		}
		String json = "{success:true,msg:'"+msg+"'}";
		actionBase.actionEnd(response, json, result);
		return null;
	}

	public String insertByAccount() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase actionBase = new ActionBase();
		String result =	actionBase.actionBegin(request);
		String msg = null;
		//进行md5加密
		Account account = SessionUtils.getAccount(request);
		String md5_pwd = Md5Key.changeMd5Psd(user.getPassword());
		user.setCompanyPoint(account.getCompanyPoint());
		user.setPassword(md5_pwd);
		user.setStatus(1);
		user.setRegister_time(new Date());
		user.setModify_time(new Date());
		try {
			userDao.create(user);
			logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "用户管理", "用户新增账户" + user.getExpress_name() + "信息成功");
			msg =  "<font color=\"green\">注册成功,点击[确定]返回列表!</font>";
		}catch (Exception e){
			logger.error("用户管理", e);
			logService.newLog("ERROE", SessionUtils.getAccount(request).getUserName(), "用户管理","用户新增账户"+user.getExpress_name()+"信息失败");
			msg = "<font color=\"red\">注册失败</font>";
		}
		String json = "{success:true,msg:'"+msg+"'}";
		actionBase.actionEnd(response, json, result);
		return null;
	}
}
