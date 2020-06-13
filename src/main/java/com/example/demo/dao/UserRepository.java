package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {
    User getByNameAndAndPassword(String name, String password);
    User getByName(String name);
    //@Query("select new User(u.id,u.username,u.name,u.phone,u.email,u.enabled) from User u")
    //List<User> list();
    @Modifying
    @Transactional
    void deleteById(int id);
}
