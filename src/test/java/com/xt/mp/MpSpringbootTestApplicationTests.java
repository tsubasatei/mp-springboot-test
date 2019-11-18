package com.xt.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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


    /**
     * AR  分页复杂操作
     */
    @Test
    public void  testARPage() {
        Employee employee = new Employee();
        IPage<Employee> employeeIPage = employee.selectPage(new Page<>(1, 2),
                new QueryWrapper<Employee>().like("last_name", "e"));
        employeeIPage.getRecords().forEach(System.out::println);
    }

    /**
     * AR 删除操作
     *
     * 注意: 删除不存在的数据 逻辑上也是属于成功的.
     */
    @Test
    public void testARDelete() {
        Employee employee = new Employee();
//        boolean result = employee.deleteById(20);

//        employee.setId(17);
//        boolean result = employee.deleteById();
//        System.out.println("result: " + result);

        // like 不区分大小写
        boolean result = employee.delete(new QueryWrapper<Employee>().like("last_name", "sanae"));
        System.out.println("result: " + result);

    }

    /**
     * AR 查询操作
     */
    @Test
    public void testARSelect () {
        Employee employee = new Employee();
//        Employee emp = employee.selectById(20);

        employee.setId(22);
        Employee emp = employee.selectById();
        System.out.println(emp);

        System.out.println("===========");

        List<Employee> employees = employee.selectAll();
        employees.forEach(System.out::println);

        System.out.println("===========");
        List<Employee> list = employee.selectList(new QueryWrapper<Employee>().like("last_name", "e"));
        list.forEach(System.out::println);

        System.out.println("==========");

        int count = employee.selectCount(new QueryWrapper<Employee>().eq("gender", 0));
        System.out.println("count: " + count);
    }

    /**
     * AR  修改操作
     */
    @Test
    public void testARUpdate () {
        Employee employee = new Employee();
        employee.setId(22);
        employee.setLastName("Sanae2");
        employee.setEmail("sanae2@163.com");
        employee.setGender("1");
        employee.setAge(30);
        boolean result = employee.updateById();
        System.out.println("result: " + result);
    }


    /**
     * AR  插入操作
     */
    @Test
    public void testARInsert () {
        Employee employee = new Employee();
        employee.setLastName("Sanae");
        employee.setEmail("sanae@163.com");
        employee.setGender("0");
        employee.setAge(30);
        boolean result = employee.insert();
        System.out.println("result: " + result);
    }
    
    /**
     * 条件构造器 删除操作
     */
    @Test
    public void testQueryWrapperDelete () {
        employeeMapper.delete(new QueryWrapper<Employee>()
                .eq("last_name", "Jerry")
                .eq("age", 30));
    }

    /**
     * 条件构造器 修改操作
     */
    @Test
    public void testQueryWrapperUpdate () {
        Employee employee = new Employee();
        employee.setLastName("Jerry");
        employee.setEmail("jerry@163.com");
        employee.setGender("0");
        employeeMapper.update(employee,
                new QueryWrapper<Employee>()
                        .eq("last_name", "Jerry")
                        .eq("age", 30));
    }

    /**
     * 条件构造器 查询操作
     */
    @Test
    public void testQueryWrapperSelect () {
        // 分页查询 tbl_employee 表中，年龄在18~50之间且性别为男且姓名为 Tom 的所有用户
        IPage<Employee> employeeIPage = employeeMapper.selectPage(new Page<>(1, 2),
                new QueryWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", "1")
                        .eq("last_name", "Tom"));
        employeeIPage.getRecords().forEach(System.out::println);

        System.out.println("==============");

        // 查询 tbl_employee 表中，性别为女并且名字中带有“老师” 或者邮箱中带有“a”

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Employee::getGender, "0")
                .like(Employee::getLastName, "老师")
                .or()
                .like(Employee::getEmail, "a");

        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        employees.forEach(System.out::println);

/*

        List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>()
                .eq("gender", "0")
                .like("last_name", "老师")
//                .or()       // gender = ? AND last_name LIKE ? OR email LIKE ?
                .or(employeeQueryWrapper -> employeeQueryWrapper.like("email", "a")));  // gender = ? AND last_name LIKE ? OR ( (email LIKE ?) )

        employees.forEach(System.out::println);
*/

        System.out.println("==============");

        // 查询性别为女的，根据 age 进行排序（asc/desc），简单分页
        List<Employee> employees2 = employeeMapper.selectList(new QueryWrapper<Employee>()
                        .eq("gender", "0")
                        .orderByDesc("age")
                        .last("limit 1, 3")
        );
        employees2.forEach(System.out::println);
    }


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
