<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<h2 class="contentTitle">修改头像</h2>


<div class="pageContent">

    <form method="post"
          action="${pageContext.request.contextPath }/userHandler/updateHeadImage"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this,dialogAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="97">
            <input type="text" name="id" value="${param.id }"
                   style="display: none;">
            <dl>
                <dt>选择新图片：</dt>
                <dd>
                    <input type="file" name="headImage" />
                </dd>

            </dl>
        </div>

        <div class="formBar">
            <ul>
                <li><div class="buttonActive">
                    <div class="buttonContent">
                        <button type="submit">提交</button>
                    </div>
                </div></li>
                <li><div class="button">
                    <div class="buttonContent">
                        <button type="button" class="close">取消</button>
                    </div>
                </div></li>
            </ul>
        </div>
    </form>

</div>
</body>
</html>