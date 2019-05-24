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
                paymentRecord.setCustomerName(newData.getCustomerName());
            }
            if (!newData.getContact().equals(oldData.getContact())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setContact(newData.getContact());
            }
            if (!newData.getPayee().equals(oldData.getPayee())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setPayee(newData.getPayee());
            }
            if (!String.valueOf(newData.getAmount()).equals(String.valueOf(oldData.getAmount()))){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setAmount(newData.getAmount());
            }
            if (newData.getType() != oldData.getType()){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setType(newData.getType());
            }
            if (!DateUtils.dateToStr(newData.getPaymentTime()).equals(DateUtils.dateToStr(oldData.getPaymentTime()))){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setPaymentTime(newData.getPaymentTime());
            }
            if (!newData.getDetailsDes().equals(oldData.getDetailsDes())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setDetailsDes(newData.getDetailsDes());
            }
            if (!newData.getRemark().equals(oldData.getRemark())){
                if (paymentRecord == null){
                    paymentRecord = new PaymentRecord();
                }
                paymentRecord.setRemark(newData.getRemark());
            }
        }
        return paymentRecord;
    }
}
