package com.zora178.user_back.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @author zora
 * @param <T>
 */

//Data注解生成get、set方法
@Data
public class BaseResponse<T> implements Serializable {
    //    接口支持序列化
//    T 泛型，data类型未知，提高可复用性
    private int code;

    private T data;

    private String message;

    private  String description;

    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data,String message) {
        this(code,data,message,"");
    }

    public BaseResponse(int code, T data) {
        this(code,data,"","");
    }

    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }
}
