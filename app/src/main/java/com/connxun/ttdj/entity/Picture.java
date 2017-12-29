package com.connxun.ttdj.entity;

/**
 * Created by zhuchenxi on 16/6/1.
 */

public class Picture {
    int width;
    int height;
    String src;

    public Picture(int width, int height, String src) {
        this.width = width;
        this.height = height;
        this.src = src;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "width=" + width +
                ", height=" + height +
                ", src='" + src + '\'' +
                '}';
    }
}
