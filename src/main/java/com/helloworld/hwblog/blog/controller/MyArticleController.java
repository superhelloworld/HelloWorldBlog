package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.blog.service.MyArticlesService;
import com.helloworld.hwblog.common.model.PageModel;
import com.helloworld.hwblog.user.model.LoginModel;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xdzy on 17-5-22.
 */
@Controller
public class MyArticleController {
    @Autowired
    private MyArticlesService myArticlesService;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMyArticlesService(MyArticlesService myArticlesService) {
        this.myArticlesService = myArticlesService;
    }

    public String myArticle(){
        HttpServletRequest request= ServletActionContext.getRequest();
        LoginModel loginModel= (LoginModel) request.getSession().getAttribute("loginUser");
        if(loginModel==null) return "error";
        if(index==0) index=1;
        PageModel<ArticleItemModel> model=myArticlesService.getArticles(index,10,loginModel.getUsername());
        request.setAttribute("pageModel",model);
        return "success";
    }
}
