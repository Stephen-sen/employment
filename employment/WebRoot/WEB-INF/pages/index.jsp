<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商家信息管理中心模板演示 带登录界面</title>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
</head>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="admin_top.html" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  <frameset cols="200,*"  rows="560,*" id="frame">
	<frame src="left.html" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="right.html" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  </frameset>
<noframes>
  <body></body>
    </noframes>
</html>

