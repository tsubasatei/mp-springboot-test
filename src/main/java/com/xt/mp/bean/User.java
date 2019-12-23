package com.xt.mp.bean;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class User {
    private Integer id;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String username;

    // 逻辑删除属性
    @TableLogic
    private Integer logicFlag;

}
