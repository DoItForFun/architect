package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.OrderItems;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface OrderItemsMapper extends MyMapper<OrderItems> {
}