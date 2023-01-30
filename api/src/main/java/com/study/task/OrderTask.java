package com.study.task;

import com.study.service.OrdersService;
import com.study.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 14:41
 */
@Component
public class OrderTask {

    @Resource
    OrdersService ordersService;

    @Scheduled(cron = "0/3 * * * * ?")
    public void task(){
        System.err.println("执行定时任务，当前时间为: " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
        ordersService.closeOrder();
    }
}
