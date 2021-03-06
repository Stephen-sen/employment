<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  
 * @version 1.0.01
 * @author zhangmin
-->
<html>
<head>
<%@ include file="/common/header.jsp"%>
</head>
<script type="text/javascript">
function back() {
	history.go(-1);
}
function onSubmit(){
	$("#form1").validate();
	var newPwd=$('#newPwd').val();
	var qrPwd=$('#qrPwd').val();
	if(newPwd != qrPwd){
		alert("两次密码输入不一致，请重新输入！");
		$('#newPwd').val("");
		$('#qrPwd').val("");
	}
    $('#form1').submit();
}

function checkOldPwd(){
    	var oldPwd=$('#oldPwd').val();
        $.ajax({
            cache: false,
            type: 'post',
            async: false,
            url: '${path}/userController/checkOldPwd.do?',
            data : {"id": $('#idHid').val(),
            	"passWord":oldPwd},
            dataType: 'json',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
			},
			success : function(data) {
				$(data).each(function(i, item) {
					if(true != item){
						$('#oldPwd').val("");
						jqueryUIAlert("原始密码输入错误！");
					}
				});
			}
        });
	}
    
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/userController/updatePwd.do" method="post">
                            <input type="hidden" name="id" id="idHid" value="${user.id}"/>
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>修改密码</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                 	<td class="ltd4">姓名</td>
                                    <td class="rtd4"> <input  autocomplete="off" id="userNameText" type="text"  name="userName" value="${user.userName}" class=" width-p40"style="background-color: #E6E9F0" readonly="readonly"/></td>
                                    <td class="ltd4">原始密码</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="oldPwd" type="password"  name="passWord" class="required width-p40" maxlength="50" onblur="checkOldPwd()" />
                                        <span class="color-red">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">新密码</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="newPwd" type="password"  name="newPwd" class="required width-p40" maxlength="50" />
                                        <span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">确认密码</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="qrPwd" type="password"  name="newPwd1" class="required width-p40" maxlength="100" />
                                        <span class="color-red">*</span>
                                    </td>
                                </tr>
								<tr>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">保存</a>
                                        <a href="javascript:back();" class="btn1 btn-small">返回</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <center>
        <t:Footer></t:Footer>
     </center>
  </body>
</html>
