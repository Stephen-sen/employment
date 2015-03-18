<!--  
 * @version 1.0.01
 * @author zhangmin
-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/common/header.jsp"%>
</head>
  <body>
    <div class="margin-lr-1">
                	<form id="form1" action="${path}/main/stuChangeSchool/list.do" method="get">
                    	<input value="${stuChangeClass.changeOutStu.id}" class="width-p90" id="changeOutStuHid" type="hidden" name="changeOutStu.id" />
                    	<input value="${stuChangeClass.changeInStu.id}" class="width-p90" id="changeInStuHid" type="hidden" name="changeInStu.id" />
                		<table class="search-tb width-p100">
	                        <tr>
	                            <td class="left">
	                                <i class="icon icon-play"></i>查询选项
	                            </td>
	                            <td class="center">
	                                <table class="search-form-tb">
	                                    <tr>
	                                    	<td class="ltd8">
	                                            <fmt:message key="stuChangeClass.changeInStu"/>
	                                        </td>
	                                        <td class="rtd8" id="changeInStuTD">
												<input value="${stuChangeClass.changeInStu.cnName}" autocomplete="off" class="width-p90" id="changeInStuTxt" type="text" name="changeInStu.cnName" maxlength="255" />
	                                        </td>
	                                        <td class="ltd8">
	                                            <fmt:message key="stuChangeClass.changeInBiOrgan"/>
	                                        </td>
	                                        <td class="rtd8">
	                                            <select class="width-p90" id="changeInBiOrganSelect" onchange="changeInBiOrgan();" name="changeInBiOrgan.id">
	                                                <option value="">--全部--</option>
													<c:forEach var="item" items='${biOrganList}' >
														<option value="${item.id}">${item.name}</option>
													</c:forEach>
	                                            </select>
	                                        </td>
	                                        <td class="ltd8">课程
	                                        </td>
	                                        <td class="rtd8">
	                                            <select id="changeInTpCourseFLSelect" onchange="javascript:changeInTpCourseFL();" class="width-p45">
	                                                <option value="">--请选择--</option>
	                                                <c:forEach var="item" items="${tpCourseFLList}">
	                                                    <option value="${item.id}">${item.name}</option>
	                                                </c:forEach>
	                                            </select>
												<select id="changeInTpCourseSelect" name="tpCourse.id" onchange="javascript:changeInTpCourse();" class="width-p45">
	                                                <option value="">--请选择--</option>
	                                            </select>
	                                        </td>
	                                        <td class="ltd8">
	                                            <fmt:message key="stuChangeClass.changeInTmClass"/>
	                                        </td>
	                                        <td class="rtd8">
	                                            <select class="width-p90" id="changeInTmClassSelect" name="changeInTmClass.id">
	                                                <option>--全部--</option>
	                                            </select>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                    	<td class="ltd8">
	                                            	<fmt:message key="stuChangeClass.changeOutStu"/>
	                                        </td>
	                                        <td class="rtd8" id="changeOutStuTD">
												<input value="${stuChangeClass.changeOutStu.cnName}" autocomplete="off" class="width-p90" id="changeOutStuTxt" type="text" name="changeOutStu.cnName" maxlength="255" />
	                                        </td>
	                                        <td class="ltd8">
	                                            <fmt:message key="stuChangeClass.changeOutBiOrgan"/>
	                                        </td>
	                                        <td class="rtd8">
	                                            <select class="width-p90" id="changeOutBiOrganSelect" onchange="changeOutBiOrgan();" name="changeOutBiOrgan.id">
	                                                <option value="">--全部--</option>
													<c:forEach var="item" items='${biOrganList}' >
														<option value="${item.id}">${item.name}</option>
													</c:forEach>
	                                            </select>
	                                        </td>
	                                        <td class="ltd8">
	                                            	课程
	                                        </td>
	                                        <td class="rtd8">
	                                            <select id="changeOutTpCourseFLSelect" onchange="javascript:changeOutTpCourseFL();" class="width-p45">
	                                                <option value="">--请选择--</option>
	                                            </select>
												<select id="changeOutTpCourseSelect" name="tpCourse.id" onchange="javascript:changeOutTpCourse();" class="width-p45">
	                                                <option value="">--请选择--</option>
	                                            </select>
	                                        </td>
	                                        <td class="ltd8">
	                                            <fmt:message key="stuChangeClass.changeOutTmClass"/>
	                                        </td>
	                                        <td class="rtd8">
	                                            <select class="width-p90" id="changeOutTmClassSelect" name="changeOutTmClass.id">
	                                                <option value="">--全部--</option>
	                                            </select>
	                                        </td>
	                                    </tr>
	                                </table>
	                            </td>
	                            <td class="td-btn">
	                                <a class="btn1 btn-small" href="javaScript:doQuery()"><fmt:message key="btn.search"/></a>
	                            </td>
	                        </tr>
		                </table>
					</form>
                 <div  id="autoHeightDIV" class="over-flow-x-hidden">
                    <c:if test='${pagedData.totalPageCount<1}'>
                        <div class="no-data-div">
                            <fmt:message key="msg.no_data" />
                        </div>
                    </c:if>
                    <c:if test='${pagedData.totalPageCount>0}'>
                        <table class="list-tb" width="100%">
                            <thead>
                                <tr>
                                    <th></th>
									<th></th>
									<th></th>
									<th><fmt:message key="stuChangeClass.changeOutMemo" /></th>
								 	<th><fmt:message key="stuChangeClass.changeOutUser" /></th>
									 
									<th><fmt:message key="stuChangeClass.changeInStu" /></th>
                                    <th><fmt:message key="stuChangeClass.changeInTmClass" /></th>
									<th><fmt:message key="stuChangeClass.changeInMemo" /></th>
								 	<th><fmt:message key="stuChangeClass.changeInUser" /></th>
									
									<th class="td-yellow"><fmt:message key="stuChangeClass.changeFee" /></th>
                                    <th><fmt:message key="list.columns.do" /></th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        
                    </c:if>
                </div>
                <t:PageBar pageUrl="${path}/main/stuChangeClass/list.do" pageAttrKey="pagedData"/>
        </div>
  </body>
</html>
