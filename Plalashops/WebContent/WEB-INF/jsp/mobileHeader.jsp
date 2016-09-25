<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1,initial-scale=1.0, user-scalable=no">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<!--   <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
  
<!--   <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"> -->
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
html {
  height: 100%;
}
html body {
  height: 100%;
  overflow: hidden;
}
html body .container-fluid.body-content {
  position: absolute;
  top: 50px;
  bottom: 30px;
  right: 0;
  left: 0;
  overflow-y: auto;
}
header {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    background-color: #1C6785;
    height: 50px;
}
footer {
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #1D5462;
    height: 50px;
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
<body>
 <div id="fullPage">
 	<header>
		  <div class ="row">
	       	  <div class="col-xs-3">
	            <div id="custom-search-input">
				  		<input type="text" class="form-control" style="width:90%" readonly="readonly" >
				</div>
			  </div>
			  <div class="col-xs-6">
			
	          <div id="custom-search-input">
	              <div class="input-group col-xs-12">
	                  <input type="search" class="search-query form-control" placeholder="Search" />
	                  <span class="input-group-btn">
	                      <button class="btn btn-danger" type="button">
	                          <span class=" glyphicon glyphicon-search"></span>
	                      </button>
	                  </span>
	              </div>
	            </div>
	           
			    </div>   
			  <div class="col-xs-3">
			      <div id="custom-search-input">
			       <a href="#" class="btn btn-primary ">
          			<span class="glyphicon glyphicon-shopping-cart"></span>
        		  </a>
			     </div>
			  </div>
		  </div>
	</header>
	<div class="container-fluid body-content">
		กลาง
	<div>
<footer>
		<div class="col-xs-3">
		      <div id="custom-search-input">
			       <a href="#" class="btn btn-primary">
          			<span class="glyphicon glyphicon-home"></span>
        		  </a>
			  </div>
		</div>
		<div class="col-xs-3">
		      <div id="custom-search-input">
			       <a href="#" class="btn btn-primary ">
          			<span class="glyphicon glyphicon-briefcase"></span>
        		  </a>
			  </div>		
		</div>
		<div class="col-xs-3">
		      <div id="custom-search-input">
			       <a href="#" class="btn btn-primary ">
          			<span class="glyphicon glyphicon-bitcoin"></span>
        		  </a>
			  </div>		
		</div>
		<div class="col-xs-3">
		      <div id="custom-search-input">
			       <a href="#" class="btn btn-primary ">
          			<span class="glyphicon glyphicon-user"></span>
        		  </a>
			  </div>		
		</div>
</footer>
</div>
<script type="text/javascript"> document.getElementById('fullPage').style.width = screen.width;</script>

</body>
</html>