<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/2/7
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show employees</title>
</head>
<body>
    <h2>show Employees Info</h2>
    <c:if test="${empty employees}">
        e...no info
    </c:if>
    <c:if test="${!empty employees}">
        Employee Info(${fn:length(employees)}):<br>
        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Gender</th>
                <th>email</th>
                <th>Department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastname}</td>
                    <td>${emp.gender==0?"Female":"Male"}</td>
                    <td>${emp.email}</td>
                    <td>${emp.department.name}</td>
                    <td><a href="#">Edit</a> </td>
                    <td><a href="#">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
