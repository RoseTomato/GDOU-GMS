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
            <%--<li><a class="delete" href="${pageContext.request.contextPath}/financeHandler/deleteSalaryRecord?id={record_id}"--%>
                   <%--target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>--%>
            <%--<li class="line">line</li>--%>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr align="center">
            <th >ID</th>
            <th >员工编号</th>
            <th >员工名字</th>
            <th >应发薪资</th>
            <th >实发薪资</th>
            <th>发放时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="salary">
            <tr align="center" target="record_id" rel="${salary.id }">
            <td>${salary.id }</td>
            <td >${salary.staffNo }</td>
            <td >${salary.staffName }</td>
            <td >${salary.shouldSalary }</td>
            <td >${salary.realSalary }</td>
            <td >${salary.createTime }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>