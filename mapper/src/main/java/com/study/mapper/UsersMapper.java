package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface UsersMapper extends MyMapper<User> {
}