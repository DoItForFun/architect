package com.study.service;

import com.study.pojo.BO.ShopCartBO;
import com.study.pojo.Items;
import com.study.pojo.ItemsImg;
import com.study.pojo.ItemsParam;
import com.study.pojo.ItemsSpec;
import com.study.pojo.vo.CommentLevelCountsVO;
import com.study.pojo.vo.ShopCartVO;
import com.study.utils.PageGridResult;

import java.util.List;

/**
 * The interface Carousel service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -11-15 09:46
 */
public interface ItemService {

    /**
     * 根据商品id查询商品信息
     * Query item by id items.
     *
     * @param itemId the item id
     * @return the items
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询图片列表
     * Query item img list list.
     *
     * @param itemId the item id
     * @return the list
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * Query item spec list list.
     *
     * @param itemId the item id
     * @return the list
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * Query item param items param.
     *
     * @param itemId the item id
     * @return the items param
     */
    ItemsParam queryItemParam(String itemId);


    /**
     * Query comment counts.
     *
     * @param itemId the item id
     * @return the comment level counts vo
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * Query paged comments list.
     *
     * @param itemId   the item id
     * @param level    the level
     * @param page     the page
     * @param pageSize the page size
     * @return the list
     */
    PageGridResult queryPagedComments(String itemId, String level, Integer page, Integer pageSize);


    /**
     * Search items page grid result.
     *
     * @param keywords the keywords
     * @param sort     the sort
     * @param page     the page
     * @param pageSize the page size
     * @return the page grid result
     */
    PageGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * Search items page grid result.
     *
     * @param catId    the catId
     * @param sort     the sort
     * @param page     the page
     * @param pageSize the page size
     * @return the page grid result
     */
    PageGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * Query items by spec ids page grid result.
     *
     * @param specIds    the specIds
     * @return the page grid result
     */
    List<ShopCartVO> queryItemsBySpecIds(String specIds);

    /**
     * Query item spec by id items spec.
     *
     * @param specId the spec id
     * @return the items spec
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * Query item main img by id string.
     *
     * @param itemId the item id
     * @return the string
     */
    String queryItemMainImgById(String itemId);

    /**
     * Decrease item spec stock.
     *
     * @param specId   the spec id
     * @param buyCount the buy count
     */
    void decreaseItemSpecStock(String specId, int buyCount);
}
