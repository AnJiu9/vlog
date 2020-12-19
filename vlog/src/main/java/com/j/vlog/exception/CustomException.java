package com.j.vlog.exception;

import com.j.vlog.common.ResultCode;

/**
 * @ClassName CustomException
 * @Description 自定义异常
 * @Author orange
 * @Date 2020-12-19 20:28
 **/

public class CustomException extends RuntimeException{
    protected ResultCode resultCode;

    public CustomException(String msg, ResultCode resultCode) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }
}
