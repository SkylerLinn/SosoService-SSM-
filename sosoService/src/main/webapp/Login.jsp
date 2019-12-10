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
    <title>注册服务</title>
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
            background: deepskyblue;
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
                    <small>登录Soso账号</small>
                </h1>

            </div>
        </div>
    </div>
<%--这里没改是save的函数--%>
    <form action="${pageContext.request.contextPath}/soso/login" name="userForm" >
        卡号：<input type="text" name="cardNum"><br><br><br>
        输入密码：<input type="password" name="passWord"><br><br><br>
        <button type="submit" >提交</button>&nbsp;&nbsp;
        <button type="reset" >清空</button>

    </form>


</div>
<h3>
    <a href="${path}/index.jsp">返回</a>
</h3>
</body>



