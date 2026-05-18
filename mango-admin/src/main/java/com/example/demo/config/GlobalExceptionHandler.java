package com.example.demo.config;

import com.evan.mangocore.http.HttpResult;
import com.evan.mangocore.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HttpResult handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return HttpResult.error(HttpStatus.SC_BAD_REQUEST, "请求参数格式错误，请检查JSON数据");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public HttpResult handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return HttpResult.error(HttpStatus.SC_BAD_REQUEST, "请求方法不支持: " + e.getMethod());
    }

    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e) {
        return HttpResult.error(e.getMessage());
    }
}