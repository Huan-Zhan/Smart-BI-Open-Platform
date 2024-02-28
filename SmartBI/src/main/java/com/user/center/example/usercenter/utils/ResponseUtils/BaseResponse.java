package com.user.center.example.usercenter.utils.ResponseUtils;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String Code ;
    private String Message ;
    private T Data ;

    public BaseResponse() {
    }

    public BaseResponse(String code, String message, T data) {
        Code = code;
        Message = message;
        Data = data;
    }

}
