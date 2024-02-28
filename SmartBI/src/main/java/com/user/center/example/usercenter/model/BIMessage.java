package com.user.center.example.usercenter.model;

import lombok.Data;

@Data
public class BIMessage {

    private Long Id ;
    private String fileData ;
    private String requestData ;
    private String userId ;
    private String userName ;
    private String chartName;
    private String chartAnalysisAim ;
    private String chartType ;
    private String chartOptions ;
    private String chartAnalysis ;

    public BIMessage() {
    }

    public BIMessage(String fileData, String requestData, String userId, String userName, String chartName, String chartAnalysisAim, String chartType, String chartOptions, String chartAnalysis) {

        this.fileData = fileData;
        this.requestData = requestData;
        this.userId = userId;
        this.userName = userName;
        this.chartName = chartName;
        this.chartAnalysisAim = chartAnalysisAim;
        this.chartType = chartType;
        this.chartOptions = chartOptions;
        this.chartAnalysis = chartAnalysis;
    }
    public BIMessage(Long id , String fileData, String requestData, String userId, String userName, String chartName, String chartAnalysisAim, String chartType) {
        this.Id = id ;
        this.fileData = fileData;
        this.requestData = requestData;
        this.userId = userId;
        this.userName = userName;
        this.chartName = chartName;
        this.chartAnalysisAim = chartAnalysisAim;
        this.chartType = chartType;
    }
}
