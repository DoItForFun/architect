package com.study.service.impl;

import com.study.mapper.OrderItemsMapper;
import com.study.mapper.OrderStatusMapper;
import com.study.mapper.OrdersMapper;
import com.study.pojo.BO.SubmitOrderBO;
import com.study.pojo.*;
import com.study.service.ItemService;
import com.study.service.OrdersService;
import com.study.service.UserAddressService;
import com.study.utils.DateUtil;
import com.study.utils.enums.OrderStatusEnum;
import com.study.utils.enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-11-15 09:47
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private UserAddressService addressService;

    @Resource
    private ItemService itemService;

    @Resource
    private OrderItemsMapper orderItemsMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        Integer postAmount = 0;

        String orderId = sid.nextShort();

        UserAddress address = addressService.queryUserAddress(userId, addressId);
        // 订单表
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " " + address.getCity() + " " + address.getDistrict() + " " + address.getDetail());
        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.NO.getType());
        newOrder.setIsDelete(YesOrNo.NO.getType());
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());


        // 订单闪屏信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        int totalAmount = 0;
        int realPaymentAmount = 0;
        for (String itemSpecId : itemSpecIdArr) {

            // TODO 整合redis后，数量重新获取
            int buyCount = 1;
            // 根据规格id查询具体信息
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCount;
            realPaymentAmount += itemsSpec.getPriceDiscount() * buyCount;
            // 根据规格id获取商品信息和商品图片
            String itemId = itemsSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            String itemImg = itemService.queryItemMainImgById(itemId);
            // 循环保存子订单
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(itemImg);
            subOrderItem.setBuyCounts(buyCount);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemsSpec.getName());
            subOrderItem.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrderItem);
            // 规格表中扣库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCount);
        }
        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPaymentAmount);
        ordersMapper.insert(newOrder);

        // 订单状态
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.getType());
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);
        return orderId;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void closeOrder() {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.getType());
        List<OrderStatus> select = orderStatusMapper.select(orderStatus);
        for (OrderStatus status : select) {
            Date createdTime = status.getCreatedTime();
            int days = DateUtil.daysBetween(createdTime, new Date());
            if (days > 1) {
                doClose(status.getOrderId());
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doClose(String orderId){
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.getType());
        orderStatus.setCloseTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
