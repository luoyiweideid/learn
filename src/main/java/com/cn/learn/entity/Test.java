package com.cn.learn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "test")
public class Test implements Serializable {
    public static final String COL_T_ID = "t_id";
    public static final String COL_TEST_NAME = "test_name";
    /**
     * 主键id
     */
    @TableId(value = "test_id", type = IdType.INPUT)
    private Integer testId;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 电话号码
     */
    @TableField(value = "phone")
    private String phone;

    private static final long serialVersionUID = 1L;

    public static final String COL_TEST_ID = "test_id";

    public static final String COL_DESC = "desc";

    public static final String COL_PHONE = "phone";
}