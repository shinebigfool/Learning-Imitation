package com.example.demo.controller;

import com.example.demo.Result.Result;
import com.example.demo.Result.ResultFactory;
import com.example.demo.pojo.AdminRole;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminMenuService adminMenuService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    @GetMapping(path = "/admin/role")
    @ResponseBody
    public Result listRoles(){
        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermsAndMenus());
    }
    @GetMapping(path = "/admin/role/perm")
    @ResponseBody
    public Result listPerms(){
        return ResultFactory.buildSuccessResult(adminPermissionService.list());
    }
    @GetMapping(path = "/admin/role/menu")
    @ResponseBody
    public Result listMenus(){
        return ResultFactory.buildSuccessResult(adminMenuService.list());
    }
    @PutMapping(path = "/admin/role")
    @ResponseBody
    public Result editRole(@RequestBody AdminRole role){
        adminRoleService.addOrUpdate(role);
        adminPermissionService.savePermChanges(role.getId(),role.getPerms());
        return ResultFactory.buildSuccessResult("修改角色信息成功");
    }
    @PutMapping(path = "/admin/role/menu")
    @ResponseBody
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds){
        adminRoleMenuService.updateRoleMenu(rid,menusIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }
    @PutMapping(path="/admin/role/status")
    @ResponseBody
    public Result changeRoleState(@RequestBody AdminRole role){
        AdminRole adminRole = adminRoleService.updateRoleStatus(role);
        return ResultFactory.buildSuccessResult("change status success");
    }
    @PostMapping(path = "/admin/role")
    @ResponseBody
    public Result addRole(@RequestBody AdminRole role){
        adminRoleService.addOrUpdate(role);
        return ResultFactory.buildSuccessResult("add success");
    }
    @PostMapping(path = "/admin/role/delete")
    @ResponseBody
    public Result deleteRole(@RequestBody AdminRole role){
        int rid = role.getId();
        System.out.println("deleteRole()");
        System.out.println(rid);
        adminRoleService.deleteRole(rid);
        return ResultFactory.buildSuccessResult("delete success");
    }
}
