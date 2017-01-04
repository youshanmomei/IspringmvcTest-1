package org.hy.dao;

import org.hy.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/1/4.
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<Integer, Department>();

        departments.put(201, new Department(201, "D-AA"));
        departments.put(202, new Department(202, "D-BB"));
        departments.put(203, new Department(203, "D-CC"));
        departments.put(204, new Department(204, "D-DD"));
        departments.put(205, new Department(205, "D-EE"));
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }
}
