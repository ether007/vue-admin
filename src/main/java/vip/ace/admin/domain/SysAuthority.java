package vip.ace.admin.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * Created by xcl on 2017/5/18.
 * 权限
 */
@Entity
@Table(name = "sys_authority")
public class SysAuthority implements GrantedAuthority {

    @Id
    private String path;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "tinyint default 1")
    private Integer status;


    @Column(insertable = false, updatable = false, columnDefinition = "datetime default now()")
    private Date gmt_create;


    @Column(columnDefinition = "datetime default now()")
    private Date gmt_modified;


    public SysAuthority() {
    }

    public SysAuthority(String path, String name, Integer status) {
        this.path = path;
        this.name = name;
        this.status = status;
    }

    @Override
    public String getAuthority() {
        return path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysAuthority that = (SysAuthority) o;

        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysAuthority{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                '}';
    }
}
