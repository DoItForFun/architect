package com.study.utils;

import lombok.Data;

import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-11-16 09:27
 */
@Data
public class PageGridResult {
    private int page;
    private int total;
    private long records;
    private List<?> rows;
}
