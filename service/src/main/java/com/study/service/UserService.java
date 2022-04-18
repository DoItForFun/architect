package com.study.service;

import com.study.pojo.Stu;

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
}
