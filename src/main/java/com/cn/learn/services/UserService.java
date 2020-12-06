package com.cn.learn.services;

import com.cn.learn.mapper.UserMapper;
import com.cn.learn.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息 - service层
 * @author : rain
 * @date : 2020-11-19 22:17
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询 - 根据id
     * @param id id
     * @return 用户信息
     */
    public User getById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 设置默认添加用户信息
     */
    public void saveUser(){
        User user = new User();
        user.setAge(18);
        user.setName("张三");
        userMapper.insertSelective(user);
    }
}
