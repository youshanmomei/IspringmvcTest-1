<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hy-springmvc-index</title>
</head>
<body>
    <h1>spingmvcTest-02</h1>
    <br>
    <hr>
    <a href="/emps">Show All Employees</a>
    <br>
    <hr>
    <a href="/i18n">I18N PAGE</a>
    <br>
    <hr>
    <h1>upload and download</h1>
    <%-- enctype 属性规定在发送到服务器之前应该如何对表单数据进行编码 --%>
    <%-- multipart/form-data 不对字符编码。在试用文件上传空间的表单时，必须试用该值。--%>
    <form action="/testFileUpload" method="post" enctype="multipart/form-data">
        File:   <input type="file" name="file"> <br>
        Desc:   <input type="text" name="desc"> <br>
        <input type="submit" value="submit">
    </form>
    <br>
    <hr>
    <a href="/testFileDownload">Download</a>
    &nbsp; | &nbsp;
    <a href="/testJson">Json</a>

</body>
</html>
