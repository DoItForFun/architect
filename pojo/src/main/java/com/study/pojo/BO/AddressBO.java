package com.study.pojo.BO;

import lombok.Data;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 08:59
 */
@Data
public class AddressBO {
    private String province;
    private String city;
    private String detail;
    private String district;
    private String mobile;
    private String receiver;
    private String userId;
}
