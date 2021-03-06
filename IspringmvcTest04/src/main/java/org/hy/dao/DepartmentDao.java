package org.hy.dao;

import org.hy.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/2/3.
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    static{
        departments = new HashMap<Integer, Department>();

        departments.put(401, new Department(401, "D-AA"));
        departments.put(402, new Department(402, "D-BB"));
        departments.put(403, new Department(403, "D-CC"));
        departments.put(404, new Department(404, "D-DD"));
        departments.put(405, new Department(405, "D-EE"));
    }

    public Collection<Department> getDepartments(){return departments.values();}

    public Department getDepartmentById(Integer key){return departments.get(key);}

}
