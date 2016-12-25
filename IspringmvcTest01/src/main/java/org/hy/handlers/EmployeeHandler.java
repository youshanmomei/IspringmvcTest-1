package org.hy.handlers;

import org.hy.dao.DepartmentDao;
import org.hy.dao.EmployeeDao;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by andy on 2016/12/24.
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * @Valid是使用hibernate validation的时候使用
     * @Validated 是只用spring  Validator 校验机制使用
     * @param employee
     * @param result
     * @param map
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee, BindingResult result, Map<String, Object> map){
//        if(result.getErrorCount()>0){
//            System.out.println("something error ...");
//            for (FieldError error : result.getFieldErrors()) {
//                System.out.println(error.getField() + "|<==============>|" + error.getDefaultMessage());
//            }
//
//            map.put("departments", departmentDao.getDepartents());
//            map.put("employee", employee);
//            return "input";
//        }


        employeeDao.save(employee);
        System.out.println("||===========>> employee: " + employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map){
        map.put("departments", departmentDao.getDepartents());
        map.put("employee", new Employee());
        return "input";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map){
        //put employee information into requestScope
        map.put("employees", employeeDao.getAll());
        return "list";
    }
}
