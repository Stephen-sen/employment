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
		$("#form1").validate();
		$('#form1').submit();
	}
	function setVal(id,name){
		var value=$('#'+name).val();
		$('#'+id).val(value);
		}
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-90 over-flow-x-hidden">
                        <form id="form1" action="${path}/stuAbiController/save.do" method="post">
                            <input type="hidden" name="student.id" id="idHid" value="${user.id }" />
                            <input type="hidden" name="job.id" id="idHid" value="${job.id }" />
                            <input type="hidden" name="job.company.id" id="idHid" value="${job.company.id }" />
                            <input type="hidden" name="job.position.id" id="idHid" value="${job.position.id }" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4" style="background-color: #4A5D62">
                                        <font size=2 color="white">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>个人就业能力评价</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">姓名</td>
                                    <td class="rtd4">
                                        <input  id="nameTxt" autocomplete="off" type="text"  name="student.name" value="${user.userName}" class="width-p40"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd4">联系方式</td>
                                    <td class="rtd4">
                                        <input id="nameTxt" autocomplete="off" type="text"  name="student.tel"value="${user.tel}" class="width-p40"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    </tr>
                                    <tr>
                                    <td class="ltd4">所选公司</td>
                                    <td class="rtd4">
                                     <input id="companyNameTxt" type="text"  name="job.company.name"value="${job.company.name}" class=" width-p40"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                     <td class="ltd4">所选职位 </td>
                                    <td class="rtd4">
                                     <input id="positionNameTxt" type="text"  name="job.position.name" value="${job.position.name}"class=" width-p40"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="td-title" colspan="4"style="background-color: #4A5D62">
                                        <font size=2 color="white">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>以下为${job.company.name}公司${job.position.name}职位的考核素质项，请根据个人情况进行自我评价</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td-title" colspan="4" style="background-color: #4A5D62">
                                        <font size=2 color="white">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>注：非常好---（5）分，好---（4）分，一般---（3）分，差---（2）分，很差---（1）分</strong>
                                        </font>
                                    </td>
                                </tr>
                                <c:forEach var="item" items="${abilityMap}" varStatus="s">
                                <tr>
                                     <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>${item.key}</strong>
                                        </font>
                                    </td>
                                </tr>
                                   <tr>
                                     <c:forEach begin="0" end="${fn:length(item.value)}" step="1" varStatus="stat" items="${item.value}"  var="posAbiList">
						               <td class="ltd4">${posAbiList.ability.name}</td>
                                    <td class="rtd4">
                                    <input id="scoreKeyTxt" type="hidden" name="scoreKeyArray" value="${posAbiList.ability.id}"/>
                                    <input id="${posAbiList.ability.id}" type="hidden" name="scoreValArray"/>
                                  	<select id="${posAbiList.ability.name}"  name="${posAbiList.ability.name}" class="required width-p40" onchange="setVal('${posAbiList.ability.id}','${posAbiList.ability.name}')">
											<option value="">---请选择---</option>
											<option value="5">非常好</option>
											<option value="4">好</option>
											<option value="3">一般</option>
											<option value="2">差</option>
											<option value="1">很差</option>
										</select>
                                    </td>
						               <c:if test="${(stat.index+1)%2==0}">
						                </tr><tr>
						                </c:if>
						            </c:forEach>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">进行分析</a>
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
