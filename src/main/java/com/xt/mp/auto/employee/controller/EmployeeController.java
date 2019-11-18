package com.xt.mp.auto.employee.controller;


import com.xt.mp.auto.employee.entity.Employee;
import com.xt.mp.auto.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> list() {
        List<Employee> list = employeeService.list();
        return list;
    }
}

