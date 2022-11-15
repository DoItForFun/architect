package com.study.utils.enums;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-19 00:04
 */
public enum YesOrNo {
    /**
     * 是否枚举
     */
    NO(0, "否"),
    YES(1, "是"),;

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final Integer type;
    private final String value;

    YesOrNo(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
