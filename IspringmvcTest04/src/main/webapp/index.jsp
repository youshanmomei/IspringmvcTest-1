<%--
  Created by IntelliJ IDEA.
  User: andy
  Date: 2017/1/27
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IS-mvc-04</title>
</head>
<body>
    <h3>Employee Info</h3>
    <h4><a href="success">test Success</a> //...jsp页面中没有/ 直接写跳转地址---href="success"></h4>
    <h4><a href="emps">Show Employees</a> </h4>

    <br><br>
    <a href="/i18n">I18N</a>
    <br><br>

    <%--enctype属性规定在服务器发送到表单之前应该如何对服务器进行编码--%>
    <%--multipart/form-data表示不对字符进行编码。在使用文件上传的控件表单时，一定要使用该值--%>
    <form action="/testFileUpload" enctype="multipart/form-data" method="post">
        File:<input type="file" name="file"/><br>
        Desc:<input type="text" name="desc"/><br>
        <input type="submit" name="submit"/>
    </form>

    <br>
    <a href="/testFileDownload">download</a>

    <br>
    <a href="/testJson">Json</a>

</body>
</html>
