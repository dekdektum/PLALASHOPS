<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>


<div class="container" style="width : 500px">
  <h2>PLALA SHOPS Application Config.</h2>
  <% String message = (String)request.getAttribute("message"); 
  	if(message != null){
  %>
  		<div class="alert alert-danger" role="alert"><%= message%></div>
  <%		
  	}
  %>
  
  <form method="POST" action="adminLogin.html" data-ajax="false">
    <div class="form-group" style="width : 300px">
      <label for="username">User Admin</label>
      <input type="text" name="username" class="form-control" id="username" placeholder="Enter username">
    </div>
    <div class="form-group" style="width : 300px">
      <label for="password">Password:</label>
      <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
    </div>
    <button type="submit" class="btn btn-default">Login</button>
  </form>
</div>

</body>
</html>

