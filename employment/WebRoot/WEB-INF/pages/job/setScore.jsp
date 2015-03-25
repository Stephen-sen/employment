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
	function onSubmit() {
		$('#form1').submit();
	}
/**
 * 返回之前先把之前保存的数据删除
 */
	function back(){
		var companyId=$("#companyHid").val();
		var positionId=$("#positionHid").val();
        $.ajax({
            beforeSend: function(){showLoading();},
            cache: false,
            type: 'post',
            async: false,
            url: '${path}/jobController/backUpStep.do?',
            data : {"companyId" : companyId,"positionId" : positionId},
            dataType: 'json',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
			},
			success : function(data) {
				$(data).each(function(i, item) {
					hideLoading();
					history.go(-1);
				});
			}
        });
	}
</script>
  <body>
    <div id="main-div" class="width-p96">
            <div class="content main-page-190" >
                <div class="margin-lr-1">
                    <div class="main-page-90 over-flow-x-hidden">
                        <form id="form1" action="${path}/pos_AbiController/save.do" method="post">
                            <input type="hidden" name="id" id="idHid" />
                            <input type="hidden" name="company.id" value="${job.company.id}" id="companyHid" />
                            <input type="hidden" name="position.id" value="${job.position.id}"  id="positionHid" />
							<input type="hidden" value="${token}" name="token" />
                            <table class="add-tb">
                                <tr>
                                    <td class="td-title" colspan="4" style="background-color: #6587a1">
                                        <font size=3 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>考核项分值设置</strong>
                                        </font>
                                    </td>
                                </tr>
                                <c:forEach var="item" items="${abilityMap}" varStatus="s">
                                <tr>
                                     <td class="td-title" colspan="4">
                                        <font size=2 color="black">
                                            <i class=" icon-chevron-down"></i>
                                            <strong>${item.key}</strong>
                                        </font>
                                    </td>
                                </tr>
                                   <tr>
                                     <c:forEach begin="0" end="${fn:length(item.value)}" step="1" varStatus="stat" items="${item.value}"  var="posAbiList">
						               <td class="ltd4">${posAbiList.ability.name}</td>
                                    <td class="rtd4">
                                    <input id="scoreKeyTxt" type="hidden" name="scoreKeyArray" value="${posAbiList.ability.id}"/>
                                  	分值：
                                  	<select id="scoreValTxt"  name="scoreValArray" class="required width-p40">
											<option value="">---请选择---</option>
											<option value="5">5</option>
											<option value="4">4</option>
											<option value="3">3</option>
											<option value="2">2</option>
											<option value="1">1</option>
										</select>
                                  	（分）
                                    </td>
						               <c:if test="${(stat.index+1)%2==0}">
						                </tr><tr>
						                </c:if>
						            </c:forEach>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td  colspan="4"style="text-align: center;">
                                     <a href="javascript:back();" class="btn1 btn-small">上一步</a>
                                     <a href="javascript:onSubmit();" class="btn1 btn-small">保存</a>
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
