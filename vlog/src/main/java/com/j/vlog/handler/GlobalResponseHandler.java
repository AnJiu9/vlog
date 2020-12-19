package com.j.vlog.handler;

import com.j.vlog.common.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName GlobalResponseHandler
 * @Description
 * @Author orange
 * @Date 2020-12-19 20:27
 **/

@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice {
    /**
     * 此处如果返回false，则不执行当前Advice的业务
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 处理响应的具体方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseResult) {
            return body;
        } else {
            return ResponseResult.success(body);
        }
    }
}
