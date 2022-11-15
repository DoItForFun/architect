package com.study.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-13 23:25
 */
public class CommonJsonResult {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Integer status;
    private String message;
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    @JsonIgnore
    private String ok;

    public CommonJsonResult(Integer status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static CommonJsonResult build(Integer status, String message, Object data){
        return new CommonJsonResult(status, message, data);
    }

    public static CommonJsonResult ok(){
        return new CommonJsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static CommonJsonResult ok(Object data){
        return new CommonJsonResult(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static CommonJsonResult errorMsg(String message){
        return new CommonJsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }
}
