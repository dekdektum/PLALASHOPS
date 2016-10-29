<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="jquery-mobile/css/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="jquery-mobile/css/jquery-mobile.css">
<script src="jquery-mobile/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="jquery-mobile/js/jquery.mobile-1.4.5.min.js" type="text/javascript"></script>
</head>
<body>
	<!-------------- First page for form ----------->
	<div data-role="page" >
		<!-------------- First page header ----------->
		<div data-role="header">
			<h1>jQuery Mobile Form</h1>
		</div>
		<!-------------- First page main content ----------->
		<div data-role="main" class="ui-content">
			<div data-role="navbar">
				<ul>
					<li><a href="#">Home</a></li>
					<li><a href="#">Page Two</a></li>
					<li><a href="#">Search</a></li>
				</ul>
			</div>

			<form method="post" action="#pageone" data-ajax="false">
				<label for="name">Name : <span>*</span></label> 
				<input type="text" name="name" id="name" placeholder="Name"> 
				<label for="email">Email: <span>*</span></label> 
				<input type="email" id="email" name="email" placeholder="Email" />
				<fieldset data-role="controlgroup">
					<legend>Gender:</legend>
					<label for="male">Male</label> <input type="radio" name="gender"
						id="male" value="male"> <label for="female">Female</label>
					<input type="radio" name="gender" id="female" value="female">
				</fieldset>
				<fieldset data-role="controlgroup">
					<legend>Qualification:</legend>
					<label for="graduation">Graduation</label> <input type="checkbox"
						id="graduation" value="graduation"> <label
						for="postgraduation">Post Graduation</label> <input
						type="checkbox" id="postgraduation" value="postgraduation">
					<label for="other">Other</label> <input type="checkbox" id="other"
						value="other">
				</fieldset>
				<label for="info">Message:</label>
				<textarea name="addinfo" id="info" placeholder="Message"></textarea>
				<input type="submit" data-inline="true" value="Submit"
					data-theme="b">
			</form>
			<table data-role="table" data-mode="columntoggle"
				class="ui-responsive" id="myTable">
				<thead>
					<tr>
						<th data-priority="6">CustomerID</th>
						<th>CustomerName</th>
						<th data-priority="1">ContactName</th>
						<th data-priority="2">Address</th>
						<th data-priority="3">City</th>
						<th data-priority="4">PostalCode</th>
						<th data-priority="5">Country</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Alfreds Futterkiste</td>
						<td>Maria Anders</td>
						<td>Obere Str. 57</td>
						<td>Berlin</td>
						<td>12209</td>
						<td>Germany</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Antonio Moreno Taquería</td>
						<td>Antonio Moreno</td>
						<td>Mataderos 2312</td>
						<td>México D.F.</td>
						<td>05023</td>
						<td>Mexico</td>
					</tr>
					<tr>
						<td>3</td>
						<td>Around the Horn</td>
						<td>Thomas Hardy</td>
						<td>120 Hanover Sq.</td>
						<td>London</td>
						<td>WA1 1DP</td>
						<td>UK</td>
					</tr>
					<tr>
						<td>4</td>
						<td>Berglunds snabbköp</td>
						<td>Christina Berglund</td>
						<td>Berguvsvägen 8</td>
						<td>Luleå</td>
						<td>S-958 22</td>
						<td>Sweden</td>
					</tr>

				</tbody>
			</table>

			<fieldset class="ui-field-contain">
				<label for="day">Select Day</label> <select name="day" id="day">
					<option value="mon">Monday</option>
					<option value="tue">Tuesday</option>
					<option value="wed">Wednesday</option>
					<option value="thu">Thursday</option>
					<option value="fri">Friday</option>
					<option value="sat">Saturday</option>
					<option value="sun">Sunday</option>
				</select>
			</fieldset>
		</div>

		<!-------------------------------------------------------------
End of First page
-------------------------------------------------------------->
		<!-------------- Second page ----------->
		<div data-role="page" id="pageone">
			<!-------------- Second page header ----------->
			<div data-role="header">
				<h1>JQuery Mobile Form</h1>
			</div>
			<!-------------- Second page main content ----------->
			<div data-role="main" class="ui-content">
				<h2>Form Submitted Successfully...!!</h2>
			</div>
		</div>
		<!-------------------------------------------------------------
End of Second page
-------------------------------------------------------------->
</body>
</html>