<%--
  Created by IntelliJ IDEA.
  User: Vinod
  Date: 4/29/2020
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload file</title>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.4.1-1/css/bootstrap.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
    <h1 class="text-center">Upload File</h1>
    <form action="${pageContext.request.contextPath}/uploadFile" method="post" enctype="multipart/form-data">
        <div class="form-group" >
            <input type="file" class="form-control-file form-control p-1" name="file"/>
        </div>
        <button type="submit" class="btn btn-primary">upload File</button>
    </form>
</div>

<div class="alert-info">
    ${msg}
</div>
</body>
</html>
