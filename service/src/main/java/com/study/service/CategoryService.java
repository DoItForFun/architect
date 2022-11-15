package com.study.service;

import com.study.pojo.Category;
import com.study.pojo.vo.CategoryVO;
import com.study.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface Carousel service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -11-15 09:46
 */
public interface CategoryService {

    /**
     * Query all.
     *
     * @return the list
     */
    List<Category> queryAllRootLevelCat();

    /**
     * Query sub level cat list.
     *
     * @param id the id
     * @return the list
     */
    List<CategoryVO> getSubCatList(Integer id);

    /**
     * Gets six new items lazy.
     *
     * @param rootCatId the root cat id
     * @return the six new items lazy
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
