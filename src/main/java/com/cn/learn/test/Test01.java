package com.cn.learn.test;

import com.alibaba.fastjson.JSON;
import com.cn.learn.entity.Test;

/**
 * @author : rain
 * @date : 2020-12-07 16:03
 */
public class Test01 {

    public static void main(String[] args) {
        Test test = new Test();
        test.setDesc("1223");
        String value = JSON.toJSONString(test);
        System.out.println(value);
        //测试修改 - 分支冲突
        System.out.println(value);
    }
}
