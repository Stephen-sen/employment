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
<script type="text/javascript">
function doQuery(){
    $('#form1').submit();
}
function clearQuery(){
	$(':input','#form1').not(':button, :submit, :reset, :hidden') .val('') .removeAttr('checked')   .removeAttr('selected');  
}
</script>
  <body>
   <div id="main-div" class="width-p100">
            <div class="content main-page-190">
               <div class="title">
                 <table class="title-tb" cellpadding="0" cellspacing="0" width="10px">
                        <tr>
                            <td class="td-left">
                            </td>
                            <td class="td-title">用户管理</td>
                            <td class="td-btn">
							</td>
                            <td class="td-right">
                            </td>
                        </tr>
                    </table>  
                </div>
                <div class="margin-lr-1">
                	<form id="form1" action="${path}/userController/list.do" method="get">
                		<table class="search-tb width-p100">
	                        <tr>
	                            <td class="left">
	                                <i class="icon icon-play"></i>查询选项
	                            </td>
	                            <td class="center">
	                                <table class="search-form-tb">
	                                    <tr>
	                                        <td class="ltd8">用户名</td>
	                                        <td class="rtd8">
	                                            <input  class="width-p45" value="${userInfo.userName }" autocomplete="off" type="text" name="userName" maxlength="40" />
	                                        </td>
											<td class="ltd8">昵称 </td>
	                                        <td class="rtd8">
												<input  class="width-p45" value="${userInfo.nickName }" autocomplete="off" type="text" name="nickName" maxlength="40" />
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                    	<td class="ltd8">注册时间</td>
	                                        <td class="rtd8">
	                                            <input  name="createDate" value="${userInfo.createDate }" class="width-p45" type="text" autocomplete="off"
												onfocus="WdatePicker({skin:'whyGreen',readOnly:'true'})" />
	                                        </td>
	                                    	<td class="ltd8">电话</td>
	                                        <td class="rtd8">
	                                        	<input type="text" value="${userInfo.tel }" autocomplete="off" class="width-p45" name="tel" maxlength="11"/>
	                                        </td>
	                                    </tr>
	                                </table>
	                            </td>
	                            <td class="td-btn">
	                                 <a class="btn1 btn-small" href="javaScript:doQuery()">查询</a>
	                                <a class="btn1 btn-small" href="javaScript:clearQuery()">重置</a>
	                            </td>
	                        </tr>
	                        <tr>
	                        </tr>
		                </table>
					</form>
                 <div  id="autoHeightDIV" class="over-flow-x-hidden" style="margin-top: 20px">
                  <c:if test='${pagedData.totalPageCount<1}'>
                        <div class="no-data-div"> 没有查询到数据！ </div>
                    </c:if>
                     <c:if test='${pagedData.totalPageCount>0}'>
                        <table class="list-tb" width="100%">
                            <thead>
                                <tr>
                                    <th>序号</th>
									<th>用户名</th>
									<th>英文名</th>
									<th>性别</th>
                                    <th>昵称</th>
                                    <th>电话</th>
									<th>邮箱</th>
									<th>注册日期</th>
                                    <th>最后登录日期</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                               <c:forEach var="item" items="${pagedData.result}"varStatus="s">
                                    <tr>
                                        <td class="tb-left-bg" style="text-align: center">
                                          ${pagedata.start+s.index+1}
                                        </td>
                                        <td> ${item.userName} </td> 
										<td> ${item.enName} </td>
										<td>
										<c:if test="${item.sex == 'M'}">男</c:if>
										<c:if test="${item.sex == 'W'}">女</c:if>
										  </td>
										<td> ${item.nickName} </td>
										<td> ${item.tel} </td>
										<td> ${item.email} </td>
										<td> ${item.createDate} </td>
										<td> ${item.lastLoginDate} </td>
										<td>
										<c:if test="${item.status == 1}">正常</c:if> 
										<c:if test="${item.status == 2}">注销</c:if> 
										 </td>
										<td>
										<a href="${path }/userController/delete.do?id=${item.id }">删除</a>
               							<a href="${path }/userController/find.do?id=${item.id }">修改</a> 
              							<c:if test="${item.status == 1}">
              							<a href="${path }/userController/statusMan.do?id=${item.id }">注销</a>
              							</c:if>
              							<c:if test="${item.status == 2}">
              							<a href="${path }/userController/statusMan.do?id=${item.id }">恢复</a>
              							</c:if>
              							</td>
            							</tr>
                                    </tr>
                                </c:forEach>
                            </tbody>
					</table>
					</c:if>
					<t:PageBar pageUrl="${path}/userController/list.do" pageAttrKey="pagedData"/>		
			</div>
        </div>
        </div>
    </div>
  </body>
</html>
