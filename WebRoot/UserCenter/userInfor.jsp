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
    <script type="text/javascript">
		function resetForm(){
		        $('#userinfor').form('clear');
		}
      function submitForm(){
             $.messager.confirm('确认','确认修改个人信息吗?',function(r){
             if (r){
              $('#userinfor').form('submit',{
                url: 'Users_UpdateInforUser.action',
                onSubmit: function(){
                    return $(this).form('validate');
                    
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.alert({
                            title: '失败',
                            msg: '修改个人信息失败'
                        }); 
                    }
                  else{
                    $.messager.alert({
                            title: '成功',
                            msg: '修改个人信息成功'
                        }); 
                    window.location.href="userInfor.jsp";
                        
                  }
                     
                }
            });    
            }
            });
            }
           function clearForm(){
                $('#ftpForm').form('clear');
            }
            $.extend($.fn.validatebox.defaults.rules, {
    minLength : { // 判断最小长度 
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            return value.length >= param[0]; 
        }, 
        message : '最少输入 {0} 个字符。'
    }, 
    length:{validator:function(value,param){ 
        var len=$.trim(value).length; 
            return len>=param[0]&&len<=param[1]; 
        }, 
            message:"输入大小不正确"
        }, 
    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入姓名或者您的名字中包含除中文或者英文中的其他字符。'
    }, 
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的密码不一致！'   
    },
      equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '两次密码不一致' }
});
    </script>
  </head>
  
  <body>
     <div class="easyui-panel" title="用户信息" style="width:auto" >
        <div style="padding:10px 60px 20px 60px">
            <table cellpadding="5" style="margin:0 auto;">
                <tr>
                    <td>用户名:</td>
                    <td><input class="easyui-validatebox easyui-textbox"  type="text" id="username" name="username"  value="${sessionScope.user.username }" readonly></input></td>
                </tr>
                <tr>
                    <td>真实姓名:</td>
                    <td><input class="easyui-validatebox easyui-textbox" type="text" id="realname" name="realname" value="${sessionScope.user.realname}" readonly></input></td>
                </tr>
                <tr>
                    <td>用户状态:</td>
                    <td><input class="easyui-validatebox easyui-textbox" type="text" id="state" name="state" value="${sessionScope.user.stateString}" readonly></input></td>
                </tr>
                <tr>
                    <td>用户类型:</td>
                    <td><input class="easyui-validatebox easyui-textbox" type="text" id="type" name="type" value="${sessionScope.user.typeString}" readonly></input></td>
                </tr> 
               <tr>
                    <td>注册时间:</td>
                    <td><input class="easyui-validatebox easyui-textbox" type="text" id="regtime" name="regtime" value="${sessionScope.user.regtime}" readonly></input></td>
                </tr> 
                </table>
            </div>
        </div>
        <br>
         <div class="easyui-panel" title="修改信息" style="width:auto" >
        <div style="padding:10px 60px 20px 60px">
        <form id="userinfor" name="userinfor" method="post" >
            <table cellpadding="5" style="margin:0 auto;">
                <tr>
                    <td>真实姓名:</td>
                    <td><input class="easyui-validatebox easyui-textbox" type="text" id="realname" name="realname" data-options="required:true,validType:'name',missingMessage:'真实姓名长度不符合'" value="${sessionScope.user.realname}"></input></td>
                </tr>
                <tr>
                    <td>原始密码:</td>
                    <td><input class="easyui-validatebox easyui-textbox passwordbox" type="password" id="opassword" name="opassword" data-options="required:true,missingMessage:'请输入密码',validType:'length[6,20]'" value=""></input></td>
                </tr>
                <tr>
                    <td>修改密码:</td>
                    <td><input class="easyui-validatebox easyui-textbox passwordbox" type="password" id="npassword" name="npassword" data-options="required:true,missingMessage:'请输入密码',validType:'length[6,20]'" value=""></input></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input class="easyui-validatebox easyui-textbox passwordbox" type="password" id="rnpassword" name="rnpassword" data-options="required:true,missingMessage:'请再次输入密码',validType:'length[6,20]'" value="" validType="equalTo['#npassword']" invalidMessage="两次输入密码不一致"></input></td>
                </tr> 
                </table>
            </form>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;height:30px;font-size:30px;" onclick="submitForm()">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;height:30px;font-size:30px;" onclick="resetForm()">重置</a>
             </div> 
            </div>
        </div>
  </body>
</html>
