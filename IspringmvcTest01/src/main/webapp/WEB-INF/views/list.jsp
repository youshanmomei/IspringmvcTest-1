<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2016/12/24
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Employee-List</title>
</head>
<body>
    <h2>This is employee list.</h2>
    <hr>
    <c:if test="${empty requestScope.employees}">
        e...is a new company, no employee.
    </c:if>

    <c:if test="${!empty requestScope.employees}">
        Employee Info(${fn:length(employees)}):<br>
        <table border="1" cellpadding="10" cellspacing="0">
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
                    <td>${emp.department.departmentName}</td>
                    <td><a href="#">Edit</a> </td>
                    <td><a href="#">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>




</body>
</html>
