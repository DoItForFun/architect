package com.study.controller;

import com.study.pojo.Carousel;
import com.study.pojo.Category;
import com.study.pojo.vo.CategoryVO;
import com.study.pojo.vo.NewItemsVO;
import com.study.service.CarouselService;
import com.study.service.CategoryService;
import com.study.utils.CommonJsonResult;
import com.study.utils.enums.YesOrNo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-10 17:52
 */
@RestController
@ApiIgnore
@RequestMapping("/index")
@Api(value = "首页", tags = {"首页展示的相关接口"})
public class IndexController {
    final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    CarouselService carouselService;
    @Resource
    CategoryService categoryService;


    @RequestMapping("/carousel")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    public CommonJsonResult setSession(HttpServletRequest request) {
        List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.getType());
        return CommonJsonResult.ok(carousels);
    }

    @RequestMapping("/cats")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "获取商品一级分类", notes = "获取商品一级分配", httpMethod = "GET")
    public CommonJsonResult category(){
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return CommonJsonResult.ok(categories);
    }

    @GetMapping("/subCat/{rootCatId}")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "获取商品下级分类", notes = "获取商品下级分类", httpMethod = "GET")
    public CommonJsonResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable("rootCatId") Integer rootCatId){
        if(rootCatId == null){
            return CommonJsonResult.errorMsg("不存在");
        }
        List<CategoryVO> categories = categoryService.getSubCatList(rootCatId);
        return CommonJsonResult.ok(categories);
    }

    @GetMapping("/sixNewItems/{rootCatId}")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "查询每个一级分类下的最新六条商品数据", notes = "查询每个一级分类下的最新六条商品数据", httpMethod = "GET")
    public CommonJsonResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable("rootCatId") Integer rootCatId){
        if(rootCatId == null){
            return CommonJsonResult.errorMsg("不存在");
        }
        List<NewItemsVO> itemsList = categoryService.getSixNewItemsLazy(rootCatId);
        return CommonJsonResult.ok(itemsList);
    }
}
