<!DOCTYPE html>
<html lang="th">
<head>
  <title>PlalaShops</title>
  <meta charset="utf-8">
 <%@page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
<script type="text/javascript">
	function insertProductType(){
		document.getElementById("productType").action = 'insertProductType.html';
		document.getElementById("productType").submit();
	}
	function getProductTypeList(){
		document.getElementById("productType").action = 'getProductTypeList.html';
		document.getElementById("productType").submit();
	}
</script>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	<% 
		List<ProductType> ProuctTypeList = (List<ProductType>)request.getAttribute("productTypeList"); 
	 	String success = (String)request.getAttribute("success"); 
	 	String fail = (String)request.getAttribute("Fail"); 
	%>
	<br/>
	<div id='productTypePage' align="center">
		<div class="container" align="center">
			<% if(success != null && success.length() > 0){ %>
				<div class="alert alert-success" align="left" style="width: 40%">
				  <strong>Success!</strong><%= success %>
				</div>
			<%} %>
			<% if(fail != null && fail.length() > 0){ %>
				<div class="alert alert-danger" style="width: 40%">
				  <strong>Danger!</strong> <%= fail %>
				</div>
			<%} %>
			<form id='productType' method="post">
			 <div class="panel panel-default" style="width: 80%">
   	 			<div class="panel-heading" align="left">Product Type Search</div>
					<div class="form-group" align="center">
						<br/>
						<table border="0">
							<tr>
								<td align="right" width="30%"> <label for="productType">Product Type :&nbsp;&nbsp;</label></td>
								<td width="70%"><input  type="text" class="form-control" id="productType" placeholder="Product Type" style="width: 250px" name="productType"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<button type="button" class="btn btn-default" onclick="getProductTypeList()">Search</button>
									<button type="button" class="btn btn-default" onclick="insertProductType()">Add Product Type</button>
								</td>
							</tr>
						</table>
					</div>
					<%if(ProuctTypeList != null && ProuctTypeList.size() > 0){ %>
				<div class="panel panel-default" style="width: 80%">
					<div class="panel-heading" align="left">Product Type result list</div>
					<br/>	
					<br/>	
					<br/>	
					<div class="form-group" align="center">
						<div class="table-responsive">
							  <table class="table" style="width: 50%" border="0">
							    <thead>
							      <tr>
							        <th>No</th>
							        <th>Product Type</th>
							        <th>Delete</th>
							      </tr>
							    </thead>
							    <tbody>
							    <% for(int i = 0 ; i < ProuctTypeList.size() ; i++){ %>
							      <tr>
							        <td><%= i+1 %></td>
							        <td><%= ProuctTypeList.get(i).getProductTypeName() %></td>
							        <td>
							        	<a href="deleteProductType.html?productType=<%=ProuctTypeList.get(i).getProductTypeName() %>">
							        		<img style="width: 25px;height: 25px" id='del<%=i %>' src="img/delete.png">
							        	</a>
							        </td>
							      </tr>
							    <%} %>
							    </tbody>
							  </table>
  						</div>
					</div>
				</div>
				<%} %>
				</div>
				
				
			</form>
		</div>
	</div>
</body>

</html>

