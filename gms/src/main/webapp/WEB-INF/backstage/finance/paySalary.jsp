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
    <form method="post" action="${pageContext.request.contextPath}/financeHandler/paySalary"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <h3>充值</h3>
            <dl>
                <dt>员工编号</dt>
                <dd>
                    <input type="text" name="staffNo">
                </dd>
            </dl>
            <dl>
                <dt>员工名字</dt>
                <dd>
                    <input type="text" name="staffName">
                </dd>
            </dl>
            <dl>
                <dt>实发薪资</dt>
                <dd>
                    <input type="text" name="realSalary">
                </dd>
            </dl>
            <dl>
                <dt>应发薪资</dt>
                <dd>
                    <input type="text" name="shouldSalary">
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