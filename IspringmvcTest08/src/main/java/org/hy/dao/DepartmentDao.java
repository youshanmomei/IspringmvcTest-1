package org.hy.dao;

import org.hy.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/4/4.
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    static{
        departments = new HashMap<Integer, Department>();

        departments.put(8001, new Department(8001, "D-AA"));
        departments.put(8002, new Department(8002, "D-BB"));
        departments.put(8003, new Department(8003, "D-CC"));
        departments.put(8004, new Department(8004, "D-DD"));
        departments.put(8005, new Department(8005, "D-EE"));
    }

    public Collection<Department> getDepts(){return departments.values();}

    public Department getDeptById(Integer id){return departments.get(id);}

}
