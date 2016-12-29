<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2016/12/25
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/emp" method="POST" modelAttribute="employee" acceptCharset="utf-8">
        <c:if test="${employee.id==null}">
            LastName:<form:input path="lastname"/> &nbsp;
            <form:errors path="lastname"/><br>
        </c:if>
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        <br>
        Email :<form:input path="email"/>&nbsp;
        <form:errors path="email"/>
        <br>
       <%
            Map<String, String> genders = new HashMap();
            genders.put("1", "Male");
            genders.put("0", "Female");
            request.setAttribute("genders", genders);
        %>
        Gender : <form:radiobuttons path="gender" items="${genders}"/><br>
        Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/><br>
        <%--Birth :<form:input path="birth"/> &nbsp;<br>--%>
        <%--Salary:<form:input path="salary"/>&nbsp;<br>--%>

        <input type="submit" value="submit" />
    </form:form>
</body>
</html>
