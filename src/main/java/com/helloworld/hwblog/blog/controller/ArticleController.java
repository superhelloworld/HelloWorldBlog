package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.model.ArticleModel;
import com.helloworld.hwblog.blog.service.ArticleService;
import com.helloworld.hwblog.blog.service.CommentService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xdzy on 17-5-15.
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    private String aid;

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String showArticle(){
        int id;
        try {
            id=Integer.parseInt(aid);
        }catch (Exception e){
            return "error";
        }
        ArticleModel articleModel=articleService.getArticle(id);
        if(articleModel==null) return "error";
        articleModel.setPublisher("相当专业");
        ServletActionContext.getRequest().setAttribute("aid",aid);
        ServletActionContext.getRequest().setAttribute("article",articleModel);
        ServletActionContext.getRequest().setAttribute("comments",commentService.getComments(id,0,10));
        return "success";
    }
}
