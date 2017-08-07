package vip.ace.admin.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by xcl on 2017/5/18.
 */

@Entity
@Table(name="sys_role")
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String roleName;


    @Column(columnDefinition = "tinyint default 1")
    private Integer status;

    @Column(insertable = false,updatable = false,columnDefinition = "datetime default now()")
    private Date gmt_create;

    @Column(columnDefinition = "datetime default now()")
    private Date gmt_modified;

    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_authorities",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "authority_path",referencedColumnName = "path")})
    private Set<SysAuthority> authorities;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<SysAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<SysAuthority> authorities) {
        this.authorities = authorities;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                ", authorities=" + authorities +
                '}';
    }
}
