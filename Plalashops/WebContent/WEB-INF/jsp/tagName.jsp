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
		<div class="form-group" align="center">
			  <div class ="row">
			     <div>
			     	<h4>ใส่ชื่อ-นามสกุลให้ถูกต้อง</h4>
			     </div>
				  <div class="col-xs-12" style="margin-top: 5%" align="left">
					  <input type="text" class="botton-plala" name="name" style="color: "  ID="name" required="required"  >
				  </div>
 
 				  <div align="left" class="col-xs-12" style="margin-top: 5%">
						<select class="input-large form-control botton-plala" style="width:50%;border-top-width: 1px;margin-top: 10px;">
			              <option selected="selected">ยศ</option>
			              <option>จ่าสิบเอกพิเศษ</option>
							<option>จ่าสิบเอก</option>
							<option>จ่าสิบโท</option>
							<option>จ่าสิบตรี</option>
							<option>สิบเอก</option>
							<option>สิบโท</option>
							<option>สิบตรี</option>
							<option>พลเอก</option>
							<option>พลโท</option>
							<option>พลตรี</option>
							<option>พลจัตวา[3]</option>
							<option>พันเอก</option>
							<option>พันโท</option>
							<option>พันตรี</option>
							<option>ร้อยเอก</option>
							<option>ร้อยโท</option>
							<option>ร้อยตรี</option>
			            </select>
				</div>
				<div align="left" class="col-xs-12" style="margin-top: 5%">
			     	<h4 style="padding-left: 30px">    ที่อยู่จัดส่ง</h4>
			     </div>
			     <div class="col-xs-12" style="margin-top: 5%" align="left">
					  <input  placeholder="กรม /กอง" type="text" class="botton-plala" name="name" style="color: "  ID="name" required="required"  >
				  </div>
			     <div class="col-xs-12" style="margin-top: 5%" align="left">
					  <input  placeholder="โทร" type="text" class="botton-plala" name="name" style="color: "  ID="name" required="required"  >
				  </div>
				  
				  
				  		  <div align="left" class="col-xs-12" style="margin-top: 5%;padding-left: 30px; ">
								<span style="float: left;">จำนวน : </span>
								<table border="0" >
								<tr>
									<td align="right">
										<span class="input-group-btn">
											<button type="button" class="btn btn-link btn-number" onclick="del();" data-type="minus" data-field="quant[1]">
												<span class="glyphicon glyphicon-minus"></span>
											</button>
										</span> 
									</td>
									<td style="width: 40%"><input type="number" id="qty" class="botton-plala form-control input-number" value="1" min="1" max="10"></td>
									<td align="left">
										<span class="input-group-btn">
										<button type="button" class="btn btn-link btn-number" onclick="add();" data-type="plus" data-field="quant[1]">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
										</span>
									
									</td>
								</tr>
								</table>
							</div>
				  
				   <div class="col-xs-12" style="margin-top: 5%">
					  <button type="submit" class="botton-plala" style="width:40%">ซื้อ</button>
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

