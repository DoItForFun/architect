package com.study.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * The type Category vo.
 *
 * @author lizhe
 * @version 1.0.0  Created by IDEA
 * @date 2022 -11-15 11:25
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    private List<SubCategoryVO> subCatList;
}
