<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="../css/style3.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        					<li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      					</ul>
    				</div>
  				</div>
			</nav>
   <div id="main">
   
		<h3>EMPLOYEE DETAILS:</h3><br>
<%-- ${employee}
${departmentList}    <!-- getting departmentList -->
${skillsList }		 <!-- getting skillsList --> --%>

		<h3>UPDATE DETAILS</h3>
		<form name="update" method="post" action="UpdateController">
			<input type="hidden" name="empId" value="${employee.empId}">
			<input type="hidden" name="addressId" value="${employee.address.addressId}">
			<table align="center">
				<tr>
					<td><label>Enter Employee Name:</label></td> 
					<td><input class="textbox" type="text" name="empName" value="${employee.empName} "></td>	
				</tr>
				<tr>
					<td><label>Enter Employee Salary:</label></td>
					<td><input class="textbox" type="text" name="empSalary" value="${employee.empSalary}"></td>
				</tr>
				<tr>
					<td><label>Select Department:</label><select class="dropdown" name="department.departmentId">
						<c:forEach items="${departmentList }" var="department">
						<%-- <option value="${department.departmentId }">${department.departmentName}</option> --%>
								<c:if test="${employee.department.departmentName eq department.departmentName}">
									<option value="${department.departmentId }" selected="selected">${department.departmentName}</option>
								</c:if>
								<c:if test="${employee.department.departmentName ne department.departmentName}">
									<option value="${department.departmentId }">${department.departmentName}</option>
								</c:if>
						</c:forEach>	
						</select></td>
				</tr>
				<tr>
					<td><label>Enter Street</label></td>
					<td><input class="textbox" type="text" name="address.street" value="${employee.address.street}"></td>
				</tr>
				<tr>
					<td><label>Enter City</label></td>
					<td><input class="textbox" type="text" name="address.city" value="${employee.address.city }"></td>
				</tr>
				<tr>
					<td><label>Enter State</label></td>
					<td><input class="textbox" type="text" name="address.state" value="${employee.address.state }"></td>
				</tr>
				<tr>
					<td><label>Enter Country</label></td>
					<td><input class="textbox" type="text" name="address.country" value="${employee.address.country }"></td>
				</tr>
				<tr>

					<td><label>Select Skills:</label>
						<c:forEach items="${ skillList }" var="skills">
		
							<c:set var="isChecked" scope="session" value="${5 }"/>
							<c:forEach items="${employee.skillsList }" var="empSkill">
							<c:if test="${skills.skillName eq empSkill.skillName }">
								<c:set var="isChecked" value="${10 }"/>
							</c:if>
			
							</c:forEach>
		
							<input type="checkbox"  name="skills" value="${skills.skillId }"<c:if test="${isChecked eq 10 }">checked="checked" </c:if>>${skills.skillName }
		
		
 							<c:set var="isChecked" value="${15 }"/>
 		
						</c:forEach>
					</td></tr>
					<tr>
						<td><input class="btn" type="submit" name="submit" value="UPDATE"></td>
					</tr>	
			</table>
   		</form>
   </div>
   
   </div>

   <div id="footer">
   
   Copyright &copy; payroll.com
   </div>
</div>


</body>
</html>

<!-- 

			Select Department    : 	<br><select name="departmentId">
			<option value="10">HR</option>
			<option value="20">FIN</option>
			<option value="30">TR</option>
			<option value="40">DEV</option>
			</select><br> 


			Select Skills:<br>
			<input type="checkbox" name="skills" value="1">java
			<input type="checkbox" name="skills" value="2">.net
			<input type="checkbox" name="skills" value="3">php
			<input type="checkbox" name="skills" value="4">cpp
			<input type="checkbox" name="skills" value="5">html<br>
			<input type="submit" name="submit" value="REGISTER">
 		-->