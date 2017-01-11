package org.hy.dao;

import org.hy.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/1/11.
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<Integer, Department>();

        departments.put(301, new Department(301, "D-AA"));
        departments.put(302, new Department(302, "D-BB"));
        departments.put(303, new Department(303, "D-CC"));
        departments.put(304, new Department(304, "D-DD"));
        departments.put(305, new Department(305, "D-EE"));
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartmentById(Integer key){
        return departments.get(key);
    }
}
