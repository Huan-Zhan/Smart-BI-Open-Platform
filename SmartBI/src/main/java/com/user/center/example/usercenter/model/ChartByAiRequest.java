package com.user.center.example.usercenter.model;

import lombok.Data;

@Data
public class ChartByAiRequest {
    private String tableName ;
    private String analysisAim ;
    private String tableType;

    public void check(){
        if (!analysisAim.substring(0,3).equals("分析")){
            analysisAim = "分析"+analysisAim;
        }
    }

}
