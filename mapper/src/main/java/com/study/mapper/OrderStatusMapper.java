package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface OrderStatusMapper extends MyMapper<OrderStatus> {
}