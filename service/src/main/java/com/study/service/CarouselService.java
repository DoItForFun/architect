package com.study.service;

import com.study.pojo.Carousel;

import java.util.List;

/**
 * The interface Carousel service.
 *
 * @author lizhe
 * @version 1.0.0 Created by IDEA
 * @date 2022 -11-15 09:46
 */
public interface CarouselService {

    /**
     * Query all.
     *
     * @param isShow the is show
     * @return the list
     */
    List<Carousel> queryAll(Integer isShow);
}
