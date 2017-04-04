<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/4/4
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sp-08</title>
</head>
<body>
    <h3>Hello world, from sp-08</h3>
    <h4><a href="/success">To Success</a> </h4>
    <h4><a href="/emps">Show Employees</a> </h4>
    <h4><a href="/i18n">I18N</a> </h4>
    <h4>upload</h4>
    <form action="/upload" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/><br>
        Desc: <input type="text" name="desc"/><br>
        <input type="submit" name="submit"/>
    </form>

    <h4><a href="/download">a.txt</a> </h4>
    <h4><a href="/returnJson">get Employee Json</a> </h4>
</body>
</html>
