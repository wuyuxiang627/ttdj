package com.connxun.ttdj.entity;

import java.io.Serializable;

/**
 * @Author anna
 * @Date 2017-12-01 17:01
 * @Descprition banner轮播图
 */

public class Carousel implements Serializable {

    private String carouselid;
    private String picurl;
    private String href;
    private String intervaltime;
    private String state;   //0 未修改 1 已修改
    private Long carousevalue;
    private String type;


    public String getCarouselid() {
        return carouselid;
    }

    public void setCarouselid(String carouselid) {
        this.carouselid = carouselid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIntervaltime() {
        return intervaltime;
    }

    public void setIntervaltime(String intervaltime) {
        this.intervaltime = intervaltime;
    }

    public Long getCarousevalue() {
        return carousevalue;
    }

    public void setCarousevalue(Long carousevalue) {
        this.carousevalue = carousevalue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "carouselid='" + carouselid + '\'' +
                ", picurl='" + picurl + '\'' +
                ", href='" + href + '\'' +
                ", intervaltime='" + intervaltime + '\'' +
                ", state='" + state + '\'' +
                ", carousevalue=" + carousevalue +
                ", type='" + type + '\'' +
                '}';
    }
}
