<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/login.css" rel="stylesheet" type="text/css" />
	<c:if test="${null!=sessionScope.user}">
      <script type="text/javascript">
      window.location.href='UserCenter/Main.jsp';
      </script>
    </c:if>
    <c:if test="${param.error=='yes'}">
          <script type="text/javascript">
      alert("登录失败，请检查用户名和密码");
      </script>
    </c:if>
  </head>
  
  <body>
   <div class="main-login">
	<div class="login-content">	
	<h2 style="color:black;"><b>用户登录</b></h2>
    <form action="User_Login.action" method="post" id="login-form" name="login-form">
    <div class="login-info">
	<span class="user">&nbsp;</span>
	<input name="username" id="username" type="text"  value="" placeholder="请输入用户名" class="login-input" required="required"/>
	</div>
    <div class="login-info">
	<span class="pwd">&nbsp;</span>
	<input name="password" id="password" type="password"  value="" placeholder="请输入密码" class="login-input" required="required"/>
	</div>
    <div class="login-oper">
	<input name="login" type="submit" value="登 录" class="login-btn" />
	<input name="reset" type="reset" value="重 置" class="login-reset"/>
	</div>
    </form>
    </div>   
</div>   

  </body>
</html>
