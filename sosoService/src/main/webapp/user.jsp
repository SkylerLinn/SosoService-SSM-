<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/9
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
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
            text-align: center;
            font-size: small;
            font: -apple-system-body;
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
                    <small>用户界面</small>
                </h1>
            </div>
        </div>
    </div>
    <h2>
        您的信息:<br>
        手机号为：
            ${sessionScope.get("user").getCardNumber()}<br>
        用户名为：
            ${sessionScope.get("user").getUserName()}<br>
        使用包为：
            ${sessionScope.get("user").getPackageIndex()}号包<br>
        实际消费的前为：
            ${sessionScope.get("user").getConsumAmount()}<br>
        余额为：
            ${sessionScope.get("user").getMoney()}<br>
        实际通话时间为:
            ${sessionScope.get("user").getRealTalkTime()}<br>
        实际短信时间为:
            ${sessionScope.get("user").getRealSMSCount()}<br>
        实际流量为:
        ${sessionScope.get("user").getRealFlow()}<br>
    </h2>
    <h2>
    ${sessionScope.get("user").}
    </h2>
</div>
<h3>
    <a href="${path}/index.jsp">返回</a>
</h3>
</body>







