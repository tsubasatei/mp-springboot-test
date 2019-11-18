package com.xt.mp.auto.employee.mapper;

import com.xt.mp.auto.employee.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xt
 * @since 2019-11-18
 */
@Component("autoMapper")
public interface EmployeeMapper extends BaseMapper<Employee> {

}
