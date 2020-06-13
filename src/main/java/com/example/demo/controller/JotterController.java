package com.example.demo.controller;

import com.example.demo.Result.Result;
import com.example.demo.Result.ResultFactory;
import com.example.demo.pojo.JotterArticle;
import com.example.demo.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JotterController {
    @Autowired
    JotterArticleService jotterArticleService;
    @PostMapping(path = "/content/article")
    @ResponseBody
    public Result saveArticle(@RequestBody JotterArticle article){
        System.out.println("saveArticle()");
        System.out.println(article.getArticleabstract());
        System.out.println(article);
        jotterArticleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }
    @GetMapping("/api/article/{size}/{page}")
    @ResponseBody
    public Page listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return jotterArticleService.list(page - 1, size);
    }
    @GetMapping("/api/articletest/{size}/{page}")
    @ResponseBody
    public Result articleTest(@PathVariable("size") int size,@PathVariable("page") int page){
        System.out.println("articleTest"+size+page);
        return ResultFactory.buildSuccessResult("testSuccess");
    }
    @GetMapping("/api/article/{id}")
    @ResponseBody
    public JotterArticle getOneArticle(@PathVariable("id") int id) {
        return jotterArticleService.findById(id);
    }
    @DeleteMapping("/content/article/{id}")
    @ResponseBody
    public Result deleteArticle(@PathVariable("id") int id) {
        jotterArticleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
