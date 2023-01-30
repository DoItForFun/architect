package com.study.pojo.BO;

import lombok.Data;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-02 11:55
 */
@Data
public class ShopCartBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;
}
