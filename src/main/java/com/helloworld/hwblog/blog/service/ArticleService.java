package com.helloworld.hwblog.blog.service;

import com.helloworld.hwblog.blog.entity.Article;
import com.helloworld.hwblog.blog.model.ArticleModel;
import com.helloworld.hwblog.blog.model.NewArticleModel;
import com.helloworld.hwblog.blog.model.UpdateModel;

/**
 * Created by xdzy on 17-5-13.
 */
public interface ArticleService {
    boolean addArticle(NewArticleModel model);
    ArticleModel getArticle(int aid);
    void deleteArticle(int id);
    void updateArticle(Article temp,UpdateModel model);
    Article getArticleTemp(int id);
}
