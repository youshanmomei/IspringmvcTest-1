package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/2/3.
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(4001, new Employee(4001, "AA", "aaa@qq.com", 1, new Department(401, "D-AA")));
        employees.put(4002, new Employee(4002, "BB", "bbb@qq.com", 0, new Department(402, "D-BB")));
        employees.put(4003, new Employee(4003, "CC", "ccc@qq.com", 1, new Department(403, "D-CC")));
        employees.put(4004, new Employee(4004, "DD", "ddd@qq.com", 0, new Department(404, "D-DD")));
        employees.put(4005, new Employee(4005, "EE", "eee@qq.com", 1, new Department(405, "D-EE")));
    }

    private static Integer initId = 4006;

    //save() getAll() get() delete()
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee getById(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }

}
