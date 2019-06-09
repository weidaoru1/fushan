package com.fushan.controller;
import com.fushan.common.util.*;
import com.fushan.entity.RoleInfo;
import com.fushan.entity.RoleUser;
import com.fushan.entity.UserInfo;
import com.fushan.service.role.RoleInfoService;
import com.fushan.service.role.RoleUserService;
import com.fushan.service.user.UserInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {
    @Resource
    UserInfoService userInfoService;
    @Resource
    RoleUserService roleUserService;
    @Resource
    RoleInfoService roleInfoService;
    @RequestMapping("/user/userList")
    public String userList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        return "views/user/userList";
    }
    @RequestMapping("/user/queryUserList")
    public @ResponseBody String queryUserList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        PageInfo<UserInfo> list = userInfoService.pagedQuery(dataGrid);
        List<UserInfo> userInfoList = list.getList();
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        if (userInfoList != null && userInfoList.size() > 0){
            for(UserInfo u : userInfoList){
                JSONObject json = new JSONObject();
                json.put("id",u.getId());
                json.put("userName",u.getUserName());
                json.put("realName",u.getRealName());
                json.put("password",u.getPassword());
                json.put("tel",u.getTel());
                json.put("des",u.getDes());
                List<RoleInfo> roleInfos =  roleInfoService.queryByUserId(u.getId());
                if (roleInfos != null && roleInfos.size() > 0){
                    json.put("roleId",roleInfos.get(0).getId());
                    json.put("roleName",roleInfos.get(0).getRoleName());
                }
                array.add(json);
            }
        }
        object.put("pageNum",list.getPageNum());
        object.put("pageSize",list.getPageSize());
        object.put("size",list.getSize());
        object.put("total",list.getTotal());
        object.put("pages",list.getPages());
        object.put("firstPage",list.getFirstPage());
        object.put("prePage",list.getPrePage());
        object.put("nextPage",list.getNextPage());
        object.put("lastPage",list.getLastPage());
        object.put("list",array);
        return DataDealUtils.dataToJson(object).toString();
    }
    @RequestMapping("/user/queryRoleByUserId")
    public @ResponseBody String queryRole(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        String userId = request.getParameter("userId");
        if (StringUtils.isNotBlank(userId)){
            List<RoleInfo> list = roleInfoService.queryByUserId(Integer.parseInt(userId));
            return DataDealUtils.dataToJson(list.get(0)).toString();
        }
        return null;
    }
    @RequestMapping("/")
    public String loginNew(Model model, HttpServletRequest request)throws Exception{
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request)throws Exception{
        return "login";
    }
    @RequestMapping("user/queryById")
    public  @ResponseBody String queryById(HttpServletRequest request, UserInfo userInfo) throws Exception{
        JSONObject object = new JSONObject();
        UserInfo user = userInfoService.selectByPrimaryKey(userInfo.getId());
        if (user != null){
            object.put("id",user.getId());
            object.put("userName",user.getUserName());
            object.put("realName",user.getRealName());
            object.put("password",user.getPassword());
            object.put("tel",user.getTel());
            object.put("des",user.getDes());
        }
        List<RoleInfo> list =  roleInfoService.queryByUserId(userInfo.getId());
        if (list != null && list.size() > 0){
            object.put("roleId",list.get(0).getId());
            object.put("roleName",list.get(0).getRoleName());
        }
        return object.toString();
    }
    @RequestMapping("user/deleteById")
    public  @ResponseBody String deleteById(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("ids");
        if (StringUtils.isNotBlank(id)){
            String[] ids = id.split(";");
            for (String s : ids){
                roleUserService.deleteByUserId(Integer.parseInt(s));
                userInfoService.deleteByPrimaryKey(Integer.valueOf(s));
            }
        }
        jsonObject.put("msg","删除成功！");
        return jsonObject.toString();
    }
    @RequestMapping("user/save")
    public @ResponseBody String save(HttpServletRequest request, UserInfo userInfo)throws Exception{
        String roleId = request.getParameter("roleId");
        JSONObject jsonObject = new JSONObject();
        UserInfo u = new UserInfo();
        u.setUserName(userInfo.getUserName());
        List<UserInfo> user = userInfoService.queryList(u);
        if (user != null && user.size() > 0 && userInfo.getId()  == null){
            jsonObject.put("msg","该用户账号已存在！");
            jsonObject.put("status",0);
            return jsonObject.toString();
        }
        //md5加密
//        userInfo.setPassword(MD5utils.encrypt(userInfo.getPassword()));
        if (userInfo.getId() != null){
            userInfoService.updateByPrimaryKey(userInfo);
        }else{
            userInfo = userInfoService.insertSelective(userInfo);
        }
        if (StringUtils.isNotBlank(roleId) && userInfo != null){
            roleUserService.deleteByUserId(userInfo.getId());
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(Integer.parseInt(roleId));
            roleUser.setUserId(userInfo.getId());
            roleUserService.insertSelective(roleUser);
        }

        jsonObject.put("status",1);
        jsonObject.put("msg","保存成功！");
        return jsonObject.toString();
    }
    @RequestMapping("/login/check")
    public @ResponseBody String check(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserInfo userInfo = userInfoService.userCheck(userName,password);
        if (userInfo == null){
            jsonObject.put("status",0);
            jsonObject.put("success",false);
            return jsonObject.toString();
        }
        request.getSession().setAttribute(UserConstants.LOGIN_USER.name(),userInfo);
        request.getSession().setAttribute(UserConstants.LOGIN_USER_NAME.name(),userInfo.getUserName());
        request.getSession().setAttribute(UserConstants.LOGIN_REAL_NAME.name(),userInfo.getRealName());
        request.getSession().setAttribute(UserConstants.LOGIN_USER_ID.name(),userInfo.getId());
        jsonObject.put("status",1);
        jsonObject.put("success",true);
        return jsonObject.toString();
    }
    @RequestMapping("/login/logout")
    public String logout(){
        return "redirect:/login";
    }
}
