package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/4/4.
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(8001, new Employee(8001, "aa", "aa@qq.com", 0, new Department(8001, "D-AA")));
        employees.put(8002, new Employee(8002, "bb", "bb@qq.com", 1, new Department(8002, "D-BB")));
        employees.put(8003, new Employee(8003, "cc", "cc@qq.com", 0, new Department(8003, "D-CC")));
        employees.put(8004, new Employee(8004, "dd", "dd@qq.com", 1, new Department(8004, "D-DD")));
        employees.put(8005, new Employee(8005, "ee", "ee@qq.com", 0, new Department(8005, "D-EE")));
    }

    public Collection<Employee> getEmployees(){return employees.values();}

    public Employee getEmpById(Integer id){return employees.get(id);}

    public void delete(Integer id){employees.remove(id);}

    private Integer initId=8006;
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDeptById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
}
