package vip.ace.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import vip.ace.admin.domain.SysUser;


/**
 * Created by xcl on 2017/5/19.
 */
public interface SysUserDao extends JpaRepository<SysUser,Integer> {

    SysUser findByuserAccount(String userAccount);

}
