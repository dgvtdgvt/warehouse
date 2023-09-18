<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>库存列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/js/js.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<c:if test="${USER==null}"><jsp:forward page="/pages/login.jsp"/></c:if>
<!--数据展示头部-->
<div class="box-header with-border">
    <h3 class="box-title" style="margin-left: 35px; margin-right:35px; font-weight: 700; line-height: 68px ">库存列表</h3>
</div>
<!--数据展示头部-->

<!--工具栏 数据搜索 -->
<div class="box-tools pull-right" style="position: absolute; right: 35px; top: 23px">
    <div class="has-feedback">
        <form action="${pageContext.request.contextPath}/warehouseSearch" method="post">
            商品名称：<input name="product_name" value="${search.product_name}">&nbsp&nbsp&nbsp&nbsp
            商品码：<input name="product_ean" value="${search.product_ean}">&nbsp&nbsp&nbsp&nbsp
            仓库名称：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp
            商品数量：
            <select class="" name="product_quantity" style="margin-right: 15px">
                <option value="" ${search.product_quantity == null ? 'selected' : ''}>全部</option>
                <option value="1" ${search.product_quantity == 1 ? 'selected' : ''}>有货</option>
                <option value="0" ${search.product_quantity == 0 ? 'selected' : ''}>没货</option>
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
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${warehouses}" var="warehouse">
            <tr style="line-height: 37px">
                <td> ${warehouse.product_name}</td>
                <td> ${warehouse.product_ean}</td>
                <td> ${warehouse.product_price}</td>
                <td> ${warehouse.product_specification}</td>
                <td> ${warehouse.product_quantity}</td>
                <td> ${warehouse.name}</td>
                <td class="text-center">
                    <c:if test="${warehouse.product_quantity!=0}">
                        <button type="button" class="btn bg-olive btn-success" data-toggle="modal"
                                data-target="#outModal" onclick="Out(${warehouse.id})">商品出库</button>
                    </c:if>
                    <c:if test="${warehouse.product_quantity==0}">
                        <button type="button" class="btn bg-olive disabled btn-success">商品出库</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->

<%--模态窗口--%>

<%--出库--%>
<div class="modal fade" id="outModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>填写出库信息</h3>
            </div>
            <div class="modal-body">
                <form id="outProduct">
                    <span><input type="hidden" id="warehouse_id" name="id"></span>
                    <table id="inProductTab" class="table table-bordered table-striped" width="800px">
                        <%--图书的id,不展示在页面--%>
                        <tr>
                            <td>商品数量</td>
                            <td><input class="form-control" placeholder="" name="product_quantity" id="ebproduct_quantity"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="a" onclick="productOut()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
