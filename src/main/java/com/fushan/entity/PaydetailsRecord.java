package com.fushan.entity;

import java.io.Serializable;
import java.util.Date;

public class PaydetailsRecord implements Serializable {
    private static final long serialVersionUID = -3638138575169686305L;
    private Integer id;
    /**
     * 修改人
     */
    private String userName;
    /**
     * 修改数据id
     */
    private Integer paydetailsId;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 收款人
     */
    private String payee;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 修改时间
     */
    private Date createTime;
    /**
     * 收款日期
     */
    private Date paymentTime;

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

    public Integer getPaydetailsId() {
        return paydetailsId;
    }

    public void setPaydetailsId(Integer paydetailsId) {
        this.paydetailsId = paydetailsId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}