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
                        <form id="form1" action="${path}/stuAbiController/save.do" method="post">
                            <input type="hidden" name="student.id" id="idHid" value="${user.id }" />
                            <input type="hidden" name="job.id" id="idHid" value="${job.id }" />
                            <input type="hidden" name="job.company.id" id="idHid" value="${job.company.id }" />
                            <input type="hidden" name="job.position.id" id="idHid" value="${job.position.id }" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="6" style="background-color: #4A5D62">
                                        <font size=2 color="white">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>你的个人能力分析如下</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd6">姓名</td>
                                    <td class="rtd6" style="width: 25%">
                                        <input  id="nameTxt" autocomplete="off" type="text"  name="student.name" value="${user.userName}" class="width-p60"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd6">所选公司</td>
                                    <td class="rtd6">
                                     <input id="companyNameTxt" type="text"  name="job.company.name"value="${job.company.name}" class=" width-p80"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd6">所选职位</td>
                                    <td class="rtd6">
                                     <input id="companyNameTxt" type="text"  name="job.position.name"value="${job.position.name}" class=" width-p80"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="td-title" colspan="6"style="background-color: #4A5D62">
                                        <font size=2 color="white">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>以下为你所选择的${job.company.name}公司${job.position.name}职位的个人能力分析结果</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <c:forEach var="item" items="${abilityMap}" varStatus="s">
                                <tr>
                                     <td class="td-title" colspan="6">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>${item.key}</strong>
                                        </font>
                                    </td>
                                </tr>
                                 <c:forEach var="posAbiList" items="${item.value}"  varStatus="stat" >
                                   <tr>
						               <td class="ltd6">${posAbiList.ability.name}</td>
                                    <td class="rtd6" >个人能力:
                                  	<input  autocomplete="off" id="personalTxt" type="text"  name="personal" 
                                  	<c:if test="${posAbiList.score == '5'}">value="非常好"</c:if>
                                  	<c:if test="${posAbiList.score == '4'}">value="好"</c:if>
                                  	<c:if test="${posAbiList.score == '3'}">value="一般"</c:if>
                                  	<c:if test="${posAbiList.score == '2'}">value="差"</c:if>
                                  	<c:if test="${posAbiList.score == '1'}">value="非常差"</c:if>
                                  	class=" width-p20" style="background-color: #E6E9F0" readonly="readonly"/>
                                   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业要求:
                                  	<input  autocomplete="off" id="companyTxt" type="text"  name="companyStand" 
                                  	<c:if test="${posAbiList.pos_Abi.score == '5'}">value="非常好"</c:if>
                                  	<c:if test="${posAbiList.pos_Abi.score == '4'}">value="好"</c:if>
                                  	<c:if test="${posAbiList.pos_Abi.score == '3'}">value="一般"</c:if>
                                  	<c:if test="${posAbiList.pos_Abi.score == '2'}">value="差"</c:if>
                                  	<c:if test="${posAbiList.pos_Abi.score == '1'}">value="非常差"</c:if>
                                  	class=" width-p20" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd6">符合度</td>
                                     <td class="rtd6">
                                   <input  autocomplete="off" id="conformityTxt" type="text"  name="conformity"
                                    value="<fmt:formatNumber value="${(posAbiList.conformity)*100}" pattern="0"/>%"class=" width-p70" style="background-color:transparent;"readonly="readonly"/>
                                    <div style="background-color:#e2e2e2;height:20px;margin:0px 0px 0px 0px; width:70%;">
									<div style="height:20px;margin-top:-21px;padding:0px; width:${(posAbiList.conformity)*100}%;background-color:#8ae738;"></div>
									</div>
                                    
                                    </td>
                                    <td class="ltd6">等级</td>
                                    <td class="rtd6">
                                  	<input  autocomplete="off" id="gradeTxt" type="text"  name="grade" 
                                  	<c:if test="${posAbiList.conformity >= 0.8}">value="优秀"</c:if>
                                  	<c:if test="${posAbiList.conformity >= 0.7 && posAbiList.conformity < 0.8}">value="良好"</c:if>
                                  	<c:if test="${posAbiList.conformity >= 0.6 && posAbiList.conformity < 0.7}">value="合格"</c:if>
                                  	<c:if test="${posAbiList.conformity < 0.6}">value="差"</c:if>
                                  	
                                  	<c:if test="${posAbiList.conformity < 0.6}">style="background-color: red"</c:if>
                                  	<c:if test="${posAbiList.conformity >= 0.6 && posAbiList.conformity < 0.7}">style="background-color: yellow"</c:if>
                                  	<c:if test="${posAbiList.conformity >= 0.7 && posAbiList.conformity < 0.8}">style="background-color: #4cd6ef"</c:if>
                                  	<c:if test="${posAbiList.conformity >= 0.8}">style="background-color: #5b47f4"</c:if>
                                  	class=" width-p40" style="background-color: #E6E9F0;" readonly="readonly"/>
                                    </td>
                                </tr>
                                </c:forEach>
                                </c:forEach>
                                <tr>
                                    <td style="text-align: center;" colspan="6">
                                        <!-- <a href="javascript:onSubmit();" class="btn1 btn-small">查看分析报告</a> -->
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
