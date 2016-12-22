package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2016/12/19.
 * stimulate employee operation
 */
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "E-AA", "aaa@qq.com", 1, new Department(101, "D-AA")));
        employees.put(1002, new Employee(1002, "E-BB", "bbb@qq.com", 1, new Department(102, "D-BB")));
        employees.put(1003, new Employee(1003, "E-CC", "ccc@qq.com", 0, new Department(103, "D-CC")));
        employees.put(1004, new Employee(1004, "E-DD", "ddd@qq.com", 1, new Department(104, "D-DD")));
        employees.put(1005, new Employee(1005, "E-EE", "eee@qq.com", 0, new Department(105, "D-EE")));
    }


    private static Integer initId = 1006;
    public void save(Employee employee){
        if (employee.getId() == null) {
            employee.setId(initId);
        }

        employee.setDepartment(departmentDao.getDepartent(employee.getDepartment().getId()));
        employees.put(employee.getGender(), employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }

}
