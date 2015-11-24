<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="edit"
                   href="${pageContext.request.contextPath}/roleHandler/detailRole?name={roleName}"
                   target="navTab" title="角色详情"><span>查看详情</span></a></li>
            <li><a class="delete" href="${pageContext.request.contextPath}/roleHandler/deleteRole?name={roleName}"
                   target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="add" href="${pageContext.request.contextPath}/roleHandler/forwardAssociatePrivilege?name={roleName}" target="navTab" title="分配权限"><span>分配权限</span></a></li>

            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >角色名</th>
            <th >描述</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
            <tr target="roleName" rel="${role.name }" align="center">
                <td>${role.id }</td>
                <td >${role.name }</td>
                <td>${role.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>