package com.helloworld.hwblog.blog.dao.impl;

import com.helloworld.hwblog.blog.dao.CommentDao;
import com.helloworld.hwblog.blog.entity.Comment;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xdzy on 17-5-19.
 */
@Repository
public class CommentDaoImpl implements CommentDao{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public void deleteComment(int id) {
        Comment comment=getComment(id);
        sessionFactory.getCurrentSession().delete(comment);
    }

    @Override
    public Comment getComment(int id) {
        return sessionFactory.getCurrentSession().get(Comment.class,id);
    }

    @Override
    public List<Comment> getCommentList(int aid, int first, int count) {
        String hql="from Comment as c where c.aid=:aid order by id desc";
        Query<Comment> query=sessionFactory.getCurrentSession().createQuery(hql,Comment.class);
        query.setParameter("aid",aid);
        query.setFirstResult(first);
        query.setMaxResults(count);
        return query.list();
    }
}
