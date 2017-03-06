<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="key" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/3/6
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I18N-1</title>
</head>
<body>
    <H1>I18N-1</H1>
    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.password"/>
    <br><br>

    <a href="/i18n?locale=zh_CH">中文</a>
    &nbsp; | &nbsp;
    <a href="/i18n?locale=en_US">英文</a>

    <br><br>
    <a href="/i18n2">i18n2</a>

</body>
</html>
