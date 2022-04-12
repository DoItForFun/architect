package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.UserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface UserAddressMapper extends MyMapper<UserAddress> {
}