package com.example.demo.dao;

import com.example.demo.pojo.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface JotterArticleDAO extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
    @Modifying
    @Transactional
    void deleteById(int id);
}
