<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.zhangmin.constant.Global" %>
<% 
	request.setAttribute("user", request.getSession().getAttribute(Global.USER_INFO)); 
	request.setAttribute("btnStr", request.getSession().getAttribute(Global.USER_BUTTON_STR)); 
	request.setAttribute("path",request.getContextPath());
	request.setAttribute("token",request.getSession().getAttribute("token"));
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/"; 
%>

<title>${title }就业能力分析后台管理${user.userName}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link rel="stylesheet" type="text/css" href="${path}/themes/css/demo.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/css/main.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/jqueryui/south-street/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/validate/css/validate.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/vtip/vtip.css">
<script type="text/javascript" src="${path}/themes/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${path}/themes/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/ui/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/My97DatePicker/WdatePicker.js"/>"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/ui/jquery.ui.autocomplete.min.js"></script>
<script type="text/javascript" src="${path}/themes/js/global.js"></script>
<script type="text/javascript" src="${path}/themes/js/rowspan.js"></script>
<script type="text/javascript" src="${path}/themes/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/themes/js/jQuery.Hz2Py-min.js"></script>
<script type="text/javascript" src="${path}/themes/validate/js/jquery.validate.js"></script>
<script type="text/javascript" src="${path}/themes/vtip/vtip-min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if("${messageCode}"=="PARAM_ERROR"){jqueryUIAlert("操作失败，缺少参数或者参数错误!");}
	})
</script>
