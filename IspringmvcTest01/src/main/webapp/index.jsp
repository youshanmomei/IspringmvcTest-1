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
    <title>hy-springmvc-index</title>
</head>
<body>
    <h1>let's start springmvc journal</h1>
    <br>
    <hr>
    <a href="/emps">Show All Employees</a>
    &nbsp;&nbsp; | &nbsp;&nbsp;
    <a href="/i18n">I18N PAGE</a>

    <br>
    <hr>
    <h2>upload & download test</h2>
    <%-- enctype 属性规定在发送到服务器之前应该如何对表单数据进行编码。--%>
    <%-- multipart/form-data	 不对字符编码。 在使用包含文件上传控件的表单时，必须使用该值。--%>
    <form action="testFileUpload" method="post" enctype="multipart/form-data">
        File:<input type="file" name="file"> <br>
        Desc:<input type="text" name="desc"><br>
        <input type="submit" value="submit">
    </form>
    <hr>
    <br>
    <a href="/testDownload">FILE DOWNLOAD</a>
    &nbsp;&nbsp; | &nbsp;&nbsp;
    <a href="/testJson">TEST JSON</a>



</body>
</html>
