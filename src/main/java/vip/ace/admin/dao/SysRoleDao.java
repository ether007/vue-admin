package vip.ace.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vip.ace.admin.domain.SysRole;

import java.util.Set;

/**
 * Created by xcl on 2017/5/19.
 */
public interface SysRoleDao extends JpaRepository<SysRole,Integer> {

    @Query(nativeQuery = true,value = "select distinct t1.* from sys_role t1,sys_user_role t2 where t1.id=t2.role_id and t2.user_id=?")
    Set<SysRole> findUserRoles(Integer sysUserId);

}
