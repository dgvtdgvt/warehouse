<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>商品出入</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<c:if test="${USER==null}"><jsp:forward page="/pages/login.jsp"/></c:if>
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title" style="margin-left: 35px; margin-right:35px; font-weight: 700; line-height: 68px ">出入库记录</h3>
</div>
<!--数据展示头部-->

<!--工具栏 数据搜索 -->
<div class="box-tools pull-right" style="position: absolute; right: 35px; top: 23px">
    <div class="has-feedback">
        <form action="${pageContext.request.contextPath}/operationsSearch" method="post">
            商品名称：<input name="product_name" value="${search.product_name}">&nbsp&nbsp&nbsp&nbsp
            商品码：<input name="product_ean" value="${search.product_ean}">&nbsp&nbsp&nbsp&nbsp
            仓库名称：<input name="warehouse_name" value="${search.warehouse_name}">&nbsp&nbsp&nbsp&nbsp
            <%--            <input name="product_name" value="${search.product_name}">&nbsp&nbsp&nbsp&nbsp--%>
            操作人：<input name="user" value="${search.user}">&nbsp&nbsp&nbsp&nbsp
            操作类型：
            <%--            <input name="product_name" value="${search.product_name}">&nbsp&nbsp&nbsp&nbsp--%>
            <select class="" name="type" style="margin-right: 15px">
                <option ${search.type == null ? 'selected' : ''}>全部</option>
                <option ${search.type == 'IN' ? 'selected' : ''}>入库</option>
                <option ${search.type == 'OUT' ? 'selected' : ''}>出库</option>
            </select>

            <input class="btn btn-default" type="submit" value="查询">
        </form>
    </div>
</div>
<!--工具栏 数据搜索 /-->

<!--数据展示内容区-->
<div class="box-body">
    <!-- 数据表格 -->
    <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
        <thead>
        <tr>
            <th class="sorting_asc">商品名称</th>
            <th class="sorting">商品EAN</th>
            <th class="sorting">商品价格</th>
            <th class="sorting">商品型号</th>
            <th class="sorting">商品数量</th>
            <th class="sorting">仓库名称</th>
            <th class="sorting">操作类型</th>
            <th class="sorting">操作人</th>
            <th class="sorting">操作时间</th>
<%--            <c:if test="${USER.role =='ADMIN'}">--%>
<%--                <th class="text-center">操作</th>--%>
<%--            </c:if>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${operations}" var="operation">
            <tr style="line-height: 37px">
                <td> ${operation.product_name}</td>
                <td> ${operation.product_ean}</td>
                <td> ${operation.product_price}</td>
                <td> ${operation.product_specification}</td>
                <td> ${operation.quantity}</td>
                <td> ${operation.warehouse_name}</td>
                <c:if test="${operation.type=='IN'}">
                    <td>入库</td>
                </c:if>
                <c:if test="${operation.type=='OUT'}">
                    <td>出库</td>
                </c:if>
                <td> ${operation.user}</td>
                <td> ${operation.time}</td>
<%--                <c:if test="${USER.role =='ADMIN'}">--%>
<%--                    <td class="text-center">--%>
<%--                        <button type="button" class="btn bg-olive btn-info" style="margin-left: 5px">修改</button>--%>
<%--                    </td>--%>
<%--                </c:if>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
</div>
</body>
</html>
