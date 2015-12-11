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
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/categoryHandler/listFirstCategory">
    <%-- 	<input type="hidden" name="status" value="${param.status}">
        <input type="hidden" name="orderField" value="${param.orderField}" />
        <input type="hidden" name="keywords" value="${param.keywords}" /> --%>
    <input type="hidden" name="pageNum" value="1" />
    <input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="delete" href="${pageContext.request.contextPath}/categoryHandler/deleteFirstCategory?id={firstCategoryId}"
                   target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="add" href="${pageContext.request.contextPath}/categoryHandler/toAddSecondCategory?id={firstCategoryId}"
                   target="navTab" title="添加二级分类"><span>添加二级分类</span></a></li>
            <li><a class="edit" href="${pageContext.request.contextPath}/categoryHandler/listSecondCategory?parentId={firstCategoryId}"
                   target="navTab" title="查看子分类" rel="showSecondCategories"><span>查看子分类</span></a></li>
            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >一级分类名称</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="firstCategory">
            <tr target="firstCategoryId" rel="${firstCategory.id }" align="center">
                <td>${firstCategory.id }</td>
                <td >${firstCategory.name }</td>
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