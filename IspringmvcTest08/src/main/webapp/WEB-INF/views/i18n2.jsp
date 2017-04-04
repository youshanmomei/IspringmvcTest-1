<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/4
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>I18N2</title>
    <meta http-equiv="content-type" charset="UTF-8">
</head>
<body>
    <h2>I18N2</h2>

    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.pass"/>
    <br><br>

    <a href="/i18n">I18N</a>
</body>
</html>
