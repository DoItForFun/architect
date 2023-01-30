package com.study.controller;

import com.study.pojo.BO.ShopCartBO;
import com.study.utils.CommonJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-02 11:52
 */
@Api(value = "购物车接口controller", tags = {"购物车接口相关api"})
@RestController
@RequestMapping("/shopcart")
public class ShopCatController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @RequestMapping("/add")
    public CommonJsonResult add(
            @RequestParam String userId,
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("");
        }
        // TODO 前端用户在登陆情况下添加商品到购物车同时在后端同步到redis缓存
        return CommonJsonResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @RequestMapping("/del")
    public CommonJsonResult del(
            @RequestParam String userId,
            @RequestBody String itemSpecId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return CommonJsonResult.errorMsg("");
        }
        // TODO 前端用户在登陆情况下删除商品到购物车同时在后端同步到redis缓存
        return CommonJsonResult.ok();
    }
}
