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
          action="${pageContext.request.contextPath}/competitionHandler/checkCompetition"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="56">
            <input type="hidden" name="id" value="${competition.id }" >
            <dl>
                <dt>赛事名：</dt>
                <dd>
                    <input type="text" name="name"  readonly="readonly" class="required" value="${competition.name }"  >
                </dd>

            </dl>
            <dl>
                <dt>描述：</dt>
                <dd>
                    <textarea name="description">${competition.description}</textarea>
                </dd>
            </dl>
            <dl>
                <dt>状态：</dt>
                <dd>
                    <input readonly type="text" name="state" value="${competition.state }">
                </dd>
            </dl>
            <dl>
                <dt>开始时间：</dt>
                <dd>
                    <input  type="text" name="startTime" value="${competition.startTime }">
                </dd>
            </dl>
            <dl>
                <dt>结束时间：</dt>
                <dd>
                    <input  type="text" name="endTime" value="${competition.endTime}">
                </dd>
            </dl>
            <dl>
                <dt>创建时间：</dt>
                <dd>
                    <input  type="text" name="createTime" value="${competition.createTime}">
                </dd>
            </dl>
            <dl>
                <dt>地点：</dt>
                <dd>
                    <input readonly type="text" name="place" value="${competition.place }">
                </dd>
            </dl>
            <dl>
                <dt>申办单位：</dt>
                <dd>
                    <input readonly type="text" name="applyer" value="${competition.applyer}">
                </dd>
            </dl>
            <dl>
                <dt>赞助商：</dt>
                <dd>
                    <input readonly type="text" name="sponsor" value="${competition.sponsor}">
                </dd>
            </dl>
            <dl>
                <dt>规模：</dt>
                <dd>
                    <input readonly type="text" name="scale" value="${competition.scale }">
                </dd>
            </dl>
            <dl>
                <dt>热度：</dt>
                <dd>
                    <input readonly type="text" name="star" value="${competition.star }">
                </dd>
            </dl>
            <input type="hidden" name="userId" value="${competition.userId }">
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li><div class="buttonActive">
                    <div class="buttonContent">
                        <button type="submit" >通过</button>
                    </div>
                </div></li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">拒绝</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>
</body>
</html>