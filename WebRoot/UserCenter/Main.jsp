<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>用户中心</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/color.css">

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-ins/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"  src="plug-ins/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
			$(document).ready(function()
			{
			    $("#firstpane .menu_body:eq(0)").show();
				$("#firstpane p.menu_head").click(function(){
				    if($(this).hasClass("current")){
				          $(this).removeClass("current");
				    }
				    else{
				          $(this).addClass("current");
				     
				    }
				     $(this).next("div.menu_body").slideToggle(300);
					//$(this).addClass("current").next("div.menu_body").slideToggle(300);
					//$(this).siblings().removeClass("current");				
				});
				$("#firstpane div.menu_body a").click(function()
				{
					$("#content iframe").attr("src",$(this).attr("href"));
					$("#position").text("当前位置: "+$(this).parent().prev().text()+">>"+$(this).text());
					
					return;
				});
			})
			function iFrameHeight() {   
				var ifm= document.getElementById("display");   
				var subWeb = document.frames ? document.frames["display"].document : ifm.contentDocument;   
				if(ifm != null && subWeb != null) {
				   ifm.style.height = subWeb.body.scrollHeight;
				   ifm.style.width = subWeb.body.scrollWidth;
				   
				}   
				} 
			function LoginOut(){
			$.messager.confirm('确认退出','确定退出本系统码?',function(r){
               if (r){

                     window.location.href="User_LoginOut.action";
                }
          
            });
			}
		 window.setInterval(JudgeAuth,1000);//1000为1秒钟
         function JudgeAuth()
          {
             $.post('User_Auth.action',function(result){  
                    if (result.success){  
                    } 
                    else{
                         $.messager.alert({
                              title: '账号登陆过期提醒',
                        });
                        window.location.href="../index.jsp";
                       }
              },'json');
          }
		</script>
    <link>
  </head>
  
  <body>
    <div class="head">用户 ${sessionScope.user.username },您好！</div>
    <div class="body">
    <div class="sidemenu">
        <div id="firstpane" class="menu_list">
			    <p class="menu_head current">我的服务</p>
			    <div style="display:block" class="menu_body" >
			      <a href="FtpForm.html" target="display" >访问FTP</a>
			      <a href="userInfor.jsp" target="display" >个人信息</a>
			    </div>
			    <c:if test="${sessionScope.user.type==1}">
			    <p class="menu_head current">系统管理</p>
			    <div style="display:block" class="menu_body" >
			      <a href="UserManage.jsp" target="display">用户管理</a>
			      <a href="Log.jsp" target="display">日志信息</a>
			      <a href="File_DownloadClear.action" target="display">清空下载目录</a>
			      <a href="computerControl.jsp" target="display">电脑选项</a>
			    </div> 
			    </c:if>
			     <p class="menu_head current">退出系统</p>
			    <div style="display:block" class="menu_body" >
			      <a href="javascript:void(0)" onclick="LoginOut()">退出系统</a>			   	      			  		      
			    </div>
		    </div>
	    </div>
	 	<div class="content" id="content">
	 	  <div id="position" class="postion" ></div>
	 	     <br>
			<iframe src="welcome.jsp" id="display" name="display" class="iframe" frameborder="no" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()"></iframe>	
		</div>
    </div>
  </body>
</html>
