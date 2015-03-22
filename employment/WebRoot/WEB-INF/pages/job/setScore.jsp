<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/tagslib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--  
 * @version 1.0.01
 * @author zhangmin
-->
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
    <div id="main-div" class="width-p96">
            <div class="content main-page-190" >
                <div class="margin-lr-1">
                    <div class="main-page-90 over-flow-x-hidden">
                        <form id="form1" action="${path}/jobController/save.do" method="post">
                            <input type="hidden" name="id" id="idHid" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4" style="background-color: #405E78">
                                        <font size=3 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>考核项分值设置</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                <c:forEach var="item" items="${abilityMap}" varStatus="s">
                                <tr>
                                     <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>${item.key}</strong>&nbsp;&nbsp;&nbsp; 
                                        </font>
                                    </td>
                                </tr>
                                  <c:forEach var="ability" items="${item.value}" >
                                   <tr>
                                     <td class="ltd4">${ability.name}</td>
                                    <td class="rtd4" colspan="3">
                                    <input id="scoreKeyTxt" type="hidden" name="scoreKeyArray" value="${ability.name}"/>
                                  	分值：<input  autocomplete="off" id="scoreValTxt" type="text"  name="scoreValArray" class=" width-p20" maxlength="3" />（分）
                                    </td>
                                </tr>
                                </c:forEach>
                                </c:forEach>
                                <tr>
                                    <td style="text-align: center;" colspan="4">
                                        <a href="javascript:onSubmit();" class="btn1 btn-small">保存</a>
                                         <a href="javascript:back();" class="btn1 btn-small">取消</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
  </body>
</html>
