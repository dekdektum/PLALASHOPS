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

</head>
<body id='bg'>
<script type="text/javascript">
		document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>

<div id="main" class="container" style="width : 75% ;">
	<form action="loginApp.html" method="Post" style="margin-top: 20%">
		<div class="form-group">
			  <div class ="row">
			     <div>
			     	<h4 style="padding-left: 50px;">สินค้าที่เลือก</h4>
			     </div>
			     
						<table class="table">
						  <thead>
						    <tr>
						      <th>รหัส</th>
						      <th>รายการ</th>
						      <th>จำนวน</th>
						      <th>ราคา</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <th scope="row">1</th>
						      <td>หมวก</td>
						      <td>2</td>
						      <td>25</td>
						    </tr>
						    <tr>
						      <th scope="row">2</th>
						      <td>ปีก</td>
						      <td>5</td>
						      <td>100</td>
						    </tr>
						    <tr>
						      <th scope="row">3</th>
						      <td>ข้าวผัก</td>
						      <td>2</td>
						      <td>1000</td>
						    </tr>
						  </tbody>
						</table>

    					<div class="radio" >
					      <label style="padding-left: 50px;"><input type="radio" name="optradio">ส่งธรรมดา 2 รอบฟรีเช้าบ่าย</label>
					    </div>
					    <div class="radio">
					      <label style="padding-left: 50px;"><input type="radio" name="optradio">ส่งด่วน ค่าบริการฟรี 40 บาท</label>
					    </div>

						
				   <div class="col-xs-12" style="margin-top: 5%" align="center">
					  <button type="submit" class="botton-plala" style="width:40%">ขั้นตอนต่อไป</button>
				  </div>
			  </div>	 	
		  </div>
	   </form>
	   <form id="jsSendForm" method="post">
	   </form>
</div>

</body>
</html>

