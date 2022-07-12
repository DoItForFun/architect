package com.study.service;

import com.study.pojo.BO.UserBO;
import com.study.pojo.User;

/**
 * The interface Stu service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -04-11 23:46
 */
public interface UserService {
    /**
     * Query username is exists.
     * 判断用户是否存在
     *
     * @param name the name
     * @return the boolean
     */
    boolean queryUsernameIsExists(String name);

    /**
     * Create user.
     *
     * @param userBO the user bo
     * @return the user
     * @throws Exception the exception
     */
    User createUser(UserBO userBO) throws Exception;


    /**
     * Query user for login user.
     * 登录
     * @param username the username
     * @param password the password
     * @return the user
     */
    User  queryUserForLogin(String username, String password);
}
