package com.helloworld.hwblog.blog.service;

import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.common.model.PageModel;

/**
 * Created by xdzy on 17-5-22.
 */
public interface MyArticlesService {
    PageModel<ArticleItemModel> getArticles(int index,int count,String username);
}
