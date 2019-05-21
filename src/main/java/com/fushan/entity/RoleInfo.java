package com.fushan.entity;
import java.io.Serializable;

public class RoleInfo implements Serializable {
    private static final long serialVersionUID = 5887591154801776318L;
    private Integer id;
    private String roleName;
    private Integer isSuper;
    private String des;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Integer isSuper) {
        this.isSuper = isSuper;
    }
}
