package com.helloworld.hwblog.blog.service.impl;

import com.helloworld.hwblog.blog.dao.ArticleDao;
import com.helloworld.hwblog.blog.dao.ArticleTypeDao;
import com.helloworld.hwblog.blog.entity.Article;
import com.helloworld.hwblog.blog.entity.ArticleType;
import com.helloworld.hwblog.blog.model.ArticleModel;
import com.helloworld.hwblog.blog.model.NewArticleModel;
import com.helloworld.hwblog.blog.model.UpdateModel;
import com.helloworld.hwblog.blog.service.ArticleService;
import com.helloworld.hwblog.user.dao.UserInfoDao;
import com.helloworld.hwblog.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xdzy on 17-5-13.
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTypeDao articleTypeDao;


    public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
        this.articleTypeDao = articleTypeDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public boolean addArticle(NewArticleModel model) {
        if(!model.validate()) return false;
        Article article=new Article(model.getTitle(),model.getType(),model.getTags(),model.getPublisher(),model.getOriginal(),model.getContent(),0);
        articleDao.addArticle(article);
        return true;
    }

    @Override
    public ArticleModel getArticle(int aid) {
        Article article=articleDao.getArticle(aid);
        if(article==null) return null;
        articleDao.addReadCount(aid);
        ArticleType articleType=articleTypeDao.getArticleType(article.getType());
        ArticleModel model=new ArticleModel(article.getTitle(),articleType.getTypeName(),article.getTags(),
                article.getPublisher(),article.getOriginal(),article.getContent(),article.getDate(),article.getReadCount()+1);
        return model;
    }

    @Override
    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }

    @Override
    public void updateArticle(Article temp,UpdateModel model) {
        temp.setType(model.getType());
        temp.setOriginal(model.getOriginal());
        temp.setTitle(model.getTitle());
        temp.setContent(model.getContent());
        temp.setTags(temp.getTags());
        articleDao.updateArticle(temp);
    }

    @Override
    public Article getArticleTemp(int id) {
        return articleDao.getArticle(id);
    }


}
