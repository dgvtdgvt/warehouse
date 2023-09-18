<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>仓库管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/login">
    <div class="login">
        <div style="text-align:center; font-size: 20px; line-height: 22px; height: 22px; color: black">${msg}</div>
        <h2>用户登录</h2>
        <div class="login_box">
            <input type="text" required="required" name="name" id="name">
            <label>用户名</label>
        </div>

        <div class="login_box">
            <input type="password" required="required" name="password" id="password">
            <label>密码</label>
        </div>
        <input type="submit" value="登录" class="btn" id="btn">
        <br>
    </div>
</form>
</body>
</html>