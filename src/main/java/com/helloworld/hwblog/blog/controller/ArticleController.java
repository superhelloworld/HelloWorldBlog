package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.entity.Article;
import com.helloworld.hwblog.blog.model.ArticleModel;
import com.helloworld.hwblog.blog.model.UpdateModel;
import com.helloworld.hwblog.blog.service.ArticleService;
import com.helloworld.hwblog.blog.service.ArticleTypeService;
import com.helloworld.hwblog.blog.service.CommentService;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xdzy on 17-5-15.
 */
@Controller
public class ArticleController implements ModelDriven<UpdateModel>{
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    private String aid;
    private UpdateModel updateModel;
    @Autowired
    private ArticleTypeService articleTypeService;

    public void setArticleTypeService(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public void setUpdateModel(UpdateModel updateModel) {

        this.updateModel = updateModel;
    }

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

    public String delete(){
        int id;
        try {
            id=Integer.parseInt(aid);
        }catch (Exception e){
            return "success";
        }
        articleService.deleteArticle(id);
        return "success";
    }

    public String updatePage(){
        int id;
        try {
            id=Integer.parseInt(aid);
        }catch (Exception e){
            return "success";
        }
        Article article=articleService.getArticleTemp(id);
        ServletActionContext.getRequest().setAttribute("typeList",articleTypeService.getArticleTypeList());
        ServletActionContext.getRequest().getSession().setAttribute("articleTemp",article);
        return "success";
    }

    public String update(){
        Article article= (Article) ServletActionContext.getRequest().getSession().getAttribute("articleTemp");
        articleService.updateArticle(article,updateModel);
        return "success";
    }

    @Override
    public UpdateModel getModel() {
        if(updateModel==null){
            updateModel=new UpdateModel();
        }
        return updateModel;
    }
}
