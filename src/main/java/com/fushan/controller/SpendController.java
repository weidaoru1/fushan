package com.fushan.controller;
import com.fushan.common.util.DataDealUtils;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.UserConstants;
import com.fushan.entity.RoleInfo;
import com.fushan.entity.SpendInfo;
import com.fushan.entity.SpendRecord;
import com.fushan.service.cost.SpendInfoService;
import com.fushan.service.cost.SpendRecordService;
import com.fushan.service.role.RoleInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SpendController {
    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    SpendInfoService spendInfoService;
    @Autowired
    SpendRecordService spendRecordService;
    @RequestMapping("cost/spendList")
    public String spendList(Model model, HttpServletRequest request) throws Exception {
        List<RoleInfo> roleInfoList = roleInfoService.queryByUserId((Integer) request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (roleInfoList != null && roleInfoList.size() > 0) {
            model.addAttribute("role", roleInfoList.get(0));
        }
        return "views/cost/spendList";
    }
    @RequestMapping("cost/spendDataList")
    public @ResponseBody String spendDataList(HttpServletRequest request, DataGrid dataGrid)throws Exception{
        String type = request.getParameter("type");
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(type)){
            map.put("type",type);
        }
        JSONObject object = DataDealUtils.dataToJson(spendInfoService.pagedQueryByCondition(dataGrid,map));
        return object.toString();
    }
    @RequestMapping("spend/save")
    public @ResponseBody String save(HttpServletRequest request, SpendInfo spendInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        spendInfoService.insertSelective(spendInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @GetMapping("spend/spendRecordList")
    public @ResponseBody String querySpendRecord(HttpServletRequest request,DataGrid dataGrid)throws Exception{
        String spendId = request.getParameter("spendId");
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(spendId)){
            map.put("spendId",spendId);
        }
        JSONObject object = DataDealUtils.dataToJson(spendRecordService.pagedQueryByCondition(dataGrid,map));
        return object.toString();
    }

    @RequestMapping("spend/edit")
    public @ResponseBody String edit(HttpServletRequest request,SpendInfo spendInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        SpendInfo oldData = spendInfoService.selectByPrimaryKey(spendInfo.getId());
        SpendRecord spendRecord = DataDealUtils.getSpendRecord(spendInfo,oldData);
        if (spendRecord != null){
            spendRecord.setSpendId(spendInfo.getId());
            spendRecord.setUserName((String)request.getSession().getAttribute(UserConstants.LOGIN_USER_NAME.name()));
            spendRecord.setCreateTime(new Date());
            spendRecordService.insertSelective(spendRecord);
        }
        spendInfoService.updateByPrimaryKey(spendInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("spend/deleteById")
    public  @ResponseBody String deleteById(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("ids");
        if (StringUtils.isNotBlank(id)) {
            String[] ids = id.split(";");
            for(String s : ids){
                spendRecordService.deleteBySpendId(Integer.parseInt(s));
                spendInfoService.deleteByPrimaryKey(Integer.parseInt(s));
            }
        }
        jsonObject.put("msg","删除成功！");
        return jsonObject.toString();
    }
}
