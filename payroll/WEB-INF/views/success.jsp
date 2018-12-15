
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="../css/style3.css" type="text/css">
<meta charset="ISO-8859-1">
<title>Payroll Management System</title>
</head>
<body>

<div class="container">

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
        					<li>
        						<div class="row">
        						<div class="col-xl-2 col-lg-4 col-md-6 col-xs-12">
        						<a data-toggle="modal" data-target="#registeremp" style="text-decoration: none;"><span class="glyphicon glyphicon-user"></span> Registration</a>

								  <!-- Modal -->
								  <div class="modal fade" id="registeremp" role="dialog">
								    <div class="modal-dialog">
								    
								      <!-- Modal content-->
								      <div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								          <h4 class="modal-title">REGISTER EMPLOYEE</h4>
								        </div>
								        <div class="modal-body">
								          <p>
								          		<form name="register" method="post" action="RegistrationController">
													<input class="textbox" type="text" name="empName" required="required" placeholder="ENTER EMPLOYEE NAME">
													<input class="textbox" type="text" name="empSalary" required="required" placeholder="ENTER EMPLOYEE SALARY">
													<label>Select Department</label>
															<select class="dropdown" name="department.departmentId">
																<option value="10">HR</option>
																<option value="20">FIN</option>
																<option value="30">TR</option>
																<option value="40">DEV</option>
																</select>
														<input class="textbox" type="text" name="address.street" required="required" placeholder="ENTER STREET NAME">
														<input class="textbox" type="text" name="address.city" required="required" placeholder="ENTER CITY NAME">
														<input class="textbox" type="text" name="address.state" required="required" placeholder="ENTER STATE NAME">
														<input class="textbox" type="text" name="address.country" required="required" placeholder="ENTER COUNTRY NAME">
														<label>Select Skills</label>
															<input type="checkbox" name="skills" value="1">java
																<input type="checkbox" name="skills" value="2">.net
																<input type="checkbox" name="skills" value="3">php
																<input type="checkbox" name="skills" value="4">cpp
																<input type="checkbox" name="skills" value="5">html
														<input class="btn" type="submit" name="submit" value="REGISTER"></td>
														
													</form>
								          </p>
								        </div>
								      </div>
								      
								    </div>
								  </div>
        						</div>
        						</div>
        					</li>
        					<li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      					</ul>
    				</div>
  				</div>
			</nav>
   
   <div id="main">
   <h3 style="font-family: cursive;">HOME PAGE</h3>
   		
   			<c:out value="${message}"></c:out>
   		</div>
   		<h3 style="font-family: cursive;">SUCCESS PAGE</h3><br><br>
				<div class="row">
			<div class="card-deck">
			<c:set var="i" value="0"></c:set>
			<c:forEach items="${list}" var="employee">
			
			<div class="col-xl-2 col-lg-4 col-md-6 col-xs-12">
			<div class="card panel panel-default panel-body" style="width:320px">
			
    			<img class="card-img-top" src="../images/<c:out value="${employee.empName}"></c:out>.jpg" alt="Card image" style="width:150px; height:200px">
    			<div class="card-body border:1px" style="text-align: left;">
    			
    				<h4 class="card-title"><b>${employee.empName}</b></h4><br>
    				<p class="card-text"><b>EmployeeSalary:</b> ${employee.empSalary }<br>
    				<b>Department Name:</b> ${employee.department.departmentName }<br>
    				<b>Department Location:</b> ${employee.department.departmentLocation }<br>
    				<b>Street:</b> ${employee.address.street }<br>
    				<b>City:</b> ${employee.address.city }<br>
    				<b>State:</b> ${employee.address.state }<br>
    				<b>Country:</b> ${employee.address.country }<br>
    				<b>Employee skill:</b> ${employee.skillStr }<br>
    				<c:set var="y" value="#"></c:set>
    				<c:set var="dest" value="${y}a${i}"></c:set>
    				<div class="links">


						<!-- <a class="btn btn-success btn-lg" role="button" data-toggle="modal" data-target=" " style="text-decoration: none;">Edit</a> -->
						
						<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="${dest }">Edit</button>
						
						<div class="modal fade" id="a${i}" role="dialog">
								    <div class="modal-dialog">
								    
								      <!-- Modal content-->
								      <div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								          <h4 class="modal-title">EDIT EMPLOYEE</h4>
								        </div>
								        <div class="modal-body">
								          <p>
								          		
								          	<form name="update" method="post" action="UpdateController">
												<input type="hidden" name="empId" value="${employee.empId}" >
												<input type="hidden" name="addressId" value="${employee.address.addressId}" >
												<input class="textbox" type="text" name="empName" value="${employee.empName} " placeholder="ENTER EMPLOYEE NAME">	
													<input class="textbox" type="text" name="empSalary" value="${employee.empSalary}" placeholder="ENTER EMPLOYEE SALARY"> 
													<label>Select Department:</label><br><select class="dropdown" name="department.departmentId">
															<c:forEach items="${departmentList }" var="department">
															<%-- <option value="${department.departmentId }">${department.departmentName}</option> --%>
																	<c:if test="${employee.department.departmentName eq department.departmentName}">
																		<option value="${department.departmentId }" selected="selected">${department.departmentName}</option>
																	</c:if>
																	<c:if test="${employee.department.departmentName ne department.departmentName}">
																		<option value="${department.departmentId }">${department.departmentName}</option>
																	</c:if>
															</c:forEach>	
															</select>
													<input class="textbox" type="text" name="address.street" value="${employee.address.street}" placeholder="ENTER STREET NAME">
													<input class="textbox" type="text" name="address.city" value="${employee.address.city }" placeholder="ENTER CITY NAME">
													<input class="textbox" type="text" name="address.state" value="${employee.address.state }" placeholder="ENTER STATE NAME">
													<input class="textbox" type="text" name="address.country" value="${employee.address.country }" placeholder="ENTER COUNTRY NAME">
													
													<c:set var="skillSet" value="${employee.skillStr.split(',') }"/>
													<label>Select Skills:</label>
													
															<c:forEach items="${ skillList }" var="skills">
											
																<c:set var="isChecked" scope="session" value="${5 }"/>
																<c:forEach items="${skillSet }" var="empSkill">
																<c:if test="${skills.skillName eq empSkill }">
																	<c:set var="isChecked" value="${10 }"/>
																	</c:if>
												
																</c:forEach>
											
																<input type="checkbox"  name="skills" value="${skills.skillId }"<c:if test="${isChecked eq 10 }">checked="checked" </c:if>>${skills.skillName }
											
											
									 							<c:set var="isChecked" value="${5 }"/>
									 		
															</c:forEach>
														<input class="btn" type="submit" name="submit" value="UPDATE">
									   			</form>		
								          </p>
								        </div>
								      </div>
								      
								    </div>
								  </div>
						
						<%-- <a class="btn btn-success btn-lg" role="button" href="getemp?empId=${employee.empId}">Edit</a> --%>
						<a class="btn btn-danger btn-lg" role="button" href="deletemp?empId=${employee.empId}">Delete</a>
					</div>
   	 			</div>
  		</div>
   </div>
   		<c:set var="i" value="${i+1}"></c:set>
  		</c:forEach>	
  		<!-- Modal -->
								  
  		
  		</div>
  		</div>
		

	
   </div>
   <!-- 
    	<a class="btn" href="LogoutServlet">LogOut</a>
		<a class="btn" href="ListEmployeeServlet">List</a>
   
    -->
   
   </div>

   <div id="footer">
   
   Copyright &copy; payroll.com
   </div>



</div>

	
</body>
</html>


<%-- <tr>

			<td><input type="hidden" name="empId" value="${employee.empId}"></td>
			<td><label>EmployeeName</label></td>
			<td>${employee.empName}</td>
			<td><label>EmployeeSalary</label></td>
			<td>${employee.empSalary }</td>
			</tr>

		<tr>
			<td><label>Department Name</label></td>
			<td>${employee.department.departmentName }</td>
			<td><label>department location</label></td>
			<td>${employee.department.departmentLocation }</td>
			 <td><label>Street</label></td>
			 <td>${employee.address.street }</td>
		</tr>
		<tr>
			<td><label>City</label></td>
			<td>${employee.address.city }</td>
			<td><label>State</label></td>
			<td>${employee.address.state }</td>
			<td><label>country</label></td>
			<td>${employee.address.country }</td>
		</tr>

		<tr>
			<td><label>Employee skill</label></td>
			<td>${employee.skillStr }</td>
			<td><a class="btn"  href="getemp?empId=${employee.empId}">Edit</a></td>
			<td><a class="btn" href="deletemp?empId=${employee.empId}">Delete</a></td>
		
		</tr>
 --%>