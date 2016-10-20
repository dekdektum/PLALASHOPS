<!DOCTYPE html>
<html lang="en">
<head>
 <%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<%@ page import="java.io.*" %>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0, user-scalable=no ">
	<link rel="stylesheet" href=bootstrap/css/bootstrap.min.css">
	<script src="bootstrap/js/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="bootstrap/js/jssor.slider-21.1.6.mini.js" type="text/javascript"></script>
    <link href="bootstrap/css/custom.css" rel="stylesheet">
    <link href="bootstrap/css/owl.carousel.css" rel="stylesheet">
    
    <script src="bootstrap/js/owl.carousel.js"></script>
    <script defer src="bootstrap/js/owl.custom.js"></script>
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 70%;
      margin: auto;
  }
  .padding-0 {
    padding: 2px;
      height: 100%;
 overflow:hidden;
}
  </style>
  <script type="text/javascript">
  function jsSendFormItem(id){
		document.getElementById('id').value = id;
		document.forms["jsSendFormItem"].submit();
	}
  </script>
</head>
<body id='bg'>
<script type="text/javascript">
	document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            
            var jssor_1_SlideoTransitions = [
              [{b:-1,d:1,o:-1},{b:0,d:1000,o:1}],
              [{b:1900,d:2000,x:-379,e:{x:7}}],
              [{b:1900,d:2000,x:-379,e:{x:7}}],
              [{b:-1,d:1,o:-1,r:288,sX:9,sY:9},{b:1000,d:900,x:-1400,y:-660,o:1,r:-288,sX:-9,sY:-9,e:{r:6}},{b:1900,d:1600,x:-200,o:-1,e:{x:16}}]
            ];
            
            var jssor_1_options = {
              $AutoPlay: true,
              $SlideDuration: 800,
              $SlideEasing: $Jease$.$OutQuint,
              $CaptionSliderOptions: {
                $Class: $JssorCaptionSlideo$,
                $Transitions: jssor_1_SlideoTransitions
              },
              $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$
              },
              $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$
              }
            };
            
            var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
            
            //responsive code begin
            //you can remove responsive code if you don't want the slider scales while window resizing
            function ScaleSlider() {
                var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1920);
                    jssor_1_slider.$ScaleWidth(refSize);
                }
                else {
                    window.setTimeout(ScaleSlider, 20);
                }
            }
            ScaleSlider();
            $(window).bind("load", ScaleSlider);
            $(window).bind("resize", ScaleSlider);
            $(window).bind("orientationchange", ScaleSlider);
            //responsive code end
        });
        
    </script>
    <style>
        /* jssor slider bullet navigator skin 05 css */
        /*
        .jssorb05 div           (normal)
        .jssorb05 div:hover     (normal mouseover)
        .jssorb05 .av           (active)
        .jssorb05 .av:hover     (active mouseover)
        .jssorb05 .dn           (mousedown)
        */
        .jssorb05 {
            position: absolute;
        }
        .jssorb05 div, .jssorb05 div:hover, .jssorb05 .av {
            position: absolute;
            /* size of bullet elment */
            width: 16px;
            height: 16px;
            background: url('img/b05.png') no-repeat;
            overflow: hidden;
            cursor: pointer;
        }
        .jssorb05 div { background-position: -7px -7px; }
        .jssorb05 div:hover, .jssorb05 .av:hover { background-position: -37px -7px; }
        .jssorb05 .av { background-position: -67px -7px; }
        .jssorb05 .dn, .jssorb05 .dn:hover { background-position: -97px -7px; }
        
        /* jssor slider arrow navigator skin 22 css */
        /*
        .jssora22l                  (normal)
        .jssora22r                  (normal)
        .jssora22l:hover            (normal mouseover)
        .jssora22r:hover            (normal mouseover)
        .jssora22l.jssora22ldn      (mousedown)
        .jssora22r.jssora22rdn      (mousedown)
        */
        .jssora22l, .jssora22r {
            display: block;
            position: absolute;
            /* size of arrow element */
            width: 40px;
            height: 58px;
            cursor: pointer;
            background: url('img/a22.png') center center no-repeat;
            overflow: hidden;
        }
        .jssora22l { background-position: -10px -31px; }
        .jssora22r { background-position: -70px -31px; }
        .jssora22l:hover { background-position: -130px -31px; }
        .jssora22r:hover { background-position: -190px -31px; }
        .jssora22l.jssora22ldn { background-position: -250px -31px; }
        .jssora22r.jssora22rdn { background-position: -310px -31px; }
    </style>
<%
		String actionPage = (String) request.getAttribute("actionPage");
		List<Advertise> advertiseList = (List<Advertise>) request.getAttribute("advertiseList");
		List<Product> productList = (List<Product>) request.getAttribute("productList");
	
	%>
<div class="container">
	<form action="register.html" method="Post">
		<div class="form-group" align="center">
			<div class="row">
					<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1300px; height: 500px; overflow: hidden; visibility: hidden;">
				        <!-- Loading Screen -->
				        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
				            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
				            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
				        </div>
				        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1300px; height: 500px; overflow: hidden;">
							<% for(Advertise advertise:advertiseList) {%>
					            <div data-p="225.00" style="display: none;" >
					                <a href="<%=advertise.getLinkAdvertise() %>"><img data-u="image" src="<%= advertise.getFileName() %>" /></a>
					            </div>
				            <%} %>
				            
				        </div>
				        <div data-u="navigator" class="jssorb05" style="bottom:30px;right:30px;" data-autocenter="1">
				            <div data-u="prototype" style="width:30px;height:30px;"></div>
				        </div>
				    </div>
				    <div class="product-box-inner">
				    	<div id="product-slide0" class="owl-carousel owl-theme">
				    	<% for(Product product: productList){ 
				    	if(product.getFileImage() != null){%>
				            <div class="thumbnail" style="width: 100%;margin-bottom: 0px;">
						   		<img class="img-rounded"  src="<%= product.getFileImage() %>"/>
				                <div class="caption">
				                    <h4 class="group inner list-group-item-heading"><%= product.getProductName() %></h4>
				                    <div class="row">
				                        <div class="col-sm-6 ">
				                        <%if(product.getPrice() > 0 ){ %>
				                            <p class="lead"><strike>฿<%= product.getPrice() %></strike> Dis. <B style="color: red;">฿<%= product.getSalePrice() %><br>ลดถึง  <%= product.getDisCount() %>%</B></p>
				                        <%}else{ %>
				                            <p class="lead"><br><B style="color: red;">฿<%= product.getSalePrice() %></B></p>
				                        <%} %>
				                        </div>
				                    </div>
				                </div>
				            </div>
					<%	} 
					} %>
               		</div>
		         </div>
			
			</div>
		</div>
	</form>
	
	<form id="jsSendFormItem" method="post" action="showDetailItem.html">
		<input type="hidden" id="id" name="id">
	</form>
</div>
<style>
    #owl-demo .item{
        margin: 1px;
    }
    #owl-demo .item img{
        display: block;
        width: 100%;
        height: auto;
    }
    </style>


    <script>
    $(document).ready(function() {
      $("#owl-demo").owlCarousel({
        items : 3,
        itemsDesktop : [1199,3],
        itemsDesktopSmall : [979,3]
      });

    });
    </script>

</body>
</html>


