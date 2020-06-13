package com.example.demo.dao;

import com.example.demo.pojo.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMenuDao extends JpaRepository<AdminMenu,Integer> {
    AdminMenu findById(int id);
    List<AdminMenu> findAllByParentid(int parentid);
}
