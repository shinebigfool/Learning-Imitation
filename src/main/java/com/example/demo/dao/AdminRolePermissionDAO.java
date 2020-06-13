package com.example.demo.dao;

import com.example.demo.pojo.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminRolePermissionDAO extends JpaRepository<AdminRolePermission,Integer> {
    List<AdminRolePermission> findAllByRid(int rid);
    List<AdminRolePermission> findAllByRidIn(List<Integer> rids);
    @Modifying
    @Transactional
    void deleteAllByRid(int rid);
}
