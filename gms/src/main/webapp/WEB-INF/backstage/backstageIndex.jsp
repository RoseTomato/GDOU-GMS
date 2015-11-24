<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>广东海洋大学-体育馆-后台管理系统-首页</title>

    <link href="${pageContext.request.contextPath}/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="${pageContext.request.contextPath}/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="${pageContext.request.contextPath}/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="${pageContext.request.contextPath}/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
    <!--[if IE]>
    <link href="${pageContext.request.contextPath}/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <script src="${pageContext.request.contextPath}/dwz/js/speedup.js" type="text/javascript"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

    <!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwz/chart/raphael.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwz/chart/g.raphael.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwz/chart/g.bar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwz/chart/g.line.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwz/chart/g.pie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwz/chart/g.dot.js"></script>

    <script src="${pageContext.request.contextPath}/dwz/js/dwz.core.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.util.date.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.drag.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.tree.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.ui.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.theme.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.tab.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.resize.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.stable.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.pagination.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.database.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.effects.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.panel.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.history.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.combox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.print.js" type="text/javascript"></script>

    <!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)
    <script src="bin/dwz.min.js" type="text/javascript"></script>
    -->
    <script src="${pageContext.request.contextPath}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function(){
            DWZ.init("${pageContext.request.contextPath}/dwz/dwz.frag.xml", {
//                loginUrl:"", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"${pageContext.request.contextPath}/gms/backstageEntrance",	// 跳到登录页面
                statusCode:{ok:200, error:300, timeout:301}, //【可选】
                pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
                debug:false,	// 调试模式 【true|false】
                callback:function(){
                    initEnv();
                    $("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
                }
            });
        });

    </script>
</head>

<body scroll="no">
<div id="layout">
    <div id="header">
        <div class="headerNav">
            <a class="logo" href="${pageContext.request.contextPath}/WEB-INF/backstage/backstageIndex.jsp">标志</a>
            <ul class="nav">
                <li><a>欢迎您，<shiro:principal/></a></li>
                <li><a href="${pageContext.request.contextPath}/logoutBackstage">退出</a></li>
            </ul>
        </div>

    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse"><div></div></div>
            </div>
        </div>
        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

            <div class="accordion" fillSpace="sidebar">
                <shiro:hasAnyRoles name="超级管理员,组织结构管理员">
                <div class="accordionHeader">
                    <h2><span>Folder</span>组织结构管理</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                            <li><a>管理员管理</a>
                                <ul>
                                    <shiro:hasPermission name="新增管理员">
                                    <li><a href="${pageContext.request.contextPath}/managerHandler/forwardAddManager" target="navTab">新增管理员</a></li>
                                    </shiro:hasPermission>
                                    <li><a href="${pageContext.request.contextPath}/managerHandler/listManager?pageNum=1&pageSize=10" target="navTab" rel="showManagers" title="管理员列表">查询管理员</a></li>
                                </ul>
                            </li>
                        <li><a>角色管理</a>
                            <ul>
                                <li><a href="" target="navTab" rel="">新增角色</a></li>
                                <li><a href="" target="navTab" rel="">查询角色</a></li>
                            </ul>
                        </li>

                        <li><a>权限管理</a>
                            <ul>
                                <li><a href="" target="navTab" rel="">新增权限</a></li>
                                <li><a href="" target="navTab" rel="">查询权限</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                </shiro:hasAnyRoles>

                <shiro:hasAnyRoles name="超级管理员,用户管理员">
                <div class="accordionHeader">
                    <h2><span>Folder</span>用户管理</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                        <li><a href="" target="navTab" rel="">新增用户</a></li>
                        <li><a href="" target="navTab" rel="">查询用户</a></li>
                    </ul>
                </div>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="超级管理员,场地管理员">
                <div class="accordionHeader">
                    <h2><span>Folder</span>场地管理</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                        <li><a href="" target="navTab" rel="">新增场地</a></li>
                        <li><a href="" target="navTab" rel="">查询场地</a></li>
                    </ul>
                </div>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="超级管理员,器材管理员">
                <div class="accordionHeader">
                    <h2><span>Folder</span>器材管理</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                        <li><a href="" target="navTab" rel="">新增场地</a></li>
                        <li><a href="" target="navTab" rel="">查询场地</a></li>
                    </ul>
                </div>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="超级管理员,赛事管理员">
                <div class="accordionHeader">
                    <h2><span>Folder</span>赛事管理</h2>
                </div>
                <div class="accordionContent">
                    <ul class="tree treeFolder">
                        <li><a href="" target="navTab" rel="">新增场地</a></li>
                        <li><a href="" target="navTab" rel="">查询场地</a></li>
                    </ul>
                </div>
                </shiro:hasAnyRoles>
            </div>
        </div>
    </div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="pageFormContent" layoutH="80" style="margin-right:230px">
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>

<div id="footer">Copyright &copy; 2015 Tomato Rose compary all right serve.</div>

</body>
</html>