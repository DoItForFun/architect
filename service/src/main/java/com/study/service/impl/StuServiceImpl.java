package com.study.service.impl;

import com.study.mapper.StuMapper;
import com.study.pojo.Stu;
import com.study.service.StuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-11 23:27
 */
@Service
public class StuServiceImpl implements StuService {
    @Resource
    StuMapper stuMapper;

    @Override
    public void save() {
        Stu stu = new Stu();
        stu.setAge(25);
        stu.setName("liam");
        stuMapper.insert(stu);
    }

    @Override
    public void saveParent() {
        Stu stu = new Stu();
        stu.setAge(55);
        stu.setName("李四-p");
        stuMapper.insert(stu);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void saveChild() {
        Stu stu = new Stu();
        stu.setAge(55);
        stu.setName("李五-c");
        stuMapper.insert(stu);
        int o = 1 / 0;
        Stu stu2 = new Stu();
        stu.setAge(55);
        stu.setName("李六-c");
        stuMapper.insert(stu2);
    }

    @Override
    public void remove(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(Stu stu) {
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    public Object get(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }
}
