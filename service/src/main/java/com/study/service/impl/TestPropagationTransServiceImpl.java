package com.study.service.impl;

import com.study.service.StuService;
import com.study.service.TestPropagationTransService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-12 00:00
 */
@Service
public class TestPropagationTransServiceImpl implements TestPropagationTransService {
    @Resource
    StuService stuService;

    /**
     *  REQUIRED : 使用当前的事务，如果当前没有事务，则自己新建一个事务，字方法必须运行在一个事务中
     *             如果当前存在事务，则加入这个事务，成为一个整体
     *  SUPPORTS : 如果当前有事务，则使用事务。如果当前没有事务，则不使用事务
     *  MANDATORY : 该传播属性强制必须存在一个事务，不存在，则抛出异常
     *  REQUIRES_NEW : 如果当前有事务，挂起该事务，新建一个事务
     *                 如果没有事物，则自己开启一个事务
     *  NOT_SUPPORTED : 如果当前有事物，则把事务挂起，自己不使用事务
     *  NEVER : 如果当前有事物，则抛出异常
     *  NESTED : 如果当前有事物，则开启子事务，嵌套事务是独立提交回滚
     *           如果当前没有事务，则通REQUIRED
     *           如果主事务提交，则携带提交事务
     *           如果子事务异常，主事务处理了，不回滚。未处理，主事务未处理，子事务一起回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void testPropagationTrans() {
        stuService.saveParent();
        stuService.saveChild();
    }
}
