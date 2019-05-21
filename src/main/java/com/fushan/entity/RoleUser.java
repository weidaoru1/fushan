package com.fushan.entity;
import java.io.Serializable;

public class RoleUser implements Serializable {
    private static final long serialVersionUID = 5887591154801776318L;
    private Integer id;
    private Integer roleId;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
