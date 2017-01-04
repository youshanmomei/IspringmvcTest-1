<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2016/12/22
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>employees</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h1>show employee info</h1>
    <c:if test="${empty employees}">
        e... no employee
    </c:if>

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
                    <td>${emp.department.departmentName}</td>
                    <td><a href="#">Edit</a> </td>
                    <td><a href="#">Delete</a> </td>
                </tr>
            </c:forEach>

        </table>

    </c:if>


</body>
</html>