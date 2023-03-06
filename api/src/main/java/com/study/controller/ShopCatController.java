package com.study.controller;

import com.study.pojo.BO.ShopCartBO;
import com.study.utils.CommonJsonResult;
import com.study.utils.JsonUtils;
import com.study.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-02 11:52
 */
@Api(value = "购物车接口controller", tags = {"购物车接口相关api"})
@RestController
@RequestMapping("/shopcart")
public class ShopCatController extends BaseController{

    @Resource
    RedisOperator redisOperator;

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
        String key = FOODIE_SHOPCART + ":" + userId;
        String shopCardJson = redisOperator.get(key);
        List<ShopCartBO> shopCartBOList = null;
        List<ShopCartBO> addList = new ArrayList<>();
        if(StringUtils.isNotBlank(shopCardJson)){
            shopCartBOList = JsonUtils.jsonToList(shopCardJson, ShopCartBO.class);
            boolean exists = false;
            for (ShopCartBO cartBO : shopCartBOList) {
                String tmp = cartBO.getSpecId();
                if(tmp.equals(shopCartBO.getSpecId())){
                    cartBO.setBuyCounts(cartBO.getBuyCounts() + shopCartBO.getBuyCounts());
                    exists = true;
                }
                if(!exists){
                    addList.add(shopCartBO);
                }
            }
            shopCartBOList.addAll(addList);
        }else{
            shopCartBOList = new ArrayList<>();
            shopCartBOList.add(shopCartBO);
        }
        redisOperator.set(key, JsonUtils.objectToJson(shopCartBOList));
        return CommonJsonResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @RequestMapping("/del")
    public CommonJsonResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return CommonJsonResult.errorMsg("");
        }
        String key = FOODIE_SHOPCART + ":" + userId;
        String shopCardJson = redisOperator.get(key);
        List<ShopCartBO> shopCartBOList = new ArrayList<>();
        if(StringUtils.isNotBlank(shopCardJson)){
            List<ShopCartBO> existList = JsonUtils.jsonToList(shopCardJson, ShopCartBO.class);
            for (ShopCartBO cartBO : existList) {
                String tmp = cartBO.getSpecId();
                if(!tmp.equals(itemSpecId)){
                    shopCartBOList.add(cartBO);
                }
            }
        }
        if(shopCartBOList.isEmpty()){
            redisOperator.del(key);
        }else{
            redisOperator.set(key, JsonUtils.objectToJson(shopCartBOList));
        }
        return CommonJsonResult.ok();
    }
}
