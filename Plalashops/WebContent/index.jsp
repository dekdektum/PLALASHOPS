<html>
<head>
<title>Spring MVC Tutorial Series by Crunchify.com</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>

<link rel="stylesheet" type="text/css" href="jquery-mobile/css/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="jquery-mobile/css/jquery-mobile.css">
<script src="jquery-mobile/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="jquery-mobile/js/jquery.mobile-1.4.5.min.js" type="text/javascript"></script>
</head>
<body>
	<!-------------- First page for form ----------->
	<div data-role="page">
		<!-------------- First page header ----------->
		<div data-role="header">
			<h3>PLALA SHOPS</h3>
		</div>
		<!-------------- First page main content ----------->
		<div data-role="main" class="ui-content">
		
		<form method="POST" action="adminLogin.html" data-ajax="false">
				<label for="username">Name/Email <span>*</span></label> 
				<input type="text" name="username" id="username" placeholder="username"> 
				<label for="password">password: <span>*</span></label> 
				<input type="password" id="password" name="password" placeholder="password" />
				<input type="submit" value="Login" /> 
				<input  type="Button" value="Login With FaceBook" /> 
		</form>
		
		</div>
		
		
</body>
</html>