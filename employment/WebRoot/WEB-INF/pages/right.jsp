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
<title>系统主页</title>
<link href="themes/images/skin.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
</style>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.scrollNews {
	width: 400px;
	height: 100px;
	overflow: hidden;
	margin-left: -20px;
}

.scrollNews ul {
	padding: 0px 0 5px 15px;
}

.scrollNews ul li {
	height: 20px;
	list-style-type: none;
	font-size: small;
}

a {
	text-decoration: none;
}
</style>

<script type="text/javascript">

        $(document).ready(function() {
        	getJobList();
        	showUrl();
        });
        
        function showUrl() {
            var settime;
            $(".scrollNews").hover(function () {
                clearInterval(settime);
            }, function () {
                settime = setInterval(function () {
                    var $first = $(".scrollNews ul:first");     //选取div下的第一个ul 而不是li；
                    var height = $first.find("li:first").height();      //获取第一个li的高度，为ul向上移动做准备；
                    $first.animate({ "marginTop": -height + "px" }, 600, function () {
                        $first.css({ marginTop: 0 }).find("li:first").appendTo($first); //设置上边距为零，为了下一次移动做准备
                    });
                }, 1500);
            }).trigger("mouseleave");       //trigger()方法的作用是触发被选元素的制定事件类型
        };
       
        function getJobList(){
            $.ajax({
                cache: false,
                type: 'post',
                async: false,
                url: '${path}/jobController/getJobList.do?',
                dataType: 'json',
                error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert(XMLHttpRequest.status);
    			},
    			success : function(data) {
    				$(data).each(function(i, item) {
        				//alert(item.company.name);
    					 $('#jobUL').append("<li style=\"padding-bottom: 5px\"><a href=\"${path}/jobController/detail.do?id="+item.id+"\""+"style=\"text-decoration:underline\"><font size=2>"+item.company.name+"招聘 "+item.position.name+" 薪资待遇 "+item.salary+"<font/></a></li>");
    				});
    			}
            });
    	}
    </script>
<body marginwidth="0" marginheight="0">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td width="17" valign="top"
					background="themes/images/mail_leftbg.gif"><img
					src="themes/images/left-top-right.gif" width="17" height="29"></td>
				<td valign="top" background="themes/images/content-bg.gif"><table
						width="100%" height="31" border="0" cellpadding="0"
						cellspacing="0" class="left_topbg" id="table2">
						<tbody>
							<tr>
								<td height="31"><div class="titlebt">欢迎界面</div></td>
							</tr>
						</tbody>
					</table></td>
				<td width="16" valign="top"
					background="themes/images/mail_rightbg.gif"><img
					src="themes/images/nav-right-bg.gif" width="16" height="29"></td>
			</tr>
			<tr>
				<td valign="middle" background="themes/images/mail_leftbg.gif">&nbsp;</td>
				<td valign="top" bgcolor="#F7F8F9"><table width="98%"
						border="0" align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td colspan="2" valign="top">&nbsp;</td>
								<td>&nbsp;</td>
								<td valign="top">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" valign="top"><span class="left_bt">感谢您使用毕业生就业能力评价系统</span><br>
									<br> <span class="left_txt">&nbsp;<img
										src="themes/images/ts.gif" width="16" height="16"><font
										style="font-weight: bolder ;color: black"> 登录信息：</font><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登陆帐号：
								</span><span class="left_ts">${user.userName}</span><span
									class="left_txt"><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注册时间：</span><span
									class="left_ts">${user.createDate}</span><span class="left_txt"><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登陆次数：</span><span
									class="left_ts">${user.loginTimes}</span><span class="left_txt"><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上次登录时间：</span><span
									class="left_ts">${lastLoginDate}</span><span class="left_txt"><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上次登录IP：</span><span
									class="left_ts">${lastLoginIp}</span><span class="left_txt"><br>
								</span></td>
								<td width="7%">&nbsp;</td>
								<td width="40%" valign="top"><table width="100%"
										height="144" border="0" cellpadding="0" cellspacing="0"
										class="line_table">
										<tbody>
											<tr>
												<td width="7%" height="27"
													background="themes/images/news-title-bg.gif"><img
													src="themes/images/news-title-bg.gif" width="2" height="27"></td>
												<td width="93%" background="themes/images/news-title-bg.gif"
													class="left_bt2">招聘公告</td>
											</tr>
											<tr>
												<td height="102" valign="top">&nbsp;</td>
												<td height="102" valign="top" style="position: absolute;">
													<div class="scrollNews">
														<ul id="jobUL">

														</ul>
													</div>
												</td>
											</tr>
										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>