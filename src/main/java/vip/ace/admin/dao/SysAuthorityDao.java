package vip.ace.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vip.ace.admin.domain.SysAuthority;

import java.util.Set;

/**
 * Created by xcl on 2017/5/19.
 */
public interface SysAuthorityDao extends JpaRepository<SysAuthority,String> {

    /**
     * 查询用户id的权限
     * @param userid
     * @return
     */
    @Query(nativeQuery = true,value = "select DISTINCT t1.* from sys_authority t1,sys_role_authorities t2,sys_user_role t3 where t1.path = t2.authority_path and t2.role_id=t3.role_id and t3.user_id=?")
    Set<SysAuthority> findUserAuthoritys(Integer userid);

    /**
     * 查询角色id的角色
     * @param roleid
     * @return
     */
    @Query(nativeQuery = true,value = "select DISTINCT t1.* from sys_authority t1,sys_role_authorities t2 where t1.path = t2.authority_path and t2.role_id=?")
    Set<SysAuthority> findRoleAuthoritys(Integer roleid);

    /**
     * 查询访问url的权限
     * @param url
     * @return
     */
    @Query(nativeQuery = true,value = "select * from sys_authority where  path=?")
    Set<SysAuthority> findAuthoritysByUrl(String url);


}
