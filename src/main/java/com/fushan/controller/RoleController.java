package com.fushan.controller;
import com.fushan.service.role.RoleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RoleController {
    @Resource
    RoleInfoService roleInfoService;
    @RequestMapping("role/roleList")
    public String roleList(Model model, HttpServletRequest request){
        return "views/role/roleList";
    }
    @RequestMapping("role/roleAll")
    public @ResponseBody Object roleAll(){
        return roleInfoService.queryListAll();
    }
}
