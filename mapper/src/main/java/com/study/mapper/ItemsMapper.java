package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.Items;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface ItemsMapper extends MyMapper<Items> {
}