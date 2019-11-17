package com.xt.mp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.mp.bean.Employee;
import com.xt.mp.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MpSpringbootTestApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testDelete () {
        // 1. 根据 id 进行删除
        Integer result = employeeMapper.deleteById(6);
        System.out.println(result);

        // 2. 根据条件进行删除
//        Map<String, Object> map = new HashMap<>();
//        map.put("last_name", "sanae");
//        map.put("gender", "0");
//        Integer result2 = employeeMapper.deleteByMap(map);
//        System.out.println(result2);

        // 3. 批量删除
//        Integer result3 = employeeMapper.deleteBatchIds(Arrays.asList(8, 14));
//        System.out.println(result3);
    }
    @Test
    public void testSelect () {
        // 1. 通过 id 查询
        Employee employee = employeeMapper.selectById(7);
        System.out.println(employee);

        System.out.println("===========");

        // 2. 通过多个 id 进行查询
        List<Employee> employees = employeeMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        employees.forEach(System.out::println);

        System.out.println("===========");

        /**
         * 3. 通过 Map 封装条件查询
         * Map 的 key 写数据库中的列名
         */
        Map<String, Object> map = new HashMap<>();
        map.put("last_name", "sanae");
        map.put("gender", "0");
        List<Employee> employees1 = employeeMapper.selectByMap(map);
        employees1.forEach(System.out::println);

        System.out.println("==========");

        // 5. 分页查询
        IPage<Employee> employeeIPage = employeeMapper.selectPage(new Page<>(3, 2), null);
        employeeIPage.getRecords().forEach(System.out::println);

    }

    @Test
    public void testUpdate () {
        Employee employee = new Employee();
        employee.setId(7);
        employee.setLastName("sanae");
        employee.setEmail("sanae@163.com");
        employee.setAge(18);
        employee.setGender("0");

        int result = employeeMapper.updateById(employee);
        System.out.println(result);
    }

    @Test
    public void testInsert () {
        Employee employee = new Employee();
        employee.setLastName("sanae");
        employee.setEmail("sanae@163.com");
        employee.setAge(18);
        employee.setGender("0");
        employee.setSalary(20000.0);

        int result = employeeMapper.insert(employee);
        System.out.println(result);

        // 获取当前数据在数据库中的主键值
        Integer key = employee.getId();
        System.out.println("key: " + key);
    }

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
    }

}
