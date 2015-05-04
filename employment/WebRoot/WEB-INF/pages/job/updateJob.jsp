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
                    <div class="main-page-90 over-flow-x-hidden">
                        <form id="form1" action="${path}/jobController/update.do" method="post">
                            <input type="hidden" name="id" value="${jobInfo.id }" id="idHid" />
                            <input type="hidden" name="type" value="company" />
                            <input type="hidden" name="company.id" value="${jobInfo.company.id }" id="idHid" />
							<input type="hidden" value="${token}" name="token" />
                        <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4" style="background-color: #6587a1">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>更新招聘信息</strong>
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">公司名称</td>
                                    <td class="rtd4">
                                     <input id="companyTxt"  name="company.name" class="width-p40" value="${jobInfo.company.name }" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd4">招聘职位 </td>
                                    <td class="rtd4">
                                     
                                     <select id="positonTxt"  name="position.id" class="required width-p40">
                                     		<option value="">---请选择---</option>
                                     		<c:forEach var="item" items="${positionList}"varStatus="s">
                                      		<option value="${item.id }"<c:if test="${item.id == jobInfo.position.id }">selected="true"</c:if>>${item.name }</option>
                                    		</c:forEach>
										</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">薪资待遇</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="salaryTxt" autocomplete="off" type="text"  name="salary" value="${jobInfo.salary }" class="width-p40" />
                                    </td>
                                    <td class="ltd4">联系人 </td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="contactPersonTxt" type="text"  name="contactPerson" class=" width-p40" value="${jobInfo.contactPerson }" />
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">职位描述</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="descriptionTxt" autocomplete="off" type="text"  name="description" class="width-p40" value="${jobInfo.description}"/>
                                    </td>
                                    <td class="ltd4">要求</td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="demandTxt" type="text"  name="demand" class=" width-p80" value="${jobInfo.demand}"/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">联系电话</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="contactTelTxt" autocomplete="off" type="text"  name="contactTel" class="width-p40" value="${jobInfo.contactTel}" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4">公司地址 </td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="addressTxt" type="text"  name="company.address" class=" width-p80" value="${jobInfo.company.address}" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="td-title" colspan="4" style="background-color:#6587a1">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>招聘考核内容及分值</strong>
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
                                  	 <input id="scoreKeyTxt" type="hidden" name="pos_Abi.scoreKeyArray" value="${posAbiList.ability.id}"/>
                                  	<select id="scoreValTxt" name="pos_Abi.scoreValArray" class="required width-p40">
											<option value="">---请选择---</option>
											<option value="5"<c:if test="${posAbiList.score == '5'}">selected="true"</c:if>>非常好</option>
											<option value="4"<c:if test="${posAbiList.score == '4'}">selected="true"</c:if>>好</option>
											<option value="3"<c:if test="${posAbiList.score == '3'}">selected="true"</c:if>>一般</option>
											<option value="2"<c:if test="${posAbiList.score == '2'}">selected="true"</c:if>>差</option>
											<option value="1"<c:if test="${posAbiList.score == '1'}">selected="true"</c:if>>非常差</option>
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
