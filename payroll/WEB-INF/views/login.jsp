<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	
<link rel="stylesheet" href="../css/style3.css" type="text/css">
<!-- <script type="text/javascript" src="js/validate.js">
</script> -->
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="container" >
		<!-- <div id="p1" onclick="print1();">HELLO WORLD!!</div> -->
		<div id="header">
			<h3><b>Payroll Management System</h3>
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

				<div id="error">
					<c:out value="${error }"></c:out>
				</div>
				<form name="login" method="post" action="LoginController">  <!-- onsubmit="return hello();" -->

					<table align="center">
						<tr>
							<!-- <td><label>Enter UserName</label></td> -->
							<td class="td1"><input class="textbox iconuser" type="text" name="userName" required="required" placeholder="Enter UserName"><br></td>
						</tr>
						<tr>
							<!-- <td><label>Enter Password</label></td> -->
							<td class="td1"><input class="textbox iconpass" type="password" name="password"
								required="required" placeholder="Enter Password"><br></td>
						</tr>
						<tr>
							<td colspan="2" class="td1"><input class="btn1" type="submit" name="submit" 
								value="LOGIN"></td>
						</tr>
					</table>
				</form>

			</div>

		</div>

		<div id="footer">Copyright &copy; payroll.com</div>



	</div>

</body>
</html>