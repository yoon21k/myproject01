/**
 * 
 */

// best-img slick slide 적용
$(function(){
	$(".slick-ul").slick({
		slide: "li",
		infinite: true,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 3000,
		pauseOnHover : true,
		arrows: true,
		nextArrow: $(".next"),
		prevArrow: $(".prev"),
		dots: true,
		dotsClass: "slick-dots"
	});
});