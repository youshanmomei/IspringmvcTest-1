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

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(6001, new Employee(6001, "AA", "aa@qq.com", 1, new Department(6001, "D-AA")));
        employees.put(6002, new Employee(6002, "BB", "bb@qq.com", 0, new Department(6002, "D-BB")));
        employees.put(6003, new Employee(6003, "CC", "cc@qq.com", 1, new Department(6003, "D-CC")));
        employees.put(6004, new Employee(6004, "DD", "dd@qq.com", 0, new Department(6004, "D-DD")));
        employees.put(6005, new Employee(6005, "EE", "ee@qq.com", 1, new Department(6005, "D-EE")));

    }

    private Integer initId = 6006;
    public void save(Employee employee){
        if(employee.getId()==null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){return employees.values();}

    public Employee getById(Integer id){
        return employees.get(id);
    }

    public Employee delete(Integer id){
        return employees.remove(id);
    }
}
