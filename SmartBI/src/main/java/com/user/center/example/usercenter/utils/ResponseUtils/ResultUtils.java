package com.user.center.example.usercenter.utils.ResponseUtils;

public class ResultUtils {
    public static <T>BaseResponse<T> Success(T Data){
        return new BaseResponse(BaseCode.Code_Success,BaseMessage.Message_Success,Data);
    }
    public static <T>BaseResponse<T> Error(T Data){
        return new BaseResponse(BaseCode.Code_Error,BaseMessage.Message_Error,Data);
    }
    public static <T>BaseResponse<T> Error(){
        return new BaseResponse(BaseCode.Code_Error,BaseMessage.Message_Error,null);
    }

    public static <T>BaseResponse<T> Warning(T Data){
        return new BaseResponse(BaseCode.Code_Error,BaseMessage.Message_Error,Data);
    }
}
