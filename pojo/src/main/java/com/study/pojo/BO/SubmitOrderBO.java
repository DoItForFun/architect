package com.study.pojo.BO;

import lombok.Data;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 10:25
 */
@Data
public class SubmitOrderBO {
    private String addressId;
    private String itemSpecIds;
    private String leftMsg;
    private Integer payMethod;
    private String userId;
}
