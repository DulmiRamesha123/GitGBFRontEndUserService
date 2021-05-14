<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Users Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/users.js"></script>
	</head>
	<body> 
			<div class="container"><div class="row"><div class="col-6"> 
			<h1>Users Management  </h1>
			<form id="formUser" name="formUser">
			 User code: 
			 <input id="userCode" name="userCode" type="text" class="form-control form-control-sm">
			 <br> First Name: 
			 <input id="firstName" name="firstName" type="text" class="form-control form-control-sm">
			 <br> Last Name:  
			 <input id="lastName" name="lastName" type="text" class="form-control form-control-sm">
			 <br> Date of Birth:  
			 <input id="dob" name="dob" type="text"  class="form-control form-control-sm">
			 <br> Gender(M/F):
			 <input id="gender_M_F" name="gender_M_F" type="text" class="form-control form-control-sm">
			 <br>
			 <br> Email:
			 <input id="email" name="email" type="text" class="form-control form-control-sm">
			 <br>
			 <br> Address:
			 <input id="address" name="address" type="text" class="form-control form-control-sm">
			 <br>
			 <br> Phone:
			 <input id="phone" name="phone" type="text"class="form-control form-control-sm">
			 <br>
			 <br> Password:
			 <input id="password" name="password" type="password" class="form-control form-control-sm">
			 <br>
			 <br> Type Buyer(T/F):
			 <input id="typeBuyer_T_F" name="typeBuyer_T_F" type="text" class="form-control form-control-sm">
			 <br>
			 <br> Type Researcher(T/F):
			 <input id="typeResearcher_T_F" name="typeResearcher_T_F" type="text"  class="form-control form-control-sm">
			 <br>
			 <br> Type FundingBodies(T/F):
			 <input id="typeFundingBodies_T_F" name="typeFundingBodies_T_F" type="text" class="form-control form-control-sm">
			 <br>
			  
			 <input id="btnSave" name="btnSave" type="button" value="Save" 
			 class="btn btn-primary">
			 <input type="hidden" id="hidUserIDSave" 
			 name="hidUserIDSave" value="">
			</form>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="divUsersGrid">
			 <%
			 User userObj = new User(); 
			 out.print(userObj.readUsers()); 
			 %>
			</div>
			</div> </div> </div> 
</body>
</html>