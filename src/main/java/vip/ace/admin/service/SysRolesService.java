package vip.ace.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vip.ace.admin.domain.SysRole;

import java.util.Set;

/**
 * Created by xcl on 2017/3/28.
 */
public interface SysRolesService {

    Set<SysRole> findUserSysRole(int userId);

    int save(SysRole sysRole);

    Page<SysRole> page(Pageable pageable);

    Long count();

    int delete(int id);



}
