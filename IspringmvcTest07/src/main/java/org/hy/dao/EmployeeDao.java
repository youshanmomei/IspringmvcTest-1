package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/4/1.
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(7001, new Employee(7001,"AA","aa@qq.com", 0, new Department(7001, "D-AA")));
        employees.put(7002, new Employee(7002,"BB","bb@qq.com", 1, new Department(7002, "D-BB")));
        employees.put(7003, new Employee(7003,"CC","cc@qq.com", 0, new Department(7003, "D-CC")));
        employees.put(7004, new Employee(7004,"DD","dd@qq.com", 1, new Department(7004, "D-EE")));
        employees.put(7005, new Employee(7005,"EE","ee@qq.com", 0, new Department(7005, "D-FF")));
    }

    private Integer initId = 7006;

    public void save(Employee employee){
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDeptById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getEmployees(){return employees.values();}

    public Employee getEmployeeById(Integer key){return employees.get(key);}

    public void delete(Integer id){employees.remove(id);}

}
