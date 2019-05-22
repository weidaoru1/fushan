package com.fushan.controller;

import com.fushan.common.util.DataGrid;
import com.fushan.entity.MenuInfo;
import com.fushan.service.menu.MenuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class menuController {
    @Resource
    MenuInfoService menuInfoService;
    @RequestMapping("/menu/menuList")
    public String menuList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        model.addAttribute("page",menuInfoService.pagedQuery(dataGrid));
        return "views/menu/menuList";
    }
    @RequestMapping("menu/menuAdd")
        public String menuAdd(Model model, HttpServletRequest request, MenuInfo menuInfo)throws Exception{
        System.out.println("。。。。");
        return "views/menu/menuAdd";
    }
}
