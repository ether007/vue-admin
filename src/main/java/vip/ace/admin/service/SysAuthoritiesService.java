package vip.ace.admin.service;

import vip.ace.admin.domain.SysAuthority;

import java.util.List;
import java.util.Set;

/**
 * Created by xcl on 2017/3/28.
 */
public interface SysAuthoritiesService {


    int save(SysAuthority sysAuthority);

    int save(Set<SysAuthority> sysAuthoritys);

    int update(SysAuthority sysAuthority);

    int del(String path);

    SysAuthority getByPath(String path);


    Set<SysAuthority> findUserSysAuthority(int userId);

    Set<SysAuthority> findAllSysAuthority();



}
