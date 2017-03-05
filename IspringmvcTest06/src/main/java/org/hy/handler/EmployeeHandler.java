package org.hy.handler;

import org.hy.dao.DepartmentDao;
import org.hy.dao.EmployeeDao;
import org.hy.entities.Department;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map){
        if (id != null) {
            map.put("employee", employeeDao.getById(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map){
        map.put("employee", employeeDao.getById(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee, Map<String, Object> map){
        if (employee != null) {
            Integer depId = employee.getDepartment().getId();
            employee.setDepartment(departmentDao.getDepartmentById(depId));
            System.out.println("|006|===>>> employee:" + employee);
            employeeDao.save(employee);
        }
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map){
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

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
