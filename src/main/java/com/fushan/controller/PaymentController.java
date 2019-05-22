package com.fushan.controller;

import com.fushan.common.util.DataGrid;
import com.fushan.entity.PaymentInfo;
import com.fushan.service.cost.PaymentInfoService;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public @ResponseBody String save(HttpServletRequest request, PaymentInfo paymentInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
}
