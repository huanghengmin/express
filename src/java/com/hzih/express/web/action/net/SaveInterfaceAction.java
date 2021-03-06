package com.hzih.express.web.action.net;

import com.hzih.express.entity.NetInfo;
import com.hzih.express.utils.NetworkUtil;
import com.hzih.express.web.action.ActionBase;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 新增虚拟接口
 * @author 钱晓盼
 *
 */
public class SaveInterfaceAction extends ActionSupport{

	private static final long serialVersionUID = -1267078981037327633L;
	private Logger log = Logger.getLogger(SaveInterfaceAction.class);
	private String json;
	private NetInfo netInfo;
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}

	public NetInfo getNetInfo() {
		return netInfo;
	}

	public void setNetInfo(NetInfo netInfo) {
		this.netInfo = netInfo;
	}

	public String execute() throws Exception {
		log.info("新增虚拟接口开始!");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionBase base = new ActionBase();
		String result =	base.actionBegin(request);
        try{
            String msg = saveInterface(netInfo);
            this.json = "{'success':true,'msg':'"+msg+"'}";
            log.info("新增虚拟接口["+msg+"]!");
        } catch (Exception e){
            log.error("新增虚拟接口失败",e);
        }
		base.actionEnd(response, json ,result);
		return "success";
	}

	private String saveInterface(NetInfo netInfo) throws Exception {
        NetworkUtil networkUtil = new NetworkUtil();
        String msg = networkUtil.saveInterface(netInfo);
		return msg;
	}
}
