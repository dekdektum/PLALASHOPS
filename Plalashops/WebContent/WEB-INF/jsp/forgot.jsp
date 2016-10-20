<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="bootstrap/css/plalashop.css">


</head>
<body id='bg'>
<div class="container" style="width : 80%;" >
	<div id='topDiv' align="center" style="color: white;"></div>
	<div id="text" align="center" style="color: white;"><big>ลืมรหัสผ่าน</big></div>
	<br>
	<br>
	<script type="text/javascript">
		document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
		document.getElementById('topDiv').style.height =  screen.height * 35 / 100 +'px';
		
	</script>
	<form action="forgotPassword.html" method="Post">
		<div class="form-group" align="center">
			  <div class ="row" style="color: white;">
			  	<small>กรุณากรอก e-mail ของคุณเพื่อส่งข้อความรีเซ็ตรหหัสผ่าน</small>
				  <div class="col-xs-12" style="margin-top: 5%">
					  <input type="email" placeholder="E-mail" class="botton-plala" name="Email" style="width:90%" ID="Email">
				  </div>
				   <div class="col-xs-12" style="margin-top: 5%">
					  <button type="submit" class="form-control" style="border-radius: 10em 10em 10em 10em;background-color : white;text-align: center;height: 40px;width:90%;">ส่งคำขอรหัสผ่านใหม่</button>
				 </div>
			  </div>	 	
		  </div>
	   </form>
</div>

</body>
</html>

