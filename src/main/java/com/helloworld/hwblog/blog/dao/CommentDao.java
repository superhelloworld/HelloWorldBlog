package com.helloworld.hwblog.blog.dao;

import com.helloworld.hwblog.blog.entity.Comment;

import java.util.List;

/**
 * Created by xdzy on 17-5-19.
 */
public interface CommentDao {
    void addComment(Comment comment);
    void deleteComment(int id);
    Comment getComment(int id);
    List<Comment> getCommentList(int aid,int first,int count);
}
