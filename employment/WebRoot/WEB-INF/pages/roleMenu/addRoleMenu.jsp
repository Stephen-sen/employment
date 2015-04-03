<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/tagslib.jsp" %>
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

$(document).ready(function() {
	initMenuList();
})

function initMenuList() {
	var ids = eval('${idjson}');
    $.each(ids, function(i, n){
        $("#_" + n.menuId).prop("checked", "true");
    });

	var menus = $("input[type='checkbox']");
	$("#allSelect").prop("checked", "true");
	for ( var i = 0; i < menus.length; i++) {
		if (!menus[i].checked) {
			$("#allSelect").removeAttr("checked");
			continue;
		}
	}
}

function checkAll(box) {
	if (box.checked) {
		$("input[type='checkbox']").prop("checked", "true");
	} else {
		$("input[type='checkbox']").removeAttr("checked");
	}
}

function checkSome(box, id) {
	if (box.checked) {
		$("input[name*='" + id + "']").each(function() {
			$(this).prop("checked", "true");
		});
	} else {
		$("input[name*='" + id + "']").each(function() {
			$(this).removeAttr("checked");
		});
	}
}

/**
 * 功能：保存菜单
 */
function onSubmit() {
	var menus = $("input[type='checkbox']");
	var menuStr = "";
	for ( var i = 0; i < menus.length; i++) {
		if (menus[i].checked && menus[i].name != "") {
			menuStr += menus[i].value + ",";
		}
	}
	menuStr = menuStr.substring(0, menuStr.length - 1);
	$('#menuStrHid').val(menuStr);
	$('#form1').submit();
}
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/roleMenuController/save.do" method="post">
                            <input type="hidden" name="role.id" id="idHid" value="${roleInfo.id }" />
                            <input type="hidden" id="menuStrHid" name="roleMenuStr" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>分配角色菜单</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">角色名称</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="nameTxt" autocomplete="off" type="text"  name="name" value="${roleInfo.name }" class="width-p40" style="background-color: #E6E9F0" readonly="readonly"/>
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">角色说明</td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="descriptionTxt" type="text"  name="description" value="${roleInfo.description }" class="width-p80" style="background-color: #E6E9F0" readonly="readonly" />
                                    </td>
                                </tr>
                                <tr>
							<td colspan="4"><font size=2 color="black"> <strong>
										<input id="allSelect" type="checkbox"
										onclick="javascript:checkAll(this);" />全选</strong>&nbsp;&nbsp;&nbsp; </font>
							</td>
						</tr>
                                <c:forEach var="menu" items="${menuList}" varStatus="s">
							<c:if test="${menu.menuType eq '01' && menu.preId != 'null'}">
								<tr>
									<td class="ltd4"><font size=2 color="black"><strong> <input
												type="checkbox" id="_${menu.id}" name="_${menu.id}"
												onclick="javascript:checkSome(this,'${menu.id}');"
												value="${menu.id }" />${menu.name} </strong> </font></td>

									<td class="rtd4"  colspan="3">
											 <c:forEach var="menus" items="${menuList}" varStatus="s">
											<c:if test="${menus.preId.id eq menu.id}">
											
												<input id="_${menus.id}" name="_${menu.id}_${menus.id}"
													onclick="javascript:checkSome(this,'${menus.id}');"
													type="checkbox" value="${menus.id}" />${menus.name}
											</c:if>
											</c:forEach>
									</td>
								</tr>
							</c:if>
						</c:forEach>
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
