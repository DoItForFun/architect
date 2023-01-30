package com.study.service;

import com.study.pojo.BO.SubmitOrderBO;
import com.study.pojo.Category;
import com.study.pojo.vo.CategoryVO;
import com.study.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * The interface Carousel service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -11-15 09:46
 */
public interface OrdersService {

    /**
     * Query all.
     *
     * @param submitOrderBO the submit order bo
     * @return the list
     */
    String createOrder(SubmitOrderBO submitOrderBO);

}
