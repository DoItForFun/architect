package com.study.pojo.vo;

import com.study.pojo.Items;
import com.study.pojo.ItemsImg;
import com.study.pojo.ItemsParam;
import com.study.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-11-15 15:32
 */
@Data
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
