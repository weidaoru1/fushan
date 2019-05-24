package com.fushan.entity;

import java.io.Serializable;
import java.util.Date;

public class PaymentRecord implements Serializable {

    private static final long serialVersionUID = -3638138575169686305L;
    private Integer id;
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
     * 实收金额
     */
    private Double amount;
    /**
     * 收款方式 1：现金 2：支付宝 3：微信 4：转账
     */
    private Integer type;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 收款日期
     */
    private Date paymentTime;
    /**
     * 详情描述
     */
    private String detailsDes;
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

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getDetailsDes() {
        return detailsDes;
    }

    public void setDetailsDes(String detailsDes) {
        this.detailsDes = detailsDes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}