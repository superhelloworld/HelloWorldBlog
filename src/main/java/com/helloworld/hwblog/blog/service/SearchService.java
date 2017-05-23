package com.helloworld.hwblog.blog.service;

import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.common.model.PageModel;

/**
 * Created by xdzy on 17-5-17.
 */
public interface SearchService {
    PageModel<ArticleItemModel> search(String keyWord, String type, int order, int index, int count);
}
