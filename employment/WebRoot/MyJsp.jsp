<!--  
 * @version 1.0.01
 * @author zhangmin
-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/common/header.jsp"%>
</head>
 <script language="javascript" type="text/javascript"> 
$(function () { 
var scrollTimer = null; 
var delay = 2000; 
//1.鼠标对新闻触发mouseout事件后每2s调用scrollNews() 
//2.鼠标对新闻触发mouseover事件后停止调用scrollNews() 
//3.初次加载页面触发鼠标对新闻的mouseout事件 
$('div.scrollNews').hover(function () { 
clearInterval(scrollTimer); 
}, function () { 
scrollTimer = setInterval(function () { 
scrollNews(); 
}, delay); 
}).triggerHandler('mouseout'); 
}); 
//滚动新闻 
function scrollNews() { 
var $news = $('div.scrollNews>ul'); 
var $lineHeight = $news.find('li:first').height(); 
$news.animate({ 'marginTop': -$lineHeight + 'px' }, 600, function () { 
$news.css({ margin: 0 }).find('li:first').appendTo($news); 
}); 
} 
</script> 
</head> 
<body> 
<a href="www.baidu.com">百度搜索</a>
<form id="form1" runat="server"> 
<div class="scrollNews" > 
<ul> 
<li><a href="#" class="tooltip" title="1甜美宽松毛衣今秋一定红.">1甜美宽松毛衣今秋一定红.</a></li> 
<li><a href="#" class="tooltip" title="2秋装百搭小马甲不到50元.">2秋装百搭小马甲不到50元.</a></li> 
<li><a href="#" class="tooltip" title="3修身韩版小西装万人疯抢.">3修身韩版小西装万人疯抢.</a></li> 
<li><a href="#" class="tooltip" title="4夏末雪纺店主含泪大甩卖.">4夏末雪纺店主含泪大甩卖.</a></li> 
<li><a href="#" class="tooltip" title="5瑞丽都疯狂推荐的秋装.">5瑞丽都疯狂推荐的秋装.</a></li> 
<li><a href="#" class="tooltip" title="6元长款针织小开衫卖疯啦.">6元长款针织小开衫卖疯啦.</a></li> 
<li><a href="#" class="tooltip" title="7长袖雪纺衫单穿内搭都超美.">7长袖雪纺衫单穿内搭都超美.</a></li> 
</ul> 
</div> 
</form> 
</body> 
</html> 