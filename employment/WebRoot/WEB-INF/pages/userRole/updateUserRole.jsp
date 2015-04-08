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
		$('#form1').submit();
	}
	$(document).ready(function() {
		$("#form1").validate();
		$("#roleSelect").val("${userRole.role.id}");
	});
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/userRoleController/update.do" method="post">
                            <input type="hidden" name="user.id" id="idHid" value="${userInfo.id }" />
                            <input type="hidden" name="id" id="idHid" value="${userRole.id }" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>分配角色信息</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">用户名称</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="nameTxt" autocomplete="off" type="text"  name="user.name" value="${userInfo.userName }" class="width-p40" style="background-color: #E6E9F0" readonly="readonly" />
										<span class="color-red">*</span>
                                    </td>
                                     <td class="ltd4">角色名称</td>
                                    <td class="rtd4">
										 <select id="roleSelect"  name="role.id" class="required width-p40">
                                     		<option value="">---请选择---</option>
                                     		<c:forEach var="item" items="${roleList}"varStatus="s">
                                      		<option value="${item.id }">${item.name}</option>
                                    		</c:forEach>
										</select>
										<span class="color-red">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">保存</a>
                                         <a href="javascript:back();" class="btn1 btn-small">取消</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
  </body>
</html>
