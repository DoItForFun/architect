package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.mapper.*;
import com.study.pojo.*;
import com.study.pojo.vo.CommentLevelCountsVO;
import com.study.pojo.vo.ItemCommentsVO;
import com.study.pojo.vo.SearchItemsVO;
import com.study.service.ItemService;
import com.study.utils.PageGridResult;
import com.study.utils.enums.CommentLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Item service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -11-15 09:47
 */
@Service
public class ItemServiceImpl implements ItemService {

    /**
     * The Items mapper.
     */
    @Resource
    ItemsMapper itemsMapper;

    /**
     * The Items img mapper.
     */
    @Resource
    ItemsImgMapper itemsImgMapper;

    /**
     * The Items spec mapper.
     */
    @Resource
    ItemsSpecMapper itemsSpecMapper;

    /**
     * The Items param mapper.
     */
    @Resource
    ItemsParamMapper itemsParamMapper;

    /**
     * The Items comments mapper.
     */
    @Resource
    ItemsCommentsMapper itemsCommentsMapper;

    /**
     * The Items custom mapper.
     */
    @Resource
    ItemsCustomMapper itemsCustomMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example itemsImgExp = new Example(ItemsImg.class);
        itemsImgExp.createCriteria().andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(itemsImgExp);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example itemsSpecExp = new Example(ItemsImg.class);
        itemsSpecExp.createCriteria().andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(itemsSpecExp);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public ItemsParam queryItemParam(String itemId) {
        Example itemsParamExp = new Example(ItemsImg.class);
        itemsParamExp.createCriteria().andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(itemsParamExp);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.getType());
        Integer bacCounts = getCommentCounts(itemId, CommentLevel.BAD.getType());
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.getType());
        Integer totalCounts = goodCounts + bacCounts + normalCounts;
        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();
        commentLevelCountsVO.setGoodCounts(goodCounts);
        commentLevelCountsVO.setBadCounts(bacCounts);
        commentLevelCountsVO.setNormalCounts(normalCounts);
        commentLevelCountsVO.setTotalCounts(totalCounts);
        return commentLevelCountsVO;
    }

    /**
     * Gets comment counts.
     *
     * @param itemId the item id
     * @param level  the level
     * @return the comment counts
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null) {
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageGridResult queryPagedComments(String itemId, String level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("itemId", itemId);
        map.put("level", level);
        PageHelper.startPage(page, pageSize);
        List<ItemCommentsVO> itemComments = itemsCustomMapper.queryItemComments(map);
        return setterPageGrid(page, itemComments);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("keywords", keywords);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> searchItems = itemsCustomMapper.searchItems(map);
        return setterPageGrid(page, searchItems);
    }

    private PageGridResult setterPageGrid(int page, List<?> list) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PageGridResult result = new PageGridResult();
        result.setPage(page);
        result.setRows(list);
        result.setTotal(pageList.getPages());
        result.setRecords(pageList.getTotal());
        return result;
    }
}
