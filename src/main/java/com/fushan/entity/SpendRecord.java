package com.fushan.entity;

import java.io.Serializable;
import java.util.Date;

public class SpendRecord implements Serializable {

    private static final long serialVersionUID = 5976932874689586141L;
    private Integer id;

    private String userName;

    private Integer spendId;

    private String spendName;

    private String spendMatters;

    private Double amount;

    private Integer type;

    private Date spendTime;

    private Date createTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSpendId() {
        return spendId;
    }

    public void setSpendId(Integer spendId) {
        this.spendId = spendId;
    }

    public String getSpendName() {
        return spendName;
    }

    public void setSpendName(String spendName) {
        this.spendName = spendName;
    }

    public String getSpendMatters() {
        return spendMatters;
    }

    public void setSpendMatters(String spendMatters) {
        this.spendMatters = spendMatters;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Date spendTime) {
        this.spendTime = spendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}