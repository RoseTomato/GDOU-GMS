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
          action="${pageContext.request.contextPath}/equipmentHandler/updateEquipment"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <input type="text" name="id" value="${equipment.id }" style="display: none;">
            <dl>
                <dt>器材名：</dt>
                <dd>
                    <input type="text" name="name" class="required" value="${equipment.name }"  >
                </dd>

            </dl>
            <dl>
                <dt>描述：</dt>
                <dd>
                    <textarea name="description">${equipment.description}</textarea>
                </dd>
            </dl>
            <dl>
                <dt>费用：</dt>
                <dd>
                    <input  type="text" name="fee" value="${equipment.fee }">
                </dd>
            </dl>
            <dl>
                <dt>状态：</dt>
                <dd>
                    <input readonly type="text"  name="state" value="${equipment.state }">
                </dd>
            </dl>
            <dl>
                <dt>总数：</dt>
                <dd>
                    <input  type="text" name="total" value="${equipment.total }">
                </dd>
            </dl>
            <dl>
                <dt>现在使用人数：</dt>
                <dd>
                    <input  type="text" name="currentNumber" value="${equipment.currentNumber }">
                </dd>
            </dl>
            <dl>
                <dt>使用类型：</dt>
                <dd>
                    <input type="radio" name="useType"  value="按次"
                    <c:if test="${equipment.useType == '按次'}"> checked="checked" </c:if>
                            >按次
                    <input type="radio" name="useType"  value="按时"
                    <c:if test="${equipment.useType == '按时'}"> checked="checked" </c:if>
                            >按时
                </dd>
            </dl>
            <dl>
                <dt>所属分类：</dt>
                <dd>
                    <select name="category_id">
                        <c:forEach items="${secondCategories}" var="sc">
                            <option value="${sc.id}"
                                    <c:if test="${sc.id == secondCategory.id}">
                                        selected="selected"
                                    </c:if>
                                    >${sc.name}
                            </option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>图片：</dt>
                <dd>
                    <a href="${pageContext.request.contextPath }/fieldHandler/forwardUpdateImage?id=${equipment.id}" target="dialog">
                        <img width="100" height="100" title="点击图片修改" src="${pageContext.request.contextPath}/upload/equipmentImage/${equipment.image}.png" alter="图片">
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