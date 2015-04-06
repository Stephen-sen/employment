<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	request.setAttribute("path",request.getContextPath());
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/"; 
%>
<%@ include file="/common/tagslib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>管理页面</title>

<script src="themes/js/prototype.lite.js" type="text/javascript"></script>
<script src="themes/js/moo.fx.js" type="text/javascript"></script>
<script src="themes/js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(themes/images/menu_bgs.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(themes/images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(themes/images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(themes/images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(themes/images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
<script type="text/javascript">

function abc(pid,url,name){
	var myul=document.getElementById("content"+pid);
	var li = document.createElement('li');   
	li.innerHTML = '<a href="'+url+'" target="main">'+name+' </a>';  
	myul.appendChild(li);
}
</script>
</head>

<body>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top">
    <div id="container">
		<c:if test="${userMenuList != null}">
			<!-- 所有菜单 -->
	 		 <c:forEach var="menu" items="${userMenuList}" varStatus="s">
	 			<!-- 一级菜单 -->
	 			 <c:choose>
	 			<c:when test="${menu.preId.id eq '1'}">
					<h1 class="type"><a href="javascript:void(0)">${menu.name } </a></h1>
					<div class="content" >
				        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
				          </tr>
				        </table>
						<ul class="MM" id='content${menu.id }'>
						</ul>
				    </div>
				</c:when>
				  <c:otherwise>
				 	<script type="text/javascript">
						abc('${menu.preId.id}','<%=path%>${menu.url }','${menu.name }');
					</script>
				 </c:otherwise>
				</c:choose>
	 		</c:forEach>
		</c:if>
	</div> 	
     </td>
  </tr>
</table>
</body>
</html>
        <script type="text/javascript">
			var contents = document.getElementsByClassName('content');
			var toggles = document.getElementsByClassName('type');
		
			var myAccordion = new fx.Accordion(
				toggles, contents, {opacity: true, duration: 400}
			);
			myAccordion.showThisHideOpen(contents[0]);
		</script>
