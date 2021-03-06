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
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/fieldHandler/listfield">
    <%-- 	<input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="orderField" value="${param.orderField}" />
        <input type="hidden" name="keywords" value="${param.keywords}" /> --%>
    <input type="hidden" name="pageNum" value="1" />
    <input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="edit"
                   href="${pageContext.request.contextPath}/fieldHandler/detailField?id={fieldId}"
                   target="navTab" title="查看场地详情"><span>查看场地详情</span></a></li>
            <li><a class="delete" href="${pageContext.request.contextPath}/fieldHandler/freezeField?id={fieldId}"
                   target="ajaxTodo" title="确定要停用吗?"><span>停用</span></a></li>
            <li><a class="add" href="${pageContext.request.contextPath}/fieldHandler/recoverField?id={fieldId}"
                   target="ajaxTodo" title="确定要恢复吗?"><span>恢复</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >场地名</th>
            <th >描述</th>
            <th >租金(元/时)</th>
            <th >状态</th>
            <th >使用类型</th>
            <th >可容纳人数</th>
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
                <td>${field.galleryful}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="200">200</option>
            </select>
            <span>条，共${page.totalRecord}条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="${page.totalRecord }"
             numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNum }"></div>

    </div>
</div>

</body>
</html>