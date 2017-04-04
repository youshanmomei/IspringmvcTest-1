package org.hy.handler;

import org.hy.dao.DepartmentDao;
import org.hy.dao.EmployeeDao;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * Created by andy on 2017/3/16.
 */
@Controller
public class EmployeeHander {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    /**
     * the method is help for the method of update.
     * and be created when create input page.
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String,Object> map) {
        if (id!=null) {
            map.put("employee", employeeDao.getEmployeeById(id));
        }
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> returnJson(){
        Collection<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/files/a.txt");
        byte[] body = new byte[in.available()];
        in.read(body);

        //add head
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", " attachment;filename=a.txt");

        //add body
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<>(body, headers, statusCode);

        return response;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam(value = "desc", required = false) String desc, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("|-> desc: " + desc);
        System.out.println("|-> file: " + file.getOriginalFilename());

        InputStream in = file.getInputStream();
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/files/" + new Date().getTime() + "_" + file.getOriginalFilename());
        System.out.println("|-> filePath: " + path);

        File uFile = new File(path);
        FileOutputStream out = new FileOutputStream(uFile);

        byte[] bytes = new byte[1024 * 8];
        int len=-1;
        while((len=in.read(bytes))!=-1){
            out.write(bytes,0, len );
        }

        out.flush();
        out.close();
        in.close();

        return "success";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map){
        map.put("employee", employeeDao.getEmployeeById(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee){
        if (employee != null) {
            //select tag just get id, so need use method to set department info.
            Integer id = employee.getDepartment().getId();
            System.out.println("|007|=>> id: " + id);
            employee.setDepartment(departmentDao.getDeptById(id));
            System.out.println("|007|=>> employee: " + employee);
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
    public String list(Map<String, Object> maps){
        maps.put("employees", employeeDao.getEmployees());
        return "list";
    }

    @RequestMapping("/testHello")
    public String testHello(){
        return "success";
    }
}
