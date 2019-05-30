package com.fushan.common.util;

import com.fushan.entity.*;

public class DataDealUtils {
    /**
     *
     * @param newData 新的数据对象
     * @param oldData 旧的数据对象
     * @return
     */
    public static PaymentRecord getPaymentRecord(PaymentInfo newData, PaymentInfo oldData){
        PaymentRecord paymentRecord = null;
        if(newData != null && oldData != null){
            if (!newData.getCustomerName().equals(oldData.getCustomerName())){
                paymentRecord = new PaymentRecord();
            }
            if (!newData.getContact().equals(oldData.getContact())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (!newData.getPayee().equals(oldData.getPayee())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (newData.getType() != oldData.getType()){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (!DateUtils.dateToStr(newData.getPaymentTime()).equals(DateUtils.dateToStr(oldData.getPaymentTime()))){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (!newData.getDetailsDes().equals(oldData.getDetailsDes())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (!newData.getRemark().equals(oldData.getRemark())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (newData.getStatus() != oldData.getStatus()){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (!String.valueOf(newData.getAmounts()).equals(String.valueOf(oldData.getAmounts()))){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
            }
            if (paymentRecord != null){
                paymentRecord.setCustomerName(newData.getCustomerName());
                paymentRecord.setContact(newData.getContact());
                paymentRecord.setPayee(newData.getPayee());
                paymentRecord.setType(newData.getType());
                paymentRecord.setPaymentTime(newData.getPaymentTime());
                paymentRecord.setDetailsDes(newData.getDetailsDes());
                paymentRecord.setRemark(newData.getRemark());
            }
        }
        return paymentRecord;
    }
    public static SpendRecord getSpendRecord(SpendInfo newData, SpendInfo oldData){
        SpendRecord spendRecord = null;
        if(newData != null && oldData != null){
            if (!newData.getSpendName().equals(oldData.getSpendName())){
                spendRecord = new SpendRecord();
            }
            if (!newData.getSpendMatters().equals(oldData.getSpendMatters())){
                if (spendRecord == null){
                    spendRecord = new SpendRecord();
                }
            }
            if (!String.valueOf(newData.getAmount()).equals(String.valueOf(oldData.getAmount()))){
                if (spendRecord == null){
                    spendRecord = new SpendRecord();
                }
            }
            if (newData.getType() != oldData.getType()){
                if (spendRecord == null){
                    spendRecord = new SpendRecord();
                }
            }
            if (!DateUtils.dateToStr(newData.getSpendTime()).equals(DateUtils.dateToStr(oldData.getSpendTime()))){
                if (spendRecord == null){
                    spendRecord = new SpendRecord();
                }
            }
            if (!newData.getRemark().equals(oldData.getRemark())){
                if (spendRecord == null){
                    spendRecord = new SpendRecord();
                }
            }
            if (spendRecord != null){
                spendRecord.setSpendName(newData.getSpendName());
                spendRecord.setSpendMatters(newData.getSpendMatters());
                spendRecord.setAmount(newData.getAmount());
                spendRecord.setType(newData.getType());
                spendRecord.setSpendTime(newData.getSpendTime());
                spendRecord.setRemark(newData.getRemark());
            }
        }
        return spendRecord;
    }
    public static PaydetailsRecord getPaydetailsRecord(PaymentDetails newData,PaymentDetails oldData){
        PaydetailsRecord paydetailsRecord = null;
        if(newData != null && oldData != null){
            if (!newData.getCustomerName().equals(oldData.getCustomerName())){
                paydetailsRecord = new PaydetailsRecord();
            }
            if (!newData.getContact().equals(oldData.getContact())){
                if (paydetailsRecord == null){
                    paydetailsRecord = new PaydetailsRecord();
                }
            }
            if (!newData.getPayee().equals(oldData.getPayee())){
                if (paydetailsRecord == null){
                    paydetailsRecord = new PaydetailsRecord();
                }
            }
            if (!String.valueOf(newData.getAmount()).equals(String.valueOf(oldData.getAmount()))){
                if (paydetailsRecord == null){
                    paydetailsRecord = new PaydetailsRecord();
                }
            }
            if (!DateUtils.dateToStr(newData.getPaymentTime()).equals(DateUtils.dateToStr(oldData.getPaymentTime()))){
                if (paydetailsRecord == null){
                    paydetailsRecord = new PaydetailsRecord();
                }
            }
            if (!newData.getRemark().equals(oldData.getRemark())){
                if (paydetailsRecord == null){
                    paydetailsRecord = new PaydetailsRecord();
                }
            }
            if (paydetailsRecord != null){
                paydetailsRecord.setCustomerName(newData.getCustomerName());
                paydetailsRecord.setContact(newData.getContact());
                paydetailsRecord.setPayee(newData.getPayee());
                paydetailsRecord.setAmount(newData.getAmount());
                paydetailsRecord.setPaymentTime(newData.getPaymentTime());
                paydetailsRecord.setRemark(newData.getRemark());
            }
        }
        return paydetailsRecord;
    }
}
