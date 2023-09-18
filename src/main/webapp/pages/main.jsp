<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>仓库管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
    <script type="text/javascript">
        function SetIFrameHeight() {
            var iframeid = document.getElementById("iframe");
            if (document.getElementById) {
                /*设置 内容展示区的高度等于页面可视区的高度*/
                iframeid.height = document.documentElement.clientHeight;
            }
        }
    </script>
</head>
<body class="">
<c:if test="${USER==null}"><jsp:forward page="/pages/login.jsp"/></c:if>
<div class="">
    <!-- 页面头部 -->
    <header class="">
        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/pages/main.jsp" class="">
            <span class=""><b>仓库管理系统</b></span>
        </a>
        <!-- 头部导航 -->
        <nav class="">
            <div class="">
                <ul class="">
                    <li class="">
                        <a>
                            <img src="${pageContext.request.contextPath}/img/user.png" class="user-image"
                                 alt="User Image">
                            <span class="">${USER.name}</span>
                        </a>
                    </li>
                    <li class="">
                        <a href="${pageContext.request.contextPath}/logout">
                            <span class="">注销</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <aside class="">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="">
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="">
<%--                <li >--%>
<%--                    <a href="${pageContext.request.contextPath}/pages/main.jsp">--%>
<%--                        <i class=""></i> <span>首页</span>--%>
<%--                    </a>--%>
<%--                </li>--%>
                <li >
                    <a href="${pageContext.request.contextPath}/product" target="iframe">
                        <i class=""></i>商品列表
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/warehouse" target="iframe">
                        <i class=""></i>库存列表
                    </a>
                </li>
                <li >
                    <a href="${pageContext.request.contextPath}/operations" target="iframe">
                        <i class=""></i>出入记录
                    </a>
                </li>
                <c:if test="${USER.role =='ADMIN'}">
                    <li >
                        <a href="${pageContext.request.contextPath}/user" target="iframe">
                            <i class=""></i>用户管理
                        </a>
                    </li>
                </c:if>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <!-- 导航侧栏 /-->
    <!-- 内容展示区域 -->
    <div class="content-wrapper">
        <iframe width="100%" id="iframe" name="iframe" onload="SetIFrameHeight()"
                frameborder="0" src="${pageContext.request.contextPath}/product"></iframe>
    </div>
</div>
</body>
</html>
