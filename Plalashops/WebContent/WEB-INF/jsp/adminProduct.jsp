<!DOCTYPE html>
<html lang="th">
<head>
  <title>PlalaShops</title>
    <meta charset="utf-8">
 <%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
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
function insertProduct(){
	document.getElementById("addProductsAction").action = 'adminAddProductsAction.html';
	document.getElementById("addProductsAction").submit();
}
</script>
<title>Product Admin</title>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	<%
		List<ProductType> ProuctTypeList = (List<ProductType>) request.getAttribute("productTypeList");
		List<Product> productList = (List<Product>) request.getAttribute("productList");
		String success = (String) request.getAttribute("success");
		String fail = (String) request.getAttribute("Fail");
		String action = (String) request.getAttribute("action");
		
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		
	%>
	<br />
	<div id='productTypePage' align="center">
		<div class="container" align="center">
			<% if(success != null && success.length() > 0){ %>
				<div class="alert alert-success" align="left" style="width: 40%">
				  <strong><%= success %></strong>
				</div>
			<%} %>
			<% if(fail != null && fail.length() > 0){ %>
				<div class="alert alert-danger" style="width: 40%">
				  <strong> <%= fail %></strong>
				</div>
			<%} %>
			<form id='product' method="post" action="adminSearchProducts.html">
			 <div class="panel panel-default" style="width: 98%">
   	 			<div class="panel-heading" align="left">Product Search</div>
   	 			<br/>
   	 			<br/>
					<div class="form-group" >
						<div class="row">
						    <div class="col-sm-6">
						      	<label for="producrType">Product Type</label>
							      <select class="form-control" id="producrType" name="producrType" style="width: 80%">
							      <option value="">All</option>
							      	<% for(ProductType productType :ProuctTypeList){ %>
							        	<option value="<%=productType.getProductTypeName() %>"><%=productType.getProductTypeName() %></option>
							       <% } %>
							      </select>
						    </div>
						    <div class="col-sm-6">
						      	<label for="productNo">Product No</label>
								<input  type="text" class="form-control" id="productNo" placeholder="Product No" style="width: 80%" name="productNo">
						    </div>
						    <div class="col-sm-6">
						      <label for="productName">Product Name</label>
							  <input  type="text" class="form-control" id="productName" placeholder="Product Name" style="width: 80%" name="productName">
						    </div>
						    <div class="col-sm-6">
						    	<label for="sex">sex</label>
							      <select class="form-control" id="sex" name="sex" style="width: 80%">
							      	<option value="">All</option>
						        	<option value="Man">ชาย</option>
						        	<option value="Woman">หญิง</option>
							      </select>
						    </div>
						</div>
						<br/>
						<button type="submit" class="btn btn-info">Search</button>
						<a href="/Plalashops/adminShopAddProducts.html" <button type="button" class="btn btn-info">Add Product</button></a>
				</div>
				</form>
				<% if("addProduct".equals(action)){ %>
				<div class="panel panel-default" style="width: 80%"">
					<div class="panel-heading" align="left">Add Product</div>
					<br/>	
					<br/>
					<form id='addProductsAction' method="post">
						<div class="form-group" align="center">
							<div class="table-responsive">
								<div class="col-sm-6">
							      	<label for="producrType">Product Type</label>
								      <select class="form-control" id="producrType" name="producrType" style="width: 90%">
								        <% for(ProductType productType :ProuctTypeList){ %>
							        		<option><%=productType.getProductTypeName() %></option>
							      		<% } %>
								      </select>
							    </div>
								<div class="col-sm-6">
							      <label for="productName">Product Name</label>
								  <input  type="text" class="form-control" id="productName" placeholder="Product Name" style="width: 90%" name="productName">
							    </div>
							     <div class="col-sm-6">
							      <label for="price">Price</label>
								  <input  type="number" class="form-control" id="price" placeholder="price" style="width: 90%" name="price">
							    </div>
							    <div class="col-sm-6">
							      <label for="salePrice">Sale Price</label>
								  <input  type="number" class="form-control" id="salePrice" placeholder="Sale Price" style="width: 90%" name="salePrice">
							    </div>
								<div class="col-sm-6">
							    	<label for="sex">sex</label>
							      <select class="form-control" id="sex" name="sex" style="width: 90%">
							      	<option value="">All</option>
						        	<option value="Man">ชาย</option>
						        	<option value="Woman">หญิง</option>
							      </select>
							    </div>
							    
							     <div class="col-sm-12">
							    	<div class="form-group">
									  <label for="description">Description</label>
									  <textarea class="form-control" rows="5" id="description" name="description" style="width: 90%"></textarea>
									</div>
							    </div>
							    <br>
							</div>
							   <button type="button" onclick="insertProduct()" class="btn btn-info">Add Product</button>
							   <button type="button" class="btn btn-info">Cancel</button>
						</div>
					</form>
				</div>
				<%}
				if("searchProduct".equals(action)){
				%>
				
				<div class="panel panel-default" style="width: 95%">
					<div class="panel-heading" align="left">Product result list</div>
					<br/>	
					<div class="form-group" align="center">
						<div class="table-responsive">
							  <table class="table" style="width: 95%" border="0">
							    <thead>
							      <tr>
							        <th>No</th>
							        <th>Product No</th>
							        <th>Product Type</th>
							        <th>Product Name</th>
							        <th>Sex</th>
							        <th>Description</th>
							        <th>Edit</th>
							        <th>Delete</th>
							      </tr>
							    </thead>
							    <tbody>
							    <% for(int i = 0 ; i < productList.size() ; i++){ %>
							      <tr>
							        <td><%= i+1 %></td>
							        <td><%= productList.get(i).getProductNo() %></td>
							        <td><%= productList.get(i).getProductType() %></td>
							        <td><%= productList.get(i).getProductName() %></td>
							        <td><%= productList.get(i).getSex() == null || "".equals(productList.get(i).getSex()) ? "All" : productList.get(i).getSex() %></td>
							        <td><textarea readonly="readonly" class="form-control" rows="3" id="description" name="description" style="width: 100%"><%= productList.get(i).getDescription() %></textarea></td>
							         
							        <td>
							        	<a href="adminShopEditProducts.html?productId=<%=productList.get(i).getProductId() %>">
							        		<img style="width: 25px;height: 25px" id='del<%=i %>' src="img/edit.png">
							        	</a>
							        </td>
							        <td>
							        	<a href="adminDeleteProducts.html?productId=<%=productList.get(i).getProductId() %>">
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
			<% 	} 
				if("editProduct".equals(action)){ 
					Product product = (Product) request.getAttribute("editProduct");
			%>
				<div class="panel panel-default" style="width: 80%"">
					<div class="panel-heading" align="left">edit Product</div>
					<br/>	
					<br/>
					<form id='editProductsAction' method="post">
						<input type="hidden" name="productNo" value="<%= product.getProductNo() %>">
						<div class="form-group" align="center">
							<div class="table-responsive">
								<div class="col-sm-6">
							      	<label for="producrType">Product Type</label>
								      <select class="form-control" id="editProducrType" name="producrType" style="width: 90%">
								        <% for(ProductType productType :ProuctTypeList){ %>
							        		<option><%=productType.getProductTypeName() %></option>
							      		<% } %>
								      </select>
								      
							    </div>
								<div class="col-sm-6">
							      <label for="productName">Product Name</label>
								  <input  type="text" class="form-control" id="productName" placeholder="Product Name" style="width: 90%" name="productName" value="<%= product.getProductName() %>">
							    </div>
							     <div class="col-sm-6">
							      <label for="price">Price</label>
								  <input  type="number" class="form-control" id="price" placeholder="price" style="width: 90%" name="price" value="<%= product.getPrice() %>">
							    </div>
							    <div class="col-sm-6">
							      <label for="salePrice">Sale Price</label>
								  <input  type="number" class="form-control" id="salePrice" placeholder="Sale Price" style="width: 90%" name="salePrice" value="<%= product.getSalePrice() %>">
							    </div>
								<div class="col-sm-6">
							    	<label for="sex">sex</label>
							      <select class="form-control" id="editSex" name="sex" style="width: 90%">
							      	<option value="">All</option>
						        	<option value="Man">ชาย</option>
						        	<option value="Woman">หญิง</option>
							      </select>
							    </div>
							     <div class="col-sm-12">
							    	<div class="form-group">
									  <label for="description">Description</label>
									  <textarea class="form-control" rows="5" id="editDescription" name="description" style="width: 90%" ><%= product.getDescription() %></textarea>
									</div>
							    </div>
							    <br>
							</div>
							   <button type="submit" class="btn btn-info">Edit Product</button>
							   <button type="button" class="btn btn-info">Cancel</button>
						</div>
						<script type="text/javascript">
					      	document.getElementById('editProducrType').value = '<%= product.getProductType() %>';
					      	document.getElementById('editSex').value = '<%= product.getSex().equals("")? "All" : product.getSex() %>';
					      </script>
					</form>
				</div>
				<%} %>
		</div>
	</div>

</body>
</html>