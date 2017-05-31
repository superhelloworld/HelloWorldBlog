package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.model.NewArticleModel;
import com.helloworld.hwblog.blog.service.ArticleService;
import com.helloworld.hwblog.blog.service.ArticleTypeService;
import com.helloworld.hwblog.user.model.LoginModel;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xdzy on 17-5-13.
 */
@Controller
public class AddArticleController implements ModelDriven<NewArticleModel>{
    @Autowired
    private ArticleService articleService;
    private NewArticleModel articleModel;
    @Autowired
    private ArticleTypeService articleTypeService;

    public void setArticleTypeService(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    public void setArticleModel(NewArticleModel articleModel) {
        this.articleModel = articleModel;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public NewArticleModel getModel() {
        if(articleModel==null) articleModel=new NewArticleModel();
        return articleModel;
    }

    public String toPage(){
        if(ServletActionContext.getRequest().getSession().getAttribute("loginUser")==null) return "error";
        ServletActionContext.getRequest().setAttribute("typeList",articleTypeService.getArticleTypeList());
        return "success";
    }

    public String save(){
        LoginModel model= (LoginModel) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        articleModel.setPublisher(model.getUsername());
        articleService.addArticle(articleModel);
        return "success";
    }
}
