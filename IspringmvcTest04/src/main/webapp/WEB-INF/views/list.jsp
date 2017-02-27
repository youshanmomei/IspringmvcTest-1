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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                $("form").attr("action", href).submit();
//                alert("hello world");
                return false;
            });
        });
    </script>
</head>
<body>
    <h3>show employee info</h3>

    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>

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
                    <td>${emp.department.name}</td>
                    <td><a href="#">Edit</a> </td>
                    <td><a href="/emp/${emp.id}" class="delete">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>

    </c:if>
    <br>
    <h4><a href="emp">add employee</a> </h4>

</body>
</html>
