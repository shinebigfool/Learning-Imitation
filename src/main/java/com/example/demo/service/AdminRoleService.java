package com.example.demo.service;

import com.example.demo.dao.AdminMenuDao;
import com.example.demo.dao.AdminRoleDAO;
import com.example.demo.dao.AdminRoleMenuDAO;
import com.example.demo.pojo.AdminMenu;
import com.example.demo.pojo.AdminPermission;
import com.example.demo.pojo.AdminRole;
import com.example.demo.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminMenuService adminMenuService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;
    public List<AdminRole> findAll() {
        return adminRoleDAO.findAll();
    }

    public List<AdminRole> listWithPermsAndMenus(){
        List<AdminRole> roles = adminRoleDAO.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for(AdminRole role : roles){
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setMenus(menus);
            role.setPerms(perms);
        }
        return roles;
    }
    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = adminUserRoleService.listAllByUid(uid)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        System.out.println("AdminRoleService:listRolesByUser()");
        System.out.println("用户名："+username+"用户id："+uid+"拥有角色："+rids);
        System.out.println("AdminRoleService:listRolesByUser()");
        return adminRoleDAO.findAllById(rids);
    }
    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return adminRoleDAO.save(roleInDB);
    }
    public void deleteRole(int rid){
        adminRoleDAO.deleteById(rid);
        adminRoleMenuDAO.deleteAllByRid(rid);
        adminRolePermissionService.deleteByRid(rid);
    }
}
