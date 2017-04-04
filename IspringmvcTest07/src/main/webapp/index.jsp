<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/3/16
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>isp-7</title>
</head>
<body>
    <h1>hello world</h1>
    <h4><a href="/testHello">To Success Page</a> </h4>
    <h4><a href="/emps">Show Employees</a> </h4>
    <h4><a href="/i18n">I18N</a> </h4>
    <br>
    <h4>upload</h4>
    <form action="/upload" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/><br>
        Desc: <input type="text" name="desc"/><br>
        <input type="submit" value="submit"/>
    </form>

    <h4><a href="/download">a.txt</a> </h4>
    <h4><a href="/testJson">Employees' List Json</a> </h4>

</body>
</html>
