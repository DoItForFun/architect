package com.study.service.impl;

import com.study.mapper.UsersMapper;
import com.study.pojo.BO.UserBO;
import com.study.pojo.User;
import com.study.service.UserService;
import com.study.utils.DateUtil;
import com.study.utils.Md5Utils;
import com.study.utils.enums.SexEnum;
import org.n3r.idworker.Sid;
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

    @Resource
    Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExists(String name){
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", name);
        User user = usersMapper.selectOneByExample(userExample);
        return user != null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public User createUser(UserBO userBO) throws Exception {
        User user = new User();
        String userId = sid.nextShort();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        user.setPassword(Md5Utils.getMD5Str(userBO.getPassword()));
        user.setNickname(userBO.getUsername());
        user.setFace("https://tse1-mm.cn.bing.net/th/id/R-C.bbe58a041c176092728c7e9eb5413b4c?rik=6lOIyLF5ETAkEw&riu=http%3a%2f%2ftvax4.sinaimg.cn%2flarge%2f006yt1Omgy1gxhvsbrr8yj31hc0u00wf.jpg&ehk=V5VHKAYrKCy3hqssKf5ijZK0fB46fah4Uq3Jphkouac%3d&risl=&pid=ImgRaw&r=0");
        user.setBirthday(DateUtil.stringToDate("2016-01-01"));
        user.setSex(SexEnum.secret.getType());
        user.setCreatedTime(DateUtil.getCurrentDateTime());
        user.setUpdatedTime(DateUtil.getCurrentDateTime());
        usersMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public User queryUserForLogin(String username, String password) {
        Example userExample = new Example(User.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);
        return usersMapper.selectOneByExample(userExample);
    }


}
