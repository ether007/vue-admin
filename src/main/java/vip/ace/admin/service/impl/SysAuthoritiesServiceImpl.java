package vip.ace.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.ace.admin.dao.SysAuthorityDao;
import vip.ace.admin.domain.SysAuthority;
import vip.ace.admin.service.SysAuthoritiesService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xcl on 2017/3/28.
 */
@Service
@Transactional
public class SysAuthoritiesServiceImpl implements SysAuthoritiesService {

    @Autowired
    private SysAuthorityDao sysAuthorityDao;

    @Override
    public int save(SysAuthority sysAuthority) {
        sysAuthorityDao.saveAndFlush(sysAuthority);
        return 1;
    }

    @Override
    public int save(Set<SysAuthority> sysAuthoritys) {
        sysAuthorityDao.save(sysAuthoritys);
        return 1;
    }

    @Override
    public int update(SysAuthority sysAuthority) {
        sysAuthorityDao.save(sysAuthority);
        return 1;
    }

    @Override
    public int del(String id) {
        sysAuthorityDao.delete(id);
        return 1;
    }

    @Override
    public SysAuthority getByPath(String path) {
        return sysAuthorityDao.findOne(path);
    }


    @Override
    public Set<SysAuthority> findUserSysAuthority(int userId) {
        return sysAuthorityDao.findUserAuthoritys(userId);
    }


    @Override
    public Set<SysAuthority> findAllSysAuthority() {
        return new HashSet<SysAuthority>((Collection<? extends SysAuthority>) sysAuthorityDao.findAll());
    }
}
