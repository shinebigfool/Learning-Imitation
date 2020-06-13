package com.example.demo.service;

import com.example.demo.dao.JotterArticleDAO;
import com.example.demo.pojo.JotterArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JotterArticleService {
    @Autowired
    JotterArticleDAO jotterArticleDAO;
    public void delete(int id){
        jotterArticleDAO.deleteById(id);
    }
    public void  addOrUpdate(JotterArticle jotterArticle){
        jotterArticleDAO.save(jotterArticle);
    }
    public JotterArticle findById(int id){
        return jotterArticleDAO.findById(id);
    }
    public Page list(int page,int size){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return jotterArticleDAO.findAll(PageRequest.of(page,size,sort));
    }
}
