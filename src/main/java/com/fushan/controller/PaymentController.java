package com.fushan.controller;

import com.fushan.common.util.DataGrid;
import com.fushan.common.util.DateUtils;
import com.fushan.entity.PaymentInfo;
import com.fushan.service.cost.PaymentInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
public class PaymentController {
    @Resource
    PaymentInfoService paymentInfoService;
    @RequestMapping("cost/PaymentList")
    public String PaymentList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        model.addAttribute("page",paymentInfoService.pagedQuery(dataGrid));
        return "views/cost/paymentList";
    }
    @RequestMapping("payment/save")
    public @ResponseBody String save(HttpServletRequest request)throws Exception{
        PaymentInfo paymentInfo = new PaymentInfo();
        JSONObject jsonObject = new JSONObject();
        String customerName = request.getParameter("customerName");
        String contact = request.getParameter("contact");
        String payee = request.getParameter("payee");
        String amount = request.getParameter("amount");
        String type = request.getParameter("type");
        String paymentTime = request.getParameter("paymentTime");
        String detailsDes = request.getParameter("detailsDes");
        String remark = request.getParameter("remark");
        String id = request.getParameter("id");
        paymentInfo.setCustomerName(customerName);
        paymentInfo.setContact(contact);
        paymentInfo.setPayee(payee);
        if (StringUtils.isNotBlank(amount)){
            paymentInfo.setAmount(Double.valueOf(amount));
        }
        if (StringUtils.isNotBlank(type)){
            paymentInfo.setType(Integer.parseInt(type));
        }
        if (StringUtils.isNotBlank(paymentTime)){
            paymentInfo.setPaymentTime(DateUtils.StrTtoDate(paymentTime));
        }
        paymentInfo.setDetailsDes(detailsDes);
        paymentInfo.setRemark(remark);
        paymentInfoService.insertSelective(paymentInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
}
