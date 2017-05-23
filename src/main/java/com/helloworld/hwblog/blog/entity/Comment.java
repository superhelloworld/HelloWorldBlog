package com.helloworld.hwblog.blog.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by xdzy on 17-5-19.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(generator = "cid")
    @GenericGenerator(name="cid",strategy = "native")
    private int id;
    private String commenter;
    private String content;
    private Date date;
    private int aid;

    public Comment() {
    }

    public Comment(String commenter, String content,int aid) {
        this.commenter = commenter;
        this.content = content;
        this.aid = aid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commenter='" + commenter + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", aid=" + aid +
                '}';
    }
}
