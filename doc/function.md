### Guide
这个项目会分为4个主要的部分，分别是人员管理、国际化、上传下载、json。 

要达到的效果是：
1. CRUD function for person
2. Internationalization
3. Upload & Download
4. Json  


> ### The first, people manager:

#### Before doing this project, I need to get two entities --- Department and Employee 

The Department has two variables：id,name  
The Employee has five variables: id, lastname, email, gender, department

Code:

```
public class Department {
    private Integer id;
    private String name;

    public Department() {
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

```
public class Employee {
    private Integer id;
    private String lastname;
    private String email;
    private Integer gender;
    private Department department;

    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, Integer gender, Department department) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
```

And then, create the propriate Dao---DepartmentDao,EmployeeDao  
to imitate datebase operations.

```
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<Integer, Department>();

        departments.put(701, new Department(601, "D-AA"));
        departments.put(702, new Department(602, "D-BB"));
        departments.put(703, new Department(603, "D-CC"));
        departments.put(704, new Department(604, "D-DD"));
        departments.put(705, new Department(605, "D-EE"));
    }

    public Collection<Department> getDepartments(){return departments.values();}

    public Department getDeptById(Integer key){return departments.get(key);}

}
```

```
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();

        employees.put(7001, new Employee(7001,"AA","aa@qq.com", 0, new Department(7001, "D-AA")));
        employees.put(7002, new Employee(7002,"BB","bb@qq.com", 1, new Department(7002, "D-BB")));
        employees.put(7003, new Employee(7003,"CC","cc@qq.com", 0, new Department(7003, "D-CC")));
        employees.put(7004, new Employee(7004,"DD","dd@qq.com", 1, new Department(7004, "D-EE")));
        employees.put(7005, new Employee(7005,"EE","ee@qq.com", 0, new Department(7005, "D-FF")));
    }

    private Integer initId = 7006;

    public void save(Employee employee){
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDeptById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getEmployees(){return employees.values();}

    public Employee getEmployeeById(Integer key){return employees.get(key);}

    public void delete(Integer id){employees.remove(id);}

}
```

#### And the next, what I shoud do is to create a show employees' page.

index.jsp add these codes:

```
<h4><a href="/emps">Show Employees</a> </h4>
```

EmployeeHander add these:

```   
code:
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Map<String, Object> maps){
        maps.put("employees", employeeDao.getEmployees());
        return "list";
    }
```

create list.jsp, a crude original version.

```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/1
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>employees</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h2>List Employee Info-7 page</h2>
    <c:if test="${empty employees}">e...sorry, new company and has no employee till now.</c:if>
    <c:if test="${!empty employees}">
        Employee Info(${fn:length(employees)}):<br>

        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Edit</th>
        <th>Delete</th>
            </tr>
            <c:forEach items="${employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastname}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender==0?"Female":"Male"}</td>
                    <td>${emp.department.name}</td>
                    <td><a href="#">Edit</a> </td>
                    <td><a href="#">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
```

#### When I finished list page, the next is add page.  

list.jsp

```
<a href="/emp">Add Employee</a>
```


finish jumping method.

```
code:
    
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
```

create input.jsp

```
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/3
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ism-07</title>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8">
</head>
<body>
    <h2>INPUT PAGE</h2>
    <hr />
    <form:form action="${pageContext.request.contextPath}/emp" method="post" accept-charset="utf-8" modelAttribute="employee">
        LastName:   <form:input path="lastname"/><br>
        Email:  <form:input path="email"/><br>

        <%
            Map<String, String> genders = new HashMap<String, String>();
            genders.put("0", "Female");
            genders.put("1", "Male");
            request.setAttribute("genders", genders);
        %>
        Gender: <form:radiobuttons path="gender" items="${genders}"/><br>

        Dept:   <form:select path="department.id" items="${departments}" itemLabel="name" itemValue="id"/><br>
        <input type="submit" name="submit" />
    </form:form>
</body>
</html>

```

#### The next, I need to imply delete function. 
web.xml

```
code:
    <!--post to delete or put-->
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

