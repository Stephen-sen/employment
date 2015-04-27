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
  <body>
   <div id="main-div" class="width-p100">
            <div class="content main-page-190">
               <div class="title">
                 <table class="title-tb" cellpadding="0" cellspacing="0" width="10px">
                        <tr>
                            <td class="td-left">
                            </td>
                            <td class="td-title">素质信息</td>
                            <td class="td-btn">
							</td>
                            <td class="td-right">
                            </td>
                        </tr>
                    </table>  
                </div>
                <div class="margin-lr-1">
                 <div  id="autoHeightDIV" class="over-flow-x-hidden" style="margin-top: 20px">
                 <c:if test='${pagedData.totalPageCount<1}'>
                        <div class="no-data-div"> 没有查询到数据！ </div>
                    </c:if>
                     <c:if test='${pagedData.totalPageCount>0}'>
                        <table class="list-tb" width="100%">
                            <thead>
                                <tr>
                                    <th>序号</th>
									<th>名称</th>
									<th>类型</th>
									<th>所属上级</th>
									<th>说明</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                               <c:forEach var="item" items="${pagedData.result}"varStatus="s">
                                    <tr>
                                        <td class="tb-left-bg" style="text-align: center">
                                         ${pagedata.start+s.index+1}
                                        </td>
                                        <td> ${item.name} </td>
                                        <c:if test="${item.assessmentType == '01'}">
										<td> 一级 </td>
										</c:if>
										<c:if test="${item.assessmentType == '02'}">
										<td> 二级 </td>
										</c:if>
										<c:if test="${item.preId.id == '-1'}">
										<td> 无上级 </td>
										</c:if>
										<c:if test="${item.preId.id != '-1'}">
										<td> ${item.preId.name} </td>
										</c:if>  
										 <td> ${item.description} </td>
										<td>
              							<a href="${path}/abilityController/find.do?id=${item.id}" class="btn1 btn-small">修改</a>
              							<a href="${path}/abilityController/delete.do?id=${item.id}" class="btn1 btn-small">删除</a>
              							</td>
            						</tr>
                                </c:forEach>
                            </tbody>
                        </table>
                          </c:if>
                        <t:PageBar pageUrl="${path}/abilityController/list.do" pageAttrKey="pagedData"/>
                </div>
        </div>
        </div>
    </div>
  </body>
</html>
