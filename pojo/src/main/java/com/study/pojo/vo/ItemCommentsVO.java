package com.study.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * The type Item comments vo.
 *
 * @author lizhe
 * @version 1.0.0  Created by IDEA
 * @date 2022 -11-15 19:23
 */
@Data
public class ItemCommentsVO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickName;
}
