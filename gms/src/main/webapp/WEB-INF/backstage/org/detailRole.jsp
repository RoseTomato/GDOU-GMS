<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div class="pageContent">
    <form method="post"
          action="${pageContext.request.contextPath}/roleHandler/updateRole"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <input type="text" name="id" value="${role.id }" style="display: none;">
            <dl>
                <dt>角色名：</dt>
                <dd>
                    <input type="text" name="name" class="required" value="${role.name }"  >
                </dd>

            </dl>
            <dl>
                <dt>描述：</dt>
                <dd>
                    <textarea name="description" value="${role.description}"></textarea>
                </dd>

            </dl>
            <dl>
                <dt>关联的角色：</dt>
                <dd>
                    <ul>
                        <c:forEach items="${managerNames}" var="managerName">
                        <li>${managerName}</li>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt>拥有的权限：</dt>
                <dd>
                    <ul>
                        <c:forEach items="${privilegeNames}" var="privilegeName">
                            <li>${privilegeName}</li>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li><div class="buttonActive">
                    <div class="buttonContent">
                        <button type="submit" >更新</button>
                    </div>
                </div></li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
</body>
</html>