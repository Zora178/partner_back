package com.zora178.user_back.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author zora
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -7249075890228704424L;

    private String userAccount;

    private String userPassword;

}
