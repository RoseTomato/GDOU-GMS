<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div class="pageContent">
    <form method="post"
          action="${pageContext.request.contextPath}/userHandler/updateUser"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <input type="text" name="id" value="${user.id }" style="display: none;">
            <input type="text" name="password" value="${user.password }" style="display: none;">
            <dl>
                <dt>账号：</dt>
                <dd>
                    <input type="text" name="username"  readonly="readonly" class="required" value="${user.username }"  >
                </dd>

            </dl>
            <dl>
                <dt>头像：</dt>
                <dd>
                    <a href="${pageContext.request.contextPath }/userHandler/forwardUpdateHeadImage?id=${user.id}" target="dialog">
                        <img width="100" height="100" title="点击图片修改" src="${pageContext.request.contextPath}/upload/userHeadImage/${user.headImage}.png" alter="头像">
                    </a>
                </dd>
            </dl>
            <dl>
                <dt>姓名：</dt>
                <dd>
                    <input type="text" name="name"  value="${user.name }">
                </dd>
            </dl>
            <dl>
                <dt>手机</dt>
                <dd>
                    <input type="text" name="phone" value="${user.phone}">
                </dd>
            </dl>
            <dl>
                <dt>年龄：</dt>
                <dd>
                    <input type="text"   name="age" value="${user.age }">
                </dd>
            </dl>
            <dl>
                <dt>出生日期：</dt>
                <dd>
                    <input type="text" name="birthday" value="<spring:eval expression="user.birthday"></spring:eval>"/>
                </dd>
            </dl>
            <dl>
                <dt>性别：</dt>
                <dd>
                    <input type="radio" name="gender"  value="女"
                        <c:if test="${user.gender == '女'}"> checked="checked" </c:if>
                            >女
                    <input type="radio" name="gender"  value="男"
                            <c:if test="${user.gender == '男'}"> checked="checked" </c:if>
                            >男
                </dd>

            </dl>
            <dl>
                <dt>状态：</dt>
                <dd>
                    <input readonly type="text" name="state" value="${user.state }" >
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