package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.blog.service.ArticleTypeService;
import com.helloworld.hwblog.blog.service.PageService;
import com.helloworld.hwblog.common.model.PageModel;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by xdzy on 17-5-13.
 */
@Controller
public class ArticlesController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private PageService pageService;

    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    private String type;
    private int index;
    private int order;
    public void setArticleTypeService(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String toPage(){
        HttpServletRequest request= ServletActionContext.getRequest();
        request.setAttribute("typeList",articleTypeService.getArticleTypeList());
        PageModel<ArticleItemModel> model=pageService.getPage(type,index,10,order);
        if(model==null) return "error";
        request.setAttribute("pageModel",model);
        return "success";
    }

    public String myArticles(){

        return "success";
    }
}
