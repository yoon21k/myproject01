/**
 * 
 */

var eventTimer;

$(function(){
	setTimeout(start, 3000);
	
	$(".fa-angle-left").click(prev);
	$(".fa-angle-right").click(next);
	
	$(".slide-btn").hover(stop, function(){
		eventTimer = setTimeout(start, 3000);
	});
});

function start(){ 
	var imgs = $(".new-product ul li");
	if(imgs.length>1){
		next();	
		eventTimer = setTimeout(start, 3000);
	}
}

function stop(){
	clearTimeout(eventTimer);
}

function next(){
	var first = $(".new-product ul li:first-child");
	var last = $(".new-product ul li:last-child");
	
	$(".new-product ul").animate({marginLeft : -100+"%"},500,function(){
		last.after(first);
		$(".new-product ul").css({marginLeft : 0});
	});
}

function prev(){ 
	var first = $(".new-product ul li:first-child");
	var last = $(".new-product ul li:last-child");

	first.before(last);
	$(".new-product ul").css({marginLeft : -100+"%"});
	$(".new-product ul").animate({marginLeft : 0},500,function(){
	});
}