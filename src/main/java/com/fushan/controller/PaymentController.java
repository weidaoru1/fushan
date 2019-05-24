package com.fushan.controller;
import com.fushan.common.util.DataGrid;
import com.fushan.entity.PaymentInfo;
import com.fushan.service.cost.PaymentInfoService;
import org.apache.commons.lang.StringUtils;
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
    @RequestMapping("cost/paymentAdd")
    public String payment(Model model, HttpServletRequest request)throws Exception{
        return "views/cost/paymentAdd";
    }
    @RequestMapping("cost/paymentEdit")
    public String paymentEdit(Model model, HttpServletRequest request)throws Exception{
        String id = request.getParameter("id");
        PaymentInfo paymentInfo = null;
        if (StringUtils.isNotBlank(id)){
            paymentInfo = paymentInfoService.selectByPrimaryKey(Integer.parseInt(id));
        }
        if(paymentInfo != null){
            model.addAttribute("paymentInfo",paymentInfo);
        }
        return "views/cost/paymentEdit";
    }
    @RequestMapping("payment/save")
    public @ResponseBody String save(HttpServletRequest request,PaymentInfo paymentInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        paymentInfoService.insertSelective(paymentInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("payment/edit")
    public @ResponseBody String edit(HttpServletRequest request,PaymentInfo paymentInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        paymentInfoService.updateByPrimaryKey(paymentInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
}
