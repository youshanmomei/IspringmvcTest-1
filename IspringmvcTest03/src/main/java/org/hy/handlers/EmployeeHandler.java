package org.hy.handlers;

import org.hy.dao.DepartmentDao;
import org.hy.dao.EmployeeDao;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Created by andy on 2017/1/11.
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/testJson")
    @ResponseBody
    public Collection<Employee> testJson() {
        Collection<Employee> employees = employeeDao.getAll();
        return employees;
    }

    @RequestMapping("/testFileDownload")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/files/a.txt");
        body = new byte[in.available()];
        in.read(body);

        //add head
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", " attachment;filename=a.txt");

        //add body
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<>(body, headers, statusCode);

        return response;
    }

    @RequestMapping(value = "/testFileUpload", method = RequestMethod.POST)
    public String testFileUpload(@RequestParam(value = "desc", required = false) String desc, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("|--->>>desc: " + desc);
        System.out.println("|--->>>file: " + file.getOriginalFilename());

        InputStream in = file.getInputStream();
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/files/" + new Date().getTime() + "_" + file.getOriginalFilename());
        System.out.println("|--->>>filePath: " + path);

        File ufile = new File(path);
        FileOutputStream out = new FileOutputStream(ufile);

        byte[] bytes = new byte[1024 * 8];
        int len=-1;
        while((len=in.read(bytes))!=-1){
            out.write(bytes, 0, len);
        }

        out.flush();
        out.close();
        in.close();

        return "success";
    }

    /**
     * the method is help for the method of update
     */
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map){
        if (id != null) {
            map.put("employee", employeeDao.get(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        //要加'/'，不然就会报如下错误：
        //HTTP Status 405 - Request method 'GET' not supported
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee, Map<String, Object> map){
        if (employee != null) {
            //select tag just get id, so need use method to set department info.
            Integer depId = employee.getDepartment().getId();
            employee.setDepartment(departmentDao.getDepartmentById(depId));
            System.out.println("|0003|===>>> employee: " + employee);
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
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        return "list";
    }
}
