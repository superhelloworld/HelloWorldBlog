package com.helloworld.hwblog.blog.service;

import com.helloworld.hwblog.blog.entity.Comment;

import java.util.List;

/**
 * Created by xdzy on 17-5-19.
 */
public interface CommentService {
    void addComment(String username,String content,int aid);
    boolean deleteComment(int id);
    List<Comment> getComments(int aid,int first,int count);
}
