package com.study.service.impl;

import com.study.mapper.UsersMapper;
import com.study.pojo.User;
import com.study.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * The interface Stu service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -04-11 23:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExists(String name){
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", name);
        User user = usersMapper.selectOneByExample(userExample);
        return user != null;
    }
}
