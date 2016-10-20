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
	function insertGroupProduct(){
		document.getElementById("GroupProduct").action = 'insertGroupProduct.html';
		document.getElementById("GroupProduct").submit();
	}
	function getGroupProductList(){
		document.getElementById("GroupProduct").action = 'getGroupProductList.html';
		document.getElementById("GroupProduct").submit();
	}
</script>
</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	<% 
		List<GroupProduct> ProuctTypeList = (List<GroupProduct>)request.getAttribute("GroupProductList"); 
	 	String success = (String)request.getAttribute("success"); 
	 	String fail = (String)request.getAttribute("Fail"); 
	 	String action = (String)request.getAttribute("action"); 
	 	GroupProduct GroupProduct = (GroupProduct)request.getAttribute("GroupProduct"); 
	%>
 	<br/>
	<div  align="center">
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
			<form id='GroupProduct' method="post">
			 <div class="panel panel-default" style="width: 80%">
   	 			<div class="panel-heading" align="left">Product Type Search</div>
					<div class="form-group" align="center">
						<br/>
						<table border="0">
							<tr>
								<td align="right" width="20%"> <label for="groupProduct">Group Product :&nbsp;&nbsp;</label></td>
								<td width="50%"><input  type="text" class="form-control" id="groupProduct" placeholder="Group Product" style="width: 250px" name="groupProduct"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<button type="button" class="btn btn-default" onclick="getGroupProduct()">Search</button>
									<button type="button" class="btn btn-default" onclick="insertGroupProduct()">Add Product Type</button>
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
							        <th>Add Image</th>
							        <th>Delete</th>
							      </tr>
							    </thead>
							    <tbody>
							    <% for(int i = 0 ; i < ProuctTypeList.size() ; i++){ %>
							      <tr>
							        <td><%= i+1 %></td>
							        <td><%= ProuctTypeList.get(i).getGroupProductName() %></td>
							        <td><% if(ProuctTypeList.get(i).getFileName() != null && !"data:image/jpeg;base64,null".equals(ProuctTypeList.get(i).getFileName())){ %>
					    					<a href="addImageProduct.html?id=<%= ProuctTypeList.get(i).getGroupProductId() %>"><img style="width: 200px" src='<%= ProuctTypeList.get(i).getFileName() %>'></a>
							    		<%}else{%>
							    			<a href="addImageProduct.html?id=<%= ProuctTypeList.get(i).getGroupProductId() %>"><button type="button" class="btn btn-info">Add Product Type</button></a>
							    		<% } %>
							    	</td>
							        <td>
							        	<a href="deleteGroupProduct.html?GroupProduct=<%=ProuctTypeList.get(i).getGroupProductId() %>">
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
			
			<%if("uploadItem".equals(action)){%>
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
							      </tr>
							    </thead>
							    <tbody>
							      <tr>
							        <td>1</td>
							        <td><%= GroupProduct.getGroupProductName() %></td>
							      </tr>
							    </tbody>
							  </table>
							  
							  <form action="uploadFileGroupProduct.html" method="post" enctype="multipart/form-data">
									<input type="file" name="file" >
									<button type="submit" class="btn btn-info">Upload Image</button>
								</form>
  						</div>
					</div>
				</div>
				<%}%>
		</div>
	</div>
</body>

</html>

