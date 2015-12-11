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
            <li><a class="delete" href="${pageContext.request.contextPath}/categoryHandler/deleteSecondCategory?id={s_id}"
                   target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >二级分类名称</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${secondCategories}" var="secondCategory">
            <tr target="s_id" rel="${secondCategory.id }" align="center">
                <td>${secondCategory.id }</td>
                <td >${secondCategory.name }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>