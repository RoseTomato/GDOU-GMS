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
    <form method="post" action="${pageContext.request.contextPath}/activityHandler/addActivity"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <h3>添加用户</h3>
            <dl>
                <dt>标题</dt>
                <dd>
                    <input type="text" name="name">
                </dd>
            </dl>
            <dl>
                <dt>内容</dt>
                <dd>
                    <textarea rows="5" cols="50" name="content"></textarea>
                </dd>
            </dl>
            <dl>
                <dt>开始时间</dt>
                <dd>
                    <input type="text" name="startTime" placeholder="格式：2016-04-04">
                </dd>
            </dl>
            <dl>
                <dt>开始时间</dt>
                <dd>
                    <input type="text" name="endTime" placeholder="格式：2016-04-04">
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