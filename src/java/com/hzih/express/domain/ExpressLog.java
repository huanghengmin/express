package com.hzih.express.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by Administrator on 15-11-12.
 */
public class ExpressLog implements Serializable{
    private long id;  //记录id
    private String name;//寄件人姓名
    private String sex;//寄件人性别
    private String nation;//民族
    private String birthday;//生日
    private String address;//地址
    private String idCard;//身份证
    private String signDepart;//签发机构
    private String validTime;//有效日期
    private Blob bitmap;//身份证书图片
    private Blob unpack_img;
    private Blob sender_img;
    private Blob face_img;
    private String shapeCode;//快递编号
    private String DN;//身份证DN编码
    private String longitude;//寄件地理位置
    private String latitude;
    private Date sendTime;//寄件时间
    private User user;//快递员
    private String type;//标识身份证识别方式
    private String contact;
    private int status;
    private String code;
    private String xq;
    private Date compare_time;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Blob getUnpack_img() {
        return unpack_img;
    }

    public void setUnpack_img(Blob unpack_img) {
        this.unpack_img = unpack_img;
    }

    public Blob getSender_img() {
        return sender_img;
    }

    public void setSender_img(Blob sender_img) {
        this.sender_img = sender_img;
    }

    public Blob getFace_img() {
        return face_img;
    }

    public void setFace_img(Blob face_img) {
        this.face_img = face_img;
    }

    public ExpressLog(long l) {
        this.id = l;
    }

    public ExpressLog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSignDepart() {
        return signDepart;
    }

    public void setSignDepart(String signDepart) {
        this.signDepart = signDepart;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public Blob getBitmap() {
        return bitmap;
    }

    public void setBitmap(Blob bitmap) {
        this.bitmap = bitmap;
    }

    public String getShapeCode() {
        return shapeCode;
    }

    public void setShapeCode(String shapeCode) {
        this.shapeCode = shapeCode;
    }

    public void setDN(String DN) {
        this.DN = DN;
    }

    public String getDN() {
        return DN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public Date getCompare_time() {
        return compare_time;
    }

    public void setCompare_time(Date compare_time) {
        this.compare_time = compare_time;
    }
}
