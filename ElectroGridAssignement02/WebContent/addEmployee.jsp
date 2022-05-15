<%@page import="com.EmployeeDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/main.js"></script>

<link rel="stylesheet" href="Views/style.css" type="text/css"/>


</head>
<body>




	<div class="container">
		<h1 class="label">Register</h1>
		
		
		<form class="login_form" id="formRegister" name="formRegister">
		
		    <div class="font">Employee ID</div>
			<input id="EMP_ID" name="EMP_ID" type="email" class="form-control form-control-sm">
		
		    <div class="font">Employee Name</div>
			<input id="EMP_Name" name="EMP_Name" type="text" class="form-control form-control-sm">
			
			<div class="font">Employee number</div>
			<input id="EMP_Number" name="EMP_Number" type="text" class="form-control form-control-sm">
			
			
			<div class="font">Employee Deparment</div>
			<input id="EMP_Deparment" name="EMP_Deparment" type="text" class="form-control form-control-sm">
			
			<div class="font font2">Employee Email</div>
			<input id="EMP_Email" name="EMP_Email" type="text" class="form-control form-control-sm">
			
			<div class="font">Employee Age</div>
			<input id="EMP_Age" name="EMP_Age" type="text" class="form-control form-control-sm">
			
			<div class="font">Employee DOB</div>
			<input id="EMP_DOB" name="EMP_DOB" type="text" class="form-control form-control-sm">
			
			<br><br>
			<input id="btnSave" name="btnSave" type="button" value="Register" class="btn btn-primary"> 
			<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
			
			<br>
			<a href="home.jsp"><input type="button" value="Home page" class="btn btn-primary"></a>
			<br>
			
		</form>
		
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		

		
	      
	</div>	
	
	
					
		        <div style="position:relative;right:20px;" id="divItemsGrid">
					<%
					EmployeeDao empDao = new EmployeeDao();
					out.print(empDao.readEmployee());
					%>
				</div>		







</body>
</html>