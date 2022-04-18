package com.study.controller;

import com.study.pojo.Stu;
import com.study.service.UserService;
import com.study.service.impl.StuServiceImpl;
import com.study.utils.CommonJsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public CommonJsonResult usernameIsExists(@RequestParam String username){
        // 判断入参
        if (StringUtils.isBlank(username)) {
            return CommonJsonResult.errorMsg("params invalid");
        }
        // 查找用户名是否存在
        boolean isExists = userService.queryUsernameIsExists(username);
        if(isExists){
            return CommonJsonResult.errorMsg("already exists!");
        }
        return CommonJsonResult.ok();
    }
}
