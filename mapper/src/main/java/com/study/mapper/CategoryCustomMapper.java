package com.study.mapper;

import com.study.pojo.vo.CategoryVO;
import com.study.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface Category mapper custom.
 *
 * @author lizhe
 */
@Mapper
public interface CategoryCustomMapper {
    /**
     * Gets sub cat list.
     *
     * @param rootCatId the root cat id
     * @return the sub cat list
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * Gets six new items lazy.
     *
     * @param paramsMap the params map
     * @return the six new items lazy
     */
    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> paramsMap);
}