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
function initMenu() {
	$("#preIdSelcet").html("");
	if($("#menuTypeSelect").val()=='03'){
		$("#menu").hide();
		$("#menuId").hide();
		$("#buttonCode").show();
		$("#buttonCodeId").show();
	}else{
		$("#menu").show();
		$("#menuId").show();
		$("#buttonCode").hide();
		$("#buttonCodeId").hide();
	}
	$('#preIdSelcet').append("<option value=\"\">---请选择---</option>");
	$.ajax({
		cache : false,
		type : 'post',
		async : false,
		url : '${path}/menuController/getMenuList.do',
		data : {"menuType": $('#menuTypeSelect').val()},
		dataType : 'json',
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(data) {
			$(data).each(
					function(i, item) {
						$('#preIdSelcet').append(
								"<option value='" + item.id + "'>"+ item.name + "</option>");
					});
		},
		complete : function(XMLHttpRequest, textStatus) {
		}
	});
}
	function back() {
		history.go(-1);
	}
	function onSubmit() {
		$("#form1").validate();
		$('#form1').submit();
	}
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/menuController/save.do" method="post">
                            <input type="hidden" name="id" id="idHid" value="${role.id }" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>菜单信息录入</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">菜单名称</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="nameTxt" autocomplete="off" type="text"  name="name" class="required width-p40" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">菜单类型</td>
                                    <td class="rtd4">
                                 <select id="menuTypeSelect" name="menuType" class="required width-p40" onchange="initMenu()">
                                    <option value="">---请选择---</option>
                                    <option value="01">一级菜单</option>
                                    <option value="02">二级菜单</option>
                                    <option value="03">按钮</option>
                                </select>
										<span class="color-red">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4"  id="menuId">菜单URL</td>
                                    <td class="rtd4" id="menu">
                                     <input  autocomplete="off" id="urlTxt" type="text"  name="url" class="width-p80" maxlength="255" />
                                    </td>
                                     <td class="ltd4" id="buttonCode"  style="display:none" >按钮代号</td>
                                     <td class="rtd4" id="buttonCodeId"  style="display:none">
                                     <input  autocomplete="off" id="buttonCodeTxt" type="text"  name="buttonCode" class="width-p40" maxlength="255" />
                                     <span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">上级菜单 </td>
                                    <td class="rtd4">
                                     <select id="preIdSelcet" name="preId.id" class="required width-p40">
                                    <option value="">---请选择---</option>
                                	</select>
                                	<span class="color-red">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">菜单描述</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="descriptionTxt" autocomplete="off" type="text"  name="description" class="width-p80" maxlength="200" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4"> </td>
                                    <td>
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
        <center>
        <t:Footer></t:Footer>
     </center>
  </body>
</html>
