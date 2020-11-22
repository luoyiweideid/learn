package com.mybatis.generator.controller;

import com.mybatis.generator.entity.User;
import com.mybatis.generator.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试入口
 * @author : rain
 * @date : 2020-11-18 22:13
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private UserService userService;
    @RequestMapping("")
    public String helloWorld(){
        return "hello World";
    }

    @GetMapping("/user")
    public User getUserById(@RequestParam(value = "id")Integer id){
        return userService.getById(id);
    }
}

