<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2016/12/22
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>hy-springmvc-index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h1>INPUT PAGE</h1>
    <br><hr>
    <form:form action="${pageContext.request.contextPath}/emp" modelAttribute="employee" method="post" acceptCharset="utf-8">
        <c:if test="${employee.id==null}">
            LastName:   <form:input path="lastname"/> &nbsp;
        </c:if>
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        <br>
        Email:  <form:input path="email"/>

        <br>
        <%
            Map<String, String> genders = new HashMap();
            genders.put("1", "Male");
            genders.put("0", "Female");
            request.setAttribute("genders", genders);
        %>
        Gender: <form:radiobuttons path="gender" items="${genders}"/>

        <br>
        Department: <form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"></form:select>

        <br>
        <input type="submit" value="submit"/>


    </form:form>

</body>
</html>
