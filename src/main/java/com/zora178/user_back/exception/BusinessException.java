package com.zora178.user_back.exception;

import com.zora178.user_back.common.ErrorCode;

/**
 * 自定义异常类
 * @author zora
 */
public class BusinessException extends RuntimeException {

    private final int code;

    private final String description;

//    构造函数
    public BusinessException( int code,String message, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode,String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
