<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>日志信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/color.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-ins/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"  src="plug-ins/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
   </head>
  
  <body>  
  <table id="userRListBox" title="日志信息" class="easyui-datagrid" style="width:auto;height:550px;"
            url="../OutPrint?outPrintInforCmd=loglist"
            toolbar="#userRListBoxtoolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="time" >时间</th>
                <th field="username" >用户名</th>
                <th field="realname" >真实姓名</th>
                <th field="action" >动作</th>
                <th field="result" >结果</th>
            </tr>
        </thead>
    </table>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
  </body>
</html>
