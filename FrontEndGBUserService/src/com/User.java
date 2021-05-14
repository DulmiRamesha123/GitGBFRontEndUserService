package com;
import java.sql.*;
public class User
{
	private Connection connect()
	 {
		 Connection con = null;
		 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
	 }
	 catch (Exception e)
	  {e.printStackTrace();}
	 return con;
	 }
	public String readUsers()
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for reading.";
				 }
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>User Code</th>"+ 
				 		"<th>First Name</th>" +
						 "<th>Last Name</th>" +
						 "<th>dob</th>" +
						 "<th>Gender</th>" +
						 "<th>Email</th>" +
						 "<th>Address</th>" +
						 "<th>Phone</th>" +
						 "<th>Password</th>" +
						 "<th>Buyer</th>" +
						 "<th>Researcher</th>" +
						 "<th>Funder</th>" +
						 "<th>Update</th><th>Remove</th></tr>";
							 
				 String query = "select * from user";
							 
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 // iterate through the rows in the result set
				 
				 while (rs.next())
				 {
					 String uID = Integer.toString(rs.getInt("uID"));
					 String userCode = rs.getString("userCode");
					 String firstName = rs.getString("firstName");
					 String lastName = rs.getString("lastName");
					 String dob = rs.getString("dob");
					 String gender_M_F = rs.getString("gender_M_F");
					 String email = rs.getString("email");
					 String address = rs.getString("address");
					 String phone = rs.getString("phone");
					 String password = rs.getString("password");
					 String typeBuyer_T_F = rs.getString("typeBuyer_T_F");
					 String typeResearcher_T_F = rs.getString("typeResearcher_T_F");
					 String typeFundingBodies_T_F = rs.getString("typeFundingBodies_T_F");
					 // Add into the html table
					 output += "<tr><td><input id='hidUserIDUpdate'name='hidUserIDUpdate'type='hidden' value='" + uID + "'>" + userCode + "</td>";
					 output += "<td>" + firstName + "</td>";
					 output += "<td>" + lastName + "</td>";
					 output += "<td>" + dob + "</td>";
					 output += "<td>" + gender_M_F + "</td>";
					 output += "<td>" + email + "</td>";
					 output += "<td>" + address + "</td>";
					 output += "<td>" + phone + "</td>";
					 output += "<td>" +  password + "</td>";
					 output += "<td>" + typeBuyer_T_F + "</td>";
					 output += "<td>" + typeResearcher_T_F + "</td>";
					 output += "<td>" + typeFundingBodies_T_F + "</td>";
					 // buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary' data-userid='" + uID + "'></td>"
					 + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger' data-userid='"+ uID + "'>" + "</td></tr>";
					 }
					 con.close();
					 // Complete the html table
					 output += "</table>";
				 }
				 catch (Exception e)
				 {
				 output = "Error while reading the users.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		 }
	public String insertUser(String code, String fname,String lname, String dob,String gender , String email , String address,String phone
			,String password,String tBuyer, String tResearcher,String tFunder)
	 {
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for inserting.";
				 }
				 // create a prepared statement
				 String query = " insert into user(`uID`,`userCode`,`firstName`,`lastName`,`dob`,`gender_M_F`,`email`,`address`,`phone`,`password`,`typeBuyer_T_F`,`typeResearcher_T_F`,`typeFundingBodies_T_F	`)"+ " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?,?)";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 // binding values
						 preparedStmt.setInt(1, 0);
						 preparedStmt.setString(2, code);
						 preparedStmt.setString(3, fname);
						 preparedStmt.setString(4, lname);
						 preparedStmt.setString(5, dob);
						 preparedStmt.setString(6, gender);
						 preparedStmt.setString(7, email);
						 preparedStmt.setString(8, address);
						 preparedStmt.setString(9, phone);
						 preparedStmt.setString(10, password);
						 preparedStmt.setString(11, tBuyer);
						 preparedStmt.setString(12, tResearcher);
						 preparedStmt.setString(13, tFunder);
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 String newUsers = readUsers();
						 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
					 }
					 catch (Exception e)
					 {
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
						 System.err.println(e.getMessage());
					 }
			 return output;
	}
			public String updateUser(String uID, String code, String fname,String lname, String dob,String gender , String email , String address,String phone
					,String password,String tBuyer, String tResearcher,String tFunder)
			 {
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {
						 return "Error while connecting to the database for updating.";
					 }
					 // create a prepared statement
					 String query = "UPDATE user SET userCode=?,firstName=?,lastName=?,dob=?,gender_M_F=?,email=?,phone=?,password=?"
					 		+ ",typeBuyer_T_F=?,typeResearcher_T_F	=?,typeFundingBodies_T_F=? WHERE uID=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setString(1, code);
					 preparedStmt.setString(2, fname);
					 preparedStmt.setString(3, lname);
					 preparedStmt.setString(4, dob);
					 preparedStmt.setString(5, gender);
					 preparedStmt.setString(6, email);
					 preparedStmt.setString(7, address);
					 preparedStmt.setString(8, phone);
					 preparedStmt.setString(9, password);
					 preparedStmt.setString(10, tBuyer);
					 preparedStmt.setString(11, tResearcher);
					 preparedStmt.setString(12, tFunder);
					 preparedStmt.setInt(13, Integer.parseInt(uID));
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newUsers = readUsers();
					 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
				 } 
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
				 System.err.println(e.getMessage());
				 }
				 return output;
			 }
			public String deleteUser(String uID)
			 {
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
						 {
						 return "Error while connectingto the database for deleting.";
						 }
					 // create a prepared statement
					 String query = "delete from user where uID=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(uID));
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newUsers = readUsers();
					 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
					 }
				 catch (Exception e)
				 {
				 output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
				 System.err.println(e.getMessage());
				 }
				 return output;
			 }
}