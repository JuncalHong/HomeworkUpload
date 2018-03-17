<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user.type!=1}">
<script type="text/javascript">
 window.location.href="Main.jsp";
</script>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>用户中心</title>   
	<meta http-equiv="pragma" content="no-cac he">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/color.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-ins/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"  src="plug-ins/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
  </head>
  
  <body>
    <h2>电脑管理</h2>
	<p>注意：以下所有选线均会导致服务器关机，请谨慎操作.</p>
	<div style="margin:10px 0 40px 0;"></div>
	<div class="easyui-panel" style="padding:5px;">
		<a href="Computer_Close.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">关机</a>
		<a href="Computer_Restart.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重启</a>
		<a href="Computer_Logoff.action" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'">注销</a>
	</div>
  </body>
</html>
