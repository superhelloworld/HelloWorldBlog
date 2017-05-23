package com.helloworld.hwblog.common.model;

import java.util.List;

/**
 * Created by xdzy on 17-5-13.
 */
public class PageModel<T> {
    private String pageType;
    private String pageName;
    private List<T> items;
    private int allItemCount;
    private int itemsCount;
    private int index;
    private int order;
    private String keyWord;
    public PageModel() {
    }

    public PageModel(String pageType, int itemsCount, int index,int order) {
        this.pageType = pageType;
        this.itemsCount = itemsCount;
        this.index = index;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getAllItemCount() {
        return allItemCount;
    }

    public void setAllItemCount(int allItemCount) {
        this.allItemCount = allItemCount;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getAllPageCount(){
        int c=allItemCount/itemsCount;
        if(allItemCount%itemsCount!=0) c+=1;
        return c;
    }
}
