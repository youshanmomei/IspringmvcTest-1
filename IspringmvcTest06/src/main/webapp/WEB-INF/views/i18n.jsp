<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/3/10
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>i18n-1</title>
</head>
<body>
    <h1>I18N-1</h1>

    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.pass"/>
    <br><br>

    <a href="/i18n?locale=zh_CN">Chinese</a>
    &nbsp; | &nbsp;
    <a href="/i18n?locale=en_US">English</a>
    <br><br>

    <a href="/i18n2">I18N-2</a>

</body>
</html>
