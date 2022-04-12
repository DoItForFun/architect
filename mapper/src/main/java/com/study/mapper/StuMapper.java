package com.study.mapper;

import com.study.my.mapper.MyMapper;
import com.study.pojo.Stu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 */
@Mapper
public interface StuMapper extends MyMapper<Stu> {
}