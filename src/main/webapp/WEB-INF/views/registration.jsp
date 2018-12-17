<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/style3.css" type="text/css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div id="container">

   <div id="header" >
   <h3>Payroll Management System</h3>
   </div>
   <div id="content">
   
   <nav class="navbar navbar-inverse">
  				<div class="container-fluid">
   			 		<div class="navbar-header">
      					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        					<span class="icon-bar"></span>
        					<span class="icon-bar"></span>
        					<span class="icon-bar"></span>                        
      					</button>
      					<!-- <a class="navbar-brand" href="logout">Payroll Management System</a> -->
   			 		</div>
   					<div class="collapse navbar-collapse" id="myNavbar">
      					<ul class="nav navbar-nav">
        					<li class="active"><a href="home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
      					</ul>
      					<ul class="nav navbar-nav navbar-right">
        					<li><a href="register"><span class="glyphicon glyphicon-user"></span> Registration</a></li>
      					</ul>
    				</div>
  				</div>
			</nav>
   
   <div id="main">
   		<h4><b>EMPLOYEE DETAILS:</b></h4><br>
		<form name="register" method="post" action="RegistrationController">
		<table align="center">
			<tr>
				<td><label>Enter Employee Name:</label></td> 
				<td><input class="textbox" type="text" name="empName" required="required"></td>
			</tr>
			<tr>
				<td><label>Enter Employee Salary:</label></td>
				<td><input class="textbox" type="text" name="empSalary" required="required"></td>
			</tr>
			<tr>
				<td><label>Select Department</label></td>
				<td><select class="dropdown" name="department.departmentId">
					<option value="10">HR</option>
					<option value="20">FIN</option>
					<option value="30">TR</option>
					<option value="40">DEV</option>
					</select></td>
			</tr>
			
			<tr>
				<td><label>Enter Street</label></td>
				<td><input class="textbox" type="text" name="address.street" required="required"></td>
			</tr>
			<tr>	
				<td><label>Enter City</label></td>
				<td><input class="textbox" type="text" name="address.city" required="required"></td>
			</tr>
			<tr>
				<td><label>Enter State</label></td>
				<td><input class="textbox" type="text" name="address.state" required="required"></td>
			</tr>
			<tr>
				<td><label>Enter Country</label></td>
				<td><input class="textbox" type="text" name="address.country" required="required"></td>
			</tr>
			<tr>
				<td><label>Select Skills</label></td>
				<td><input type="checkbox" name="skills" value="1">java
					<input type="checkbox" name="skills" value="2">.net
					<input type="checkbox" name="skills" value="3">php
					<input type="checkbox" name="skills" value="4">cpp
					<input type="checkbox" name="skills" value="5">html</td>
			</tr>
			<tr>
				<td><input class="btn" type="submit" name="submit" value="REGISTER"></td>
			</tr>
		</table>
		</form>
   
   </div>

   <div id="footer">
   
   Copyright &copy; payroll.com
   </div>



</div>
</div>



</body>
</html>