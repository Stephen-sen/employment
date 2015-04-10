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
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190" >
                <div class="margin-lr-1">
                    <div class="main-page-90 over-flow-x-hidden">
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4" style="background-color: #6587a1">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>招聘详细信息</strong>
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">公司名称</td>
                                    <td class="rtd4">
                                     <input id="companyTxt"  name="company" class="width-p40" value="${jobInfo.company.name }"style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd4">招聘职位 </td>
                                    <td class="rtd4">
                                     <input id="positonTxt"  name="position" class="width-p40" value="${jobInfo.position.name }" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">薪资待遇</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="salaryTxt" autocomplete="off" type="text"  name="salary" value="${jobInfo.salary }" class="width-p40" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd4">联系人 </td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="contactPersonTxt" type="text"  name="contactPerson" class=" width-p40" value="${jobInfo.contactPerson }" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">职位描述</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="descriptionTxt" autocomplete="off" type="text"  name="description" class="width-p40" value="${jobInfo.description}" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd4">要求</td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="demandTxt" type="text"  name="demand" class=" width-p80" value="${jobInfo.demand}" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="ltd4">联系电话</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="contactTelTxt" autocomplete="off" type="text"  name="contactTel" class="width-p40" value="${jobInfo.contactTel}" style="background-color: #E6E9F0" readonly="readonly"/>
                                    </td>
                                    <td class="ltd4">公司地址 </td>
                                    <td class="rtd4">
                                     <input  autocomplete="off" id="addressTxt" type="text"  name="address" class=" width-p80" value="${jobInfo.company.address}" style="background-color: #E6E9F0" readonly="readonly" />
                                    </td>
                                </tr>
                                 <tr>
                                    <td class="td-title" colspan="4" style="background-color:#6587a1">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>招聘考核内容</strong>
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
                                  	<input  autocomplete="off" id="scoreValTxt" type="text"  name="scoreVal" 
                                  	<c:if test="${posAbiList.score == '5'}">value="非常好"</c:if>
                                  	<c:if test="${posAbiList.score == '4'}">value="好"</c:if>
                                  	<c:if test="${posAbiList.score == '3'}">value="一般"</c:if>
                                  	<c:if test="${posAbiList.score == '2'}">value="差"</c:if>
                                  	<c:if test="${posAbiList.score == '1'}">value="非常差"</c:if>
                                  	class=" width-p40" style="background-color:transparent;" readonly="readonly"/>
                                  	
                                    <div style="background-color:#e2e2e2;height:20px;margin:0px 0px 0px 0px; width:40%;">
									<div style="height:20px;margin-top:-21px;padding:0px; width:${((posAbiList.score)/5)*100}%;background-color:#8ae738;"></div>
									</div>
                                    </td>
						               <c:if test="${(stat.index+1)%2==0}">
						                <tr></tr>
						                </c:if>
						            </c:forEach>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                         <a href="javascript:back();" class="btn1 btn-small">返回</a>
                                    </td>
                                </tr>
                            </table>
                    </div>
                </div>
            </div>
        </div>
  </body>
</html>
