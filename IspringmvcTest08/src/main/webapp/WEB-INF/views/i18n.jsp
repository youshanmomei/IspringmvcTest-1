<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/4
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>i18n</title>
</head>
<body>
    <h2>I18N</h2>

    <fmt:message key="i18n.user"/>
    <br> <br>
    <fmt:message key="i18n.pass"/>
    <br> <br>

    <a href="/i18n?locale=zh_CN">中文</a>
    &nbsp; | &nbsp;
    <a href="/i18n?locale=en_US">English</a>
    <br><br>

    <a href="/i18n2">I18N2</a>

</body>
</html>
