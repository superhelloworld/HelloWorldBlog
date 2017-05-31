package com.helloworld.hwblog.blog.dao;

import com.helloworld.hwblog.blog.entity.Article;

import java.util.List;

/**
 * Created by xdzy on 17-5-12.
 */
public interface ArticleDao {
    void addArticle(Article article);
    Article getArticle(int id);
    void updateArticle(Article article);
    void deleteArticle(int id);
    List<Article> getArticleList(int first,int count);
    List<Article> getArticleListByType(int first,int count,int type);
    List<Article> getArticleList_Hot(int first,int count);
    List<Article> getArticleListByType_Hot(int first,int count,int type);
    List<Article> getArticleListByOwner(int first,int count,String username);
    List<Article> getArticleListByKeyWord(int first,int count,String keyWord);
    List<Article> getArticleListByKeyWord(int first,int count,int type,String keyWord);
    List<Article> getArticleListByKeyWord_Hot(int first,int count,String keyWord);
    List<Article> getArticleListByKeyWord_Hot(int first,int count,int type,String keyWord);
    int getDataCount();
    int getDataCount(int type);
    int getDataCount(String keyWord);
    int getDataCount(int type,String keyWord);
    int getDataCountByOwner(String owner);
    void addReadCount(int id);
}
