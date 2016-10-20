$(window).scroll(function(){
	var sticky = $('header'),
		scroll = $(window).scrollTop();
  
	if (scroll >= 110) sticky.addClass('scrolling');
	else sticky.removeClass('scrolling');
});

 

 


// Custom mobile menu 
$(function() { 
	$('nav#menu').mmenu();
	$("nav#menu").find( ".mm-subopen" ).addClass( "mm-fullsubopen" );
});


 
 
// hover effect
$(document).ready(function() {
	$('.gallery-thumb').hover(
		function(){
			$(this).find('.gallery-overlay').fadeIn(300);			 
		},
		function(){
			$(this).find('.gallery-overlay').fadeOut(300);
		}
	);
	
	$('.photo').hover(
		function(){
			$(this).find('.photo-overlay').fadeIn(300);			 
		},
		function(){
			$(this).find('.photo-overlay').fadeOut(300);
		}
	);
}); 
 