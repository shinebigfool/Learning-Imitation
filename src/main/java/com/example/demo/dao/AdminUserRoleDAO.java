package com.example.demo.dao;

import com.example.demo.pojo.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    @Modifying
    @Transactional
    void deleteAllByUid(int uid);
    //List<AdminUserRole> findAllByUid(int uid);
}
