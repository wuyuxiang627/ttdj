package com.connxun.ttdj.entity;

import java.io.Serializable;

/**
 * @Author anna
 * @Date 2017-12-01 17:14
 * @Description 首页小菜单：二级服务
 */
public class CategorySub implements Serializable {

    private String categoryid;
    private String name;
    private String picurl;
    private String centerpicurl;

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getCenterpicurl() {
        return centerpicurl;
    }

    public void setCenterpicurl(String centerpicurl) {
        this.centerpicurl = centerpicurl;
    }

    @Override
    public String toString() {
        return name;
    }
}
