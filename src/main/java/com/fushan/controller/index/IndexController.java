package com.fushan.controller.index;
import com.fushan.common.util.UserConstants;
import com.fushan.entity.MenuChildren;
import com.fushan.entity.MenuInfo;
import com.fushan.entity.RoleInfo;
import com.fushan.service.menu.MenuChildrenService;
import com.fushan.service.menu.MenuInfoService;
import com.fushan.service.role.RoleInfoService;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    MenuInfoService menuInfoService;
    @Resource
    MenuChildrenService menuChildrenService;
    @Resource
    RoleInfoService roleInfoService;
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request)throws Exception{
        JSONArray jsonArray = new JSONArray();
        List<MenuInfo> menuInfoList = menuInfoService.queryByUserId((Integer)request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (menuInfoList != null && menuInfoList.size() > 0){
            for (MenuInfo menuInfo : menuInfoList){
                JSONObject json = new JSONObject();
                JSONArray jsonArray1 = new JSONArray();
                List<MenuChildren> menuChildren = menuChildrenService.queryMenuByParentId(menuInfo.getId());
                if (menuChildren != null && menuChildren.size() > 0){
                    for (MenuChildren menu : menuChildren){
                        JSONObject json1 = new JSONObject();
                        json1.put("name",menu.getName());
                        json1.put("url",menu.getUrl());
                        jsonArray1.put(json1);
                    }
                }
                json.put("name",menuInfo.getName());
                json.put("children",jsonArray1);
                jsonArray.put(json);
            }
        }
        String roleName = "";
        List<RoleInfo> roleInfo = roleInfoService.queryByUserId((Integer)request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (roleInfo != null && roleInfo.size() > 0){
            roleName = roleInfo.get(0).getRoleName();
        }
        model.addAttribute("roleName",roleName);
        model.addAttribute("userName",request.getSession().getAttribute(UserConstants.LOGIN_USER_NAME.name()));
        model.addAttribute("menuList",jsonArray);
        return "index/index";
    }
    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest request)throws Exception{
        return "index/main";
    }
    @RequestMapping(value = "/login/menuList", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public @ResponseBody
    String menuList(HttpServletRequest request)throws Exception{
        JSONArray jsonArray = new JSONArray();
        List<MenuInfo> menuInfoList = menuInfoService.queryByUserId((Integer)request.getSession().getAttribute(UserConstants.LOGIN_USER_ID.name()));
        if (menuInfoList != null && menuInfoList.size() > 0){
            for (MenuInfo menuInfo : menuInfoList){
                JSONObject json = new JSONObject();
                JSONArray jsonArray1 = new JSONArray();
                List<MenuChildren> menuChildren = menuChildrenService.queryMenuByParentId(menuInfo.getId());
                if (menuChildren != null && menuChildren.size() > 0){
                    for (MenuChildren menu : menuChildren){
                        JSONObject json1 = new JSONObject();
                        json1.put("name",menu.getName());
                        json1.put("url",menu.getUrl());
                        jsonArray1.put(json1);
                    }
                }
                json.put("name",menuInfo.getName());
                json.put("children",jsonArray1);
                jsonArray.put(json);
            }
        }
        return jsonArray.toString();
    }
}
