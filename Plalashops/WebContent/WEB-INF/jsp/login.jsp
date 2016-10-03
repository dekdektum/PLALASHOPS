<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.container {
	text-align: center;
	vertical-align: middle;
	display: table-cell;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ffffff),
		to(#5dafd3));
	background-image: -webkit-linear-gradient(#ffffff, #5dafd3);
	background-image: -moz-linear-gradient(#ffffff, #5dafd3);
	background-image: -ms-linear-gradient(#ffffff, #5dafd3);
	background-image: -o-linear-gradient(#ffffff, #5dafd3);
	background-image: linear-gradient(#ffffff, #5dafd3);
}

.new-Button {
	width: 150px;
	height: 70px;
	border-radius: 10px;
	text-align: center;
	vertical-align: middle;
	font-size: 14px;
	margin: 7px;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2) inset;
}

.new-Button:hover {
	box-shadow: 0 0 12px #387BBE;
	text-shadow: 0 1px 0 #fdf581;
	font-size: 16px;
	font-weight: bold;
}

.div-style-button {
	text-shadow: 0 1px 0 #FFFFFF;
	border-radius: 10px;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2) inset;
}

.div-style-button:hover {
	text-shadow: 0 1px 0 #FFFFFF;
	border-radius: 10px;
	margin: 7px;
	box-shadow: 0 0 12px #387BBE;
}
</style>
</head>
<body>
<div class="container" style="width : 90%" >
	<div class="panel panel-default">
	<div class="container">
	  <img src="http://downloadicons.net/sites/default/files/shop-icon-61450.png" class="img-circle center-block" alt="Cinque Terre" width="200" height="200">
	</div>
	<div class="panel-heading" align="center">PLALA SHOP</div>
	<br>
	<br>
	<form action="register.html" method="Post">
		<div class="form-group" align="center">
			  <div class ="row">
				  <div class="col-xs-12">
					  <input type="email" placeholder="E-mail" class="form-control" name="Email" style="width:90%" ID="Email">
				  </div>
				  <br>
				  <br>
				  <div class="col-xs-12">
					  <input type="password" placeholder="รหัสผ่าน"  class="form-control" name="pass" style="width:90%" ID="pass">
				  </div>
				  <br>
				   <br>
				   <div class="col-xs-12">
					  <button type="submit" class="form-control" style="width:90%">Log In</button>
				  </div>
				  <br>
				  <br>
				   <div class="col-xs-12">
					  <button type="submit" class="form-control" style="width:90%">Log In With Facebook</button>
				  	<br>
				  </div>
				 <button type="submit" class="btn btn-default" >ลืมรหัสผ่าน</button>
				 <button type="submit" class="btn btn-default" >สมัครสมาชิก</button>
				 <div class="col-xs-12">
				 <br>
				 	<button type="submit" class="btn btn-default"  style="width:20%">ข้าม</button>
				 </div>
			  </div>	 	
		  </div>
	   </form>
	</div> 
</div>

</body>
</html>

