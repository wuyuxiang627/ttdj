package com.connxun.ttdj.entity;

/**
 * Created by connxun-16 on 2018/1/3.
 */

public class PublishCardEntity {
        private String addr;
        private String cardid;
        private String categoryid;
        private String cname;
        private String company;
        private String content;
        private String couname;
        private String createtime;
        private String discount;
        private String isagree;
        private String name;
        private String pic;
        private String pname;
        private int price;
        private int pricemax;
        private int pricemin;
        private int recommendvalue;
        private String servicemode;
        private String state;
        private String updatetime;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCouname() {
        return couname;
    }

    public void setCouname(String couname) {
        this.couname = couname;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getIsagree() {
        return isagree;
    }

    public void setIsagree(String isagree) {
        this.isagree = isagree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPricemax() {
        return pricemax;
    }

    public void setPricemax(int pricemax) {
        this.pricemax = pricemax;
    }

    public int getPricemin() {
        return pricemin;
    }

    public void setPricemin(int pricemin) {
        this.pricemin = pricemin;
    }

    public int getRecommendvalue() {
        return recommendvalue;
    }

    public void setRecommendvalue(int recommendvalue) {
        this.recommendvalue = recommendvalue;
    }

    public String getServicemode() {
        return servicemode;
    }

    public void setServicemode(String servicemode) {
        this.servicemode = servicemode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "PublishCardEntity{" +
                "addr='" + addr + '\'' +
                ", cardid='" + cardid + '\'' +
                ", categoryid='" + categoryid + '\'' +
                ", cname='" + cname + '\'' +
                ", company='" + company + '\'' +
                ", content='" + content + '\'' +
                ", couname='" + couname + '\'' +
                ", createtime='" + createtime + '\'' +
                ", discount='" + discount + '\'' +
                ", isagree='" + isagree + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", pricemax=" + pricemax +
                ", pricemin=" + pricemin +
                ", recommendvalue=" + recommendvalue +
                ", servicemode='" + servicemode + '\'' +
                ", state='" + state + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
