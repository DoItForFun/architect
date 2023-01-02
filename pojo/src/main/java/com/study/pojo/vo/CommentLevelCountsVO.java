package com.study.pojo.vo;

import lombok.Data;

/**
 * The type Comment level counts vo.
 *
 * @author lizhe
 * @version 1.0.0  Created by IDEA
 * @date 2022 -11-15 18:09
 */
@Data
public class CommentLevelCountsVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
