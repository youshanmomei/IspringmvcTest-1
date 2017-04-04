package org.hy.dao;

import org.hy.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/3/21.
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<Integer, Department>();

        departments.put(7001, new Department(7001, "D-AA"));
        departments.put(7002, new Department(7002, "D-BB"));
        departments.put(7003, new Department(7003, "D-CC"));
        departments.put(7004, new Department(7004, "D-DD"));
        departments.put(7005, new Department(7005, "D-EE"));
    }

    public Collection<Department> getDepartments(){return departments.values();}

    public Department getDeptById(Integer key){return departments.get(key);}

}
