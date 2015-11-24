<%@ page import="java.net.URI" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/managerHandler/listManager">
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
                   href="${pageContext.request.contextPath}/managerHandler/detailManager?managerId={sid_manager}"
                   target="navTab" title="管理员详情"><span>查看详情</span></a></li>
            <li><a class="delete" href="${pageContext.request.contextPath}/managerHandler/freezeManager?id={sid_manager}"
                   target="ajaxTodo" title="确定要冻结吗?"><span>冻结</span></a></li>
            <li><a class="edit" href="${pageContext.request.contextPath}/managerHandler/recoverManager?id={sid_manager}" target="ajaxTodo"><span>恢复</span></a></li>

            <li class="line">line</li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >账号</th>
            <th >真实姓名</th>
            <th >年龄</th>
            <th >生日</th>
            <th >性别</th>
            <th >手机</th>
            <th>地址</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="manager">
            <tr target="sid_manager" rel="${manager.id }" align="center">
                <td>${manager.id }</td>
                <td>${manager.username }</td>
                <td >${manager.name }</td>
                <td>${manager.age}</td>
                <%--<td >${manager.birthday}</td>--%>
                <td><spring:eval expression="manager.birthday"></spring:eval></td>
                <td>${manager.gender}</td>
                <td>${manager.phone }</td>
                <td>${manager.address }</td>
                <td>${manager.state}</td>
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