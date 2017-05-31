package com.helloworld.hwblog.blog.controller;

import com.helloworld.hwblog.blog.service.CommentService;
import com.helloworld.hwblog.user.model.LoginModel;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by xdzy on 17-5-19.
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    private int aid;
    private String content;
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String addComment(){
        LoginModel model= (LoginModel) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        if(model==null) return "error";
        String username=model.getUsername();
        commentService.addComment(username,content,aid);
        return "success";
    }
}
