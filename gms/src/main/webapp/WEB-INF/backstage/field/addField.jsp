<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form method="post" action="${pageContext.request.contextPath}/fieldHandler/addField"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <h3>添加用户</h3>
            <dl>
                <dt>场地名</dt>
                <dd>
                    <input type="text" name="name" class="required">
                </dd>
            </dl>
            <dl>
                <dt>描述</dt>
                <dd>
                   <textarea name="description"></textarea>
                </dd>
            </dl>
            <dl>
                <dt>租金</dt>
                <dd>
                    <input type="text" name="fee">
                </dd>
            </dl>
            <dl>
                <dt>使用类型</dt>
                <dd>
                    <input type="radio" name="useType" value="按次"/>按次&nbsp;
                    <input type="radio" name="useType" value="按时"/>按时
                </dd>
            </dl>
            <dl>
                <dt>可容纳人数</dt>
                <dd>
                    <input type="text" name="galleryful">
                </dd>
            </dl>
            <dl>
                <dt>所属类型</dt>
                <dd>
                    <select name="categoryId">
                        <c:forEach items="${secondCategories}" var="sc">
                            <option value="${sc.id}">${sc.name}</option>
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