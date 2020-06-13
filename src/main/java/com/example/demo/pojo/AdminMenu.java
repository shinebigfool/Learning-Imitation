package com.example.demo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "admin_menu")
@Table(name = "admin_menu")
public class AdminMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "path")
    private String path;
    @Column(name = "name")
    private String name;
    @Column(name = "namezh")
    private String namezh;
    @Column(name = "iconcls")
    private String iconcls;
    @Column(name = "component")
    private String component;
    @Column(name = "parentid")
    private int parentid;
    @Transient
    List<AdminMenu> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }


    public List<AdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }

    public String getNamezh() {
        return namezh;
    }

    public void setNamezh(String namezh) {
        this.namezh = namezh;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "AdminMenu{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", namezh='" + namezh + '\'' +
                ", iconcls='" + iconcls + '\'' +
                ", component='" + component + '\'' +
                ", parentid=" + parentid +
                ", children=" + children +
                '}';
    }
}
