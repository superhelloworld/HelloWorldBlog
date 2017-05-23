package com.helloworld.hwblog.blog.service.impl;

import com.helloworld.hwblog.blog.dao.CommentDao;
import com.helloworld.hwblog.blog.entity.Comment;
import com.helloworld.hwblog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xdzy on 17-5-19.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(String username, String content, int aid) {
        Comment comment=new Comment(username,content,aid);
        commentDao.addComment(comment);
    }

    @Override
    public boolean deleteComment(int id) {
        return false;
    }

    @Override
    public List<Comment> getComments(int aid, int first, int count) {
        return commentDao.getCommentList(aid,first,count);
    }
}
