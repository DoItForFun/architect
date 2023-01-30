package com.study.controller;

import com.study.pojo.BO.SubmitOrderBO;
import com.study.service.OrdersService;
import com.study.utils.CommonJsonResult;
import com.study.utils.CookieUtils;
import com.study.utils.enums.PayMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 10:23
 */
@Api(value = "订单接口", tags = {"订单的相关接口"})
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController{
    @Resource
    private OrdersService ordersService;
    @RequestMapping("/create")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "POST")
    public CommonJsonResult create(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response) {
        if (submitOrderBO.getPayMethod() != PayMethod.WECHAT.getType().intValue() && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.getType().intValue()) {
            return CommonJsonResult.errorMsg("支付方式不用支持！");
        }
        // 创建订单
        String orderId = ordersService.createOrder(submitOrderBO);
        // 移除购物车已提交商品
        // TODO 整合redis后，完善购物车中的已结算商品清除，并且同步到前端
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);
        // 向支付中心发送当前订单，用于保存支付中心的订单数据

        return CommonJsonResult.ok(orderId);
    }
}
