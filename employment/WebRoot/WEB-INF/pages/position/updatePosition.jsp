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
<script type="text/javascript">

	function back() {
		history.go(-1);
	}
	function onSubmit() {
		$('#form1').submit();
	}
</script>
  <body>
    <div id="main-div" class="width-p100">
            <div class="content main-page-190">
                <div class="margin-lr-1">
                    <div class="main-page-230 over-flow-x-hidden">
                        <form id="form1" action="${path}/positionController/update.do" method="post">
                            <input type="hidden" name="id" value="${positionInfo.id }" id="idHid" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>职位信息修改</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ltd4">职位名称</td>
                                    <td class="rtd4">
                                        <input  autocomplete="off" id="nameTxt" autocomplete="off" type="text"  name="name" value="${positionInfo.name }" class="required width-p40" maxlength="20" />
										<span class="color-red">*</span>
                                    </td>
                                    <td class="ltd4"> 职位描述 </td>
                                    <td>
                                     <input  autocomplete="off" id="descriptionTxt" type="text"  name="description" value="${positionInfo.description }" class=" width-p80" maxlength="50" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">修改</a>
                                         <a href="javascript:back();" class="btn1 btn-small">取消</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <center>
        <t:Footer></t:Footer>
     </center>
  </body>
</html>
