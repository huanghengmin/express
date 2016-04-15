package com.hzih.express.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 16-1-25.
 */
public class User implements Serializable{
    private Long id; //编号
    private String idCard;//快递员身份证
    private String express_name;//快递员姓名
    private String express_number;//快递员编号
    private String password;//登陆密码
    private String phone;//注册手机
    private Date register_time;//注册时间
    private Date modify_time;//更新时间
    private int status;
    private Set<ExpressLog> expressLogs;
    private CompanyPoint companyPoint;

    public CompanyPoint getCompanyPoint() {
        return companyPoint;
    }

    public void setCompanyPoint(CompanyPoint companyPoint) {
        this.companyPoint = companyPoint;
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String phone) {
        this.phone = phone;
    }

    public User(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }

    public User() {
    }

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }

    /*public String getExpress_company() {
        return express_company;
    }

    public void setExpress_company(String express_company) {
        this.express_company = express_company;
    }*/

    public String getExpress_number() {
        return express_number;
    }

    public void setExpress_number(String express_number) {
        this.express_number = express_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<ExpressLog> getExpressLogs() {
        return expressLogs;
    }

    public void setExpressLogs(Set<ExpressLog> expressLogs) {
        this.expressLogs = expressLogs;
    }
}
