package com.helloworld.hwblog.blog.dao.impl;

import com.helloworld.hwblog.blog.dao.ArticleDao;
import com.helloworld.hwblog.blog.entity.Article;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xdzy on 17-5-12.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addArticle(Article article) {
        sessionFactory.getCurrentSession().save(article);
    }

    @Override
    public Article getArticle(int id) {
        return sessionFactory.getCurrentSession().get(Article.class,id);
    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticle(int id) {

    }

    @Override
    public List<Article> getArticleList(int first, int count) {
        String hql="from Article order by id desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<Article> getArticleListByType(int first, int count, int type) {
        String hql="from Article as a where a.type=:t order by id desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setParameter("t",type);
        query.setFirstResult(first);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<Article> getArticleList_Hot(int first, int count) {
        String hql="from Article order by readCount desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<Article> getArticleListByType_Hot(int first, int count, int type) {
        String hql="from Article as a where a.type=:t order by readCount desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setParameter("t",type);
        query.setFirstResult(first);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<Article> getArticleListByOwner(int first, int count,String username) {
        String hql="from Article a where a.publisher=:u order by readCount desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        query.setParameter("u",username);
        return query.list();
    }

    @Override
    public List<Article> getArticleListByKeyWord(int first, int count, String keyWord) {
        String hql="from Article a where a.title like :k or a.content like :k or a.tags like :k order by id desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        query.setParameter("k","%"+keyWord+"%");
        return query.list();
    }

    @Override
    public List<Article> getArticleListByKeyWord(int first, int count, int type, String keyWord) {
        String hql="from Article a where (a.title like :k or a.content like :k or a.tags like :k) and a.type=:t order by id desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        query.setParameter("k","%"+keyWord+"%");
        query.setParameter("t",type);
        return query.list();
    }

    @Override
    public List<Article> getArticleListByKeyWord_Hot(int first, int count, String keyWord) {
        String hql="from Article a where a.title like :k or a.content like :k or a.tags like :k order by readCount desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        query.setParameter("k","%"+keyWord+"%");
        return query.list();
    }

    @Override
    public List<Article> getArticleListByKeyWord_Hot(int first, int count, int type, String keyWord) {
        String hql="from Article a where (a.title like :k or a.content like :k or a.tags like :k) and a.type=:t order by readCount desc";
        Query<Article> query=sessionFactory.getCurrentSession().createQuery(hql,Article.class);
        query.setFirstResult(first);
        query.setMaxResults(count);
        query.setParameter("k","%"+keyWord+"%");
        query.setParameter("t",type);
        return query.list();
    }

    @Override
    public int getDataCount() {
        String hql="select count(*) from Article";
        Query<Long> query=sessionFactory.getCurrentSession().createQuery(hql,Long.class);
        return query.uniqueResult().intValue();
    }

    @Override
    public int getDataCount(int type) {
        String hql="select count(*) from Article a where a.type=:t";
        Query<Long> query=sessionFactory.getCurrentSession().createQuery(hql,Long.class);
        query.setParameter("t",type);
        return query.uniqueResult().intValue();
    }

    @Override
    public int getDataCount(String keyWord) {
        String hql="select count(*) from Article a where a.title like :k or a.content like :k or a.tags like :k";
        Query<Long> query=sessionFactory.getCurrentSession().createQuery(hql,Long.class);
        query.setParameter("k","%"+keyWord+"%");
        return query.uniqueResult().intValue();
    }

    @Override
    public int getDataCount(int type, String keyWord) {
        String hql="select count(*) from Article a where (a.title like :k or a.content like :k or a.tags like :k) and a.type=:t";
        Query<Long> query=sessionFactory.getCurrentSession().createQuery(hql,Long.class);
        query.setParameter("t",type);
        query.setParameter("k","%"+keyWord+"%");
        return query.uniqueResult().intValue();
    }

    @Override
    public void addReadCount(int id) {
        Article article=sessionFactory.getCurrentSession().get(Article.class,id);
        int c=article.getReadCount();
        article.setReadCount(c+1);
        sessionFactory.getCurrentSession().save(article);
    }
}
