package com.example.demo.controller;

import com.example.demo.Result.Result;
import com.example.demo.Result.ResultFactory;
import com.example.demo.Result.loginResult;
import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.AdminRole;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminUserRoleService;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @RequiresPermissions("/api/admin/user/status")
    @PutMapping("/status")
    @ResponseBody
    public Result changeStatus(@RequestBody User user){
        User u = userService.getByName(user.getName());
        System.out.println(u);
        u.setEnabled(!u.getEnabled());
        userService.save(u);
        return ResultFactory.buildSuccessResult("Status Change Success");
    }
    @CrossOrigin
    @RequiresPermissions("/api/admin/user/all")
    @GetMapping(path = "/all")
    @ResponseBody
    public Result getAllUsers(){
        System.out.println("UserController:ListUsers()");
        return ResultFactory.buildSuccessResult(userService.findAll());
    }
//    @GetMapping(path = "/listUser")
//    @ResponseBody
//    public List<User> listUsers(){
//        return userService.listUser();
//    }
    //@CrossOrigin
    @GetMapping(path = "/login")
    @ResponseBody
    public Result login(User u){
        String name = u.getName();
        String password = u.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,password);
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            System.out.println("login in success");
            System.out.println("isRemembered:"+subject.isRemembered());
            System.out.println("isAuthenticated:"+subject.isAuthenticated());
            return ResultFactory.buildSuccessResult(name);
        }catch (AuthenticationException e){
            String message="账号密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }
    @CrossOrigin
    @PostMapping(path = "/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        System.out.println(user);
        String name = user.getName();
        String password = user.getPassword();
        System.out.println("register:");
        System.out.println(name);
        System.out.println(password);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        user.setEnabled(true);
        boolean exist = userService.isExist(name);
        System.out.println(exist);
        if(exist){
            String message="用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodePassword = new SimpleHash("md5",password,salt,times).toString();
        user.setPassword(encodePassword);
        user.setSalt(salt);
        userService.save(user);
        return ResultFactory.buildSuccessResult(user);
    }
    @CrossOrigin
    @GetMapping(path = "/logout")
    @ResponseBody
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message="成功登出";
        System.out.println(message);
        return ResultFactory.buildSuccessResult(message);
    }
    @PutMapping(path = "/editUser")
    @ResponseBody
    public Result editUser(@RequestBody User user) throws Exception{
        System.out.println("UserController:editUser()");
        System.out.println(user);
        userService.editUser(user);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }
    @PutMapping(path = "/roleService")
    @ResponseBody
    public Result test(@RequestBody User user){
        System.out.println("UserController:test()");
        User userInDB = userService.getByName(user.getName());
        int uid = userInDB.getId();
        System.out.println(uid);
        System.out.println(user.getRoles());
        adminUserRoleService.saveRoleChanges(uid,user.getRoles());
        return ResultFactory.buildSuccessResult("Test");
    }
    @PostMapping(path = "/delete")
    @ResponseBody
    public Result deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return ResultFactory.buildSuccessResult("deleteSuccess");
    }
}
