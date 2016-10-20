<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="bootstrap/css/plalashop.css">
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
          message.innerHTML = "Passwords Match!";
      }else{
          pass2.style.backgroundColor = badColor;
          message.style.color = badColor;
          message.innerHTML = "Passwords Do Not Match!";
      }
  }  
  </script>
  
  
  <style type="text/css">
</style>

</head>
<body>
<%
		String message = (String) request.getAttribute("message");
%>
<div id="main" class="container" style="width : 90% ;">
<br>
<br>
	<% if(message != null && message.length() > 0){ %>
	<div class="alert alert-danger" align="center" >
		  <strong><%= message %></strong>
		</div>
	<%} %>
<script type="text/javascript">document.getElementById('main').style.height = screen.height+'px';</script>
<br>
<!-- 	<div class="panel panel-default"> -->
	
	<div id="text" align="center" style="color: white;margin-top: 30%"><big>สมัครสมาชิก</big></div>
	<form action="registerAction.html" method="Post">
	<input type="hidden" name="userType" value="register">
		<div class="form-group" align="center">
			  <div class ="row">
				  <div class="col-xs-12" style="margin-top: 15%">
					  <input type="email" placeholder="E-mail" class="botton-plala" name="email" style="width:90%" ID="Email" required>
				  </div>
				  <div class="col-xs-12" style="margin-top: 3%">
					  <input type="number" placeholder="เบอร์โทรศัพท์" class="botton-plala" name="phone" style="width:90%" required>
				  </div>
				  <div class="col-xs-12" style="margin-top: 3%">
					  <input type="password" placeholder="รหัสผ่าน"  class="botton-plala" name="password" style="width:90%" ID="pass">
				  </div>
				  <div class="col-xs-12" style="margin-top: 3%">
					   <input type="password" placeholder="ยืนยันรหัสผ่าน" class="botton-plala" name="conPass" id="conPass" style="width:90%"  onkeyup="checkPass(); return false;">
					   <span id="confirmMessage" class="confirmMessage"></span>
				  </div>
				  <div class="col-xs-12" style="margin-top: 3%">
					 <button type="submit" class="botton-plala" style="width: 30%">เงื่อนไข</button>
					 <button type="submit" class="botton-plala" style="width: 30%">สมัครสมาชิก</button>
				</div>
			  </div>
		  </div>
	   </form>
<!-- 	</div>  -->
</div>

</body>
</html>

