package com.baidu.disconf.web.service.user.vo;

/**
 * Created by praker on 04/01/2018.
 */
public class VisitorVo {

    private long id;
    private String name;
    private String role;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "VisitorVo [id=" + id + ", name=" + name + ", role=" + role + "]";
    }




}
