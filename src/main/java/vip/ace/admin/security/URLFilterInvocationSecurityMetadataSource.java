package vip.ace.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import vip.ace.admin.domain.SysAuthority;
import vip.ace.admin.service.SysAuthoritiesService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by xcl on 16/9/27.
 * 获取所有的权限或者根据资获取权限
 */

public class URLFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(URLFilterInvocationSecurityMetadataSource.class);

    @Autowired
    private SysAuthoritiesService sysAuthoritiesService;


    /**
     * 用户获取正在访问的资源所对应的权限
     */

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation)object).getRequest();
        String url = request.getRequestURI();
        logger.info("获取请求url资源需要的权限******"+url);
        SysAuthority auth = sysAuthoritiesService.getByPath(url);
        List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
        if(auth != null){
            list.add(new SecurityConfig(auth.getAuthority()));
        }
        return list;
    }

    /**
     * 获取所有权限配置属性
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<ConfigAttribute> _list = new ArrayList<ConfigAttribute>();
        Set<SysAuthority> authlist = sysAuthoritiesService.findAllSysAuthority();
        for(SysAuthority a:authlist){
            _list.add(new SecurityConfig(a.getAuthority()));
        }
        return _list;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
