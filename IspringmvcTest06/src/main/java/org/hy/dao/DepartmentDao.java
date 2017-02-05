package org.hy.dao;

import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/2/5.
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    static{
        departments = new HashMap<Integer, Department>();

        departments.put(601, new Department(601, "D-AA"));
        departments.put(602, new Department(602, "D-BB"));
        departments.put(603, new Department(603, "D-CC"));
        departments.put(604, new Department(604, "D-DD"));
        departments.put(605, new Department(605, "D-EE"));
    }

    public Collection<Department> getDepartments(){return departments.values();}

    public Department getDepartmentById(Integer key){
        return departments.get(key);
    }

}
