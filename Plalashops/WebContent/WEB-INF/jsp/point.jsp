<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<%@ page import="java.io.*" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap-social.css">
<link rel="stylesheet" href="bootstrap/css/plalashop.css">
<link rel="stylesheet" href="bootstrap/css/font-awesome.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function jsSendForm(page) {
		document.forms["jsSendForm"].action = page;
		document.forms["jsSendForm"].submit();
	}
</script>


</style>

</head>
<detailLabel2>
	<%
		List<Product> promotionProductList = (List<Product>) request.getAttribute("promotionProductList");
	%>
	<form id="pointForm" action="pointProduct.html">
		<input type="hidden" id="productId" name="productId" >
	</form>
	<div id="main" class="container" style="width: 95%;">
		<div class="form-group">
			<div class="row" align="center">
				<table border="0">
					<%for(Product product : promotionProductList){ %>
						<tr>
							<td style="width: 30%;" align="right"><%= product.getSalePrice().intValue() / 10 %> P = &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td style="width: 70%;">
								<div onclick="jsSendFormPoint('<%= product.getProductId() %>')">
									<img style="width: 65%;height: 50%;" src="<%= product.getFileImage() %>">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					<%} %>
				</table>
			</div>
		</div>
	</div>
	
</detailLabel2>
<script type="text/javascript">
	function  jsSendFormPoint(idProduct){
		document.getElementById('productId').value = idProduct;
		document.forms["pointForm"].submit();
	}
</script>
</html>

