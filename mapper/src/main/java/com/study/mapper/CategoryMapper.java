package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface CategoryMapper extends MyMapper<Category> {
}