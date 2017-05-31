package com.helloworld.hwblog.blog.test;

import com.helloworld.hwblog.blog.dao.ArticleDao;
import com.helloworld.hwblog.blog.dao.ArticleTypeDao;
import com.helloworld.hwblog.blog.dao.CommentDao;
import com.helloworld.hwblog.blog.entity.Article;
import com.helloworld.hwblog.blog.entity.ArticleType;
import com.helloworld.hwblog.blog.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

/**
 * Created by xdzy on 17-5-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/rootconfig.xml")
@ActiveProfiles("dev")
public class TestDao {
    @Autowired
    private ArticleTypeDao articleTypeDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    @Test
    public void test1(){

    }

    @Test
    public void test2(){
        for(int i=0;i<31;i++){
            Article article=new Article("测试文章x"+i,1,"测试标签",
                    "xdzy",1,"测试文章内容"+i,0);
            articleDao.addArticle(article);
        }
        //System.out.println(articleDao.getArticle(1));
       // System.out.println(articleDao.getArticleList(0,2));
        //System.out.println(articleDao.getArticleListByType(0,2,"1-"));
    }

    @Test
    public void test3(){
        Comment comment=new Comment("xdzy12421","测试评论",1);
        commentDao.addComment(comment);
    }
}
