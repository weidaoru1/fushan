package com.fushan.entity;

import java.io.Serializable;
import java.util.Date;

public class SpendInfo implements Serializable {

    private static final long serialVersionUID = -3638138575169686305L;
    private Integer id;
    /**
     * 支出人
     */
    private String spendName;
    /**
     * 支出事项
     */
    private String spendMatters;
    /**
     * 支出金额
     */
    private Double amount;
    /**
     * 支出方式 1：现金 2：支付宝 3：微信 4：转账
     */
    private Integer type;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 支出日期
     */
    private Date spendTime;
    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Date getSpendTime() {
        return spendTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setSpendTime(Date spendTime) {
        this.spendTime = spendTime;
    }
}