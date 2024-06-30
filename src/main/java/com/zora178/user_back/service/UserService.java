package com.zora178.user_back.service;

import com.zora178.user_back.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.zora178.user_back.contant.UserConston.ADMIN_ROLE;
import static com.zora178.user_back.contant.UserConston.USER_LOGIN_STATE;

/**
* @author WXY0412nauy
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-18 16:59:42
 *
 * 用户服务
 *
*/


public interface UserService extends IService<User> {

    /**
     *用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @param planetCode 校验编号
     * @return 新用户 id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request 请求
     * @return 脱敏后的用户信息
     */


     User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getsafetyUser(User originUser);

    /**
     * 用户注销
     * @param request
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户
     * @param tagNameList
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user, User loginUser);

    /**
     * 获取当前登录用户信息
     * @return
     */
     User getLoginUser(HttpServletRequest request);


    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
     boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param loginUser
     * @return
     */
     boolean isAdmin(User loginUser);
}
