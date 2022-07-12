package com.study.pojo.BO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-18 23:46
 */
@ApiModel(value = "Json", description = "用户参数接收容器")
@Data
public class UserBO {
    @ApiModelProperty(value = "用户名称", name = "username",  required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password",  required = true)
    private String password;
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", required = false)
    private String confirmPassword;
}
