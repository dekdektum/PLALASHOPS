$(document).ready(function() {
 
	
	/*$("#product-slide").owlCarousel({ 
		rewindNav : false,	
		navigation : true,
		items : 4,  
		itemsDesktop : [1200,4], 
		itemsDesktopSmall : [970,3],  
		itemsTablet: [600,1], 
		itemsMobile : [480,1]
	});*/
	$("div[id*='product-slide']").each(function(){
		$(this).owlCarousel({ 
			rewindNav : false,	
			navigation : true,
			items : 3,  
			itemsDesktop : [1200,3], 
			itemsDesktopSmall : [970,3],  
			itemsTablet: [414,2], 
			itemsMobile : [375,2]
		});
	});
});	


 