var adminCookieExpiry = 5000000;



$(document).ready(function () {
	
	 $("input[type='text']").each(function(){
         if ($(this).val() == '0.0') {
             $(this).val('0');
         }
     });
	
	 $(".list-tb tr").hover(function(){
		 $(this).addClass("td-hover-color")
	 },function(){
		  $(this).removeClass("td-hover-color")
	 });
	 
	var height=$(window).height();
	$('.main-page-190').height(height-190);
	$('.main-page-210').height(height-210);
	$('.main-page-230').height(height-230);
	$('.main-page-250').height(height-250);
	$('.main-page-260').height(height-260);
	$('.main-page-270').height(height-270);
	$('.main-page-280').height(height-280);
	$('.main-page-290').height(height-290);
	$('.main-page-300').height(height-300);
	$('.main-page-310').height(height-310);
	$('.main-page-320').height(height-320);
	$('.main-page-330').height(height-330);
	$('.main-page-340').height(height-340);
	$('.main-page-350').height(height-350);
	$('.main-page-360').height(height-360);
	$('.main-page-370').height(height-370);
	$('.main-page-380').height(height-380);
	$('.main-page-390').height(height-390);
	$('.main-page-400').height(height-400);
	$('.main-page-40').height(height-40);
	$('.main-page-80').height(height-80);
	$('.main-page-90').height(height-90);
	
	$('input:text').each(function(){
		var id = $(this).attr("id");
        var name = $(this).attr("tagname");
        
    	$(this).focus(function(){
  		  $(this).toggleClass("onfocus");
      	});
    	$(this).blur(function(){
  		  $(this).toggleClass("onfocus");
      	});
    	
    	if(name=="dateInputYear"){
    		$(this).datepicker({
    			changeYear:true,
    			changeMonth: true,
    			showOtherMonths: true,
    			selectOtherMonths: true,
    			yearRange: '1980:2020'
    		});
    	}
    	if(name=="dateminDate"){
    		$(this).datepicker({
    			changeYear:true,
    			minDate: new Date(),
    			changeMonth: true,
    			showOtherMonths: true,
    			selectOtherMonths: true
    		});
    	}
	});
	
	$('textarea').each(function(){
    	$(this).focus(function(){
      		  $(this).toggleClass("onfocus");
      	});
    	$(this).blur(function(){
      		  $(this).toggleClass("onfocus");
      	});
	});
	
});


function autoHeight(obj,height){
	$(obj).height($(window).height()-height);
}


function getQueryString(query) {
    var search = window.location.search + '';
    if (search.charAt(0) != '?') {
        return undefined;
    }
    else {
        search = search.replace('?', '').split('&');
        for (var i = 0; i < search.length; i++) {
            if (search[i].split('=')[0] == query) {
                return decodeURI(search[i].split('=')[1]);
            }
        }
        return undefined;
    }
}

function getSearchParam(){
	var param="";
	var search = window.location.search + '';
    if (search.charAt(0) != '?') {
        return param;
    }
    else {
        search = search.replace('?', '').split('&');
        for (var i = 0; i < search.length; i++) {
            if (search[i].split('=')[0] != 'pageNo') {
            	param+="&"+search[i];
            }
        }
    }
    return param;
}

function getQuestParam(){
	var param="";
	var search = window.location.search + '';
    if (search.charAt(0) != '?') {
        return param;
    }
    else {
        search = search.replace('?', '').split('&');
        for (var i = 0; i < search.length; i++) {
            //if (search[i].split('=')[0] != 'pageNo') {
            	param+="&"+search[i];
            //}
        }
    }
    return param;
}

function winload(url){
	window.location.href=url;
}

function openwin(url,height,width) { 
	window.open (url, "newwindow", "height="+height+", width="+width+", toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}

function addCookies(key,value){
	$.cookie && $.cookie(key, value, {
		expires : adminCookieExpiry, 
		path : "/"
	 });
}

function numRand() {
    var x = 23456; //上限
    var y = 7; //下限
    var rand = parseInt(Math.random() * (x - y + 1) + y);
    return rand;
}

String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}










