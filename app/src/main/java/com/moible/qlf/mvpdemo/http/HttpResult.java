package com.moible.qlf.mvpdemo.http;

import java.io.Serializable;

/**
 * @className HttpResult
 * @描述 网络返回格式类
 * @作者lml
 * @日期2017/1/19 15:27
 * @修改日期
 * @版本
 */
public class HttpResult<T> implements Serializable {

    private int code; //响应码
    private String message; //返回提示语
    private T data;//数据

    public HttpResult() {

    }

    public HttpResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
