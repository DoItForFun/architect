package com.study.utils.enums;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-19 00:04
 */
public enum SexEnum {
    /**
     * 性别枚举
     */
    woman(0, "女"),
    man(1, "男"),
    secret(2, "保密");

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final Integer type;
    private final String value;

    SexEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
