package com.study.utils.enums;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 11:18
 */
public enum OrderStatusEnum {

    /**
     * 支付方式枚举
     */
    WAIT_PAY(10, "待付款"),
    WAIT_DELIVER(20, "已付款，代发货"),
    WAIT_RECEIVE(30, "已发货，待收货"),
    SUCCESS(40, "交易成功"),
    CLOSE(50, "交易失败"),

    ;

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final Integer type;
    private final String value;

    OrderStatusEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
