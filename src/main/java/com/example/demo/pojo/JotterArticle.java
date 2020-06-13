package com.example.demo.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity(name="jotter_article")
public class JotterArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "articletitle")
    private String articletitle;
    @Column(name = "articlecontenthtml")
    private String articlecontenthtml;
    @Column(name = "articlecontentmd")
    private String articlecontentmd;
    @Column(name = "articleabstract")
    private String articleabstract;
    @Column(name = "articlecover")
    private String articlecover;
    @Column(name = "articledate")
    private Date articledate;

    @Override
    public String toString() {
        return "JotterArticle{" +
                "id=" + id +
                ", articletitle='" + articletitle + '\'' +
                ", articlecontenthtml='" + articlecontenthtml + '\'' +
                ", articlecontentmd='" + articlecontentmd + '\'' +
                ", articleabstract='" + articleabstract + '\'' +
                ", articlecover='" + articlecover + '\'' +
                ", articledate=" + articledate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticlecontenthtml() {
        return articlecontenthtml;
    }

    public void setArticlecontenthtml(String articlecontenthtml) {
        this.articlecontenthtml = articlecontenthtml;
    }

    public String getArticlecontentmd() {
        return articlecontentmd;
    }

    public void setArticlecontentmd(String articlecontentmd) {
        this.articlecontentmd = articlecontentmd;
    }

    public String getArticleabstract() {
        return articleabstract;
    }

    public void setArticleabstract(String articleabstract) {
        this.articleabstract = articleabstract;
    }

    public String getArticlecover() {
        return articlecover;
    }

    public void setArticlecover(String articlecover) {
        this.articlecover = articlecover;
    }

    public Date getArticledate() {
        return articledate;
    }

    public void setArticledate(Date articledate) {
        this.articledate = articledate;
    }
}
