<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div class="pageContent">
    <form method="post" action="${pageContext.request.contextPath}/userHandler/addUser"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <h3>添加用户</h3>
            <dl>
                <dt>用户名</dt>
                <dd>
                    <input type="text" name="username" class="required">
                </dd>
            </dl>
            <dl>
                <dt>密码</dt>
                <dd>
                    <input type="password" name="password" class="required">
                </dd>
            </dl>
            <dl>
                <dt>姓名</dt>
                <dd>
                    <input type="text" name="name">
                </dd>
            </dl>
            <dl>
                <dt>手机</dt>
                <dd>
                    <input type="text" name="phone">
                </dd>
            </dl>
            <dl>
                <dt>性别</dt>
                <dd>
                    <input type="radio" name="gender" value="男">男
                    <input type="radio" name="gender" value="女">女
                </dd>
            </dl>
            <dl>
                <dt>年龄</dt>
                <dd>
                    <input type="text" name="age">
                </dd>
            </dl>
            <dl>
                <dt>生日</dt>
                <dd>
                    <input type="text" name="birthday" placeholder="1994-07-14">
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