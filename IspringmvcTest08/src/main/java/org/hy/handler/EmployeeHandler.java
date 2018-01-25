package org.hy.handler;

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
 * Created by andy on 2017/4/4.
 */
@Controller
public class EmployeeHandler {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("download")
    public ResponseEntity<byte[]> fileDownload(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/files/a.txt");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);

        //add head
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileInfo.getName(),"UTF-8"));//遇到中文名，需要encode

        //add body
        HttpStatus ok = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, ok);

        return responseEntity;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file")MultipartFile file, @RequestParam("desc") String desc, HttpServletRequest request) throws IOException {
        System.out.println("|-> fileName: " + file.getOriginalFilename());
        System.out.println("|-> desc: " + desc);

        InputStream is = file.getInputStream();
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/" + new Date().getTime() + "_" + file.getOriginalFilename());
        System.out.println("|-> path: " + realPath);

        File uFile = new File(realPath);
        FileOutputStream out = new FileOutputStream(uFile);

        byte[] bytes = new byte[1024 * 8];
        int len=-1;
        while((len=is.read(bytes))!=-1){
            out.write(bytes, 0, len);
        }

        out.flush();
        out.close();
        is.close();

        return "success";
    }


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map){
        if (id != null) {
            map.put("employee", employeeDao.getEmpById(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> maps){
        maps.put("departments", departmentDao.getDepts());
        maps.put("employee", employeeDao.getEmpById(id));
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
            Integer id = employee.getDepartment().getId();
            employee.setDepartment(departmentDao.getDeptById(id));
            employeeDao.save(employee);
        }
        return "redirect:/emps";
    }


    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> maps){
        maps.put("departments", departmentDao.getDepts());
        maps.put("employee", new Employee());
        return "input";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> maps){
        maps.put("employees", employeeDao.getEmployees());
        return "list";
    }

    @ResponseBody
    @RequestMapping("/returnJson")
    public Collection<Employee> returnJson(){
        Collection<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    @RequestMapping("/success")
    public String toSuccess(){
        return "success";
    }
}
