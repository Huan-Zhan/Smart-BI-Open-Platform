package com.user.center.example.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * api项目的用户信息表
 * @TableName apicenter
 */
@TableName(value ="apicenter")
@Data
public class Apicenter implements Serializable {
    /**
     * 
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long ID;

    /**
     * 用户账号
     */
    @TableField(value = "UserName")
    private String userName;

    /**
     * 用户密码
     */
    @TableField(value = "UserPassword")
    private String userPassword;

    /**
     * 密钥账号
     */
    @TableField(value = "AccessKey")
    private String accessKey;

    /**
     * 密钥密码
     */
    @TableField(value = "SecretKey")
    private String secretKey;

    /**
     * 头像地址
     */
    @TableField(value = "AvatarUrl")
    private String avatarUrl;

    /**
     * 电话号码
     */
    @TableField(value = "Phone")
    private String phone;

    /**
     * 定义自定义算法 ，将当前时间点 转化为秒 ，存入数据库
     */
    @TableField(value = "LastDate")
    private Long lastDate;

    /**
     * 随机数
     */
    @TableField(value = "NoneNumber")
    private Integer noneNumber;

    /**
     * 0 存在 ，1 删除
     */
    @TableField(value = "IsDelete")
    private Integer isDelete;

    /**
     * 0 女 ， 1 男
     */
    @TableField(value = "gender")
    private Integer gender;

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
        Apicenter other = (Apicenter) that;
        return (this.getID() == null ? other.getID() == null : this.getID().equals(other.getID()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getAccessKey() == null ? other.getAccessKey() == null : this.getAccessKey().equals(other.getAccessKey()))
            && (this.getSecretKey() == null ? other.getSecretKey() == null : this.getSecretKey().equals(other.getSecretKey()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getLastDate() == null ? other.getLastDate() == null : this.getLastDate().equals(other.getLastDate()))
            && (this.getNoneNumber() == null ? other.getNoneNumber() == null : this.getNoneNumber().equals(other.getNoneNumber()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getID() == null) ? 0 : getID().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getAccessKey() == null) ? 0 : getAccessKey().hashCode());
        result = prime * result + ((getSecretKey() == null) ? 0 : getSecretKey().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getLastDate() == null) ? 0 : getLastDate().hashCode());
        result = prime * result + ((getNoneNumber() == null) ? 0 : getNoneNumber().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ID=").append(ID);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", accessKey=").append(accessKey);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", phone=").append(phone);
        sb.append(", lastDate=").append(lastDate);
        sb.append(", noneNumber=").append(noneNumber);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", gender=").append(gender);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}