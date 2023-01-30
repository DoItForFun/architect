package com.study.mapper;

import com.study.pojo.vo.ItemCommentsVO;
import com.study.pojo.vo.SearchItemsVO;
import com.study.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface Items custom mapper.
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
     * Search items list.
     *
     * @param paramsMap the params map
     * @return the list
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * Search items by third cat list.
     *
     * @param paramsMap the params map
     * @return the list
     */
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> paramsMap);


    /**
     * Query items by spec ids list.
     *
     * @param specIdsList the spec ids list
     * @return the list
     */
    List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIdsList);

    /**
     * Decrease item spec stock int.
     *
     * @param specId        the spec id
     * @param pendingCounts the pending counts
     * @return the int
     */
    int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") int pendingCounts);
}