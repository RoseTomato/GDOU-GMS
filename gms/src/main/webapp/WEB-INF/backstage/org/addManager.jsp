<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div class="pageContent">
    <form method="post" action="${pageContext.request.contextPath}/managerHandler/addManager"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <h3>添加管理员</h3>
            <dl>
                <dt>用户名（E-mail）：</dt>
                <dd>
                    <input type="text" name="username" class="required">
                </dd>

            </dl>
            <dl>
                <dt>密码：</dt>
                <dd>
                    <input cols="65" rows="8" name="password" class="required alphanumeric" minlength="6" maxlength="20"/>
                </dd>

            </dl>
            <dl>
                <dt>真实姓名：</dt>
                <dd>
                    <input type="text" name="name"/>
                </dd>
            </dl>
            <dl>
                <dt>年龄：</dt>
                <dd>
                    <input type="text" name="age"/>
                </dd>
            </dl>
            <dl>
                <dt>出生日期：</dt>
                <dd>
                    <input type="text" name="birthday" placeholder="1994-01-01"/>
                </dd>
            </dl>
            <dl>
                <dt>性别：</dt>
                <dd>
                    男<input type="radio" name="gender" value="男"/>
                    女<input type="radio" name="gender" value="女"/>
                </dd>
            </dl>
            <dl>
                <dt>地址：</dt>
                <dd>
                    <textArea type="text" name="address"></textArea>
                </dd>
            </dl>
            <dl>
                <dt>手机：</dt>
                <dd>
                    <input type="text" name="phone" class="phone"/>
                </dd>
            </dl>
            <dl>
                <dt>角色：</dt>
                <dd>
                    <select name="roleId">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li><div class="buttonActive">
                    <div class="buttonContent">
                        <button type="submit">保存</button>
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