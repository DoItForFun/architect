package com.study.controller;

import com.study.pojo.Items;
import com.study.pojo.ItemsImg;
import com.study.pojo.ItemsParam;
import com.study.pojo.ItemsSpec;
import com.study.pojo.vo.CommentLevelCountsVO;
import com.study.pojo.vo.ItemInfoVO;
import com.study.pojo.vo.ShopCartVO;
import com.study.service.ItemService;
import com.study.utils.CommonJsonResult;
import com.study.utils.PageGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-11-15 16:11
 */
@RestController
@ApiIgnore
@RequestMapping("/items")
@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
public class ItemsController extends BaseController{
    @Resource
    ItemService itemService;

    @GetMapping("/info/{itemId}")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    public CommonJsonResult subCat(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable("itemId") String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return CommonJsonResult.errorMsg(null);
        }
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);
        return CommonJsonResult.ok(itemInfoVO);
    }

    @GetMapping("/commentLevel")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    public CommonJsonResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam("itemId") String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return CommonJsonResult.errorMsg(null);
        }
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return CommonJsonResult.ok(commentLevelCountsVO);
    }

    @GetMapping("/comments")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "查看评论", notes = "查看评论", httpMethod = "GET")
    public CommonJsonResult getComments(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam("itemId") String itemId,
            @ApiParam(name = "level", value = "评价等级")
            @RequestParam("level") String level,
            @ApiParam(name = "page", value = "页码")
            @RequestParam("page") Integer page,
            @ApiParam(name = "pageSize", value = "页面大小")
            @RequestParam("pageSize") Integer pageSize
    ) {
        if (StringUtils.isBlank(itemId)) {
            return CommonJsonResult.errorMsg(null);
        }
        if(page == null){
            page = START_PAGE;
        }
        if(pageSize == null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PageGridResult pageGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return CommonJsonResult.ok(pageGridResult);
    }

    @GetMapping("/search")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    public CommonJsonResult searchItems(
            @ApiParam(name = "keywords", value = "关键字", required = true)
            @RequestParam("keywords") String keywords,
            @ApiParam(name = "sort", value = "排序")
            @RequestParam("sort") String sort,
            @ApiParam(name = "page", value = "页码")
            @RequestParam("page") Integer page,
            @ApiParam(name = "pageSize", value = "页面大小")
            @RequestParam("pageSize") Integer pageSize
    ) {
        if (StringUtils.isBlank(keywords)) {
            return CommonJsonResult.errorMsg(null);
        }
        if(page == null){
            page = START_PAGE;
        }
        if(pageSize == null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PageGridResult pageGridResult = itemService.searchItems(keywords, sort, page, pageSize);
        return CommonJsonResult.ok(pageGridResult);
    }


    @GetMapping("/catItems")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    public CommonJsonResult catItems(
            @ApiParam(name = "catId", value = "catId", required = true)
            @RequestParam("catId") Integer catId,
            @ApiParam(name = "sort", value = "排序")
            @RequestParam("sort") String sort,
            @ApiParam(name = "page", value = "页码")
            @RequestParam("page") Integer page,
            @ApiParam(name = "pageSize", value = "页面大小")
            @RequestParam("pageSize") Integer pageSize
    ) {
        if (catId == null) {
            return CommonJsonResult.errorMsg(null);
        }
        if(page == null){
            page = START_PAGE;
        }
        if(pageSize == null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PageGridResult pageGridResult = itemService.searchItemsByThirdCat(catId, sort, page, pageSize);
        return CommonJsonResult.ok(pageGridResult);
    }

    @GetMapping("/refresh")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "根据商品规格ids查找最新的商品数据", notes = "根据商品规格ids查找最新的商品数据", httpMethod = "GET")
    public CommonJsonResult refresh(
            @ApiParam(name = "itemSpecIds", value = "itemSpecIds", required = true, example = "1001,1002")
            @RequestParam("itemSpecIds") String itemSpecId
    ) {
        if (StringUtils.isBlank(itemSpecId)) {
            return CommonJsonResult.errorMsg(null);
        }
        List<ShopCartVO> shopCart = itemService.queryItemsBySpecIds(itemSpecId);
        return CommonJsonResult.ok(shopCart);
    }

}
