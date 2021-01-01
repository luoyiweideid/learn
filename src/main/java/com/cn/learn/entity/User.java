package com.cn.learn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "`user`")
public class User implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";
}