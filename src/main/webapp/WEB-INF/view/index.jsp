<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        tr {
            height: 50px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Upload File</h1>
    <form action="${pageContext.request.contextPath}/uploadFile" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="file" class="form-control-file form-control p-1" name="file" required/>
        </div>
        <button type="submit" class="btn btn-primary d-flex justify-content-end">upload File</button>
    </form>
</div>
<div class="alert-success m-2">
    ${msg}
</div>
<div class="container-fluid table-responsive">
    <table class="table-success text-center w-100">
        <thead>
        <tr>
            <th>Sr no.</th>
            <th>File name</th>
            <th>File Updated Date</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <tbody>
        <%int count = 1;%>
        <c:forEach items="${fileList}" var="file">
            <tr>
                <td><%=count++%>
                </td>
                <td>${file.fileName}</td>
                <td>${file.updatedDate}</td>
                <td><a href="${pageContext.request.contextPath}/download?id=${file.id}"
                       class="btn btn-info p-1 font-weight-bold text-capitalize" style="color: aliceblue" download>Download</a>
                </td>
                <td><a href="${pageContext.request.contextPath}/delete?id=${file.id}"
                       class="btn btn-danger p-1 font-weight-bold text-capitalize" style="color: aliceblue">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
