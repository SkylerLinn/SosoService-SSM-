<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
            font-size: 18px;
        }
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
            font: -apple-system-body;
        }
        h1{
            text-align: center;
            font: -apple-system-caption1;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    SosoService Welcome Page
                </h1>
                <h2>
                    @Author:ZhutianLin
                </h2>
            </div>
        </div>
    </div>
</div>

<br><br>
<h3>

    <a href="Login.jsp" >登录页面</a>
</h3>
<h3>
    <a href="Register.jsp" >注册界面</a>
</h3>
<h3>
    <a href="delete.jsp" >退网</a>
</h3>

</body>

