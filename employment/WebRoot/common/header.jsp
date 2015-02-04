<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.framework.constant.Global" %>
<% 
	request.setAttribute("user", request.getSession().getAttribute(Global.USER_INFO)); 
	request.setAttribute("btnStr", request.getSession().getAttribute(Global.USER_BUTTON_STR)); 
	request.setAttribute("path",request.getContextPath());
	request.setAttribute("title",Global.getGlobalConfigValue("schoolName"));
	request.setAttribute("token",request.getSession().getAttribute("token"));
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/"; 
%>

<title>${title }TMS管理系统V1.0${user.userName}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link rel="stylesheet" type="text/css" href="${path}/themes/css/demo.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/css/main.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/jqueryui/south-street/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/validate/css/validate.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/ztree/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="${path}/themes/vtip/vtip.css">
<script type="text/javascript" src="${path}/themes/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${path}/themes/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/ui/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
<script type="text/javascript" src="${path}/themes/jqueryui/ui/jquery.ui.autocomplete.min.js"></script>
<script type="text/javascript" src="${path}/themes/ztree/js/jquery.ztree.all-3.5.min.js"></script>
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
    
    
    function back1(){
    	winload("${path}/main/back1.do");
    }

    function back2(){
    	winload("${path}/main/back2.do");
    }

	
	$(document).ready(function() {
		if("${messageCode}"=="PARAM_ERROR"){jqueryUIAlert("操作失败，缺少参数或者参数错误!");}
		if("${messageCode}"=="NOT_FOUND_DATA"){jqueryUIAlert("操作失败，未找到相应的数据");}
		if("${messageCode}"=="STUENROLL_ALREADY_OWNFEE"){jqueryUIAlert("操作失败，欠款已经交清");}
		if("${messageCode}"=="STUENROLL_HAPPEND"){jqueryUIAlert("操作失败，该交易已经存在上课记录,不能进行作废操作!");}
		if("${messageCode}"=="IS_EXIST"){jqueryUIAlert("操作失败，数据已经存在");}
		if("${messageCode}"=="BIORGAN_PRESTR_IS_EXIST"){jqueryUIAlert("操作失败，校区前缀字段已经存在,请重新确认以后重试");}
		
		if("${messageCode}"=="STU_IS_IN_CLASS"){jqueryUIAlert("操作失败，该学生已经存在您所选择的班级中，不能进行定金交费操作，请重新选班后重试！");}
		if("${messageCode}"=="STUEARNEST_IS_USED"){jqueryUIAlert("操作失败，您所操作的定金业务已经被报名使用，不能进行作废或者退费操作！");}
		if("${messageCode}"=="STUEARNEST_IS_NOT_DEFAULT"){jqueryUIAlert("操作失败，您所操作的定金业务已经作废或者退费，不能再次进行作废或者退费操作！");}
		if("${messageCode}"=="stu is not in class"){jqueryUIAlert("操作失败，该学生已经不在该班级！请检查该生是否已经退学或、转出或者休学");}
		if("${messageCode}"=="STUENROLL_IS_NOT_DEFAULT"){jqueryUIAlert("操作失败，您所操作的报名收费业务已经作废或者退费，不能再次进行作废或者退费操作！");}
		if("${messageCode}"=="OWN_FEE_IS_EXIST"){jqueryUIAlert("操作失败，您所操作学生存在欠款，不能进行退费操作！");}
		
		if("${messageCode}"=="TPC_STU_ATTEND_ENROLL_ISUSED"){jqueryUIAlert("操作失败，您所修改的考勤相关的交费记录金额已经用完，不能进行修改操作！");}
		if("${messageCode}"=="TM_CLASS_NATURE_LEAVE"){jqueryUIAlert("操作失败，您所操作的学生还有没使用完的收费记录或者存在欠款，不能进行自然退学操作！");}
		if("${messageCode}"=="CHANGE_COUNT_IS_MORE_THAN_REMAIN"){jqueryUIAlert("操作失败，转出数量大于剩余数量，不允许转班、转校操作！");}
		if("${messageCode}"=="CHANGE_OUT_NULL"){jqueryUIAlert("操作失败，系统不允许转出零课时或者零天操作。请重新选择后重试！");}
		
		
		if("${messageCode}"=="TPC_ATTEND_CLASS_NOT_FIND_STUDENT"){jqueryUIAlert("操作失败，学生已经不在该班级上课。请重新选择后重试！点击确定将回到上一操作页面");}
		
	});
	
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
					back2();
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
