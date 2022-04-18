package com.study.pojo.BO;

import lombok.Data;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-18 23:46
 */
@Data
public class UserBO {
    private String username;
    private String password;
    private String confirmPassword;
}
