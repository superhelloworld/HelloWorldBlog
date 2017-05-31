package com.helloworld.hwblog.blog.service.impl;

import com.helloworld.hwblog.blog.dao.ArticleDao;
import com.helloworld.hwblog.blog.dao.ArticleTypeDao;
import com.helloworld.hwblog.blog.entity.Article;
import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.blog.service.MyArticlesService;
import com.helloworld.hwblog.common.model.PageModel;
import com.helloworld.hwblog.user.dao.UserInfoDao;
import com.helloworld.hwblog.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xdzy on 17-5-22.
 */
@Service
public class MyArticlesServiceImpl implements MyArticlesService{
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTypeDao articleTypeDao;
    @Autowired
    private UserInfoDao userInfoDao;

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
        this.articleTypeDao = articleTypeDao;
    }


    @Override
    public PageModel<ArticleItemModel> getArticles(int index, int count, String username) {
        PageModel<ArticleItemModel> page=new PageModel<>();
        page.setIndex(index);
        page.setItemsCount(count);
        page.setPageName("我的文章");
        List<ArticleItemModel> list=new ArrayList<>();
        List<Article> articles=null;
        articles=articleDao.getArticleListByOwner((index-1)*count,count,username);
        page.setAllItemCount(articleDao.getDataCountByOwner(username));
        for(Article a:articles){
            list.add(makeItem(a));
        }
        page.setItems(list);
        return page;
    }

    public ArticleItemModel makeItem(Article a){
        ArticleItemModel articleItemModel=new ArticleItemModel();
        articleItemModel.setaId(a.getId());
        articleItemModel.setTitle(a.getTitle());
        articleItemModel.setDate(a.getDate());
        articleItemModel.setReadCount(a.getReadCount());
        articleItemModel.setTags(a.getTags());
        String content=a.getContent();
        articleItemModel.setDetail(makeDeatilContent(content));
        return articleItemModel;
    }

    public String makeDeatilContent(String content){
        String regEx_html="<[^>]+>";
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(content);
        String detail=m_html.replaceAll("");
        if(detail.length()>105) return detail.substring(0,105);
        return detail.trim();
    }
}
