package com.nanfenggongxiang.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Commodity implements Serializable {
    /**
     * 商品id
     */
    private Integer gid;

    /**
     * 发布者id
     */
    private Integer uid;

    /**
     * 商品名字
     */
    private String goodsName;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 成色
     */
    private Integer condition;

    /**
     * 详细描述
     */
    private String content;

    /**
     * 存图片的url
     */
    private String picture;

    /**
     * 商品的类型
     */
    private Integer goodsType;

    /**
     * 浏览量
     */
    private Integer pageViews;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 是否卖出
     */
    private Integer isSellOut;

    /**
     * 是否是想买
     */
    private Integer isWantBy;

    private static final long serialVersionUID = 1L;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getIsSellOut() {
        return isSellOut;
    }

    public void setIsSellOut(Integer isSellOut) {
        this.isSellOut = isSellOut;
    }

    public Integer getIsWantBy() {
        return isWantBy;
    }

    public void setIsWantBy(Integer isWantBy) {
        this.isWantBy = isWantBy;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Commodity other = (Commodity) that;
        return (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCondition() == null ? other.getCondition() == null : this.getCondition().equals(other.getCondition()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getGoodsType() == null ? other.getGoodsType() == null : this.getGoodsType().equals(other.getGoodsType()))
            && (this.getPageViews() == null ? other.getPageViews() == null : this.getPageViews().equals(other.getPageViews()))
            && (this.getReleaseTime() == null ? other.getReleaseTime() == null : this.getReleaseTime().equals(other.getReleaseTime()))
            && (this.getIsSellOut() == null ? other.getIsSellOut() == null : this.getIsSellOut().equals(other.getIsSellOut()))
            && (this.getIsWantBy() == null ? other.getIsWantBy() == null : this.getIsWantBy().equals(other.getIsWantBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCondition() == null) ? 0 : getCondition().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getGoodsType() == null) ? 0 : getGoodsType().hashCode());
        result = prime * result + ((getPageViews() == null) ? 0 : getPageViews().hashCode());
        result = prime * result + ((getReleaseTime() == null) ? 0 : getReleaseTime().hashCode());
        result = prime * result + ((getIsSellOut() == null) ? 0 : getIsSellOut().hashCode());
        result = prime * result + ((getIsWantBy() == null) ? 0 : getIsWantBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gid=").append(gid);
        sb.append(", uid=").append(uid);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", price=").append(price);
        sb.append(", condition=").append(condition);
        sb.append(", content=").append(content);
        sb.append(", picture=").append(picture);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", pageViews=").append(pageViews);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", isSellOut=").append(isSellOut);
        sb.append(", isWantBy=").append(isWantBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}