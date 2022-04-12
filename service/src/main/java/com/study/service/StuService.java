package com.study.service;

import com.study.pojo.Stu;

/**
 * The interface Stu service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -04-11 23:46
 */
public interface StuService {
    /**
     * Save.
     */
    void save();

    /**
     * Save parent.
     */
    void saveParent();

    /**
     * Save child.
     */
    void saveChild();

    /**
     * Remove.
     *
     * @param id the id
     */
    void remove(int id);

    /**
     * Update.
     *
     * @param stu the stu
     */
    void update(Stu stu);

    /**
     * Get object.
     *
     * @param id the id
     * @return the object
     */
    Object get(int id);
}
