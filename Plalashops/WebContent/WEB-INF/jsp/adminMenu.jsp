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
	<% String menuSelect = (String)request.getAttribute("menuSelect"); %>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">PLALA SHOP</a>
			</div>
			<ul class="nav navbar-nav">
				<li id='itemhomePage' <%= menuSelect.equals("Home") ? "class='active'" : "" %> ><a href="/Plalashops/adminHome.html">Home</a></li>
				<li id='itemproductTypePage' <%= menuSelect.equals("ProductType") ? "class='active'" : "" %> ><a href="/Plalashops/adminProductType.html">Product Type</a></li>
				<li id='itemproductsPage'  <%= menuSelect.equals("Products") ? "class='active'" : "" %> ><a href="/Plalashops/adminProduct.html">Products</a></li>
				<li id='itemoederManagementPage' <%= menuSelect.equals("Order") ? "class='active'" : "" %> ><a href="">Oeder Management</a></li>
			</ul>
		</div>
	</nav>

</body>


</html>

