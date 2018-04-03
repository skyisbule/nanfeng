package com.nanfenggongxiang.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel("商品的留言信息")
public class Message implements Serializable {
    /**
     * 留言id
     */
    private Integer mid;

    /**
     * 写这条留言的用户id
     */
    @ApiModelProperty("写这条留言的用户id")
    private Integer releaser;

    /**
     * 接收留言者的id
     */
    @ApiModelProperty("接收留言者的id")
    private Integer receiver;

    /**
     * 留言内容
     */
    @ApiModelProperty("留言内容")
    @Size(max = 600)
    private String content;

    /**
     * 是否已读
     */
    private Integer isReaded;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Integer gid;

    private static final long serialVersionUID = 1L;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getReleaser() {
        return releaser;
    }

    public void setReleaser(Integer releaser) {
        this.releaser = releaser;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
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
        Message other = (Message) that;
        return (this.getMid() == null ? other.getMid() == null : this.getMid().equals(other.getMid()))
            && (this.getReleaser() == null ? other.getReleaser() == null : this.getReleaser().equals(other.getReleaser()))
            && (this.getReceiver() == null ? other.getReceiver() == null : this.getReceiver().equals(other.getReceiver()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getIsReaded() == null ? other.getIsReaded() == null : this.getIsReaded().equals(other.getIsReaded()))
            && (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMid() == null) ? 0 : getMid().hashCode());
        result = prime * result + ((getReleaser() == null) ? 0 : getReleaser().hashCode());
        result = prime * result + ((getReceiver() == null) ? 0 : getReceiver().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getIsReaded() == null) ? 0 : getIsReaded().hashCode());
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mid=").append(mid);
        sb.append(", releaser=").append(releaser);
        sb.append(", receiver=").append(receiver);
        sb.append(", content=").append(content);
        sb.append(", isReaded=").append(isReaded);
        sb.append(", gid=").append(gid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}