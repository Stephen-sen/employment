<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/tagslib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  
 * @version 1.0.01
 * @author zhangmin
-->
<html>
<head>
<%@ include file="/common/header.jsp"%>
    <title>系统登陆</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
function correctPNG()
{
    var arVersion = navigator.appVersion.split("MSIE")
    var version = parseFloat(arVersion[1])
    if ((version >= 5.5) && (document.body.filters)) 
    {
       for(var j=0; j<document.images.length; j++)
       {
          var img = document.images[j]
          var imgName = img.src.toUpperCase()
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
          {
             var imgID = (img.id) ? "id='" + img.id + "' " : ""
             var imgClass = (img.className) ? "class='" + img.className + "' " : ""
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle
             if (img.align == "right") imgStyle = "float:right;" + imgStyle
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
             var strNewHTML = "<span " + imgID + imgClass + imgTitle
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML
             j = j-1
          }
       }
    }    
}
//window.attachEvent("onload", correctPNG);

function redirect(){
	window.location = "/emp/userController/regist.do";
	return false;
}
$(document).ready(function() {
	correctPNG();
	$("#VerifyCode").css("cursor", "pointer").click(function() {
	      $(this).attr("src", "code.php?" + Math.random());
	    });
});

 function myReload() {
    document.getElementById("createVerifyCode").src = document.getElementById("createVerifyCode").src + "?nocache=" + new Date().getTime();
  }

 $(function(){
	     if (window != top)
	     top.location.href = location.href; 
	 })
</script>


<link href="themes/images/skin.css" rel="stylesheet" type="text/css">
<body>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="80" align="right" valign="top"><img src="themes/images/logo.png" width="279" height="68"></td>
                </tr>
                <tr>
                  <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="35%">&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p><font size="5px">温馨提醒：</font></p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td height="25" colspan="2" class="left_txt"><p><p><font size="3px">如果您没有账户，请在这里注册！</font></p></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td width="30%" height="40"><img src="themes/images/icon-demo.gif" width="16" height="16"><a href="javascript:redirect();" class="left_txt3" onclick="redirect"> 注册</a> </td>
                      <td width="35%"><img src="themes/images/icon-login-seaver.gif" width="16" height="16"><a href="http://www.nongfuit.com" class="left_txt3"> 使用说明</a></td>
                    </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">登陆就业能力分析后台管理</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                    <form name="form1" action="${path }/dologin.do" method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text"><input name="userName" class="editbox4" size="20">                            </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="passWord" size="20" name="passWord">
                              <img src="themes/images/luck.gif" width="19" height="18"> </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" ><span class="login_txt">验证码：</span></td>
                            <td height="35" colspan="2" class="top_hui_text"><input class=wenbenkuang name="verifycode" type=text  maxLength=4 size=10>
                            <img src="${path }/verifyController/getVerify.do" id="createVerifyCode">
                            </td>
                          </tr>
			                <tr>
			                  <td colspan="3" class="top_hui_text"><a onclick="myReload()" href='#' class=hand>看不清楚？换一个</a></td>
			                </tr>
                          <tr>
                            <td height="35" >&nbsp;</td>
                            <td width="20%" height="35" ><input name="Submit" type="submit" class="button" id="Submit" value="登 陆"> </td>
                            <td width="67%" class="top_hui_text"><input name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()"></td>
                          </tr>
                        </table>
                        <br>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="themes/images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright &copy; 2014-2015 www.nongfuit.com</span></td>
      </tr>
    </table></td>
  </tr>
</table>

</html>
