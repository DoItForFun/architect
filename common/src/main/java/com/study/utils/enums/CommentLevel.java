package com.study.utils.enums;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-19 00:04
 */
public enum CommentLevel {
    /**
     * 是否枚举
     */
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final Integer type;
    private final String value;

    CommentLevel(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
