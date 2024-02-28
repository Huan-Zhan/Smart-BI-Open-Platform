package com.user.center.example.usercenter.model.restful;

import lombok.Data;

@Data
public class AiChartMessage {
    private String myOption ;
    private String analysisResult ;

    public AiChartMessage(String myOption, String analysisResult) {
        this.myOption = myOption;
        this.analysisResult = analysisResult;
    }

    public AiChartMessage() {
    }
}
