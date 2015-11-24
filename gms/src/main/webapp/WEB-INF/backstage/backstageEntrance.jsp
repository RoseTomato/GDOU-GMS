<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>广东海洋大学-体育馆-后台管理系统-入口</title>
    <!-- CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/backstageEntrance/style.css"
          type="text/css" />

    <!-- Javascript libraries (jQuery and Selectivizr) used for the custom checkbox ->

	<!--[if (gte IE 6)&(lte IE 8)]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/backstageEntrance/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/backstageEntrance/selectivizr.js"></script>
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/backstageEntrance/fallback.css" /></noscript>
    <![endif]-->

    <script type="text/javascript" charset="utf-8">

        window.onload = function() {
            var message = getURLParam("message")
            if (message == "UnknownAccountException" || message == "IncorrectCredentialsException") {
                alert("账户名或密码错误")
            } else if (message == "AuthenticationException") {
                alert("认证错误")
            }
        }

        function validate() {
            with (document.all) {
                var username = document.getElementById('username');
                var password = document.getElementById('password');
                if (password.value == "" || username.value == "") {
                    alert("用户名和密码都不能为空！");
                    return false;
                } else {
                    document.forms[0].submit();
                }
            }
        }

        /**
        * 获取url参数
        * @param name
        * @returns {*}
         */
        function getURLParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)")
            var r = window.location.search.substr(1).match(reg)
            if(r != null) {
                return unescape(r[2])
            }
            return null
        }
    </script>

</head>

<body>
<c:if test="${not empty message}">
    <script type="text/javascript">
        alert("${message}");
    </script>
</c:if>
<div id="container">
    <form action="${pageContext.request.contextPath}/loginBackstage"
          method="post" onsubmit="return validate()">
        <div class="login">登陆窗口</div>
        <div class="username-text">用户名:</div>
        <div class="password-text">密码:</div>
        <div class="username-field">
            <input type="text" name="username" id="username"
                   placeholder="请输入用户名" />
        </div>
        <div class="password-field">
            <input type="password" name="password" id="password"
                   placeholder="请输入密码"/>
        </div>
        <input type="checkbox" name="remember-me" id="remember-me" /><label
            for="remember-me">Remember me</label>
        <div class="forgot-usr-pwd">
            Forgot <a href="#">username</a> or <a href="#">password</a>?
        </div>
        <input type="submit" name="submit" value="GO" />
    </form>
</div>
<div id="footer">
    Copyright &copy; 2014.Tomato Rose All rights reserved.
</div>
</body>
</html>