<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2016/12/22
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>hy-springmvc-index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <%--<fmt:formatNumber>标签用于格式化数字，百分比，货币。--%>
    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.password"/>
    <br><br>

    <hr>
    <br><br>
    <a href="i18n">I18N1</a>

</body>
</html>
