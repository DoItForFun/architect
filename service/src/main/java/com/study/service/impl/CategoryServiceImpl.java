package com.study.service.impl;

import com.study.mapper.CategoryMapper;
import com.study.mapper.CategoryCustomMapper;
import com.study.pojo.Category;
import com.study.pojo.vo.CategoryVO;
import com.study.pojo.vo.NewItemsVO;
import com.study.pojo.vo.SimpleItemVO;
import com.study.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-11-15 09:47
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;
    @Autowired
    CategoryCustomMapper categoryCustomMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CategoryVO> getSubCatList(Integer id) {
        return categoryCustomMapper.getSubCatList(id);
    }

    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> paramsMap = new HashMap<>(2);
        paramsMap.put("rootCatId", rootCatId);
        return categoryCustomMapper.getSixNewItemsLazy(paramsMap);
    }
}
