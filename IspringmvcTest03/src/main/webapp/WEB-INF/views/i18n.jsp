<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2016/12/22
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hy-springmvc</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h1>I18N-1</h1>
    <%--<fmt:formatNumber>该标签用于格式化数字，百分比，货币</fmt:formatNumber>--%>
    <fmt:message key="i18n.user"/>
    <br><br>
    <fmt:message key="i18n.password"/>
    <br><br>

    <%--properties中 中文用这两个配置文件名都可以实现 i18n_zh_CN.properties、i18n_zh_CH.properties--%>
    <a href="/i18n?locale=zh_CH">中文</a>
    &nbsp; | &nbsp;
    <a href="/i18n?locale=en_US">英文</a>

    <hr>
    <br><br>
    <a href="/i18n2">I18N2</a>


</body>
</html>
