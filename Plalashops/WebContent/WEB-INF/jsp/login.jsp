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
<form id="jsSendForm" method="post">
	<input type="hidden" id="autoEmail" name="email">
	<input type="hidden" id="autoPass" name="password">
</form>
<detailLabel>
<script type="text/javascript">
	var username = localStorage.getItem('username');
	var password = localStorage.getItem('password');
	if(username != null && username != '' && username != 'null' && password != null && password != '' && password != 'null'){
		document.forms["jsSendForm"].action = 'loginApp.html';
		localStorage.setItem('username','');
		localStorage.setItem('password','');
		document.getElementById('autoEmail').value = username;
		document.getElementById('autoPass').value = password;
		document.forms["jsSendForm"].submit();
	}
	document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>
<div id="main" class="container" style="width : 75% ;">
	<div class="container" style="height: 45%;margin-top: 30%;">
	  <img src="img/logo.png" class="center-block" alt="Cinque Terre" width="90%" height="90%">
	</div>
	<form id="loginForm" action="loginApp.html" method="Post" style="margin-top: 20%">
		<div class="form-group" align="center">
			  <div class ="row">
				  <div class="col-xs-12" style="margin-top: 5%">
					  <input type="email" placeholder="Name / Email" class="botton-plala" name="email" style="color: "  ID="Email" required="required" value="qqq@a" >
				  </div>
				  
				  <div class="col-xs-12" style="margin-top: 5%">
					  <input type="password" placeholder="Password"  class="botton-plala" name="password" style="width:90%" ID="pass" required="required" value="1234" >
				  </div>
				  <br>
				  <br>
				  <br>
				   <div class="col-xs-12" style="margin-top: 5%">
					  <button type="submit" class="botton-plala" style="width:90%">Log In</button>
				  </div>
				  <br>
				  <br>
				  <br>
				  <div class="col-xs-12" style="margin-top: 5%">
					  <button type="submit" class="botton-plala" style="width:90%">Log In With Facebook</button>
				  </div>
				 
				  <div class="col-xs-6" onclick="jsSendForm('forgot.html')" style="color: white;margin-top: 5%">
				 	ลืมรหัสผ่าน
				 </div>
				 <div class="col-xs-6" onclick="jsSendForm('register.html')" style="color: white;margin-top: 5%">
				 	สมัครสมาชิก
				 </div>
				 <div class="col-xs-12">
				 <br>
				 	<button type="button" class="botton-plala"  style="width:20%"><div>ข้าม</div></button>
				 </div>
			  </div>	 	
		  </div>
	   </form>
<!-- 	</div>  -->
</div>
</detailLabel>
</body>
</html>

