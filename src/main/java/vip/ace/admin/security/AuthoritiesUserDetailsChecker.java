package vip.ace.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import vip.ace.admin.domain.SysAuthority;
import vip.ace.admin.domain.SysRole;
import vip.ace.admin.domain.SysUser;
import vip.ace.admin.service.SysAuthoritiesService;
import vip.ace.admin.service.SysRolesService;

import java.util.Set;

/**
 * Created by xcl on 2017/3/20.
 */
@Component
public class AuthoritiesUserDetailsChecker implements UserDetailsChecker {

    private Logger logger = LoggerFactory.getLogger(AuthoritiesUserDetailsChecker.class);

    @Autowired
    private SysRolesService sysRolesService;

    @Autowired
    private SysAuthoritiesService sysAuthoritiesService;


    @Override
    public void check(UserDetails user) {
        if (!user.isCredentialsNonExpired()) {
            logger.debug("User account credentials have expired");
            throw new CredentialsExpiredException("无效用户");
        }
        SysUser u = (SysUser) user;
        int issys = u.getIssys();

        Set<SysRole> roles = sysRolesService.findUserSysRole(u.getId());
        Set<SysAuthority> auths = null;
        if (issys == 1) {
            auths = sysAuthoritiesService.findAllSysAuthority();
        } else {
            auths = sysAuthoritiesService.findUserSysAuthority(u.getId());
        }
        u.setAuths(auths);
        u.setRoles(roles);
        String name = user.getUsername();
        logger.info("----登陆成功------" + name);
    }
}
