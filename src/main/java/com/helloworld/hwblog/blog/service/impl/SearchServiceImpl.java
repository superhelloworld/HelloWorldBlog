package com.helloworld.hwblog.blog.service.impl;

import com.helloworld.hwblog.blog.dao.ArticleDao;
import com.helloworld.hwblog.blog.dao.ArticleTypeDao;
import com.helloworld.hwblog.blog.entity.Article;
import com.helloworld.hwblog.blog.entity.ArticleType;
import com.helloworld.hwblog.blog.model.ArticleItemModel;
import com.helloworld.hwblog.blog.service.SearchService;
import com.helloworld.hwblog.common.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xdzy on 17-5-17.
 */
@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTypeDao articleTypeDao;
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
        this.articleTypeDao = articleTypeDao;
    }
    @Override
    public PageModel<ArticleItemModel> search(String keyWord, String type, int order, int index, int count) {
        try {
            keyWord= URLDecoder.decode(keyWord,"utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        if(keyWord.length()>35) keyWord=keyWord.substring(0,35);
        PageModel<ArticleItemModel> page=new PageModel<>();
        page.setIndex(index);
        page.setItemsCount(count);
        page.setPageType(type);
        page.setOrder(order);
        page.setKeyWord(keyWord);
        List<ArticleItemModel> list=new ArrayList<>();
        List<Article> articles=null;
        if(type.equals("all")){
            articles=order==0?articleDao.getArticleListByKeyWord((index-1)*count,count,keyWord):articleDao.getArticleListByKeyWord_Hot((index-1)*count,count,keyWord);
            page.setAllItemCount(articleDao.getDataCount(keyWord));
            page.setPageName("全部");
        }else{
            ArticleType articleType=articleTypeDao.getArticleType(type);
            if(articleType==null) return null;
            articles=order==0?articleDao.getArticleListByKeyWord((index-1)*count,count,articleType.getId(),keyWord):articleDao.getArticleListByKeyWord_Hot((index-1)*count,count,articleType.getId(),keyWord);
            page.setPageName(articleType.getTypeName());
            page.setAllItemCount(articleDao.getDataCount(articleType.getId(),keyWord));
        }
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
        articleItemModel.setPublisher("相当专业x");
        articleItemModel.setPubnisherIcon("system_man.jpg");
        return articleItemModel;
    }

    public String makeDeatilContent(String content){
        String regEx_html="<[^>]+>";
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(content);
        String detail=m_html.replaceAll("");
        if(detail.length()>101) return detail.substring(0,101);
        return detail.trim();
    }
}
