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
<title> - 管理页面</title>
<script language=JavaScript>
function logout(){
	if (confirm("您确定要退出控制面板吗？"))
	top.location = "/emp/logout.do";
	return false;
}
</script>
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
<%--<meta http-equiv="refresh" content="60">
--%><base target="main">
<link href="themes/images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="61%" height="64"><img src="themes/images/logo.gif" width="262" height="64"></td>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="50%" height="38" class="admin_txt"><b>${user.userName}</b> 您好,感谢登陆使用！</td>
        <td width="20%"><a href="#" target="_self" onClick="logout();"><img src="themes/images/out.gif" alt="安全退出" width="46" height="20" border="2"></a></td>
        <td width="16%" style="padding-left: 10px"><a href="${path }/right.do" target="main"><img src="themes/images/back_main.gif" alt="返回主页" width="60" height="20" border="2"></a></td>
        <td width="4%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="4">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
