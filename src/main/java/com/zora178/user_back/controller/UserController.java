package com.zora178.user_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zora178.user_back.common.BaseResponse;
import com.zora178.user_back.common.ErrorCode;
import com.zora178.user_back.common.ResultUtils;
import com.zora178.user_back.exception.BusinessException;
import com.zora178.user_back.model.domain.User;
import com.zora178.user_back.model.domain.request.UserLoginRequest;
import com.zora178.user_back.model.domain.request.UserRegisterRequest;
import com.zora178.user_back.service.UserService;
import com.zora178.user_back.service.impl.UserServiceImpl;
import io.swagger.models.auth.In;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zora178.user_back.contant.UserConston.ADMIN_ROLE;
import static com.zora178.user_back.contant.UserConston.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 * @author zora
 */
@RestController
@RequestMapping("/user")
//解决跨域
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"注册请求为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,planetCode)) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"注册信息存在空值");
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"登录请求为空");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.NULL_ERROR,"登录信息存在空值");
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"请求为空");
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userobj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User)userobj;
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        User user =  userService.getById(userId);
        User safetyuser = userService.getsafetyUser(user);
        return ResultUtils.success(safetyuser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {

        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH,"非管理员");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> { user.setUserPassword(null);return userService.getsafetyUser(user); }).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList){
        if (CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUsersByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    @PostMapping("/updatate")
    public BaseResponse<Integer> updateUser(@RequestBody User user, HttpServletRequest request){
//        1、校验参数是否为空 2、校验权限 3、触发更新
        if(user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        int result = userService.updateUser(user, loginUser);
//        成功响应类
        return ResultUtils.success(result);

    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUsers(@RequestBody long id, HttpServletRequest request) {
        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH,"非管理员");
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不存在");
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }


}
