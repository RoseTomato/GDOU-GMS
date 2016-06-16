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
                   href="${pageContext.request.contextPath}/equipmentHandler/detailEquipment?id={fieldId}"
                   target="navTab" title="查看器材详情"><span>查看器材详情</span></a>
            </li>
            <li><a class="delete" href="${pageContext.request.contextPath}/equipmentHandler/freezeEquipment?id={fieldId}"
                   target="ajaxTodo" title="确定要停用吗?"><span>停用</span></a></li>
            <li><a class="add" href="${pageContext.request.contextPath}/equipmentHandler/recoverEquipment?id={fieldId}"
                   target="ajaxTodo" title="确定要恢复吗?"><span>恢复</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >名称</th>
            <th >描述</th>
            <th >租金(元/时)</th>
            <th >状态</th>
            <th >使用类型</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="field">
            <tr target="fieldId" rel="${field.id }" align="center">
                <td>${field.id }</td>
                <td >${field.name }</td>
                <td>${field.description}</td>
                <td>${field.fee}</td>
                <td>${field.state}</td>
                <td>${field.useType}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>