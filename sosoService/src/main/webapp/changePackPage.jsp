<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";//+"WEB-INF/classes/com/controller/"
    pageContext.setAttribute("path", path);
    pageContext.setAttribute("basePath",basePath);

%>
<!DOCTYPE HTML>
<html>
<head>
    <title>套餐变更</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">

        h3 {
            width: 180px;
            height: 38px;
            margin: 100px auto;
            text-align: center;
            line-height: 38px;
            background: red;
            border-radius: 4px;
            font: -apple-system-caption2;
        }
        h2{
            font-size: medium;
            font: -apple-system-caption2;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>

                    SosoService服务
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改服务包</small>
                </h1>

            </div>
        </div>
    </div>
    请您选择:
        0：话痨套餐  1：网虫套餐  2：超人套餐
    <form action="${pageContext.request.contextPath}/soso/changePack" name="userForm" >
        套餐变更为：<input type="text" name="packageIndex"><br><br><br>
        <button type="submit" >提交</button>&nbsp;&nbsp;
        <button type="reset" >清空</button>

    </form>
    <h2>
        ${sessionScope.get("changePackInfo")}
    </h2>

</div>
<h3>
    <a href="${path}/user.jsp">返回用户主界面</a>
</h3>
</body>



