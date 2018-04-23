package com.nanfenggongxiang.Domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class News implements Serializable {
    /**
     * 资讯id
     */
    private Integer nid;

    /**
     * 标题
     */
    private String title;

    /**
     * 资讯内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date releaseTime;

    /**
     * 所属类别
     */
    private Integer classification;

    private Integer isTop;

    private static final long serialVersionUID = 1L;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
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

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
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
        News other = (News) that;
        return (this.getNid() == null ? other.getNid() == null : this.getNid().equals(other.getNid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReleaseTime() == null ? other.getReleaseTime() == null : this.getReleaseTime().equals(other.getReleaseTime()))
            && (this.getClassification() == null ? other.getClassification() == null : this.getClassification().equals(other.getClassification()))
            && (this.getIsTop() == null ? other.getIsTop() == null : this.getIsTop().equals(other.getIsTop()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNid() == null) ? 0 : getNid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReleaseTime() == null) ? 0 : getReleaseTime().hashCode());
        result = prime * result + ((getClassification() == null) ? 0 : getClassification().hashCode());
        result = prime * result + ((getIsTop() == null) ? 0 : getIsTop().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nid=").append(nid);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", classification=").append(classification);
        sb.append(", isTop=").append(isTop);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}