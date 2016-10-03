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

</head>
<body>
	<jsp:include page="adminMenu.jsp" />
	<% 
		List<Advertise> advertiseList = (List<Advertise>)request.getAttribute("advertiseList"); 
	 	String fail = (String)request.getAttribute("Fail"); 
	 	String success = (String)request.getAttribute("success"); 
	 	Advertise advertise = (Advertise)request.getAttribute("advertise"); 
	 	String action = (String)request.getAttribute("action"); 
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
			<%}
			if(!"edit".equals(action) && (advertiseList != null && advertiseList.size() < 5)){%>
			<div class="panel panel-default" style="width: 80%">
				<div class="panel-heading" align="left">Add Advertise</div>
				<form id='addAdvertise' action="addAdvertise.html" method="post">
					<div class="form-group" >
						<div class="row">
						    <div class="col-sm-6">
						    	<label for="advertiseOwner">Advertise Owner</label>
								<input  type="text"  class="form-control" id="advertiseOwner" placeholder="Advertise Owner" style="width: 80%" name="advertiseOwner">
						    </div>
						    <div class="col-sm-6">
						    	<label for="linkAdvertise">Link Advertise</label>
								<input  type="text"  class="form-control" id="linkAdvertise" placeholder="Link Advertise" style="width: 80%" name="linkAdvertise">
						    </div>
						    <div class="col-sm-6">
						    	<label for="queue">Queue</label>
								<input  type="Number"  class="form-control" id="queue" placeholder="Queue" style="width: 80%" name="queue">
						    </div>
						    
						   
						</div>
					<br>
					<button type="submit" class="btn btn-info">Add Advertise</button>
					</div>
				</form>
			</div>
			<% }
			if("edit".equals(action)){ %>
			<div class="panel panel-default" style="width: 80%">
				<div class="panel-heading" align="left">Edit Advertise</div>
				<form id='editAdvertiseAction' action="editAdvertiseAction.html" method="post">
					<input type="hidden" name="id" value="<%= advertise.getAdvertiseId()%>">
					<div class="form-group" >
						<div class="row">
						    <div class="col-sm-6">
						    	<label for="advertiseOwner">Advertise Owner</label>
								<input  type="text" value="<%= advertise.getAdvertiseOwner() %>" class="form-control"  id="advertiseOwner" placeholder="Advertise Owner" style="width: 80%" name="advertiseOwner">
						    </div>
						    <div class="col-sm-6">
						    	<label for="linkAdvertise">Link Advertise</label>
								<input  type="text" value="<%= advertise.getLinkAdvertise() %>" class="form-control" id="linkAdvertise" placeholder="Link Advertise" style="width: 80%" name="linkAdvertise">
						    </div>
						    <div class="col-sm-6">
						    	<label for="queue">Queue</label>
								<input  type="Number" value="<%= advertise.getQueue() %>" class="form-control" id="queue" placeholder="Queue" style="width: 80%" name="queue">
						    </div>
						</div>
						<br>
						    <button type="submit" class="btn btn-info">Edit Advertise</button>
					</div>
				</form>
			</div>
			<%}else if("uploadItem".equals(action)){%>
			<div class="panel panel-default" style="width: 80%">
				<div class="panel-heading" align="left">Edit Advertise</div>
			<table class="table" style="width: 95%" border="0">
				<thead>
			      <tr>
			        <th>Advertise Owner</th>
			        <th>Link Advertise</th>
			        <th>Queue</th>
			      </tr>
			    </thead>
			    <tbody>
				    <tr>
				    	<td><%= advertise.getAdvertiseOwner() %></td>
				    	<td><%= advertise.getLinkAdvertise() %></td>
				    	<td><%= advertise.getQueue() %></td>
				    </tr>
			    </tbody>
			</table>
			<br>
			<form action="uploadFileAdvertise.html" method="post" enctype="multipart/form-data">
				<input type="file" name="file" >
				<button type="submit" class="btn btn-info">Upload Image</button>
			</form>
			<br>
			</div>
			
		<% }else{ %>
			<div class="panel panel-default" style="width: 80%">
				<div class="panel-heading" align="left">View Advertise</div>
				<div class="table-responsive">
				<table class="table" style="width: 95%" border="0">
					<thead>
				      <tr>
				        <th>No</th>
				        <th>Advertise Owner</th>
				        <th>Link Advertise</th>
				        <th>Image</th>
				        <th>Edit</th>
				        <th>Delete</th>
				      </tr>
				    </thead>
				    <tbody>
				    <% for(int i = 0 ; i < advertiseList.size() ; i++){ %>
					    <tr>
					    	<td><%= i+1 %></td>
					    	<td><%= advertiseList.get(i).getAdvertiseOwner() %></td>
					    	<td><%= advertiseList.get(i).getLinkAdvertise() %></td>
					    	<td><% if(advertiseList.get(i).getFileName() != null && !"data:image/jpeg;base64,null".equals(advertiseList.get(i).getFileName())){ %>
					    			<a href="addImageAdvertise.html?id=<%= advertiseList.get(i).getAdvertiseId() %>"><img style="width: 200px" src='<%= advertiseList.get(i).getFileName() %>'></a>
					    		<%}else{%>
					    			<a href="addImageAdvertise.html?id=<%= advertiseList.get(i).getAdvertiseId() %>"><button type="submit" class="btn btn-info">Add Advertise</button></a>
					    		<% } %>
					    	</td>
					    	<td>
					        	<a href="editAdvertise.html?id=<%= advertiseList.get(i).getAdvertiseId() %>">
					        		<img style="width: 25px;height: 25px" id='del<%=i %>' src="img/edit.png">
					        	</a>
					        </td>
					        <td>
					        	<a href="deleteAdvertise.html?id=<%= advertiseList.get(i).getAdvertiseId() %>">
					        		<img style="width: 25px;height: 25px" id='del<%=i %>' src="img/delete.png">
					        	</a>
					        </td>
					    </tr>
				    <%} %>
				    </tbody>
				</table>
				<br>
				<br>
				<br>
				</div>
			</div>
			<%} %>
		</div>
	</div>
</body>

</html>

