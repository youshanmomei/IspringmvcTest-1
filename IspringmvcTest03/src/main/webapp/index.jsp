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
    <title>hy-springmvc-index-03</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <h1>Ispringtest-03</h1>
    <hr>
    <a href="/emps">show lists</a>
    <hr>
    <a href="/i18n">国际化</a>
    <hr>
    <h1>upload and download</h1>
    <%--enctype属性规定在发送到服务期之前应该如何对表单数据进行编码--%>
    <%--multipart/form-data不对字符编码。在试用文件上传控件的表单时，必须使用该值--%>
    <form action="/testFileUpload" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/> <br>
        Desc: <input type="text" name="desc"/> <br>
        <input type="submit" value="submit">
    </form>
    <br>
    <a href="/testFileDownload">Download</a>

</body>
</html>
