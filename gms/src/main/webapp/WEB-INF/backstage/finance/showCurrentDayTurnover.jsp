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
            <th >当日营业额</th>
        </tr>
        </thead>
        <tbody>
        <tr align="center">
            <td>${curDayTurnover}</td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>