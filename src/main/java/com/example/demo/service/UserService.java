package com.example.demo.service;

import com.example.demo.dao.AdminUserRoleDAO;
import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.AdminRole;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;
    public void save(User n){
        userRepository.save(n);
    }
    public Iterable<User> findAll(){
        List<User> users= userRepository.findAll();
        users.forEach(u->{
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getName());
            u.setRoles(roles);
        });
        return users;
    }
    public User login(String name,String password){
        return userRepository.getByNameAndAndPassword(name,password);
    }
    public boolean isExist(String name){
        User u = userRepository.getByName(name);
        if(u==null){
            return false;
        }else{
            return true;
        }
    }
//    public List<User> listUser(){
//        return userRepository.list();
//    }
    public User getByName(String name){
        return userRepository.getByName(name);
    }
    public void editUser(User user){
        User userInDB = userRepository.getByName(user.getName());
        userInDB.setEmail(user.getEmail());
        userInDB.setPhone(user.getPhone());
        userInDB.setUname(user.getUname());
        userRepository.save(userInDB);
        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
    }
    public void deleteUser(User user){
        User userInDB = userRepository.getByName(user.getName());
        int id = userInDB.getId();
        userRepository.deleteById(id);
        adminUserRoleDAO.deleteAllByUid(id);
    }
}
