package com.fushan.entity;

import java.io.Serializable;
import java.util.Date;

public class PaymentDetails implements Serializable {

    private static final long serialVersionUID = -3638138575169685805L;
    private Integer id;
    /**
     * 收款数据id
     */
    private Integer paymentId;
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
     * 收款日期
     */
    private Date paymentTime;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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