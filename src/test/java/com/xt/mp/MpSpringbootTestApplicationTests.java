package com.xt.mp;

import com.xt.mp.bean.Employee;
import com.xt.mp.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MpSpringbootTestApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
    }

}
