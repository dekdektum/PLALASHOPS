<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  
   <%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<%@ page import="java.io.*" %>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0, user-scalable=no ">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  .padding-0 {
    padding: 0px;
      height: 100%;
 overflow:hidden;
}
  </style>
  <script type="text/javascript">
  	function selectProductType(productType){
  		document.getElementById('productType').value = productType;
		document.forms["jsSendFormShop"].submit();
  	}
  </script>
</head>
<body id='bg' background="img/bg.jpg">
<script type="text/javascript">
	document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>
      
<%
	String actionPage = (String) request.getAttribute("actionPage");
	List<ProductType> productTypeList = (List<ProductType>) request.getAttribute("productTypeList");

%>
<div class="container " style="width: 100%">
		<div class="form-group" align="center">
			<div class="row">
			<br> <br>
				    <br>
				    <% for(ProductType productType :productTypeList){ %>
					 <div class="col-lg-3 col-md-4 col-xs-6 thumb  padding-0">
					 	<div onclick="selectProductType('<%=productType.getProductTypeName() %>')" class="thumbnail" style="margin-bottom: 0px">
					 	<%= productType.getProductTypeName() %>
		   			     	<img class="img-rounded" src="<%=productType.getFileName() %>"/>
		   			     </div>
		            </div>
		            <%} %>
					
			
			</div>
		</div>
</div>
<form id="jsSendFormShop" method="post" action="gotoProductItem.html">
	<input type="hidden" id="productType" name="productType">
</form>

</body>
</html>


