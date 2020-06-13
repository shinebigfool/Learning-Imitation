package com.example.demo.controller;

import com.example.demo.Result.loginResult;
import com.example.demo.pojo.Book;
import com.example.demo.service.BookService;
import com.example.demo.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    BookService bookService;
    //@CrossOrigin
    @GetMapping(path = "/api/books")
    @ResponseBody
    public List<Book> list(){
        System.out.println("LibraryController:ListBooks");
        return bookService.list();
    }
   // @CrossOrigin
    @PostMapping(path = "/api/savebooks")
    @ResponseBody
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        System.out.println("savebooks");
        bookService.addOrUpdate(book);
        return book;
    }
    //@CrossOrigin
    @RequiresPermissions("/api/admin/content/books/delete")
    @PostMapping(path = "/api/delete")
    @ResponseBody
    public loginResult delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
        return new loginResult(200);
    }
    //@CrossOrigin
    @GetMapping("/api/categories/{cid}/books")
    @ResponseBody
    public List<Book> listByCategory(@PathVariable("cid") int cid)throws  Exception{
        System.out.println(cid);
        System.out.println("/api/categories/{cid}/books");
        if(0!=cid){
            System.out.println(bookService.listByCategory(cid).get(0));
            return bookService.listByCategory(cid);
        }else {
            System.out.println(list());
            return list();
        }
    }
    //@CrossOrigin
    @GetMapping("/api/search")
    @ResponseBody
    public List<Book> searchResult(@RequestParam("keywords") String keywords){
        if("".equals(keywords)){
            return bookService.list();
        }
        else {
            return bookService.Search(keywords);
        }
    }
    //@CrossOrigin
    @PostMapping("/api/covers")
    @ResponseBody
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/pic";
        System.out.println("connect success");
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8088/api/file/" + f.getName();
            System.out.println(imgURL);
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
