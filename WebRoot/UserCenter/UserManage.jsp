<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户列表</title>
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
     
  <table id="userRListBox" title="用户信息列表" class="easyui-datagrid" style="width:auto;height:550px;"
            url="../OutPrint?outPrintInforCmd=yhlb_r"
            toolbar="#userRListBoxtoolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="username" style="width:20%;">用户名</th>
                <th field="realname" style="width:20%;">真实姓名</th>
                <th field="typeString" style="width:19%;">用户类型</th>
                <th field="stateString" style="width:20%;">用户状态</th>
                <th field="regtime" style="width:20%;">注册时间</th>
            </tr>
        </thead>
    </table>
    <div id="userRListBoxtoolbar">

        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateUser()">更新用户信息</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteUser()">删除用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-lock" plain="true" onclick="disableUser()">禁用该账号</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-man" plain="true" onclick="enableUser()">认证该账号</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="showUser()">查看用户信息</a>
         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-sum" plain="true" onclick="allUser()">列表全部</a>
    </div>
    
    <div id="Update_User_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#Update_User_buttons">
        <div class="ftitle">更新用户信息</div> 
              <form id="Update_User_form" method="post" enctype="multipart/form-data" novalidate>
               <input type="hidden" name="type" readonly >
               <input type="hidden" name="state" readonly >
               		<div class="fitem">
               			<label>用户名:</label>
                		<input name="username" class="easyui-textbox" readonly >
              	 </div>
                 <div class="fitem">
                		<label>真实姓名:</label>
               			 <input name="realname" class="easyui-textbox" required="true">
              	</div>
            	 <div class="fitem">
               		 <label>用户类型:</label>
               		 <input name="typeString" class="easyui-textbox" readonly>
            	</div>
 
            	 <div class="fitem">
               		 <label>用户状态:</label>
               		 <input name="stateString" class="easyui-textbox" readonly>
            	</div>
            	 <div class="fitem">
               		 <label>注册时间:</label>
               		 <input name="regtime" class="easyui-textbox" readonly>
            	</div>
        </form>
    </div>
    <div id="show_User_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#showUserInfor-buttons">
        <div class="ftitle">查看用户信息</div> 
        <form id="fm_user" novalidate>
               <input type="hidden" name="type" readonly >
               <input type="hidden" name="state" readonly >
               		<div class="fitem">
               			<label>用户名:</label>
                		<input name="username" class="easyui-textbox" readonly>
              	 </div>
                 <div class="fitem">
                		<label>真实姓名:</label>
               			 <input name="realname" class="easyui-textbox"  readonly>
              	</div>
            	 <div class="fitem">
               		 <label>用户类型:</label>
               		 <input name="typeString" class="easyui-textbox" readonly>
            	</div>
     
            	 <div class="fitem">
               		 <label>用户状态:</label>
               		 <input name="stateString" class="easyui-textbox" readonly>
            	</div>
            	 <div class="fitem">
               		 <label>注册时间:</label>
               		 <input name="regtime" class="easyui-textbox" readonly>
            	</div>
            	 
            	</form>
    </div>
      <div id="addUserDlg" class="easyui-dialog" style="width:400px;height:230px;padding:10px 20px"
            closed="true" buttons="#table_button_add">
        <div class="ftitle">添加新用户</div> 
        <br>
              <form id="formnewuser" method="post"  novalidate>
               		<div class="fitem">
               			<label>用户名:</label>
                		<input name="username" class="easyui-textbox" required="true">
              	 </div>
              	<div class="fitem">
               		 <label>密码:</label>
               		 <input name="password" type="password"class="easyui-textbox" required="true">
            	</div>
                 <div class="fitem">
                		<label>真实姓名:</label>
               			 <input name="realname" class="easyui-textbox"  required="true">
              	</div>	 
        </form>
    </div>
      <div id="table_button_add">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addUserDlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <div id="Update_User_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUpdateUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#Update_User_dlg').dialog('close')" style="width:90px">取消</a>
    </div>

    <div id="showUserInfor-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#show_User_dlg').dialog('close')" style="width:90px">确定</a>
    </div>
    <script type="text/javascript">
    
        var url;
        function newUser(){
             $('#addUserDlg').dialog('open').dialog('center').dialog('setTitle','新增用户');
             
            
        }
         function allUser(){
            $('#userRListBox').datagrid({
             url:'../OutPrint?outPrintInforCmd=yhlb_r'
         });
            
        }
        function updateUser(){
            var row = $('#userRListBox').datagrid('getSelected');
            if (row){
                $('#Update_User_dlg').dialog('open').dialog('center').dialog('setTitle','更新用户信息');
                $('#Update_User_form').form('load',row);
                url = 'Users_UpdateRealnameManage.action';
            }
        }
        function showUser(){
            var row = $('#userRListBox').datagrid('getSelected');
            if (row){
                $('#show_User_dlg').dialog('open').dialog('center').dialog('setTitle','查看用户详细信息');
                $('#fm_user').form('load',row);
                
            }
        }
        function saveUser(){
        
        
        $.messager.confirm('确认','添加该用户吗?',function(r){
             if (r){
                     $('#formnewuser').form('submit',{
                url: 'Users_NewUser.action',
                onSubmit: function(){
                    return $(this).form('validate');
                    
                },

                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.alert({
                            title: '错误',
                            msg: '用户添加失败'
                        });
                       
                    } else {
                         $.messager.alert({
                            title: '成功',
                            msg: '用户添加成功'
                        });
                        $('#addUserDlg').dialog('close');        // close the dialog
                         // reload the user data
                         allUser();
                          $('#formnewuser').form('clear');
                    }
                }
            });
            
                        
                 }
             });
           
        }
        function saveUpdateUser(){
            $('#Update_User_form').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                         $.messager.alert({
                            title: '错误',
                            msg: '更新该用户信息失败.原因可能如下:1、用户权限不足；2、更新的的用户不存在；3、未知名错误；4、用户字段信息不正确；'
   
                        });
                    } else {
                        $('#Update_User_dlg').dialog('close');        // close the dialog
                        $('#userRListBox').datagrid('reload');    // reload the user data
                        $.messager.alert({
                            title: '更新用户信息成功',
                            msg: '更新用户信息成功'
                        });
                    }
                }
            });
        }
        function deleteUser(){
            var row = $('#userRListBox').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认删除','确认删除该用户吗?',function(r){
                    if (r){
                        $.post('Users_Delete.action',{username:row.username},function(result){  
                            if (result.success){
                                $('#userRListBox').datagrid('reload');    // reload the user data
                                  $.messager.alert({
                            title: row.username+'账号删除成功',
                            msg: row.username+'账号删除成功'
                        });
                            } else {
                               $.messager.alert({
                            title: '删除失败',
                            msg: '用户删除失败，原因可能如下:1、您的权限不足；2、删除的用户不存在；3、未知名错误，4、自身不能删除自身用户'
                        });
                            }
                        },'json');
                    }
                });
            }
        }
        function disableUser(){
         var row = $('#userRListBox').datagrid('getSelected');
            if (row){
                if(row.state==0){
                      $.messager.alert({
                            title: '提醒',
                            msg: '用户——'+row.username+'已经处于禁用状态,无须禁用'
                       });
                }
                else{
                $.messager.confirm('确认禁用','确认禁用'+row.username+'用户吗?',function(r){
                    if (r){
                        $.post('Users_Unable.action',{username:row.username},function(result){  
                            if (result.success){
                                $('#userRListBox').datagrid('reload');    // reload the user data
                                 $.messager.alert({
                            title: row.username+'账号禁用成功',
                            msg: row.username+'账号禁用成功'
                        });
                            } else {
                               $.messager.alert({
                            title: '禁用失败',
                            msg: '账号禁用失败，原因可能如下:1、您的权限不足；2、您想禁用的账号不存在；3、未知名错误，4、自身不能禁用自身用户'
                        });
                            }
                        },'json');
                    }
                });
                }
            }
            else
            {
                     $.messager.alert({
                            title: '提醒',
                            msg: '用请至少选择一个用户'
                        });
            }
        }
         function enableUser(){
         var row = $('#userRListBox').datagrid('getSelected');
            if (row){
                if(row.ustate==1){
                      $.messager.alert({
                            title: '提醒',
                            msg: '用户——'+row.username+'已经处于正常状态,无须认证'
                       });
                }
                else{
                $.messager.confirm('确认认证','确认认证'+row.username+'用户吗?',function(r){
                    if (r){
                        $.post('Users_Enable.action',{username:row.username},function(result){  
                            if (result.success){
                                $('#userRListBox').datagrid('reload');    // reload the user data
                                 $.messager.alert({
                            title: row.username+'账号认证成功',
                            msg: row.username+'账号认证成功'
                        });
                            } else {
                               $.messager.alert({
                            title: '认证失败',
                            msg: '账号认证失败，原因可能如下:1、您的权限不足；2、您想认证的账号不存在；3、未知名错误；4、自身不能认证自身用户'
                        });
                            }
                        },'json');
                    }
                });
                }
            }
            else
            {
                     $.messager.alert({
                            title: '提醒',
                            msg: '用请至少选择一个用户'
                        });
            }
        }
    </script>
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
