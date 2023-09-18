<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>商品列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/js/js.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<c:if test="${USER==null}"><jsp:forward page="/pages/login.jsp"/></c:if>
<!--数据展示头部-->
<div class="box-header with-border" style="display: flex">
    <h3 class="box-title" style="margin-left: 35px; margin-right:35px; font-weight: 700; line-height: 68px ">商品列表</h3>
    <c:if test="${USER.role =='ADMIN'}">
        <button type="button" class="btn bg-olive btn-primary" style="margin: 15px" data-toggle="modal"
                data-target="#addOrEditModal" onclick="addProduct()">新增商品</button>
    </c:if>
</div>
<!--数据展示头部-->

<!--工具栏 数据搜索 -->
<div class="box-tools pull-right" style="position: absolute; right: 35px; top: 23px">
    <div class="has-feedback">
        <form action="${pageContext.request.contextPath}/productSearch" method="post">
            商品名称：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp
            商品码：<input name="ean" value="${search.ean}">&nbsp&nbsp&nbsp&nbsp
            商品价格：<input name="price" value="${search.price}">&nbsp&nbsp&nbsp&nbsp
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
<%--            <th class="sorting_asc">商品id</th>--%>
            <th class="sorting_asc">商品名称</th>
            <th class="sorting">商品码</th>
            <th class="sorting">商品价格</th>
            <th class="sorting">商品型号</th>
            <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr style="line-height: 37px">
<%--                <td id="proId">${product.id}</td>--%>
                <td>${product.name}</td>
                <td>${product.ean}</td>
                <td>${product.price}</td>
                <td>${product.specification}</td>
                <td class="text-center">
                        <button type="button" class="btn bg-olive btn-success" data-toggle="modal"
                                data-target="#inModal" onclick="In(${product.id})">商品入库</button>
                        <c:if test="${USER.role =='ADMIN'}">
                        <button type="button" class="btn bg-olive btn-info" data-toggle="modal"
                                data-target="#addOrEditModal" style="margin-left: 5px" onclick="editProduct(${product.id})">修改</button>
                        </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 数据表格 /-->
</div>

<%--模态窗口--%>

<%--编辑或新增--%>
<div class="modal fade" id="addOrEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>商品信息</h3>
            </div>
            <div class="modal-body">
                <form id="addOrEditProduct">
                    <span><input type="hidden" id="ebid" name="id"></span>
                    <table id="addOrEditTab" class="table table-bordered table-striped" width="800px">
                        <%--图书的id,不展示在页面--%>
                        <tr>
                            <td>商品名称</td>
                            <td><input class="form-control" placeholder="" name="name" id="ebname"></td>
                            <td>商品EAN</td>
                            <td><input class="form-control" placeholder="" name="ean" id="ebean"></td>
                        </tr>
                        <tr>
                            <td>商品价格</td>
                            <td><input class="form-control" placeholder="" name="price" id="ebprice"></td>
                            <td>商品型号</td>
                            <td><input class="form-control" placeholder="" name="specification" id="ebspecification"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" onclick="addOrEdit()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--商品入库--%>
<div class="modal fade" id="inModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>填写入库信息</h3>
            </div>
            <div class="modal-body">
                <form id="inProduct">
                    <span><input type="hidden" id="proId" name="product_id"></span>
                    <table id="inProductTab" class="table table-bordered table-striped" width="800px">
                        <%--id,不展示在页面--%>
                        <tr>
                            <td>商品数量</td>
                            <td><input class="form-control" placeholder="" name="product_quantity" id="ebproduct_quantity"></td>
                            <td>仓库名称</td>
                            <td><input class="form-control" placeholder="" name="name" id="edname"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="a" onclick="productIn()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
