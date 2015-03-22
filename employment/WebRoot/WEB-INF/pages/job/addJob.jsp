<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/tagslib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  
 * @version 1.0.01
 * @author zhangmin
-->
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

	function showMenu(id,str) {
		var greadObj = $("#"+id);
		var greadOffset = $("#"+id).offset();
		$("#menuContent"+str).css({left:greadOffset.left + "px", top:greadOffset.top + greadObj.outerHeight() + "px"}).slideDown("fast");
	}

	function hideMenu(str) {
		$("#menuContent"+str).fadeOut("fast");
	}

	function checkItem(id1, flg, id2){
        if (flg == 'N') {
            if ($('#' + id1).prop("checked")) {
                $('#' + id1).removeAttr("checked");
            }
            else {
                $('#' + id1).prop("checked", "true");
            }
        }
        if ($('#' + id1).prop("checked")) {
            var jobIdStr = $('#jobid'+id2).val();
			var jobValue = $('#jobValue'+id2).val();
            var str = "," + $("#span_" + $('#' + id1).val()).html();
			var jobValueStr = ","+ $('#' + id1).val();
            jobIdStr = jobIdStr + str;
			jobValue = jobValue + jobValueStr;
            var indexf = jobIdStr.indexOf(",");
            if (indexf == 0) {
                jobIdStr = jobIdStr.substring(1, jobIdStr.length);
				jobValue = jobValue.substring(1, jobValue.length);
            }
            $('#jobid'+id2).val(jobIdStr);
			$('#jobValue'+id2).val(jobValue);
        }
        else {
            var jobIdStr = $('#jobid'+id2).val();
			var jobValue = $('#jobValue'+id2).val();
            jobIdStr = jobIdStr + ",";
			jobValue = jobValue + ",";
            var str = $("#span_" + $('#' + id1).val()).html() + ",";
			var jobValueStr = $('#' + id1).val() + ",";
            jobIdStr = jobIdStr.replace(str, "");
			jobValue = jobValue.replace(jobValueStr, "");
            var indexf = jobIdStr.indexOf(",");
            var indexl = jobIdStr.lastIndexOf(",");
            if (indexf == 0) {
                jobIdStr = jobIdStr.substring(1, jobIdStr.length);
				jobValue = jobValue.substring(1, jobIdStr.length);
            }
            if (indexl == jobIdStr.length - 1) {
                jobIdStr = jobIdStr.substring(0, jobIdStr.length - 1);
            }
			 if (jobValue.lastIndexOf(",") == jobValue.length - 1) {
                jobValue = jobValue.substring(0, jobValue.length - 1);
            }
            $('#jobid'+id2).val(jobIdStr);
			$('#jobValue'+id2).val(jobValue);
        }
    }
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190" >
                <div class="margin-lr-1">
                    <div class="main-page-90 over-flow-x-hidden">
                        <form id="form1" action="${path}/jobController/save.do" method="post">
                            <input type="hidden" name="id" id="idHid" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>招聘信息录入</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">公司名称</td>
                                    <td class="rtd4">
                                     <select id="preIdTxt"  name="company.id" class="required width-p40">
                                     		<option value="">---请选择---</option>
                                     		<c:forEach var="item" items="${companyList}"varStatus="s">
                                      		<option value="${item.id }">${item.name }</option>
                                    		</c:forEach>
										</select>
                                    </td>
                                    <td class="ltd4">招聘职位 </td>
                                    <td class="rtd4">
                                     <select id="preIdTxt"  name="position.id" class="required width-p40">
                                     		<option value="">---请选择---</option>
                                     		<c:forEach var="item" items="${positionList}"varStatus="s">
                                      		<option value="${item.id }">${item.name }</option>
                                    		</c:forEach>
										</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">薪资待遇</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="salaryTxt" autocomplete="off" type="text"  name="salary" class="required width-p40" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">联系人 </td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="contactPersonTxt" type="text"  name="contactPerson" class=" width-p40" maxlength="50" />
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">职位描述</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="descriptionTxt" autocomplete="off" type="text"  name="description" class="required width-p40" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">要求</td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="demandTxt" type="text"  name="demand" class=" width-p80" maxlength="50" />
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">联系电话</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="contactTelTxt" autocomplete="off" type="text"  name="contactTel" class="required width-p40" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">公司地址 </td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="addressTxt" type="text"  name="address" class=" width-p80" maxlength="50" />
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>请选择招聘考核内容</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <c:forEach var="item" items="${abilityMap}" varStatus="s">
                                <tr>
                                    <td class="ltd4">${item.key }</td>
                                    <td class="rtd4" colspan="3">
                                     <input id="jobid${item.key }" name="jobStr" type="text" class=" width-p60" readonly="readonly"/><a href="javascript:showMenu('jobid${item.key }','${item.key }');" class="btn1 btn-small">选择</a>
									 <input id="jobValue${item.key }" name="abilityArry" type="text" style="display:none"/>
										<div id="menuContent${item.key }" class="menuContent" style="display:none; position: absolute; background-color:white; border:solid 1px #adba84;">
									        <div style="width:100%; height:30px;line-height:30px; background-color:#263F52;">
									            <table style="width:100%;">
									                <tr>
									                    <td style="width:40%; text-align:right; line-height:20px;">
															<a href="javascript:hideMenu('${item.key }');" class="btn2 btn-small">关闭</a>
									                    </td>
									                </tr>
									            </table>
									        </div>
									        <div style="margin:5px;">
									            <div style="line-height:40px;">
									            <c:forEach var="abilityVal" items="${item.value}" varStatus="t">
									                <input id="check_${abilityVal.id }" style="margin:3px; height:15px;" type="checkbox" value="${abilityVal.id }" name="job" onclick="javascritp:checkItem('check_${abilityVal.id }','Y','${item.key }');"/><span id="span_${abilityVal.id }" onclick="javascritp:checkItem('check_${abilityVal.id }','N','${item.key }');">${abilityVal.name }</span>
									            </c:forEach>
									            </div>
									        </div>
									    </div>
                                    </td>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">下一步</a>
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
