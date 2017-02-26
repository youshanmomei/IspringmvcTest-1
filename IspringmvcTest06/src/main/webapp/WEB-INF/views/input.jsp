<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/2/26
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add-006</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <c:if test="${employee.id==null}">
            LastName:<form:input path="lastname"/>
        </c:if>
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method"value="PUT">
        </c:if>
        <br>

        Email:<form:input path="email"/><br>

        <%
            Map<String, String> genders = new HashMap<String, String>();
            genders.put("0", "female");
            genders.put("1", "male");
            request.setAttribute("genders", genders);
        %>
        Gender:<form:radiobuttons path="gender" items="${genders}"/><br>
        Department:<form:select path="department.id" items="${departments}" itemLabel="name" itemValue="id"/> <br>

        <input type="submit" name="submit"/>
    </form:form>
</body>
</html>
