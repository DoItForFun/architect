package com.study.controller;

import com.study.pojo.BO.UserBO;
import com.study.pojo.Stu;
import com.study.pojo.User;
import com.study.service.UserService;
import com.study.service.impl.StuServiceImpl;
import com.study.utils.CommonJsonResult;
import com.study.utils.CookieUtils;
import com.study.utils.JsonUtils;
import com.study.utils.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-11 23:26
 */
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("/passport")
public class PassportController {
    @Resource
    UserService userService;

    @ApiOperation(value = "判断用户名是否存在", notes = "用户名是否存在", httpMethod = "POST")
    @RequestMapping("/isExists")
    public CommonJsonResult usernameIsExists(@RequestParam String username) {
        // 判断入参
        if (StringUtils.isBlank(username)) {
            return CommonJsonResult.errorMsg("params invalid");
        }
        // 查找用户名是否存在
        boolean isExists = userService.queryUsernameIsExists(username);
        if (isExists) {
            return CommonJsonResult.errorMsg("already exists!");
        }
        return CommonJsonResult.ok();
    }


    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @RequestMapping("/register")
    public CommonJsonResult createUser(@RequestBody UserBO userBO) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return CommonJsonResult.errorMsg("用户名和密码不能为空");
        }
        boolean isExists = userService.queryUsernameIsExists(username);
        if (isExists) {
            return CommonJsonResult.errorMsg("用户名已经存在");
        }
        int lengthLimit = 6;
        if (password.length() < lengthLimit) {
            return CommonJsonResult.errorMsg("密码长度不能少于" + lengthLimit + "位");
        }
        if (!Objects.equals(confirmPassword, password)) {
            return CommonJsonResult.errorMsg("俩次密码不一致");
        }
        User user = userService.createUser(userBO);
        return CommonJsonResult.build(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @RequestMapping("/login")
    public CommonJsonResult login(@RequestBody UserBO userBO, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return CommonJsonResult.errorMsg("用户名和密码不能为空");
        }
        User user = userService.queryUserForLogin(username, Md5Utils.getMD5Str(password));
        if(user == null){
            return CommonJsonResult.errorMsg("用户名或密码不正确");
        }
        CookieUtils.setCookie(request, response,"user", JsonUtils.objectToJson(user), true);
        setNullProperty(user);
        return CommonJsonResult.build(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    private void setNullProperty(User user){
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @RequestMapping("/logout")
    public CommonJsonResult logout(@RequestParam String userId,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        CookieUtils.deleteCookie(request, response, "user");
        return CommonJsonResult.ok();
    }
}
