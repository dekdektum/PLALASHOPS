<!DOCTYPE html>
<html lang="en">
<head>
 <%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="plalashop.domain.*" %>
<%@ page import="java.io.*" %>
  <title>Bootstrap Example</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0">
  	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap/css/plalashop.css">
  	<script src="bootstrap/js/jquery.min.js"></script>
  	<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function jsSendForm(page) {
		document.forms["jsSendForm"].action = page;
		document.forms["jsSendForm"].submit();
	}
</script>
<style type="text/css">
html {
  height: 100%;
  width: 100%;
}
html body {
  height: 100%;
  width: 100%;
  overflow: hidden;
}
html body .container-fluid.body-content {
  position: absolute;
  top: 50px;
  bottom: 30px;
  right: 0;
  left: 0;
  overflow-y: auto; 
  padding-left: 0px;
  padding-right: 0px;
}
header {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    height: 50px;
}
footer {
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: black;
    height: 65px;
}

#custom-search-input {
        margin:0;
        margin-top: 10px;
        padding: 0;
    }
 
    #custom-search-input .search-query {
        padding-right: 3px;
        padding-right: 4px \9;
        padding-left: 3px;
        padding-left: 4px \9;
        /* IE7-8 doesn't have border-radius, so don't indent the padding */
 
        margin-bottom: 0;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
 
    #custom-search-input button {
        border: 0;
        background: none;
        /** belows styles are working good */
        padding: 2px 5px;
        margin-top: 2px;
        position: relative;
        left: -28px;
        /* IE7-8 doesn't have border-radius, so don't indent the padding */
        margin-bottom: 0;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        color:#D9230F;
    }
 
    .search-query:focus + button {
        z-index: 3;   
    }

</style>
</head>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<header>
	<%
		String actionPage = (String) request.getAttribute("actionPage");
		List<Advertise> advertiseList = (List<Advertise>) request.getAttribute("advertiseList");
		List<Product> productList = (List<Product>) request.getAttribute("productList");
	
	%>
		  <div align="center">
		  	<table border="0" style="width: 100%">
		  <tr>
		  	<td width="25%" align="center">
	            <div id="custom-search-input">
				  		<input type="text" class="form-control" style="width:80%;border-radius: 10em 10em 10em 10em;" readonly="readonly" >
				</div>
			</td>
			<td colspan="3" align="center">
	          	<div id="custom-search-input" align="center">
	              <div class="input-group col-xs-12">
	                  <input type="search" class="search-query form-control" placeholder="Search" style="border-radius: 10em 10em 10em 10em;" />
	                  <span class="input-group-btn" >
	                      <button class="btn btn-danger" type="button" style="margin-right: -10px">
	                          <img src="img/search.png" width="15px"/>
	                      </button>
	                  </span>
	              </div>
	            </div>
	          </td>
			<td width="10%" align="left"> 
			      <div id="custom-search-input" style="margin-right: 25px">
			       <a href="#">
          			<img src="img/shopping-cart2.png" width="30px"/>
        		  </a>
			  </div>
			  </td>
		  </tr>
		  </table>
		  </div>
	</header>
<body>
<div class="container-fluid body-content">
	<% if(actionPage.indexOf("Home")>= 0){ %>
		<jsp:include page="home.jsp" />
	<% }else if(actionPage.indexOf("Group")>= 0){%>
		<jsp:include page="group.jsp" />
	<% }else if(actionPage.indexOf("Shop")>= 0){%>
		<jsp:include page="shops.jsp" />
	<% }else if(actionPage.indexOf("Item")>= 0){%>
		<jsp:include page="item.jsp" />
	<% }else if(actionPage.indexOf("ShowDetail")>= 0){%>
		<jsp:include page="detail.jsp" />
	<% } %>
</body>
</div>
<footer>
	<div style="width: 100%" >
		<div class="col-xs-3" align="center">
		      <div id="custom-search-input">
			       <div>
          			<span onclick="jsSendForm('appHome.html')">
          				<img src="img/icon-home<%=actionPage.indexOf("Home") >= 0  ? "2" : "1" %>.png" height="50px">
          			</span>
        		  </div>
			  </div>
		</div>
		<div class="col-xs-3" align="center">
		      <div id="custom-search-input" >
			       <div>
          			<span onclick="jsSendForm('appGroup.html')">
          				<img src="img/icon-shop<%=actionPage.indexOf("Shop") >= 0  ? "2" : "1" %>.png" height="50px">
          			</span>
        		  </div>
			  </div>		
		</div>
		<div class="col-xs-3" align="center">
		      <div id="custom-search-input" >
			       <div href="#" style="color: white;" >
          			<span>
          				<img src="img/icon-pv<%=actionPage.indexOf("PV") >= 0  ? "2" : "1" %>.png" height="50px">
          			</span>
        		  </div>
			  </div>		
		</div>
		<div class="col-xs-3" align="center">
		      <div id="custom-search-input">
			       <div href="#" style="color: white;">
          			<span>
          				<img src="img/icon-me<%=actionPage.indexOf("PV") >= 0  ? "2" : "1" %>.png" height="50px">
          			</span>
        		  </div>
			  </div>		
		</div>
	</div>
	<form id="jsSendForm" method="post">
	</form>
</footer>