package com.nanfenggongxiang.Domain;

import java.io.Serializable;

/**
 * @author 
 */
public class Plate implements Serializable {
    private Integer plateId;

    /**
     * 版面名字
     */
    private String plateName;

    private static final long serialVersionUID = 1L;

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
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
        Plate other = (Plate) that;
        return (this.getPlateId() == null ? other.getPlateId() == null : this.getPlateId().equals(other.getPlateId()))
            && (this.getPlateName() == null ? other.getPlateName() == null : this.getPlateName().equals(other.getPlateName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlateId() == null) ? 0 : getPlateId().hashCode());
        result = prime * result + ((getPlateName() == null) ? 0 : getPlateName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", plateId=").append(plateId);
        sb.append(", plateName=").append(plateName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}