package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface OrdersMapper extends MyMapper<Orders> {
}