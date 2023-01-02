package com.study.mapper;

import com.study.pojo.vo.ItemCommentsVO;
import com.study.pojo.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface Items custom mapper.
 *
 * @author lizhe
 */
@Mapper
public interface ItemsCustomMapper {

    /**
     * Query item comments list.
     *
     * @param paramsMap the params map
     * @return the list
     */
    List<ItemCommentsVO> queryItemComments(@Param("paramsMap") Map<String, Object> paramsMap);


    /**
     * Search items list <>.
     *
     * @param paramsMap the params map
     * @return the list <>
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> paramsMap);
}