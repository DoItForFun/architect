package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-10 17:52
 */
@RestController
@ApiIgnore
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }
}
