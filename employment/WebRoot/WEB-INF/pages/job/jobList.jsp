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
                            <td class="td-title">职位信息</td>
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
									<th>公司名称</th>
									<th>招聘职位</th>
									<th>薪资待遇</th>
									<th>联系人</th>
									<th>联系电话</th>
									<th>公司地址</th>
									<th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                               <c:forEach var="item" items="${pagedData.result}"varStatus="s">
                                    <tr>
                                        <td class="tb-left-bg" style="text-align: center">
                                         ${pagedata.start+s.index+1}
                                        </td>
                                        <td> ${item.company.name} </td> 
                                        <td> ${item.position.name} </td> 
                                        <td> ${item.salary} </td> 
										<td> ${item.contactPerson} </td>
										<td> ${item.contactTel} </td>
										<td> ${item.company.address} </td>
										<td> ${item.updateDate} </td>
										<td>
              							<a href="${path}/jobController/detail.do?id=${item.id}" class="btn1 btn-small">详细</a>
              							<a href="${path}/jobController/find.do?id=${item.id}" class="btn1 btn-small">修改</a>
              							<a href="${path}/jobController/delete.do?id=${item.id}" class="btn1 btn-small">删除</a>
              							</td>
            						</tr>
                                </c:forEach>
                            </tbody>
                        </table>
                          </c:if>
                        <t:PageBar pageUrl="${path}/jobController/list.do" pageAttrKey="pagedData"/>
                </div>
        </div>
        </div>
    </div>
  </body>
</html>
