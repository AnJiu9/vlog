package com.j.vlog.handler;

import com.j.vlog.common.ResponseResult;
import com.j.vlog.common.ResultCode;
import com.j.vlog.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description
 * @Author orange
 * @Date 2020-12-19 20:27
 **/

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常的处理，统一在这里捕获返回JSON格式的友好提示
     *
     * @param exception 异常
     * @return ResponseResult
     */
    @ExceptionHandler(value = {CustomException.class})
    @ResponseBody
    public ResponseResult sendError(CustomException exception) {
        log.error(exception.getMessage());
        return ResponseResult.failure(exception.getResultCode());
    }

    /**
     * SQL异常处理
     *
     * @param exception 异常
     * @return ResponseResult
     */
    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    public ResponseResult sendError(SQLException exception) {
        log.error(exception.getMessage());
        return ResponseResult.failure(ResultCode.DATABASE_ERROR);
    }

    /**
     * NPE空值异常处理
     *
     * @param exception 异常
     * @return ResponseResult
     */
    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public ResponseResult sendError(NullPointerException exception) {
        log.error(exception.getMessage());
        return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
