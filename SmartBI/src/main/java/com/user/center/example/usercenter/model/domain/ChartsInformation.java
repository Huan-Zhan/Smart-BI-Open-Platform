package com.user.center.example.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ChartsInformation {

    public ChartsInformation() {
    }

    public ChartsInformation(Long id ,Long userId,  String chartAnalysis, String chartCode ,int chartStatus) {
        this.id = id ;
        this.userId = userId;
        this.chartAnalysis = chartAnalysis ;
        this.chartCode = chartCode;
        this.chartStatus = chartStatus ;
    }

    public ChartsInformation(String userName, Long userId, String fileInformation, String chartName, String analysisAim, String chartType, String chartCode ,String chartAnalysis ) {
        this.userName = userName;
        this.userId = userId;
        this.fileInformation = fileInformation;
        this.chartName = chartName;
        this.analysisAim = analysisAim;
        this.chartType = chartType;
        this.chartCode = chartCode;
        this.chartAnalysis = chartAnalysis;
    }
    public ChartsInformation(String userName, Long userId, String fileInformation, String chartName, String analysisAim, String chartType, String chartCode ,String chartAnalysis ,int chartStatus) {
        this.userName = userName;
        this.userId = userId;
        this.fileInformation = fileInformation;
        this.chartName = chartName;
        this.analysisAim = analysisAim;
        this.chartType = chartType;
        this.chartCode = chartCode;
        this.chartAnalysis = chartAnalysis;
        this.chartStatus = chartStatus;
    }

    @TableId(value = "Id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "UserName")
    private String userName;
    @TableField(value = "UserId")
    private Long userId;

    @TableField(value = "FileInformation")
    private String fileInformation;

    @TableField(value = "ChartName")
    private String chartName;


    @TableField(value = "AnalysisAim")
    private String analysisAim;

    @TableField(value = "ChartType")
    private String chartType;


    @TableField(value = "ChartCode")
    private String chartCode;

    @TableField(value = "ChartAnalysis")
    private String chartAnalysis;

    @TableField(value = "CreateDate")
    private Date createDate;

    @TableField(value = "IsDelete")
    private Integer isDelete;

    @TableField(value = "ChartStatus")
    private Integer chartStatus;
}
