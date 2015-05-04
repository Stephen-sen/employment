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
</script>
  <body>
   <div id="main-div" class="width-p100">
            <div class="content main-page-190">
               <div class="title">
                 <table class="title-tb" cellpadding="0" cellspacing="0" width="10px">
                        <tr>
                            <td class="td-left">
                            </td>
                            <td class="td-title">已发布职位</td>
                            <td class="td-btn">
							</td>
                            <td class="td-right">
                            </td>
                        </tr>
                    </table>  
                </div>
                <div class="margin-lr-1">
                 <form id="form1" action="${path}/jobController/list.do" method="get">
                 <input type="hidden" name="type" value="${type }" />
                		<table class="search-tb width-p100">
	                        <tr>
	                            <td class="left">
	                                <i class="icon icon-play"></i>查询选项
	                            </td>
	                            <td class="center">
	                                <table class="search-form-tb">
	                                    <tr>
	                                        <td class="ltd8">公司名称</td>
	                                        <td class="rtd8">
	                                            <input  class="width-p45" autocomplete="off" type="text" name="company.name" maxlength="40" />
	                                        </td>
	                                        <td class="ltd8">招聘职位</td>
	                                        <td class="rtd8">
	                                        	<input type="text" autocomplete="off" class="width-p45" name="position.name" maxlength="11"/>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                    	<td class="ltd8">薪资待遇</td>
	                                        <td class="rtd8">
	                                            <select id="statusSelect" name="salary" class="required width-p45">
	                                        <option value="">---请选择---</option>
											<option value="12">1000-2000</option>
											<option value="24">2000-4000</option>
											<option value="46">4000-6000</option>
											<option value="68">6000-8000</option>
											<option value="81">8000-1万</option>
											<option value="100">1万以上</option>
										</select>
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
									<th>公司名称</th>
									<th>招聘职位</th>
									<th>薪资待遇</th>
									<th>联系人</th>
									<th>联系电话</th>
									<th>公司地址</th>
									<th>更新时间</th>
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
                                        <td> ${item.company.name} </td> 
                                        <td> ${item.position.name} </td> 
                                        <td> ${item.salary} </td> 
										<td> ${item.contactPerson} </td>
										<td> ${item.contactTel} </td>
										<td> ${item.company.address} </td>
										<td> ${item.updateDate} </td>
										<td>
										  <c:if test="${item.status != '1'}">未评价</c:if>
										  <c:if test="${item.status eq '1'}">已评价</c:if>
										 </td>
										<td>
										<c:if test="${type eq 'company'}">
              							<a href="${path}/jobController/detail.do?id=${item.id}" class="btn1 btn-small">详细</a>
              							<c:if test="${item.status != '1'}">
              							<c:if test="${fn:contains(btnStr, '05xg_yfbjob')}" >
              							<a href="${path}/jobController/find.do?id=${item.id}" class="btn1 btn-small">修改</a>
              							</c:if>
              							<c:if test="${fn:contains(btnStr, '05del_yfbjob')}" >
              							<a href="${path}/jobController/delete.do?id=${item.id}&&type=company" class="btn1 btn-small">删除</a>
              							</c:if>
              							</c:if>
              							</c:if>
              							<c:if test="${type eq 'persional'}">
              							 <c:if test="${item.status != '1'}">
              							 <c:if test="${fn:contains(btnStr, '08jy_ability')}" >
              							 <a href="${path}/stuAbiController/view.do?job.id=${item.id}" class="btn1 btn-small">就业能力评价</a>
              							 </c:if>
              							 </c:if>
										  <c:if test="${item.status eq '1'}">
										   <c:if test="${fn:contains(btnStr, '08resetjy_ability')}" >
										   <a href="${path}/stuAbiController/reAnalysisView.do?job.id=${item.id}" class="btn1 btn-small">重新评价</a>
										   </c:if>
										   <a href="${path}/stuAbiController/seeResult.do?job.id=${item.id}" class="btn1 btn-small">查看结果</a>
										  </c:if>
              							
              							</c:if>
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
    <center>
        <t:Footer></t:Footer>
     </center>
  </body>
</html>
