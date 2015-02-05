<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ attribute name="pageUrl" required="true" rtexprvalue="true" description="分页页面对应的URl" %>
<%@ attribute name="pageAttrKey" required="true" rtexprvalue="true" description="Page对象在Request域中的键名称" %>
<c:set var="pageUrl" value="${pageUrl}" />
<%
   String separator = pageUrl.indexOf("?") > -1?"&":"?";
   jspContext.setAttribute("pageResult", request.getAttribute(pageAttrKey));
   jspContext.setAttribute("pageUrl", pageUrl);
   jspContext.setAttribute("separator", separator);
%>
<script type="text/javascript">	
	$(document).ready(function(){
		$('#pageSizeSelect').val("${pageResult.pageSize}");
	});
	
	function goPage(pageNo){
		if(pageNo!=""){
			window.location.href="${pageUrl}${separator}pageNo="+pageNo+getSearchParam(); 
		}
		else{
			alert("未输入页码，请输入后重试！");
		}
	}
	
	function pageNumChange(){
	 	$.cookie && $.cookie('pageSize', $('#pageSizeSelect').val(), {
	                expires : adminCookieExpiry, 
	                path : "/"
	            });
		window.location.href="${pageUrl}${separator}pageNo=${pageResult.currentPageNo}"+getSearchParam(); 
	}
</script>
<div style="padding-left:20px;padding-top:10px;">
	<table style="width:80%;">
		<tr>
			<td class="width_p90" style="text-align: left;  padding-left:10px;">
				记录条目：${pageResult.totalCount}条，共${pageResult.totalPageCount}页。
				当前：第${pageResult.currentPageNo}页
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${pageResult.currentPageNo <=1}">
				   首页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.currentPageNo >1 }">
				   <a class="easyui-linkbutton" href="javascript:goPage(1);">首页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.hasPreviousPage}">
				  <a class="easyui-linkbutton" href="javascript:goPage(${pageResult.currentPageNo-1});">上一页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${!pageResult.hasPreviousPage}">
				  上一页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.hasNextPage}">
				  <a class="easyui-linkbutton" href="javascript:goPage(${pageResult.currentPageNo +1});">下一页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${!pageResult.hasNextPage}">
				  下一页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.currentPageNo >= pageResult.totalPageCount}">
				   末页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.currentPageNo < pageResult.totalPageCount}">
				   <a class="easyui-linkbutton" href="javascript:goPage(${pageResult.totalPageCount});">末页</a>&nbsp;&nbsp;
				</c:if>
			</td>
		</tr>
	</table>
	
</div>