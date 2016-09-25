<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function checkPass()
  {
      var pass1 = document.getElementById('pass');
      var pass2 = document.getElementById('conPass');
      var message = document.getElementById('confirmMessage');
      var goodColor = "#66cc66";
      var badColor = "#ff6666";
  
      if(pass1.value == pass2.value){
          pass2.style.backgroundColor = goodColor;
          message.style.color = goodColor;
          message.innerHTML = "Passwords Match!"
      }else{
          pass2.style.backgroundColor = badColor;
          message.style.color = badColor;
          message.innerHTML = "Passwords Do Not Match!"
      }
  }  
  </script>
  
  
  <style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>

</head>
<body>
<div class="container" style="width : 90%" >
	<div class="panel panel-default">
	<div class="panel-heading" align="center">สมัครสมาชิก</div>
	<br>
	<br>
	<form action="register.html" method="Post">
		<div class="form-group" align="center">
			  <div class ="row">
				  <div class="col-xs-12">
					  <input type="email" placeholder="E-mail" class="form-control" name="Email" style="width:90%" ID="Email" required>
				  </div>
				  <br>
				  <br>
				  <div class="col-xs-12">
					  <input type="number" placeholder="เบอร์โทรศัพท์" class="form-control" name="phone" style="width:90%" required>
				  </div>
				  <br>
				   <br>
				  <div class="col-xs-12">
					  <input type="password" placeholder="รหัสผ่าน"  class="form-control" name="pass" style="width:90%" ID="pass">
				  </div>
				  <br>
				   <br>
				  <div class="col-xs-12">
					   <input type="password" placeholder="ยืนยันรหัสผ่าน" class="form-control" name="conPass" id="conPass" style="width:90%"  onkeyup="checkPass(); return false;">
					   <span id="confirmMessage" class="confirmMessage"></span>
				  </div>
			  </div>
			  <br>
			 <button type="submit" class="btn btn-default">เงื่อนไข</button>
			 <button type="submit" class="btn btn-default">สมัครสมาชิก</button>
		  </div>
	   </form>
	</div> 
</div>

</body>
</html>

