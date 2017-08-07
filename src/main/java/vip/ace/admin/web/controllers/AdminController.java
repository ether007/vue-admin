package vip.ace.admin.web.controllers;

import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.ace.admin.common.Resp;
import vip.ace.admin.domain.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping(value = "/login", produces = {MediaType.TEXT_HTML_VALUE})
    public String toLogin(HttpServletRequest request) {
        //登录界面
        return "admin/login";
    }

    @RequestMapping("/loginSuccess")
    @ResponseBody
    public Resp loginSuccess(HttpServletRequest request) {
        //登录成功结果
        Resp resp = new Resp();
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JSONObject json = new JSONObject();
        json.put("id", sysUser.getId());
        json.put("issys", sysUser.getIssys());
        json.put("userAccount", sysUser.getUserAccount());
        json.put("username", sysUser.getUserName());
        Set<String> permission = new HashSet<String>();
        Collection<? extends GrantedAuthority> auths = sysUser.getAuthorities();
        for (GrantedAuthority a : auths) {
            permission.add(a.getAuthority());
        }
        json.put("permission", permission);
        resp.setData(json);
        return resp;
    }

    @RequestMapping("/islogin")
    @ResponseBody
    public Resp permission(HttpServletRequest request) {
        //p判断是否登录
        Resp resp = new Resp();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JSONObject json = new JSONObject();
        json.put("islogin", authentication!=null && authentication.isAuthenticated());
        resp.setData(json);
        return resp;
    }


    @RequestMapping("/loginFailure")
    @ResponseBody
    public Resp loginFailure(HttpServletRequest request) {
        //登录失败结果
        AuthenticationException exception = (AuthenticationException) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Resp resp = new Resp(0,exception.getMessage());
        resp.setData(exception);
        return resp;
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public JSONObject loginOut(HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        //退出成功结果页
        JSONObject object = new JSONObject();
        object.put("code", 1);
        return object;
    }

}
