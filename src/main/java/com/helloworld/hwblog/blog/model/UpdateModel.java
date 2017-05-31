package com.helloworld.hwblog.blog.model;

/**
 * Created by xdzy on 17-5-22.
 */
public class UpdateModel {
    private int type;
    private String title;
    private String content;
    private String tags;
    private int original;

    public UpdateModel(int type, String title, String content, String tags, int original) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.original = original;
    }

    public UpdateModel() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateModel that = (UpdateModel) o;

        if (type != that.type) return false;
        if (original != that.original) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return tags != null ? tags.equals(that.tags) : that.tags == null;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + original;
        return result;
    }
}
