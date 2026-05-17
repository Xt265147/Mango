package com.evan.mangocore.http;

import lombok.Getter;
import lombok.Setter;

/**
 * HTTP结果封装
 *
 * @author Qianxun
 * @date May 17, 2026
 */
public class HttpResult {
    @Getter
    @Setter
    private int code = 200;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private Object data;

    public static HttpResult error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");

    }
    public static HttpResult error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);

    }

    public static HttpResult error(int code, String msg){
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static HttpResult ok(String msg){
        HttpResult r = new HttpResult();
        r.setMessage(msg);
        return r;
    }

    public static HttpResult ok(Object data){
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

    public static HttpResult ok(){
        return new HttpResult();
    }
}
