package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/1/4.
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(2001, new Employee(2001, "AA", "aaa@qq.com", 1, new Department(201, "D-AA")));
        employees.put(2002, new Employee(2002, "BB", "bbb@qq.com", 0, new Department(202, "D-BB")));
        employees.put(2003, new Employee(2003, "CC", "ccc@qq.com", 1, new Department(203, "D-CC")));
        employees.put(2004, new Employee(2004, "DD", "ddd@qq.com", 0, new Department(204, "D-DD")));
        employees.put(2005, new Employee(2005, "EE", "eee@qq.com", 1, new Department(205, "D-EE")));
    }

    private static Integer initId = 2006;
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

    public void delete(Integer id) {
        employees.remove(id);
    }


}
