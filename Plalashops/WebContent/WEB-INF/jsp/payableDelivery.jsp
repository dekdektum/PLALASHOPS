<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap-social.css">
  <link rel="stylesheet" href="bootstrap/css/plalashop.css">
  <link rel="stylesheet" href="bootstrap/css/font-awesome.min.css">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function jsSendForm(page) {
		document.forms["jsSendForm"].action = page;
		document.forms["jsSendForm"].submit();
	}
</script>

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
<body id='bg'>
<script type="text/javascript">
		document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>

<!-- <script type="text/javascript">
  $(document).ready(function(){
    $('.combobox').combobox();
  });
</script> -->

<div id="main" class="container" style="width : 75% ;">
	<form action="loginApp.html" method="Post" style="margin-top: 20%">
		<div class="form-group" >
			  <div class ="row">
			     <div align="center">
			     	<h4 >ยอดเงินรวม</h4>
			     </div>
			    <div align="center">
					  <input type="text"  readonly="readonly" value="1,000.00 บาท" class="botton-plala">					  
			     </div> 
			     
			     <br>
				 <br>
				 <br>		
				  <p align="center">บริการเก็บเงินปลายทางกรุณาใส่ชื่อที่อยู่และเบอร์โทร</p>
			    	<div class="radio" >
				      <label style="padding-left: 50px;"><input type="radio" name="optradio">ข้อมูลสมาชิก</label>
				    </div>
				    <div class="radio">
				      <label style="padding-left: 50px;"><input type="radio" name="optradio">ไม่ใช้สมาชิก กรุณาใส่ที่อยู่</label>
				    </div>	
				    <div align="center">
				     	<textarea class="form-control" rows="5" id="address"></textarea>
				    </div> 	
				     <br>
				 <br>
				   <div class="col-xs-12" style="margin-top: 5%" align="center">
					  <button type="submit" class="botton-plala" style="width:60%">ยืนยันการสั่งซื้อ</button>
				  </div>
			  </div>	 	
		  </div>
	   </form>
	   <form id="jsSendForm" method="post">
	   </form>
<!-- 	</div>  -->
</div>

</body>
</html>

