package org.hy.handlers;

import org.hy.dao.DepartmentDao;
import org.hy.dao.EmployeeDao;
import org.hy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Created by andy on 2016/12/24.
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @ResponseBody
    @RequestMapping(value = "/testJson", method = RequestMethod.GET)
    public Collection<Employee> testJson(){
        Collection<Employee> employees = employeeDao.getAll();
        return employees;
    }

    /**
     * 下载
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/testDownload")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/files/a.txt");
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=a.txt");

        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);

        return response;
    }

    /**
     * 上传
     * @param desc
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/testFileUpload", method = RequestMethod.POST)
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("|------>>>>> upload file, and file's desc: " + desc);
        System.out.println("|------>>>>> OriginalFileName: " + file.getOriginalFilename());

        InputStream in = file.getInputStream();
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/files/" + new Date().getTime() + "_" + file.getOriginalFilename());
        System.out.println("|------>>>>> file path: " + path );
        File ufile = new File(path);
        FileOutputStream out = new FileOutputStream(ufile);

        byte[] bytes = new byte[1024 * 8];
        int len = -1;
        while((len=in.read(bytes))!=-1){
            out.write(bytes, 0, len);
        }

        out.flush();
        out.close();
        in.close();

        return "success";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map){
        if (id!=null)
            map.put("employee", employeeDao.get(id));
    }

    @RequestMapping(value="/emp", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }


    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartents());
        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    /**
     * @Valid是使用hibernate validation的时候使用
     * @Validated 是只用spring  Validator 校验机制使用
     * @param employee
     * @param result
     * @param map
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result, Map<String, Object> map){
        if(result.getErrorCount()>0){
            System.out.println("something error ...");
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + "|<==============>|" + error.getDefaultMessage());
            }

            map.put("departments", departmentDao.getDepartents());
            map.put("employee", employee);
            return "input";
        }


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