I need to use js to imply convert post to delete or put.  
the first, add js file into project. and register resource reference in the springmvc.xml  
springmvc.xml

```
code: 
    <!--static resource mapping-->
    <mvc:resources mapping="/scripts/**" location="/WEB-INF/scripts"/>
```

using js in list.jsp

```
head:
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("form").attr("action", href).submit();
                return false;
            });
        });
    </script>
    
    
body1:
    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>
    
    
body2:
    <td><a href="/emp/${emp.id}" class="delete">Delete</a> </td>
```

EmployeeHandler.java

```
code:
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
```


#### The next function is to imply the modify function.  
input.jsp
```
 code:
        <c:if test="${employee.id==null}">
            LastName:   <form:input path="lastname"/>
        </c:if>
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
```
list.jsp
```
<td><a href="/emp/${emp.id}">Edit</a> </td>
```
EmployeeHander.java
```
code:
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
```

> ### The second function is internationalization
the first is to add some interceptor in springmvc.xml
```
code:

    <!--to configure resolve convert-->
    <bean class="org.springframework.web.servlet.i18n.SessionLocaleResolver" id="localeResolver"/>

    <!--to configure international resource file-->
    <bean class="org.springframework.context.support.ResourceBundleMessageSource" id="messageSource">
        <property name="basename" value="i18n"/>
        <property name="defaultEncoding" value="UTF-8"/>
    </bean>

    <!--to configure international request interceptor, the request which is based on session or cookie must be configured-->
    <mvc:interceptors>
        <bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor"/>
    </mvc:interceptors>

    <!--page jumping directly do not through handler-->
    <mvc:view-controller path="/i18n" view-name="i18n"/>
    <mvc:view-controller path="/i18n2" view-name="/i18n2"/>
```

the second is to create three .properties file,namely i18n.properties,i18n_en_US.properties, i18n_zh_CN.properties.
```
i18n.properties:
    i18n.user=user
    i18n.pass=pass
    
i18n_en_US.properties:
    i18n.user=user
    i18n.pass=pass
    
i18n_zh_CN.properties:
    i18n.user=用户名
    i18n.pass=密码
```

the third is create i18n.jsp and i18n2.jsp
i18n.jsp 
```
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/4
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I18N</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
    <h1>I18N-1</h1>

    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.pass"/>
    <br><br>

    <a href="/i18n?locale=zh_CH">中文</a>
    &nbsp; | &nbsp;
    <a href="/i18n?locale=en_US">English</a>

    <hr><br>
    <a href="/i18n2">I18n2</a>

</body>
</html>
```
i18n2.jsp
```
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
 Created by IntelliJ IDEA.
 User: andy
 Date: 2017/4/4
 Time: 11:32
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>I18N2</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
   <h1>I18N-2</h1>

   <fmt:message key="i18n.user"/>
   <br><br>
   <fmt:message key="i18n.pass"/>
   <br><br>

   <hr><br>
   <a href="/i18n">I18N</a>

</body>
</html>
```
the end is to add link in index.jsp
```
<h4><a href="/i18n">I18N</a> </h4>
```

> ### The third function is upload function
the first is to add resolver in springmvc.xml
```
code:
    <!--implement upload and download function-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="maxUploadSize" value="1024000"/>
    </bean>
```

the second is to write a form in index.jsp
```
code:
    <h4>upload</h4>
    <form action="/upload" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/><br>
        Desc: <input type="text" name="desc"/><br>
        <input type="submit" value="submit"/>
    </form>
```

the third is to finish handler
```
code:
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
```
> ### The Fourth function is to imply download function
index.jsp
```
<h4><a href="/download">a.txt</a> </h4>
```

handler:
```
code:
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
```

> ### The Fifth function is to return json
index.jsp
```
<h4><a href="/testJson">Employees' List Json</a> </h4>
```

handler
``` 
code:
    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> returnJson(){
        Collection<Employee> employees = employeeDao.getEmployees();
        return employees;
    }
```