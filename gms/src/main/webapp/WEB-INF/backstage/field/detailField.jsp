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
          action="${pageContext.request.contextPath}/fieldHandler/updateField"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <input type="text" name="id" value="${field.id }" style="display: none;">
            <dl>
                <dt>场地名：</dt>
                <dd>
                    <input type="text" name="name"  readonly="readonly" class="required" value="${field.name }"  >
                </dd>

            </dl>
            <dl>
                <dt>描述：</dt>
                <dd>
                    <textarea name="description">${field.description}</textarea>
                </dd>
            </dl>
            <dl>
                <dt>状态：</dt>
                <dd>
                    <input readonly type="text" name="state" value="${field.state }">
                </dd>
            </dl>
            <dl>
                <dt>图片：</dt>
                <dd>
                    <a href="${pageContext.request.contextPath }/fieldHandler/forwardUpdateImage?id=${field.id}" target="dialog">
                        <img width="100" height="100" title="点击图片修改" src="${pageContext.request.contextPath}/upload/fieldImage/${field.image}.png" alter="图片">
                    </a>
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