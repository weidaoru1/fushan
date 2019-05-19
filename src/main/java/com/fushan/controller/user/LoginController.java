package com.fushan.controller.user;
import com.fushan.common.util.*;
import com.fushan.entity.UserInfo;
import com.fushan.service.user.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class LoginController {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Resource
    UserInfoService userInfoService;
    @RequestMapping("/user/userList")
    public String userList(Model model, HttpServletRequest request, DataGrid dataGrid)throws Exception{
        Object obj = userInfoService.pagedQuery(dataGrid);
        model.addAttribute("page",obj);
        return "views/user/userList";
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
    public  @ResponseBody Object queryById(HttpServletRequest request, UserInfo userInfo){
        return userInfoService.selectByPrimaryKey(userInfo.getId());
    }
    @RequestMapping("user/deleteById")
    public  @ResponseBody String deleteById(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("ids");
        if (StringUtils.isNotBlank(id)){
            String[] ids = id.split(";");
            for (String s : ids){
                userInfoService.deleteByPrimaryKey(Integer.valueOf(s));
            }
        }
        jsonObject.put("msg","删除成功！");
        return jsonObject.toString();
    }
    @RequestMapping("user/save")
    public @ResponseBody String save(HttpServletRequest request, UserInfo userInfo)throws Exception{
        JSONObject jsonObject = new JSONObject();
        UserInfo u = new UserInfo();
        u.setUserName(userInfo.getUserName());
        List<UserInfo> user = userInfoService.queryList(u);
        if (user != null && user.size() > 0 && userInfo.getId()  == null){
            jsonObject.put("msg","该用户账号已存在！");
            jsonObject.put("status",0);
            return jsonObject.toString();
        }
        userInfo.setPassword(MD5utils.encrypt(userInfo.getPassword()));
        if (userInfo.getId() != null){
            userInfoService.updateByPrimaryKey(userInfo);
        }else{
           userInfoService.insertSelective(userInfo);
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
        jsonObject.put("status",1);
        jsonObject.put("success",true);
        return jsonObject.toString();
    }

}
