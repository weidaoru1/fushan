package com.fushan.controller;
import com.fushan.common.util.DataDealUtils;
import com.fushan.common.util.DataGrid;
import com.fushan.common.util.UserConstants;
import com.fushan.entity.MenuChildren;
import com.fushan.entity.MenuInfo;
import com.fushan.entity.RoleInfo;
import com.fushan.service.menu.MenuChildrenService;
import com.fushan.service.menu.MenuInfoService;
import com.fushan.service.role.RoleInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class menuController {
    @Resource
    MenuInfoService menuInfoService;
    @Resource
    RoleInfoService roleInfoService;
    @Resource
    MenuChildrenService menuChildrenService;
    @RequestMapping("/menu/menuList")
    public String menuList(Model model, HttpServletRequest request)throws Exception{
        List<RoleInfo> roleInfoList = roleInfoService.queryByUserId((Integer)request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (roleInfoList != null && roleInfoList.size() > 0){
            model.addAttribute("role",roleInfoList.get(0));
        }
        return "views/menu/menuList";
    }
    @RequestMapping("/menu/menuDataList")
    public @ResponseBody String menuDataList(DataGrid dataGrid)throws Exception{
        JSONObject object = DataDealUtils.dataToJson(menuInfoService.pagedQuery(dataGrid));
        return object.toString();
    }
    @RequestMapping("/menu/childMenuList")
    public @ResponseBody String childMenuList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        String parentId = request.getParameter("parentId");
        if (StringUtils.isNotBlank(parentId)){
            Map<String,Object> map = new HashMap<>();
            map.put("parentId",parentId);
            JSONObject object = DataDealUtils.dataToJson(menuChildrenService.pagedQueryByCondition(dataGrid,map));
            return object.toString();
        }
        return null;
    }
    @RequestMapping("/menu/save")
    public @ResponseBody String save(MenuInfo menuInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        menuInfoService.insertSelective(menuInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("/menuChild/save")
    public @ResponseBody String menuSave(MenuChildren menuChildren)throws Exception{
        JSONObject jsonObject = new JSONObject();
        menuChildrenService.insertSelective(menuChildren);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("/menuChild/edit")
    public @ResponseBody String menuEdit(MenuChildren menuChildren)throws Exception{
        JSONObject jsonObject = new JSONObject();
        menuChildrenService.updateByPrimaryKey(menuChildren);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("/menuChild/deleteById")
    public  @ResponseBody String childDeleteById(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("ids");
        if (StringUtils.isNotBlank(id)) {
            String[] ids = id.split(";");
            for(String s : ids){
                menuChildrenService.deleteByPrimaryKey(Integer.parseInt(s));
            }
        }
        jsonObject.put("status",1);
        jsonObject.put("msg","删除成功！");
        return jsonObject.toString();
    }
    @RequestMapping("/menu/edit")
    public @ResponseBody String edit(MenuInfo menuInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        menuInfoService.updateByPrimaryKey(menuInfo);
        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("/menu/deleteById")
    public  @ResponseBody String deleteById(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("ids");
        if (StringUtils.isNotBlank(id)) {
            String[] ids = id.split(";");
            for(String s : ids){
                menuInfoService.deleteByPrimaryKey(Integer.parseInt(s));
                menuChildrenService.deleteByParentId(Integer.parseInt(s));
            }
        }
        jsonObject.put("status",1);
        jsonObject.put("msg","删除成功！");
        return jsonObject.toString();
    }
}
