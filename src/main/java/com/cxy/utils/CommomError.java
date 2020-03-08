package com.cxy.utils;

public interface CommomError {

    public int getErrCode();

    public String getMsg();

    //自定义错误消息
    public CommomError setErrMsg(String errMsg);
}
