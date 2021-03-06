<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
   <%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<%@ page import="java.io.*" %>
<meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0, user-scalable=no ">
<!-- ไม่ให้ซูม -->
<meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0, user-scalable=no ">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="bootstrap/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="bootstrap/js/jssor.slider-21.1.6.mini.js" type="text/javascript"></script>


<style type="text/css">


.form-horizontal .control-label.text-left {
	text-align: left;
}

div.party {
	width: 9px;
	margin: 4px 5px 0px 5px
}
</style>

<script type="text/javascript">
	function add(){
		var unit = document.getElementById('qty').value;
		if(unit <= 99){
			document.getElementById('qty').value = parseInt(unit)+1;
		}
	} 
	function del(){
		var unit = document.getElementById('qty').value;
		if(unit > 1){
			document.getElementById('qty').value = parseInt(unit)-1;
		}
	} 
</script>


</head>
<body>
<script type="text/javascript">
	document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>

<%
	Product product = (Product) request.getAttribute("product");
	String actionMode = (String) request.getAttribute("actionMode");
	List<ImgMapping> imgList = (List<ImgMapping>) request.getAttribute("imgList");
%>
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
                    window.setTimeout(ScaleSlider, 30);
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
    
	<div class="container" style="width: 90%">
<!-- 		<div class="panel panel-default"> -->
			<br> 
			<form  method="Post"  action="buyItem.html">
				<input type="hidden" id="id" name="id" value="<%= product.getProductId() %>">
				<div  class="form-group" align="center">
						<table style="width: 100%" border="0">
							<tr>
							
								<td align="left" colspan="2">
									<detailLabel2 class="col-xs-12"><%= product.getProductType() %></detailLabel2>
								</td>
								<td style="width: 5%"></td>
							</tr>
							<tr>
							<td style="width: 5%"></td>
								<td>
									 <div  id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 200px; height: 200px; overflow: hidden; visibility: hidden;">
								        <!-- Loading Screen -->
								        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
								            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
								            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
								        </div>
								        <div  data-u="slides" style="cursor: default; position: relative; top: 0px; right: 12px; width: 175px; height: 175px; overflow: hidden;">
											<%for(ImgMapping iMapping : imgList){ %>
									            <div data-p="225.00" style="display: none;">
									                <img data-u="image" src="<%= iMapping.getFileName() %>" />
									            </div>
								            <%} %>
								        </div>
								      
								    </div>
								</td>
								<td style="width: 5%"></td>
							</tr>
						</table>

					   
						<detailLabel2 id="special_price_area"  align="left">
							<table style="width: 100%" border="0">
								<tr>
									<td style="width: auto;">
										<span style="float: left;">รหัสสินค้า   :&nbsp; </span>
										<div class="price_erase" align="left"> <%= product.getProductNo() %> </div>
									</td>
								</tr>
								<tr>
									<td style="width: auto;">
										<detailLabel2 style="float: left;">ราคา  :&nbsp; </detailLabel2> 
										<span id="price_box"> 
											<%if(product.getPrice() != null && product.getPrice() > 0 ){ %>
												<strike><%= product.getPrice() %>บาท </strike> 
												<B style="color: red;"> &nbsp; ลดทันทีเหลือ  &nbsp;<B style="color: red;"><%= product.getSalePrice() %> บาท </B></B>
											<%}else{ %>
												<B style="color: red;"> &nbsp; ขายเพียง  &nbsp;<B style="color: red;"><%= product.getSalePrice() %> บาท </B></B>
											<%} %>
										</span> 
									</td>
								</tr>
								<tr>
									<td>
										รายละเอียด 
									</td>
								</tr>
								<tr>
									<td>
										<%=product.getDescription() %>
									</td>
								</tr>
								<%if(!"pointMode".equals(actionMode)){ %>
								<tr>
									<td style="vertical-align: middle;">
										<table border="0" >
											<tr>
												<td>
													<span style="float: left;" >จำนวน : </span>
												
												</td>
												<td align="right">
													<span class="input-group-btn">
														<button type="button" class="btn btn-link btn-number" onclick="del();" data-type="minus" data-field="quant[1]">
															<span class="glyphicon glyphicon-minus" style="color: white;"></span>
														</button>
													</span> 
												</td>
												<td style="width: 40%"><input type="number" id="qty" name="qty" class="form-control input-number" value="1" min="1" max="10" style="color: #1C6785;text-align: center;" ></td>
												<td align="left">
													<span class="input-group-btn">
													<button type="button" class="btn btn-link btn-number" onclick="add();" data-type="plus" data-field="quant[1]">
														<span class="glyphicon glyphicon-plus" style="color: white;"></span>
													</button>
													</span>
												
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<%} %>		
							</table>
							<br>
					     	<div align="center">
					     		<%if(!"pointMode".equals(actionMode)){ %>
									<button type="submit" class="btn btn-default btn-lg">ซื้อ</button>
								<%}else{%>
									<button type="submit" class="btn btn-default btn-lg">แลกเลย</button>
								<%} %>
							</div>
						</detailLabel2>
						
					</div>
				</form>
			</div>
			
<!-- 		</div> -->
	<!-- </div> -->
	
	<script>
$(document).ready(function() {
   $("#myCarousel").swiperight(function() {
      $(this).carousel('prev');
    });
   $("#myCarousel").swipeleft(function() {
      $(this).carousel('next');
   });
});

</script>
	
</body>
</html>