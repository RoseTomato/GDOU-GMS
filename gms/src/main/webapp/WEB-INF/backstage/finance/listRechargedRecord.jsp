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
            <%--<li><a class="delete" href="${pageContext.request.contextPath}/financeHandler/deleteRecord?id={record_id}"--%>
                   <%--target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>--%>
            <%--<li class="line">line</li>--%>
        <%--</ul>--%>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >用户id</th>
            <th >充值金额</th>
            <th >充值时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="recharge">
            <tr align="center" target="record_id" rel="${recharge.id }">
            <td>${recharge.id }</td>
            <td >${recharge.userId }</td>
            <td >${recharge.rechargeMoney }</td>
            <td >${recharge.createTime }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>