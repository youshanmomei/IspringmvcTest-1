package org.hy.handler;

import org.hy.dao.DepartmentDao;
import org.hy.dao.EmployeeDao;
import org.hy.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by andy on 2017/1/27.
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Map<String, Object> map){
        map.put("employees", employeeDao.getAll());
        return "list";
    }

    @RequestMapping("/testSuccess")
    public String testSuccess(){
        return "success";
    }
}
