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
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/abilityController/save.do" method="post">
                            <input type="hidden" name="id" id="idHid" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb" >
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>考核项目录入</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">名称</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="nameTxt" autocomplete="off" type="text"  name="name" class="required width-p40" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">类型 </td>
                                    <td>
                                     <select id="assessmentTypeTxt"  name="assessmentType" class="required width-p40">
											<option value="">---请选择---</option>
											<option value="01">一级</option>
											<option value="02">二级</option>
										</select>
										<span class="color-red">*</span>
                                    </td>
                                </tr>
                                 <tr>
                                 <td class="ltd4">最大分值</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="maxScoreTxt" autocomplete="off" type="text"  name="maxScore" class="required width-p40" maxlength="2" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">所属上级 </td>
                                    <td>
                                     <select id="preIdTxt"  name="Ability.preId" class="required width-p40">
                                     		<option value="">---请选择---</option>
                                     		<c:forEach var="item" items="${preAbilityList}">
											<option value="${item.id }">${item.name }</option>
											</c:forEach>
										</select>
										<span class="color-red">*</span>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">考核内容说明</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="descriptionTxt" autocomplete="off" type="text"  name="description" class="required width-p80" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4"></td>
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
  </body>
</html>
