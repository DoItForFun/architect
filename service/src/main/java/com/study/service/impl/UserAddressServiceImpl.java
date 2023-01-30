package com.study.service.impl;

import com.study.mapper.UserAddressMapper;
import com.study.pojo.BO.AddressBO;
import com.study.pojo.UserAddress;
import com.study.service.UserAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2023-01-30 09:09
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    UserAddressMapper userAddressMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(UUID.randomUUID().toString());
        userAddress.setCity(addressBO.getCity());
        userAddress.setUserId(addressBO.getUserId());
        userAddress.setCreatedTime(new Date());
        userAddress.setDetail(addressBO.getDetail());
        userAddress.setMobile(addressBO.getMobile());
        userAddress.setProvince(addressBO.getProvince());
        userAddress.setReceiver(addressBO.getReceiver());
        userAddress.setUpdatedTime(new Date());
        userAddress.setDistrict(addressBO.getDistrict());
        userAddressMapper.insert(userAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<UserAddress> list(String userId) {
        Example userAddressExp = new Example(UserAddress.class);
        userAddressExp.createCriteria().andEqualTo("userId", userId);
        return userAddressMapper.selectByExample(userAddressExp);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean delete(String userId, String addressId) {
        Example userAddressExp = new Example(UserAddress.class);
        userAddressExp.createCriteria().andEqualTo("id", addressId).andEqualTo("userId", userId);
        int i = userAddressMapper.deleteByExample(userAddressExp);
        return i > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean setDefault(String userId, String addressId) {
        int i = 0;
        UserAddress userAddress = queryUserAddress(userId, addressId);
        if (userAddress != null && !Objects.equals(userAddress.getIsDefault(), 1)) {
            removeDefault(userId);
            userAddress.setIsDefault(1);
            i = userAddressMapper.updateByPrimaryKeySelective(userAddress);
        }
        return i > 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public UserAddress queryUserAddress(String userId, String addressId) {
        UserAddress singleAddress = new UserAddress();
        singleAddress.setId(addressId);
        singleAddress.setUserId(userId);
        return userAddressMapper.selectOne(singleAddress);
    }

    private void removeDefault(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setIsDefault(0);
        Example userAddressExp = new Example(UserAddress.class);
        userAddressExp.createCriteria().andEqualTo("userId", userId);
        userAddressMapper.updateByExampleSelective(userAddress, userAddressExp);
    }
}
