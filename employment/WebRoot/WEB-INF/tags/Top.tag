<%@tag import="com.zhangmin.constant.Global" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%
request.setAttribute("user", request.getSession().getAttribute(Global.USER_INFO)); 
request.setAttribute("userRole", request.getSession().getAttribute(Global.USER_ROLE)); 
request.setAttribute("allMenuList", request.getSession().getAttribute(Global.USER_ALL_MENU)); 
request.setAttribute("biOrgan", request.getSession().getAttribute(Global.USER_ORGAN)); 
request.setAttribute("employeeTypeList", request.getSession().getAttribute("employeeTypeList")); 
request.setAttribute("employee", request.getSession().getAttribute(Global.USER_EMPLOYEE_INFO)); %>
<script type="text/javascript">
    $(document).ready(function(){
        $('.top-menu > li').bind('mouseover', openSubMenu);
        $('.top-menu > li').bind('mouseout', closeSubMenu);
        function openSubMenu(){
            $(this).find('ul').css('visibility', 'visible');
        };
        function closeSubMenu(){
            $(this).find('ul').css('visibility', 'hidden');
        };
    });
</script>
<div class="header">
<table class="header-tb" cellspacing=0 cellpadding=0>
    <tr style="height: 82px;">
        <td style="text-align: center" class="header-left width-p20">
            <img height="60px" src="${path}/themes/images/logo.png" />
        </td>
        <td class="header-right width-p80">
            <table>
                <tr height="23px">
                    <td>
						${biOrgan.name}/ ${userRole.biRole.name}
						&nbsp;&nbsp;&nbsp;
						<c:if test="${employee.status=='SHIXI'}">实习 </c:if>
						<c:if test="${employee.status=='SHIYONG'}">试用</c:if>
						<c:if test="${employee.status=='ZHENGSHI'}">正式</c:if>
						<c:if test="${employee.status=='XIUJIA'}">休假</c:if>
						<c:if test="${employee.status=='LIZHI'}">离职</c:if>
						<c:forEach var="initem" items='${employeeTypeList}' varStatus="s">
							<c:if test="${employee.employeeType==initem.typeCode}">${initem.typeName}</c:if>
						</c:forEach>
						${user.userName} 
                    </td>
                    <td>
                    </td>
                </tr>
                <tr height="23px">
                    <td colspan="2">
                        <a class="btn-small btn1" href="${path}/main/modifyPassWord/view.do" style="COLOR: #fff">修改密码</a>
                        <a class="btn-small btn1" href="${path}/main/changeRole/view.do" style="COLOR: #fff">切换岗位</a>
                        <a class="btn-small btn1" style="color: #fff" onclick="if (confirm('确定要退出吗？')) return true; else return false;" href="${path}/dologout.do" target=_top>退出系统</a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
	</table>
	<table class="header-tb" cellspacing=0 cellpadding=0>
    <tr height="28px" class="menu">
    	<td class="width-p40" style="color:white;text-indent:10px;">上次登录时间：${user.lastLoginDateString}</td>
        <td class="width-p60">
           <ul class="top-menu">
            	<c:forEach var="item" items='${allMenuList}' varStatus="s">
            		<li>
            			<c:if test="${item.biMenu.menuType=='01'}">
            				<a href="#">${item.biMenu.name}</a>
							<ul style="visibility: hidden;">
								<c:forEach var="inItem" items="${allMenuList}" varStatus="s">
									<c:if test="${item.biMenu.id==inItem.biMenu.preId}">
										<li><a href="${path}${inItem.biMenu.url}" >●&nbsp;&nbsp;${inItem.biMenu.name}</a></li>
									</c:if>
								</c:forEach>
		                    </ul>
            			</c:if>
	                </li>
				</c:forEach>
            </ul>
        </td>
    </tr>
</table>
</div>