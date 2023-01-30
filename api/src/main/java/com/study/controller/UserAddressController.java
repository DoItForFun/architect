package com.study.controller;

import com.study.pojo.BO.AddressBO;
import com.study.pojo.UserAddress;
import com.study.service.UserAddressService;
import com.study.utils.CommonJsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 08:57
 */
@RestController
@RequestMapping("address")
public class UserAddressController extends BaseController {
    @Resource
    UserAddressService userAddressService;

    @RequestMapping("/add")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "POST")
    public CommonJsonResult add(@RequestBody AddressBO addressBO) {
        userAddressService.save(addressBO);
        return CommonJsonResult.ok();
    }

    @RequestMapping("/list")
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @ApiOperation(value = "获取地址列表", notes = "获取地址列表", httpMethod = "GET")
    public CommonJsonResult list(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId
    ) {
        List<UserAddress> list = userAddressService.list(userId);
        return CommonJsonResult.ok(list);
    }

    @RequestMapping("/delete")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @ApiOperation(value = "删除地址", notes = "删除地址", httpMethod = "GET")
    public CommonJsonResult delete(
            @ApiParam(name = "userId", value = "用户Id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId", value = "地址Id", required = true)
            @RequestParam String addressId
    ) {
        boolean delete = userAddressService.delete(userId, addressId);
        if (delete) {
            return CommonJsonResult.ok();
        }
        return CommonJsonResult.errorMsg("失败");
    }

    @RequestMapping("/setDefalut")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @ApiOperation(value = "设置默认地址", notes = "设置默认地址", httpMethod = "GET")
    public CommonJsonResult setDefault(
            @ApiParam(name = "userId", value = "用户Id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId", value = "地址Id", required = true)
            @RequestParam String addressId
    ) {
        boolean setDefault = userAddressService.setDefault(userId, addressId);
        if (setDefault) {
            return CommonJsonResult.ok();
        }
        return CommonJsonResult.errorMsg("失败");
    }

}
