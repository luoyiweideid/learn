package com.cn.learn.controller;

import com.cn.learn.services.TestService;
import com.cn.learn.dto.TestDTO;
import com.cn.learn.entity.Test;
import com.cn.learn.entity.User;
import com.cn.learn.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private TestService testService;

    /**
     * helloWorld
     * @return helloWorld
     */
    @RequestMapping("")
    public String helloWorld(){
        return "hello World";
    }

    /**
     * 测试
     * @param id 主键id
     * @return 用户信息
     */
    @GetMapping("/user")
    public User getUserById(@RequestParam(value = "id")Integer id){
        return userService.getById(id);
    }

    /**
     * 创建索引
     * @param dto 实体
     */
    @PostMapping("/index")
    public void addTestIndexByName(@RequestBody TestDTO dto) {
        testService.addTestIndexByName(dto);
    }

    /**
     * 查询
     * @param keyWord 关键字
     * @return 集合
     */
    @GetMapping("/index")
    public List<Test> getByTestName(@RequestParam(value = "keyWord")String keyWord) {
        return testService.getByDesc(keyWord);
    }

}

