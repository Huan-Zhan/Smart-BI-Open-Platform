package com.user.center.example.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户图标总表
 * @TableName usercharts
 */
@TableName(value ="usercharts")
@Data
public class Usercharts implements Serializable {
    public Usercharts( String userName, String userId, String chartName, String analysisAim, String chartType) {
        this.userName = userName;
        this.userId = userId;
        this.chartName = chartName;
        this.analysisAim = analysisAim;
        this.chartType = chartType;
    }

    public Usercharts() {
    }

    /**
     * 
     */

    @TableId(value = "Id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建表时的用户名
     */
    @TableField(value = "UserName")
    private String userName;

    /**
     * 用户唯一id , 八位有效数字
     */
    @TableField(value = "UserId")
    private String userId;

    /**
     * 自动添加
     */
    @TableField(value = "CreateDate")
    private Date createDate;

    /**
     * 图标名称
     */
    @TableField(value = "ChartName")
    private String chartName;

    /**
     * 
     */
    @TableField(value = "AnalysisAim")
    private String analysisAim;

    /**
     * 
     */
    @TableField(value = "ChartType")
    private String chartType;

    /**
     * 0-正常
1-删除
     */
    @TableField(value = "IsDelete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        Usercharts other = (Usercharts) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getChartName() == null ? other.getChartName() == null : this.getChartName().equals(other.getChartName()))
            && (this.getAnalysisAim() == null ? other.getAnalysisAim() == null : this.getAnalysisAim().equals(other.getAnalysisAim()))
            && (this.getChartType() == null ? other.getChartType() == null : this.getChartType().equals(other.getChartType()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getChartName() == null) ? 0 : getChartName().hashCode());
        result = prime * result + ((getAnalysisAim() == null) ? 0 : getAnalysisAim().hashCode());
        result = prime * result + ((getChartType() == null) ? 0 : getChartType().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userId=").append(userId);
        sb.append(", createDate=").append(createDate);
        sb.append(", chartName=").append(chartName);
        sb.append(", analysisAim=").append(analysisAim);
        sb.append(", chartType=").append(chartType);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}