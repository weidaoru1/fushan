package com.fushan.entity;

import java.io.Serializable;
import java.util.Date;

public class PaymentRecord implements Serializable {

    private static final long serialVersionUID = -3638138575169686305L;
    private Integer id;
    /**
     * 修改人
     */
    private String userName;
    /**
     * 修改数据id
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
     * 收款方式 1：现金 2：支付宝 3：微信 4：转账
     */
    private Integer type;
    /**
     * 修改时间
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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