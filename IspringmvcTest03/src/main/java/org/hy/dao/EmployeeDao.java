package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/1/13.
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(3001, new Employee(3001, "AA", "aaa@qq.com", 1, new Department(301, "D-AA")));
        employees.put(3002, new Employee(3002, "BB", "bbb@qq.com", 0, new Department(302, "D-BB")));
        employees.put(3003, new Employee(3003, "CC", "ccc@qq.com", 1, new Department(303, "D-CC")));
        employees.put(3004, new Employee(3004, "DD", "ddd@qq.com", 0, new Department(304, "D-DD")));
        employees.put(3005, new Employee(3005, "EE", "eee@qq.com", 1, new Department(305, "D-EE")));
    }

    private static Integer initId = 3006;
    public void save(Employee employee){
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee get(Integer id){
        return employees.get(id);
    }

    public Employee delete(Integer id){ return employees.remove(id); }


}
