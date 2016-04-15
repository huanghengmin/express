package com.hzih.express.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Administrator on 16-2-18.
 */
public class Company implements Serializable {
    private long id;
    private String code;
    private String name;

    public Company() {
    }

    public Company(long id) {
        this.id = id;
    }

    public Company(String code) {
        this.code = code;
    }

    private Set<CompanyPoint> companyPoints;

    public Set<CompanyPoint> getCompanyPoints() {
        return companyPoints;
    }

    public void setCompanyPoints(Set<CompanyPoint> companyPoints) {
        this.companyPoints = companyPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
