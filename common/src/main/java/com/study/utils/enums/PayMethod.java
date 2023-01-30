package com.study.utils.enums;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 10:33
 */
public enum PayMethod {
    /**
     * 支付方式枚举
     */
    WECHAT(1, "微信"),
    ALIPAY(2, "支付宝");

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final Integer type;
    private final String value;

    PayMethod(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
