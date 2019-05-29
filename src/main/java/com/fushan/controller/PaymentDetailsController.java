package com.fushan.controller;
import com.fushan.common.util.DataDealUtils;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.UserConstants;
import com.fushan.entity.PaydetailsRecord;
import com.fushan.entity.PaymentDetails;
import com.fushan.entity.PaymentInfo;
import com.fushan.entity.RoleInfo;
import com.fushan.service.cost.PaydetailsRecordService;
import com.fushan.service.cost.PaymentDetailsService;
import com.fushan.service.cost.PaymentInfoService;
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
    @Resource
    PaymentInfoService paymentInfoService;
    @Resource
    PaydetailsRecordService paydetailsRecordService;
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
        PaymentInfo paymentInfo = paymentInfoService.selectByPrimaryKey(paymentDetails.getPaymentId());
        double aomunt = paymentDetailsService.sumAmountByPyamentId(paymentDetails.getPaymentId());
        if (paymentInfo != null){
            if (aomunt >= paymentInfo.getAmounts()){
                //已付清
                paymentInfo.setStatus(1);
            }
            paymentInfo.setAmount(aomunt);
            paymentInfoService.updateByPrimaryKey(paymentInfo);
        }
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("details/edit")
    public String edit(Model model, HttpServletRequest request)throws Exception{
        String id = request.getParameter("id");
        PaymentDetails paymentDetails = new PaymentDetails();
        if (StringUtils.isNotBlank(id)){
            paymentDetails = paymentDetailsService.selectByPrimaryKey(Integer.parseInt(id));
        }
        model.addAttribute("paymentDetails",paymentDetails);
        return "views/cost/detailsEdit";
    }
    @RequestMapping("details/editSave")
    public @ResponseBody String editSave(HttpServletRequest request, PaymentDetails paymentDetails)throws Exception{
        JSONObject jsonObject = new JSONObject();
        PaymentDetails oldData = paymentDetailsService.selectByPrimaryKey(paymentDetails.getId());
        paymentDetailsService.updateByPrimaryKey(paymentDetails);
        double aomunt = paymentDetailsService.sumAmountByPyamentId(paymentDetails.getPaymentId());
        PaymentInfo paymentInfo = paymentInfoService.selectByPrimaryKey(paymentDetails.getPaymentId());
        if (paymentInfo != null){
            if (aomunt >= paymentInfo.getAmounts()){
                //已付清
                paymentInfo.setStatus(1);
            }
            paymentInfo.setAmount(aomunt);
            paymentInfoService.updateByPrimaryKey(paymentInfo);
        }
        PaydetailsRecord paydetailsRecord = DataDealUtils.getPaydetailsRecord(paymentDetails,oldData);
        if (paydetailsRecord != null){
            paydetailsRecord.setUserName((String)request.getSession().getAttribute(UserConstants.LOGIN_USER_NAME.name()));
            paydetailsRecord.setPaydetailsId(paymentDetails.getId());
            paydetailsRecord.setCreateTime(new Date());
            paydetailsRecordService.insertSelective(paydetailsRecord);
        }
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
    @GetMapping("payment/detailsRecordList")
    public String detailsRecordList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        String paydetailsId = request.getParameter("paydetailsId");
        PaymentDetails paymentDetails = new PaymentDetails();
        Map<String,Object> map = new HashMap<>();
        map.put("paydetailsId",paydetailsId);
        if (StringUtils.isNotBlank(paydetailsId)){
            paymentDetails = paymentDetailsService.selectByPrimaryKey(Integer.parseInt(paydetailsId));
        }
        model.addAttribute("page",paydetailsRecordService.pagedQueryByCondition(dataGrid,map));
        model.addAttribute("paymentId",paymentDetails.getPaymentId());
        return "views/cost/paydetailsRecordList";
    }
}
