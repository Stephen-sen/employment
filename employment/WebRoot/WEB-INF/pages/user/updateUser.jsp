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

$(document).ready(function() {
	$("#form1").validate();
	$("#sexSelect").val("${userInfo.sex}");
	$("#majorSelect").val("${userInfo.major.id}");
});	
function back() {
	history.go(-1);
}
function onSubmit(){
    $('#form1').submit();
}
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/userController/update.do" method="post">
                            <input type="hidden" name="id" id="idHid" value="${userInfo.id}"/>
                            <input type="hidden" name="type" value="${type}"/>
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>更新用户信息</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">姓名</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="cnNameTxt" autocomplete="off" type="text"  name="userName" value="${userInfo.userName }" class="required width-p40" maxlength="20" />
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
									</td>
                                    <td class="ltd4">出生日期</td>
                                    <td class="rtd4">
                                        <input  name="birthDate" value="${userInfo.birthDate }" class="width-p40" type="text" autocomplete="off"
												onfocus="WdatePicker({skin:'whyGreen',readOnly:'true'})" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">邮箱</td>
                                    <td class="rtd4">
                                        <input  autocomplete="email" id="emailTxt" type="text"  name="email"value="${userInfo.email}" class=" width-p40" maxlength="100" />
                                    </td>
                                    <td class="ltd4">电话 </td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="telTxt" type="text"  name="tel" value="${userInfo.tel }"class=" width-p40" maxlength="50" />
                                        <span class="color-red">*</span>
                                    </td>
                                </tr>
								 <tr>
                                     <td class="ltd4">地址 </td>
                                    <td class="rtd4" colspan="3">
                                        <input  autocomplete="off" id="addressTxt" type="text"  name="address" value="${userInfo.address }"class=" width-p80" maxlength="50" />
                                        <span class="color-red">*</span>
                                    </td>
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
