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
	function onSubmit() {
		checkPass();
	}
	function checkName(){
		var userName=$('#cnNameTxt').val();
        $.ajax({
            cache: false,
            type: 'post',
            async: false,
            url: '${path}/userController/checkUserName.do?',
            data : {"userName" : userName},
            dataType: 'json',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
			},
			success : function(data) {
				$(data).each(function(i, item) {
					if('true' == item){
						jqueryUIAlert("该用户已存在！");
						$('#cnNameTxt').val("");
						return;
						}
				});
			}
        });
	}
	function checkPass(){
		passWord1 = $("#passwordText").val();
		passWord2 = $("#passwordText1").val();
		if(passWord1 != passWord2){
			$("#passwordText").val("");
			$("#passwordText1").val("");
			jqueryUIAlert("两次密码输入不一致，请重新输入！");
			}else{
				$("#form1").validate();
				$('#form1').submit();
			}
		}
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/userController/save.do" method="post">
                            <input type="hidden" name="id" id="idHid" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>个人信息注册</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">姓名</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="cnNameTxt" autocomplete="off" type="text"  name="userName" class="required width-p40" maxlength="20" onblur="checkName()" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4"> 性别 </td>
                                    <td class="rtd4">
                                    	<select id="sexSelect"  name="sex" class="required width-p40">
											<option value="M">男</option>
											<option value="W">女</option>
										</select>
										<span style="color:red">*</span>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">专业</td>
                                    <td class="rtd4">
                                        <select id="majorSelect" name="major.id" class="required width-p40">
	                                           <option value="">---请选择---</option>
	                                           <c:forEach var="item" items="${majorList}">
	                                           <option value="${item.id }">${item.name }</option>
	                                           </c:forEach>
										</select>
										<span class="color-red">*</span>
									</td>
                                    <td class="ltd4">出生日期</td>
                                    <td class="rtd4">
                                        <input  name="birthDate" class="required width-p40" type="text" autocomplete="off"
												onfocus="WdatePicker({skin:'whyGreen',readOnly:'true'})" />
												<span class="color-red">*</span>
                                    </td>
                                </tr>
								 <tr>
                                    <td class="ltd4">电话 </td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="telTxt" type="text"  name="tel" class="required  width-p40" maxlength="50" />
                                        <span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">邮箱</td>
                                    <td class="rtd4">
                                        <input  autocomplete="email" id="emailTxt" type="text"  name="email" class=" width-p40" maxlength="100" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">密码</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="passwordText" type="password"  name="passWord" class="required  width-p40" maxlength="50" />
                                        <span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">确认密码</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="passwordText1" type="password"  name="passWord1" class="required  width-p40" maxlength="100"/>
                                        <span class="color-red">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">注册类型 </td>
                                    <td class="rtd4">
                                       <select id="roleSelect" name="role.id" class="required width-p40">
	                                           <option value="">---请选择---</option>
	                                           <c:forEach var="item" items="${roleList}">
	                                           <option value="${item.id }">${item.name }</option>
	                                           </c:forEach>
										</select>
                                        <span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">地址 </td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="addressTxt" type="text"  name="address" class="required  width-p80" maxlength="50" />
                                        <span class="color-red">*</span>
                                    </td>
                                </tr>
								<tr>
								
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">注册</a>
                                         <a href="javascript:back();" class="btn1 btn-small">取消</a>
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
