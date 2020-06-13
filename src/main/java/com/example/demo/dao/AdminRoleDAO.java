package com.example.demo.dao;

import com.example.demo.pojo.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDAO extends JpaRepository<AdminRole,Integer> {
    AdminRole findById(int id);
}
