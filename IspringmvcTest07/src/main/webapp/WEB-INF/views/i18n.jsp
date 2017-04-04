<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/4
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I18N</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
    <h1>I18N-1</h1>

    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.pass"/>
    <br><br>

    <a href="/i18n?locale=zh_CH">中文</a>
    &nbsp; | &nbsp;
    <a href="/i18n?locale=en_US">English</a>

    <hr><br>
    <a href="/i18n2">I18n2</a>


</body>
</html>
