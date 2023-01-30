package com.study.service;

import com.study.pojo.BO.AddressBO;
import com.study.pojo.UserAddress;

import java.util.List;

/**
 * The interface User address service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2023 -01-30 09:04
 */
public interface UserAddressService {
    /**
     * Save.
     *
     * @param addressBO the address bo
     */
    void save(AddressBO addressBO);

    /**
     * List list.
     *
     * @param userId the user id
     * @return the list
     */
    List<UserAddress> list(String userId);

    /**
     * Delete boolean.
     *
     * @param userId    the user id
     * @param addressId the address id
     * @return the boolean
     */
    boolean delete(String userId, String addressId);

    /**
     * Sets default.
     *
     * @param userId    the user id
     * @param addressId the address id
     * @return the default
     */
    boolean setDefault(String userId, String addressId);

    /**
     * Query user address user address.
     *
     * @param userId    the user id
     * @param addressId the address id
     * @return the user address
     */
    UserAddress queryUserAddress(String userId, String addressId);
}
