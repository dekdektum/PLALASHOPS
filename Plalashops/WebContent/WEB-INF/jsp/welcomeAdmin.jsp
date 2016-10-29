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
	<jsp:include page="adminMenu.jsp" />
	<div id='homePage' align="center">
		Hello PlalaShop
	</div>
	<div id='productTypePage' align="center">
		<div class="container" align="center">
			<form id='productType'>
			 <div class="panel panel-default" style="width: 800px">
   	 			<div class="panel-heading">Product Type Search</div>
					<div class="form-group" align="center">
						<br/>
						<table style="width: 80%;" border="0">
							<tr>
								<td align="right" width="30%"> <label for="productType">Product Type :&nbsp;&nbsp;</label></td>
								<td width="70%"><input  type="text" class="form-control" id="productType" placeholder="Product Type" width="250px"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" align="left">
									<button type="submit" class="btn btn-default">Search</button>
									<button type="submit" class="btn btn-default">Add Product Type</button>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id='productsPage' align="center">
		3333333333333333333333333
	</div>
	<div id='oederManagementPage' align="center">
		44444444444444444444444444
	</div>
</body>

</html>

