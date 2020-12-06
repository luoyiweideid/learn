package com.cn.learn.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端测试实体
 * @author : rain
 * @date : 2020-12-06 15:09
 */
@Data
public class TestDTO implements Serializable {
    /**
     * 描述
     */
    private String desc;
}
