package vip.ace.admin.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ace.admin.common.Resp;
import vip.ace.admin.domain.SysUser;
import vip.ace.admin.service.SysUsersService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private SysUsersService sysUsersService;

    @RequestMapping("/list")
    public Resp list(HttpServletRequest request) {
        Resp resp = new Resp();
        Pageable page = new PageRequest(0, 20);
        Page<SysUser> result = sysUsersService.page(page);
        resp.setData(result);
        return resp;
    }

    @RequestMapping("/save")
    public Resp save(HttpServletRequest request) {
        Resp resp = new Resp();
        return resp;
    }

    @RequestMapping("/delete")
    public Resp delete(HttpServletRequest request) {
        Resp resp = new Resp();
        return resp;
    }

}
