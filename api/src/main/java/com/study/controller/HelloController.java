package com.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-10 17:52
 */
@RestController
@ApiIgnore
public class HelloController {
    final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/hello")
    public String hello(){
        logger.info("hhhhh");
        return "hello world!";
    }

    @RequestMapping("/setSession")
    public Object setSession(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("userInfo", "new user");
        httpSession.setMaxInactiveInterval(3600);
        httpSession.getAttribute("userInfo");
//        httpSession.removeAttribute("userInfo");
        return "ok";
    }
}
