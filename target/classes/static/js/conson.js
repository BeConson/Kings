$('.menu.toggle').click(function() {
	$('.m-item').toggleClass('m-mobile-hide');
});

$('.menu .item')
	.tab()
;

$('.ui.dropdown')
	.dropdown();

$(function(){
	setTimeout(function(){
		var buy = document.getElementById('clickMe');//给你的a标签加一个id :btnBuy
		buy.click();
	},1000)//后面的500是以毫秒为单位。
});