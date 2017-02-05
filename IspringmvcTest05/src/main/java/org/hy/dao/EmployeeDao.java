package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/2/5.
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(5001, new Employee(5001, "AA", "aa@qq.com", 1, new Department(501, "D-AA")));
        employees.put(5002, new Employee(5002, "BB", "bb@qq.com", 0, new Department(502, "D-BB")));
        employees.put(5003, new Employee(5003, "CC", "cc@qq.com", 1, new Department(503, "D-CC")));
        employees.put(5004, new Employee(5004, "DD", "dd@qq.com", 0, new Department(504, "D-DD")));
        employees.put(5005, new Employee(5005, "EE", "ee@qq.com", 1, new Department(505, "D-EE")));
    }

    public static Integer initId = 5006;
    public void save(Employee employee){
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){return employees.values();}

    public Employee get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }

}
