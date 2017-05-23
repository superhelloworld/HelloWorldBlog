package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.blog.service.SearchService;
import com.helloworld.hwblog.common.model.PageModel;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xdzy on 17-5-17.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    private String type;
    private int index;
    private int order;
    private String keyWord;
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String toPage(){
        //System.out.println(type+" "+index+" "+order+" "+keyWord);
        PageModel<ArticleItemModel> result=searchService.search(keyWord,type,order,index,10);
        ServletActionContext.getRequest().setAttribute("result",result);
        return "success";
    }
}
