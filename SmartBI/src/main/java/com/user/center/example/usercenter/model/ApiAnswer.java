package com.user.center.example.usercenter.model;

import lombok.Data;

@Data
public class ApiAnswer {
    private String myOptions  ;
    private String myAnalysis ;

    public ApiAnswer(String myOptions, String myAnalysis) {
        this.myOptions = myOptions;
        this.myAnalysis = myAnalysis;
    }

    public ApiAnswer() {
    }


}
