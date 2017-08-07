package vip.ace.admin.web.controllers;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ace.admin.common.Resp;
import vip.ace.admin.domain.SysAuthority;
import vip.ace.admin.domain.SysRole;
import vip.ace.admin.service.SysRolesService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    private SysRolesService sysRolesService;

    @RequestMapping("/role/list")
    public Resp list(HttpServletRequest request) {
        Resp resp = new Resp();
        Pageable page = new PageRequest(0, 20);
        Page<SysRole> result = sysRolesService.page(page);
        resp.setData(result);
        return resp;
    }

    @RequestMapping("/role/save")
    public Resp add(SysRole sysRole,HttpServletRequest request) {

        if(sysRole.getId()==-1){
            sysRole.setId(null);
        }
        String authorities = request.getParameter("authorities");
        sysRole.setAuthorities(null);
        if(StringUtils.hasText(authorities)){
            List<SysAuthority> auths = JSON.parseArray(authorities, SysAuthority.class);
            System.out.println(authorities);
            sysRole.setAuthorities(new HashSet<SysAuthority>(auths));
        }
        Resp resp = new Resp();
        sysRolesService.save(sysRole);
        resp.setData(sysRole);
        return resp;
    }


    @RequestMapping("/role/delete")
    public Resp delete(int id) {
        Resp resp = new Resp();
        sysRolesService.delete(id);
        resp.setData(id);
        return resp;
    }
}
