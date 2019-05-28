package com.fushan.controller;

import com.fushan.common.util.DataDealUtils;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.UserConstants;
import com.fushan.entity.PaymentDetails;
import com.fushan.entity.PaymentInfo;
import com.fushan.entity.PaymentRecord;
import com.fushan.entity.RoleInfo;
import com.fushan.service.cost.PaymentDetailsService;
import com.fushan.service.cost.PaymentInfoService;
import com.fushan.service.cost.PaymentRecordService;
import com.fushan.service.role.RoleInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PaymentDetailsController {
    @Resource
    RoleInfoService roleInfoService;
    @Resource
    PaymentDetailsService paymentDetailsService;
    @RequestMapping("payment/detailsAdd")
    public String detailsAdd(Model model, HttpServletRequest request)throws Exception{
        String paymentId = request.getParameter("paymentId");
        model.addAttribute("paymentId",paymentId);
        return "views/cost/detailsAdd";
    }
    @RequestMapping("details/save")
    public @ResponseBody String save(HttpServletRequest request, PaymentDetails paymentDetails)throws Exception{
        JSONObject jsonObject = new JSONObject();
        paymentDetailsService.insertSelective(paymentDetails);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }

    @GetMapping("payment/detailsList")
    public String detailsList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        String paymentId = request.getParameter("paymentId");
        Map<String,Object> map = new HashMap<>();
        map.put("paymentId",paymentId);
        model.addAttribute("paymentId",paymentId);
        model.addAttribute("page",paymentDetailsService.pagedQueryByCondition(dataGrid,map));        ;
        List<RoleInfo> roleInfoList = roleInfoService.queryByUserId((Integer)request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (roleInfoList != null && roleInfoList.size() > 0){
            model.addAttribute("role",roleInfoList.get(0));
        }
        return "views/cost/paymentDetailsList";
    }
}