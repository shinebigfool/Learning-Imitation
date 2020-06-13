package com.example.demo.service;

import com.example.demo.dao.AdminUserRoleDAO;
import com.example.demo.pojo.AdminRole;
import com.example.demo.pojo.AdminUserRole;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;
    public List<AdminUserRole> listAllByUid(int uid){
        return adminUserRoleDAO.findAllByUid(uid);
    }
    @Transient
    public void saveRoleChanges(int uid, List<AdminRole> roles){
        System.out.println("AdminUserRoleService:saveRoleChanges()");
        System.out.println(uid);
        System.out.println(roles);
        List<AdminUserRole>lists = adminUserRoleDAO.findAllByUid(uid);
        lists.forEach(list->{
            adminUserRoleDAO.delete(list);
            System.out.println(list.getId());
        });

        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r->{
            AdminUserRole ur = new AdminUserRole();
            ur.setRid(r.getId());
            ur.setUid(uid);
            urs.add(ur);
        });
        adminUserRoleDAO.saveAll(urs);
    }
}
