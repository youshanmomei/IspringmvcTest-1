package org.hy.dao;

import org.hy.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andy on 2017/2/5.
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments=null;

    static{
        departments = new HashMap<Integer, Department>();

        departments.put(501, new Department(501, "D-AA"));
        departments.put(502, new Department(502, "D-BB"));
        departments.put(503, new Department(503, "D-CC"));
        departments.put(504, new Department(504, "D-DD"));
        departments.put(505, new Department(505, "D-EE"));
    }

    public Collection<Department> getAll(){return departments.values();}

    public Department getDepById(Integer key){return departments.get(key);}
}
