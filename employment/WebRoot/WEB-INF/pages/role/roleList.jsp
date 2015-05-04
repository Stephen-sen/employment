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
  <body>
   <div id="main-div" class="width-p100">
            <div class="content main-page-190">
               <div class="title">
                 <table class="title-tb" cellpadding="0" cellspacing="0" width="10px">
                        <tr>
                            <td class="td-left">
                            </td>
                            <td class="td-title">菜单信息</td>
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
									<th>角色名称</th>
									<th>角色描述</th>
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
										<td> ${item.description} </td>
										<td>
										<c:if test="${fn:contains(btnStr, '11xg_role')}" >
              							<a href="${path}/roleController/find.do?id=${item.id}" class="btn1 btn-small">修改</a>
              							</c:if>
              							<c:if test="${fn:contains(btnStr, '11del_role')}" >
              							<a href="${path}/roleController/delete.do?id=${item.id}" class="btn1 btn-small">删除</a>
              							</c:if>
              							<c:if test="${fn:contains(btnStr, '11fp_limit')}" >
              							<a href="${path}/roleMenuController/find.do?id=${item.id}" class="btn1 btn-small">分配权限</a>
              							</c:if>
              							</td>
            						</tr>
                                </c:forEach>
                            </tbody>
                        </table>
                          </c:if>
                        <t:PageBar pageUrl="${path}/roleController/list.do" pageAttrKey="pagedData"/>
                </div>
        </div>
        </div>
    </div>
    <center>
        <t:Footer></t:Footer>
     </center>
  </body>
</html>
