package vip.ace.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vip.ace.admin.dao.SysUserDao;
import vip.ace.admin.domain.SysUser;
import vip.ace.admin.service.SysUsersService;


/**
 * Created by xcl on 2017/3/15.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, SysUsersService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser u = sysUserDao.findByuserAccount(s);
        if (u == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return u;
    }

    @Override
    public int register(SysUser sysUser) {
        sysUser.setUserPassword(bCryptPasswordEncoder.encode(sysUser.getUserPassword()));
        sysUserDao.save(sysUser);
        return 1;
    }

    @Override
    public Page<SysUser> page(Pageable pageable) {
        return sysUserDao.findAll(pageable);
    }

    @Override
    public int delete(int id) {
        sysUserDao.delete(id);
        return 0;
    }
}
