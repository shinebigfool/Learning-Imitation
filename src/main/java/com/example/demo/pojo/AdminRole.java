package com.example.demo.pojo;

import javax.persistence.*;
import java.util.List;

@Entity(name="admin_role")
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "namezh")
    private String NameZh;
    @Column(name = "enabled")
    private boolean enabled;
    @Transient
    private List<AdminPermission> perms;
    @Transient
    private List<AdminMenu> menus;
    public int getId() {
        return id;
    }

    public AdminRole() {
    }

    public List<AdminPermission> getPerms() {
        return perms;
    }

    public void setPerms(List<AdminPermission> perms) {
        this.perms = perms;
    }

    public List<AdminMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AdminMenu> menus) {
        this.menus = menus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return NameZh;
    }

    public void setNameZh(String nameZh) {
        NameZh = nameZh;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", NameZh='" + NameZh + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
