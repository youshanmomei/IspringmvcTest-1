<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/4
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
    <meta http-equiv="content-type" charset="UTF-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
//                alert("hello world " + href);
                $("form").attr("action", href).submit();
                return false;
            });
        });
    </script>
</head>
<body>
    <h3>List Employee Info-08</h3>

    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>

    <c:if test="${empty employees}">e...sorry, this new company has no employees</c:if>
    <c:if test="${!empty employees}">
        Employee Info(${employees.size()}):<br>
        <table border="1" cellspacing="0" cellpadding="10">
            <tr>
                <th>Id</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Dept</th>
                <th>Editor</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastname}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender==0?"Female":"Male"}</td>
                    <td>${emp.department.name}</td>
                    <td><a href="/emp/${emp.id}">Edit</a> </td>
                    <td><a href="/emp/${emp.id}" class="delete">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <h3><a href="/emp">add employee</a> </h3>

</body>
</html>
