package com.study.controller;

import com.study.pojo.Stu;
import com.study.service.impl.StuServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-04-11 23:26
 */
@RestController
@ApiIgnore
@RequestMapping("/stu")
public class StuFooController {
    @Resource
    StuServiceImpl stuServiceImpl;

    @RequestMapping("/save")
    public Object save() {
        stuServiceImpl.save();
        return "ok";
    }

    @RequestMapping("/remove/{id}")
    public Object remove(@PathVariable int id) {
        stuServiceImpl.remove(id);
        return "ok";
    }

    @RequestMapping("/update")
    public Object update(@RequestBody Stu stu) {
        stuServiceImpl.update(stu);
        return "ok";
    }

    @RequestMapping("/get/{id}")
    public Object update(@PathVariable int id) {
        return stuServiceImpl.get(id);
    }
}
