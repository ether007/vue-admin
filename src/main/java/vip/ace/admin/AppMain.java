package vip.ace.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vip.ace.admin.domain.SysAuthority;
import vip.ace.admin.domain.SysRole;
import vip.ace.admin.domain.SysUser;
import vip.ace.admin.service.SysAuthoritiesService;
import vip.ace.admin.service.SysRolesService;
import vip.ace.admin.service.SysUsersService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AppMain implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(AppMain.class, args);

    }

    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private SysRolesService sysRolesService;

    @Autowired
    private SysAuthoritiesService sysAuthoritiesService;

    @Override
    public void run(String... args) throws Exception {
        SysUser sysuser = new SysUser();
        sysuser.setIssys(0);
        sysuser.setStatus(1);
        sysuser.setUserName("臣");
        sysuser.setUserPassword("1111111");
        sysuser.setUserAccount("chen");



        SysRole r1 = new SysRole();

        //r1.setId(14);
        r1.setRoleName("产品");
        r1.setStatus(1);
        Set<SysAuthority> list = new HashSet<>();
        list.add(new SysAuthority("/dashboard", "仪表板", 1));
        list.add(new SysAuthority("/user/list", "用户列表", 1));
        r1.setAuthorities(list);
       // sysRolesService.save(r1);

        Set<SysRole> roles = new HashSet<>();
        roles.add(r1);
        sysuser.setRoles(roles);

       // sysUsersService.register(sysuser);
        System.out.println(1111111);
    }
}
