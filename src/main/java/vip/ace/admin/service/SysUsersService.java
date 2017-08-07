package vip.ace.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vip.ace.admin.domain.SysUser;

/**
 * Created by xcl on 2017/3/28.
 */
public interface SysUsersService {
    int register(SysUser sysUser);

    Page<SysUser> page(Pageable pageable);

    int delete(int id);
}
