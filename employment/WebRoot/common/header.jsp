<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/tagslib.jsp" %>
<%@ page import="com.zhangmin.constant.Global" %>
<% 
	request.setAttribute("user", request.getSession().getAttribute(Global.USER_INFO)); 
	request.setAttribute("btnStr", request.getSession().getAttribute(Global.USER_BUTTON_STR)); 
	request.setAttribute("path",request.getContextPath());
	request.setAttribute("token",request.getSession().getAttribute("token"));
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/"; 
%>

<title>毕业生就业能力评价系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />


<link rel="shortcut icon" href="${path}/themes/images/hkdlogo.png" type="image/x-icon" /> 
<link rel="stylesheet" type="text/css" href="${path}/themes/css/demo.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/css/main.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/jqueryui/south-street/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/validate/css/validate.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/vtip/vtip.css">
<script type="text/javascript" src="${path}/themes/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${path}/themes/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/ui/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/My97DatePicker/WdatePicker.js"/></script>
<script type="text/javascript" src="${path}/themes/jqueryui/ui/jquery.ui.autocomplete.min.js"></script>
<script type="text/javascript" src="${path}/themes/js/global.js"></script>
<script type="text/javascript" src="${path}/themes/js/rowspan.js"></script>
<script type="text/javascript" src="${path}/themes/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/themes/js/jQuery.Hz2Py-min.js"></script>
<script type="text/javascript" src="${path}/themes/validate/js/jquery.validate.js"></script>
<script type="text/javascript" src="${path}/themes/vtip/vtip-min.js"></script>
<script type="text/javascript">

function showLoading(){
    $('#loadding').show();
    $('#fullbg').show();
}

function hideLoading(){
    $('#loadding').hide();
    $('#fullbg').hide();
}

function back(){
	winload("${path}/main/back.do");
}
	$(document).ready(function() {
		if("${messageCode}"=="PARAM_ERROR"){jqueryUIAlert("操作失败，缺少参数或者参数错误!");}
		if("${messageCode}"=="01"){jqueryUIAlert("操作成功!");}

		if(${messagecode == '04'}){
			jqueryUIAlert("密码错误!");}
		if(${messagecode == '05'}){
			jqueryUIAlert("未找到匹配的用户名!");}
		if(${messagecode == '06'}){
			jqueryUIAlert("验证码错误!");}
		if(${messagecode == '07'}){
			jqueryUIAlert("验证码不能为空!");}
	})
	
	function jqueryUIAlert(msg){
		$('#msgDialog').append("<div id=\"dialog-message\" title=\"系统消息\"></div>");
		$('#dialog-message').empty();
		$('#dialog-message').append("<p><span class=\"ui-icon ui-icon-circle-check\" style=\"float: left; margin: 0 7px 20px 0;\"></span>");
		$('#dialog-message').append(msg);
		$('#dialog-message').append("</p>");
		$("#dialog-message").dialog({
			modal: true,
            buttons: {
                "确定": function(){
                    $(this).dialog("close");
                }
            }
        });
	}
</script>
<div id="loadding" style="display:none; background-color:white; z-index:9999;padding:5px;text-align:center; color:black;  position:absolute;top:40%;right:40%;width:20%;height:30px;line-height:30px;">
 	正在努力加载数据中，请等待……
</div>
<div id="msgDialog"></div>
<div id="fullbg" style=" background-color: Gray; display: none; z-index: 9998; position: absolute; left: 0px; top: 0px; width:100%; height:100%; filter: Alpha(Opacity  = 30);-moz-opacity: 0.4; opacity: 0.4;" >
</div>
