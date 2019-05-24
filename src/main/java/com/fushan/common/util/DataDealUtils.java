package com.fushan.common.util;

import com.fushan.entity.PaymentInfo;
import com.fushan.entity.PaymentRecord;

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
            if (!String.valueOf(newData.getAmount()).equals(String.valueOf(oldData.getAmount()))){
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
            if (paymentRecord != null){
                paymentRecord.setCustomerName(newData.getCustomerName());
                paymentRecord.setContact(newData.getContact());
                paymentRecord.setPayee(newData.getPayee());
                paymentRecord.setAmount(newData.getAmount());
                paymentRecord.setType(newData.getType());
                paymentRecord.setPaymentTime(newData.getPaymentTime());
                paymentRecord.setDetailsDes(newData.getDetailsDes());
                paymentRecord.setRemark(newData.getRemark());
            }
        }
        return paymentRecord;
    }
}
