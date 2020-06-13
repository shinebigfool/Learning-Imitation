package com.example.demo.dao;

import com.example.demo.pojo.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPermissionDAO extends JpaRepository<AdminPermission,Integer> {
    AdminPermission findById(int id);
}
