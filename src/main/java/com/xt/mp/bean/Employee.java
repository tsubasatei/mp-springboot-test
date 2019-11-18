package com.xt.mp.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Employee extends Model<Employee> {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Integer age;

    @TableField(exist = false)
    private Double salary;
}
