package com.example.demo.controller;

import com.example.demo.pojo.AdminMenu;
import com.example.demo.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;
    @GetMapping("/api/menu")
    @ResponseBody
    public List<AdminMenu> menu() {
        System.out.println("MenuController:user-root-menu");
        //System.out.println(adminMenuService.getMenusByCurrentUser());
        return adminMenuService.getMenusByCurrentUser();
    }

}
