<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/1/27
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IS-mvc-05</title>
</head>
<body>
    <h3>Employees Info</h3>
    <h4><a href="testSuccess">Test</a></h4>
    <h4><a href="emps">Show Employees</a> </h4>
    <br>
    <a href="/i18n">Internationalization</a>

    <br><br>
    <form action="/testFileUpload" enctype="multipart/form-data" method="post">
        File:<input type="file" name="file"/> <br>
        Desc:<input type="text" name="desc"/> <br>
        <input type="submit" name="submit"/>
    </form>

    <br><br>
    <a href="/testFileDownload">testDownload</a>

    <br><br>
    <a href="/testJson">testJson</a>
</body>
</html>
