<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <title>用户管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script charset="UTF-8" src="${pageContext.request.contextPath}/js/js.js"></script>
</head>
<body class="hold-transition skin-red sidebar-mini">
<c:if test="${USER==null}"><jsp:forward page="/pages/login.jsp"/></c:if>
<c:if test="${USER.role=='USER'}"><jsp:forward page="/pages/login.jsp"/></c:if>
<!--数据展示头部-->
<div class="box-header with-border" style="display: flex">
  <h3 class="box-title" style="margin-left: 35px; margin-right:35px; font-weight: 700; line-height: 68px ">用户管理</h3>
    <c:if test="${USER.role =='ADMIN'}">
        <button type="button" class="btn bg-olive btn-primary" style="margin: 15px" data-toggle="modal"
                data-target="#AEUser" onclick="addUser()">新增用户</button>
    </c:if>
</div>
<!--数据展示头部-->

<!--工具栏 数据搜索 -->
<div class="box-tools pull-right" style="position: absolute; right: 35px; top: 23px">
    <div class="has-feedback">
        <form action="${pageContext.request.contextPath}/userSearch" method="post">
            用户名称：<input name="name" value="${search.name}">&nbsp&nbsp&nbsp&nbsp
            用户类型：
            <select class="" name="role" style="margin-right: 15px">
                <option ${search.role == null ? 'selected' : ''}>全部</option>
                <option ${search.role == 'ADMIN' ? 'selected' : ''}>管理员</option>
                <option ${search.role == 'USER' ? 'selected' : ''}>用户</option>
            </select>
            用户状态：
            <select class="" name="password" style="margin-right: 15px">
                <option ${search.status == null ? 'selected' : ''}>全部</option>
                <option ${search.status == 0 ? 'selected' : ''}>正常</option>
                <option ${search.status == 1 ? 'selected' : ''}>禁用</option>
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
      <th class="sorting_asc">用户名称</th>
<%--      <th class="sorting">用户邮箱</th>--%>
      <th class="sorting">用户类型</th>
      <th class="sorting">用户状态</th>
      <th class="text-center">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
      <tr style="line-height: 37px">
        <td> ${user.name}</td>
          <td>
              <c:if test="${user.role=='ADMIN'}">管理员</c:if>
              <c:if test="${user.role=='USER'}">用户</c:if>
          </td>
          <td>
              <c:if test="${user.status ==0}">正常</c:if>
              <c:if test="${user.status ==1}">禁用</c:if>
          </td>
        <td class="text-center">
          <button type="button" class="btn bg-olive btn-info" data-toggle="modal"
                  data-target="#AEUser" onclick="editUser(${user.id})">修改</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <!-- 数据表格 /-->
</div>
<!-- 数据展示内容区/ -->

<%--添加或编辑用户--%>
<div class="modal fade" id="AEUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>用户管理</h3>
            </div>
            <div class="modal-body">
                <form id="addOrEditUser">
                    <span><input type="hidden" id="user_id" name="id"></span>
                    <table id="addOrEditUserTab" class="table table-bordered table-striped" width="800px">
                        <%--图书的id,不展示在页面--%>
                        <tr>
                            <td>用户名称</td>
                            <td><input class="form-control" placeholder="" name="name" id="ebname"></td>
                            <td>用户密码</td>
                            <td><input class="form-control" placeholder="(留空默认不修改)" name="password" id="ebpassword"></td>
                        </tr>
                        <tr>
                            <td>用户类型</td>
                            <td>
<%--                                <input class="form-control" placeholder="" name="price" id="ebprice">--%>
                                <select class="form-control form-control" name="role" id="edrole">
                                    <option value="USER">用户</option>
                                    <option value="ADMIN">管理员</option>
                                </select>
                            </td>
                            <td>用户状态</td>
                            <td>
<%--                                <input class="form-control" placeholder="" name="specification" id="ebspecification">--%>
                                <select class="form-control form-control" name="status" id="ebstatus">
                                    <option value="0">正常</option>
                                    <option value="1">禁用</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" aria-hidden="true" id="aoe" onclick="addOrEditUser()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
