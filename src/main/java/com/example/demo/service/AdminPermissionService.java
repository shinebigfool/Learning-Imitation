package com.example.demo.service;

import com.example.demo.dao.AdminPermissionDAO;
import com.example.demo.dao.AdminRolePermissionDAO;
import com.example.demo.pojo.AdminPermission;
import com.example.demo.pojo.AdminRole;
import com.example.demo.pojo.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminPermissionService {
    @Autowired
    AdminPermissionDAO adminPermissionDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;
    public List<AdminPermission> list() {return adminPermissionDAO.findAll();}
    public Set<String> listPermissionURLsByUser(String username) {
        System.out.println("AdminPermissionService:listPermissionURLsByUser():");
        List<Integer> rids = adminRoleService.listRolesByUser(username)
                .stream().map(AdminRole::getId).collect(Collectors.toList());

        List<Integer> pids = adminRolePermissionDAO.findAllByRidIn(rids)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());

        List<AdminPermission> perms = adminPermissionDAO.findAllById(pids);

        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());
        System.out.println("用户名："+username+"pids："+pids);
        System.out.println(URLs);
        System.out.println("AdminPermissionService:listPermissionURLsByUser():");
        return URLs;
    }
    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<Integer> pids = adminRolePermissionDAO.findAllByRid(rid)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        return adminPermissionDAO.findAllById(pids);
    }
    public boolean needFilter(String requestAPI) {
        System.out.println("AdminPermissionService:needFilter()");
        List<AdminPermission> ps = adminPermissionDAO.findAll();
        for (AdminPermission p: ps) {
            if (p.getUrl().equals(requestAPI)) {
                return true;
            }
        }
        return false;
    }
    @Transactional
    public void savePermChanges(int rid,List<AdminPermission>perms){
        adminRolePermissionDAO.deleteAllByRid(rid);
        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p->{
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDAO.saveAll(rps);
    }
}
