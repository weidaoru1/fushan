package com.fushan.controller;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.UserConstants;
import com.fushan.entity.PaymentInfo;
import com.fushan.entity.RoleInfo;
import com.fushan.entity.SpendInfo;
import com.fushan.service.cost.SpendInfoService;
import com.fushan.service.role.RoleInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SpendController {
    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    SpendInfoService spendInfoService;
    @RequestMapping("cost/spendList")
    public String spendList(Model model, HttpServletRequest request, DataGrid dataGrid) throws Exception {
        List<RoleInfo> roleInfoList = roleInfoService.queryByUserId((Integer) request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (roleInfoList != null && roleInfoList.size() > 0) {
            model.addAttribute("role", roleInfoList.get(0));
        }
        model.addAttribute("page", spendInfoService.pagedQuery(dataGrid));
        return "views/cost/spendList";
    }
    @RequestMapping("cost/spendAdd")
    public String spendAdd(Model model, HttpServletRequest request, DataGrid dataGrid) throws Exception {
        return "views/cost/spendAdd";
    }
    @RequestMapping("spend/save")
    public @ResponseBody String save(HttpServletRequest request, SpendInfo spendInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        spendInfoService.insertSelective(spendInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("spend/queryById")
    public String queryById(Model model, HttpServletRequest request)throws Exception{
        String id = request.getParameter("id");
        SpendInfo spendInfo = null;
        if (StringUtils.isNotBlank(id)){
            spendInfo = spendInfoService.selectByPrimaryKey(Integer.parseInt(id));
        }
        if (spendInfo != null){
            model.addAttribute("spendInfo",spendInfo);
        }
        return "views/cost/spendEdit";
    }
    @RequestMapping("spend/edit")
    public @ResponseBody String edit(HttpServletRequest request,SpendInfo spendInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        spendInfoService.updateByPrimaryKey(spendInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
}
