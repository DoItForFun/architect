package com.study.controller;

import com.study.pojo.BO.UserBO;
import com.study.pojo.Stu;
import com.study.pojo.User;
import com.study.service.UserService;
import com.study.service.impl.StuServiceImpl;
import com.study.utils.CommonJsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-11 23:26
 */
@RestController
@RequestMapping("/passport")
public class PassportController {
    @Resource
    UserService userService;

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
}
