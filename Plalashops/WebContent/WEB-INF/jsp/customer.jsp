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
<link rel="stylesheet" href=bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="bootstrap/js/jssor.slider-21.1.6.mini.js" type="text/javascript"></script>
<script type="text/javascript">
  
  </script>

<style type="text/css">
  .padding-0 {
    padding: 0px;
      height: 100%;
 overflow:hidden;
}

.form-horizontal .control-label.text-left {
	text-align: left;
}

div.party {
	width: 9px;
	margin: 4px 5px 0px 5px
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
	    $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
	});
	function jsSendFormItem(id){
		document.getElementById('id').value = id;
		document.forms["jsSendFormItem"].submit();
	}
</script>


</head>
<body>
	<script type="text/javascript">
		document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
	</script>
	
	<form id="createCustomer" action="createCustomer.html">
		<div class="container-fluid" align="center">
			<detailLabel style="margin-top: 5%">ข้อมูลผู้รับสินค้า</detailLabel>
			<div class="row">
				<div class="col-lg-6 col-md-12 col-xs-12" style="margin-top: 5%">
					<input type="text" placeholder="ชื่อและนามสกุล" class="botton-plala" name="fullName"   ID="name" required="required">
				</div>
				<div class="col-lg-6 col-md-12 col-xs-12" style="margin-top: 5%">
					<textarea placeholder="ที่อยู่ (บ้านเลขที่ หมู่ แขวง/ตำบล เขต/อำเภอ จังหวัด)" class="botton-plala" name="address" style="width: 266px; margin: 0px; height: 50px;"  ID="address" required="required" ></textarea>
					
				</div>
				<div class="col-lg-6 col-md-12 col-xs-12" style="margin-top: 5%">
					<input type="number" placeholder="รหัสไปรษณีย์" class="botton-plala" name="zipcode"   ID="zipcode" required="required">
				</div>
				<div class="col-lg-6 col-md-12 col-xs-12" style="margin-top: 5%">
					<button type="submit" class="botton-plala" style="width:90%">บันทึกข้อมูลผู้รีบสินค้า</button>
				</div>
			</div>
		</div>
		
	
	</form>
	
	
	<form id="jsSendFormCustomer" method="post" action="createCustomer.html">
		<input type="hidden" id="id" name="id">
	</form>



</body></html>