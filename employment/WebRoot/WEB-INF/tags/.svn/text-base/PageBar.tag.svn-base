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
	    addCookies('pageSize',$('#pageSizeSelect').val());
		window.location.href="${pageUrl}${separator}pageNo=${pageResult.currentPageNo}"+getSearchParam(); 
	}
</script>
<div class="pagebar" >
	<table style="width:100%;">
		<tr>
			<td class="width_p30" style="text-align: left;  padding-left:10px;">
				记录条目：${pageResult.totalCount}条，共${pageResult.totalPageCount}页。
				当前：第${pageResult.currentPageNo}页
			</td>
			<td class="width_p70" style="text-align: right; padding-right:2px;">
				<c:if test="${pageResult.currentPageNo <=1}">
				   首页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.currentPageNo >1 }">
					<a class="btn2 btn-small" href="javascript:goPage(1);">首页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.hasPreviousPage}">
					<a class="btn2 btn-small" href="javascript:goPage(${pageResult.currentPageNo-1});">上一页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${!pageResult.hasPreviousPage}">
				  上一页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.hasNextPage}">
					<a class="btn2 btn-small" href="javascript:goPage(${pageResult.currentPageNo +1});">下一页</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${!pageResult.hasNextPage}">
				  下一页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.currentPageNo >= pageResult.totalPageCount}">
				   末页&nbsp;&nbsp;
				</c:if>
				<c:if test="${pageResult.currentPageNo < pageResult.totalPageCount}">
				   <a class="btn2 btn-small" href="javascript:goPage(${pageResult.totalPageCount});">末页</a>&nbsp;&nbsp;
				</c:if>
				&nbsp;&nbsp;
				个性化显示：
				<select class="width-50" id="pageSizeSelect" onchange="javascript:pageNumChange();">
					<option>5</option>
					<option>10</option>
					<option>15</option>
					<option>20</option>
					<option>25</option>
					<option>30</option>
					<option>35</option>
					<option>40</option>
					<option>45</option>
					<option>50</option>
					<option>100</option>
				</select>
				条，
				快速到达
				<input type="text" id="goPageNoTxt" style="width:30px;" />
				<a class="btn2 btn-small" href="javascript:goPage($('#goPageNoTxt').val());">GO</a>
			</td>
		</tr>
	</table>
	
</div>