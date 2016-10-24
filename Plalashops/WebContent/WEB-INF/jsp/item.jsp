<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
   <%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<%@ page import="java.io.*" %>
<meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0, user-scalable=no ">
<link rel="stylesheet" href=bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="bootstrap/js/jssor.slider-21.1.6.mini.js" type="text/javascript"></script>
<script type="text/javascript">
  
  </script>

<style type="text/css">
  .padding-0 {
    padding: 0px;
      height: 100%;
 overflow:hidden;
}

.form-horizontal .control-label.text-left {
	text-align: left;
}

div.party {
	width: 9px;
	margin: 4px 5px 0px 5px
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
	    $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
	});
	function jsSendFormItem(id){
		document.getElementById('id').value = id;
		document.forms["jsSendFormItem"].submit();
	}
</script>


</head>
<body>
<script type="text/javascript">
	document.getElementById('bg').style["background-size"] = screen.width+'px '+screen.height+'px';
</script>
<%
	String actionPage = (String) request.getAttribute("actionPage");
	List<Product> productList = (List<Product>) request.getAttribute("productList");
%>

    <div class="container">
    <div id="products" class="row list-group">
    <% if (productList != null && productList.size() > 0){ %>
        <div class="col-lg-12" align="center" style="padding-left: 0px;padding-right: 0px;"> <h3 style="background-color: #ffffff;margin-bottom: 0px;"><%= productList.get(0).getProductType() %></h3></div>
    
    <%} %>
    
    	<% for(Product product :productList){ %>
        <div class="col-lg-3 col-md-4 col-xs-6 thumb  padding-0" onclick="jsSendFormItem('<%=product.getProductId() %>')">
            <div class="thumbnail" style="width: 100%; margin-bottom: 0px;">
		   		<img class="img-rounded"  src="<%= product.getFileImage() %>"/>
                <div class="caption">
                    <h4 class="group inner list-group-item-heading"><%= product.getProductName() %></h4>
                    <div class="row">
                        <div class="col-sm-6 ">
                        <%if(product.getPrice() > 0 ){ %>
                            <p class="lead"><strike>฿<%= product.getPrice() %></strike> Dis. <B style="color: red;">฿<%= product.getSalePrice() %><br>ลดถึง  <%= product.getDisCount() %>%</B></p>
                        <%}else{ %>
                            <p class="lead"><br><B style="color: red;">฿<%= product.getSalePrice() %></B></p>
                        <%} %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      <% } %>  

    </div>
</div>

<form id="jsSendFormItem" method="post" action="showDetailItem.html">
	<input type="hidden" id="id" name="id">
</form>



</body></html>