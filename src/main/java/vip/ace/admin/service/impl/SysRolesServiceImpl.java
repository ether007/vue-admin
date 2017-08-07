package vip.ace.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vip.ace.admin.dao.SysAuthorityDao;
import vip.ace.admin.dao.SysRoleDao;
import vip.ace.admin.domain.SysAuthority;
import vip.ace.admin.domain.SysRole;
import vip.ace.admin.service.SysRolesService;

import java.util.Set;

/**
 * Created by xcl on 2017/3/28.
 */
@Service
public class SysRolesServiceImpl implements SysRolesService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysAuthorityDao sysAuthorityDao;

    @Override
    public Set<SysRole> findUserSysRole(int userId) {
        return sysRoleDao.findUserRoles(userId);
    }

    @Override
    public int save(SysRole sysRole) {
        Set<SysAuthority> auths = sysRole.getAuthorities();
        if(auths!=null && auths.size()>0){
            sysAuthorityDao.save(auths);
        }
        sysRoleDao.save(sysRole);
        return 1;
    }

    @Override
    public Page<SysRole> page(Pageable pageable) {
        return sysRoleDao.findAll(pageable);
    }

    @Override
    public Long count() {
        return sysRoleDao.count();
    }

    @Override
    public int delete(int id) {
        sysRoleDao.delete(id);
        return 1;
    }
}
