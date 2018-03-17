<%@ page language="java" import="java.util.*,pojo.Ftp" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<% 
			if(request.getParameter("ftpUrl")==null||request.getParameter("path")==null||request.getParameter("parentPath")==null||"".equals(request.getParameter("ftpUrl").replaceAll(" ", ""))||"".equals(request.getParameter("path").replaceAll(" ", ""))||"".equals(request.getParameter("parentPath").replaceAll(" ", "")))
			{
			response.sendRedirect("FtpForm.html"); 
			   //request.getRequestDispatcher("FtpForm.html").forward(request, response);
			   return;
			}
			if(request.getSession().getAttribute(request.getParameter("ftpUrl"))==null){
			response.sendRedirect("FtpForm.html"); 
			//request.getRequestDispatcher("FtpForm.html").forward(request, response);
			}
			
			Ftp ftpSession=(Ftp)request.getSession().getAttribute(request.getParameter("ftpUrl"));
			session.setAttribute("ftp", ftpSession);
	%>
<jsp:useBean id="function" class="service.Function"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>正在访问ftp://${param.ftpUrl}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="plug-ins/jquery-easyui-1.4.3/themes/color.css">
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

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="plug-ins/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"  src="plug-ins/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
    
        function GetQueryString(name) {
		   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
		   var r = window.location.search.substr(1).match(reg);
		   if (r!=null) return (r[2]); return null;
	     }
         function openDialog(cmd,path){
             if(cmd==1){
                $('#uploadFileTo1').dialog('open').dialog('center').dialog('setTitle','上传文件至本目录'+decodeURI(GetQueryString("path")));
                $('#f1').textbox('setValue',decodeURI(GetQueryString("path")));
              }
              else if(cmd==2){
                $('#uploadFileTo2').dialog('open').dialog('center').dialog('setTitle','上传文件至该文件夹'+path);
                $('#f3').textbox('setValue',path);
              }
        }
        function FileUpload(cmd){
        var url='File_Upload.action';
        if(cmd==1){
          $('#form1').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                   
                },

                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.alert({
                            title: '错误',
                            msg: '文件上传至FTP服务器失败'
                        });
                       
                    } else {
                         $.messager.alert({
                            title: '成功',
                            msg: '文件上传至FTP服务器成功'
                        });
                        $('#uploadFileTo1').dialog('close');        // close the dialog
                        window.location.href=window.location.href;    // reload the user data
                    }
                }
            });
            
          }
          else
          {
           $('#form2').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                    
                },

                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.alert({
                            title: '错误',
                            msg: '文件上传至FTP服务器失败'
                        });
                       
                    } else {
                         $.messager.alert({
                            title: '成功',
                            msg: '文件上传至FTP服务器成功'
                        });
                        $('#uploadFileTo2').dialog('close');        // close the dialog
                         // reload the user data
                    }
                }
            });
            
          }
        }
        function downloadFile(FtpFilePath){
            window.location.href=""+FtpFilePath;
        }
        function loginOutFtp(ftpUrl){
  
         $.messager.confirm('确认','确定退出该FTP服务器吗?',function(r){
             if (r){
                $.post('Ftp_LoginOut.action',{ftpUrl:ftpUrl},function(result){  
                            if (result.success){ 
                              
                                 $.messager.alert({
                                 title:'登出成功',
                                 msg: '登出FTP服务器:ftp://'+ftpUrl+'成功'
                              });
                             
                           
                            } 
                            else {
                               $.messager.alert({
                                 title: '登出失败',
                                 msg: '您可能没有登录到该ftp服务器,请检查。'
                                  });
                             }
                              
                               window.location.href= "FtpForm.html";
                              
                        },'json');
                        
                 }
             });
        }
   
    </script>
  </head>
  <body>
      <center><h1>文件列表</h1></center>

       <div class="easyui-panel" title="FTP文件目录" style="width:auto" >
       <div style="margin-left:3%;margin-right:3%;">
       <h3>FTP服务器地址: <span style="color:red;">${param.ftpUrl}</span></h3>
       <h3>所在目录: <span style="color:red;">${function.decodeBianma(param.path)} </span></h3>
       <h3>下载须知: <span style="color:red;">当您进行上传或下载文件时，因为服务器资源有限，可能会使等待时间较长，请耐心操作。另外请尽量选择0-20M之间的文件进行操作。大文件将会需要等待比较长的时间。</span></h3>
        <h3>注意: <span style="color:red;">当您使用本系统进行对FTP进行访问(包括文件的上传和下载以及其他操作)时，即表示您已经知道本系统连接到的是第三方的域名,使用本系统不能很好的保证数据传输的中所产生的可能的所有安全性问题，如果您传输的是机密数据，建议您采用其他方式进行传输.当您使用本系统时，所产生的任何问题由您（使用方本人）自行承担。</span></h3>
       </div>
       <div style="padding:5px 0;margin-left:3%;margin-right:3%;">
        <a href="?ftpUrl=${param.ftpUrl}&path=/&parentPath=/" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="width:15%">根目录</a>
        <a href="?ftpUrl=${param.ftpUrl}&path=${function.decodeBianma(param.parentPath)}&parentPath=${function.getParentPath(param.parentPath)}" class="easyui-linkbutton" data-options="iconCls:'icon-back'" style="width:15%">返回上层</a>
        <a href="?ftpUrl=${param.ftpUrl}&path=${function.decodeBianma(param.path)}&parentPath=${function.decodeBianma(param.parentPath)}" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:15%">刷新</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:15%" onclick="loginOutFtp('${param.ftpUrl}')">退出Ftp服务器</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-pencil" plain="true" onclick="openDialog(1,1)">上传文件至本目录</a>

     </div>
       <table border="1px" style="width:94%;margin-left:3%;margin-right:3%;" >
       <tr><td class="title" style="width:10%;">序号</td>
		   <td class="title" style="width:35%;">文件名</td>
		   <td class="title" style="width:10%;">文件类型</td>
		   <td class="title" style="width:20%;">文件大小</td>
		   <td class="title" style="width:25%;">操作</td>
		</tr>
		
		
		<c:set var="FileList" value="${function.getFtpList(param.path,param.ftpUrl,sessionScope.ftp)}"></c:set>
		<c:forEach  items="${FileList}" var="file" >
		<tr>
		   <td class="title" style="width:25px;">${file.id}</td>
		   <td class="title" style="width:150px;">${file.fileName}</td>
		   <td class="title" style="width:60px;"><c:if test="${file.fileType==0}">文件</c:if><c:if test="${file.fileType==1}">文件夹</c:if></td>
		   <td class="title" style="width:200px;">${file.fileSize}B</td>
		   <td class="title" style="width:150px;">
		        <c:if test="${file.fileType==1}">
		        	<a href="?ftpUrl=${param.ftpUrl}&path=${file.filePath}&parentPath=${function.decodeBianma(param.path)}" class="easyui-linkbutton">打开</a>
		        	 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-pencil" plain="true" onclick="openDialog(2,'${file.filePath}')">上传文件至该文件夹</a>
		        	
		        </c:if>
		          <c:if test="${file.fileType==0}">
		        	<a href="File_Download.action?filePath=${file.filePath}&filename=${file.fileName}" target="_blank" class="easyui-linkbutton"  >下载</a>		        	
		        </c:if>
		   </td>
		</tr>
		</c:forEach>
       </table>
       <br>
       <br>
       </div>
        <div id="uploadFileTo1" class="easyui-dialog" style="width:400px;height:230px;padding:10px 20px;"
            closed="true" buttons="#table_button1">
        <div class="ftitle">上传文件至本目录</div> 
              <br>
              <form id="form1" method="post" enctype="multipart/form-data" novalidate>
               		<div class="fitem">
               			<label>文件夹目录:</label>
                		<input id="f1" name="filePath" class="easyui-textbox" readonly >
              	 </div>
              	 <br>
                 <div class="fitem">
                		<label>上传文件:</label>
                         <input class="easyui-filebox" id="f2"name="myfile" data-options="required:true,prompt:'请选择文件'">
            	</div>
            	 
        </form>
    </div>
        <div id="uploadFileTo2" class="easyui-dialog" style="width:400px;height:230px;padding:10px 20px"
            closed="true" buttons="#table_button2">
        <div class="ftitle">上传文件至该文件夹</div> 
        <br>
              <form id="form2" method="post" enctype="multipart/form-data" novalidate>
               		<div class="fitem">
               			<label>文件夹目录:</label>
                		<input name="filePath" id="f3" class="easyui-textbox" readonly >
              	 </div>
              	 <br>
                 <div class="fitem">
                		<label>上传文件:</label>
                         <input class="easyui-filebox" id="f4" name="myfile" data-options="required:true,prompt:'请选择文件'" >
            	</div>
            	 
        </form>
    </div>
      <div id="table_button1">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="FileUpload(1)" style="width:90px">上传</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#uploadFileTo1').dialog('close')" style="width:90px">取消</a>
    </div>
     <div id="table_button2">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="FileUpload(2)" style="width:90px">上传</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#uploadFileTo2').dialog('close')" style="width:90px">取消</a>
    </div>
    
  </body>
</html>
