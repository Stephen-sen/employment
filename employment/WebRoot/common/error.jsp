<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" isErrorPage="true"%>
<%
	String rootPath = request.getContextPath();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<link href="<%=rootPath%>/default/css/page.css" rel="stylesheet" type="text/css">
		<title>错误通用页面</title>
		<script language="JavaScript" type="text/JavaScript">
		function MM_preloadImages() { //v3.0
			var d = document;
			if (d.images) {
				if (!d.MM_p)
					d.MM_p = new Array();
				var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
				for (i = 0; i < a.length; i++)
					if (a[i].indexOf("#") != 0) {
						d.MM_p[j] = new Image;
						d.MM_p[j++].src = a[i];
					}
			}
		}

		function MM_swapImgRestore() { //v3.0
			var i, x, a = document.MM_sr;
			for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
				x.src = x.oSrc;
		}

		function MM_findObj(n, d) { //v4.01
			var p, i, x;
			if (!d)
				d = document;
			if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
				d = parent.frames[n.substring(p + 1)].document;
				n = n.substring(0, p);
			}
			if (!(x = d[n]) && d.all)
				x = d.all[n];
			for (i = 0; !x && i < d.forms.length; i++)
				x = d.forms[i][n];
			for (i = 0; !x && d.layers && i < d.layers.length; i++)
				x = MM_findObj(n, d.layers[i].document);
			if (!x && d.getElementById)
				x = d.getElementById(n);
			return x;
		}

		function MM_swapImage() { //v3.0
			var i, j = 0, x, a = MM_swapImage.arguments;
			document.MM_sr = new Array;
			for (i = 0; i < (a.length - 2); i += 3)
				if ((x = MM_findObj(a[i])) != null) {
					document.MM_sr[j++] = x;
					if (!x.oSrc)
						x.oSrc = x.src;
					x.src = a[i + 2];
				}
		}
		//-->
	</script>

	<Script language="JavaScript" type="text/javascript">
	function toggleDebugInfo() {
		if (debugInfoSwitch.innerText == "显示调试信息") {
			//debugInfoText.style.height = "auto";
			debugInfoText.style.height = document.body.clientHeight-112;
			debugInfoText.style.width = document.body.clientWidth-10;
			debugInfoText.style.overflow = "scroll";
			debugInfoText.style.visibility = "visible";
			debugInfoSwitch.innerText = "隐藏调试信息";
		} else {
			debugInfoText.style.height = "0";
			debugInfoText.style.visibility = "hidden";
			debugInfoSwitch.innerText = "显示调试信息";
		}
	}
	</Script>

	</head>

	<body>
		<!-- 	 -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="kuang_hui">
			<tr>
				<td width="406" height="20" align="left" class="space_left5 titleTag03">
					<span style="font-size: 14px; font-weight: bold;">提示信息</span>
				</td>
			</tr>
			<tr>
				<td valign="top">
				<div style="margin: 10px;" align="center">
					<span style="font-size: 14px; font-weight: bold; color: #0080FF">
						<s:property value="exception.message" escape="false" />
					</span>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="windowsTitleTd"></td>
					</tr>
					<tr>
					<td class="windowDownTd">
					<table border="0" cellpadding="0" cellspacing="0" class="windowsBottonTd" align="center">
						<tr>
							<td height="25">
								<a href="javascript:window.history.go(-1)" onMouseOver="MM_swapImage('Image1','','<%=rootPath%>/frame/img/ZiYuanDiaoDu/14_anniu007.gif',1)" onMouseOut="MM_swapImgRestore();">
									<img src="<%=rootPath%>/frame/img/ZiYuanDiaoDu/14_anniu007.gif" alt="返回" name="Image1" width="15" height="14" border="0" align="left" id="Image1" />返回
								</a>
							</td>
						</tr>
					</table>
					</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="25" style="font-size: 14px;" class="text_blue space_left10">
										<a id="debugInfoSwitch" href="javascript:toggleDebugInfo()">显示调试信息</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="space_left10">
							<div id="debugInfoText" align="left" style="visibility: hidden">
								<pre><s:property value="exceptionStack" /></pre>
									<s:fielderror/>
									<s:actionerror/>
							<s:actionmessage/>
							</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
	</body>
</html>
