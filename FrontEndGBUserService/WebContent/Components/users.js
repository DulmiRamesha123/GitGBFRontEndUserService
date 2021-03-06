$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});





$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "UsersAPI",
 type : type,
 data : $("#formUser").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onUserSaveComplete(response.responseText, status);
 }
 });
});
 
 
 function onUserSaveComplete(response, status)
 {
 if (status == "success")
  {
  var resultSet = JSON.parse(response);
  if (resultSet.status.trim() == "success")
  {
  $("#alertSuccess").text("Successfully saved.");
  $("#alertSuccess").show();
  $("#divUsersGrid").html(resultSet.data);
  } else if (resultSet.status.trim() == "error")
  {
  $("#alertError").text(resultSet.data);
  $("#alertError").show();
  }
  } else if (status == "error")
  {
  $("#alertError").text("Error while saving.");
  $("#alertError").show();
  } else
  {
  $("#alertError").text("Unknown error while saving..");
  $("#alertError").show();
  } 
 $("#hidUserIDSave").val("");
 $("#formUser")[0].reset();
}

 
 
 $(document).on("click", ".btnUpdate", function(event)
		 {
		 $("#hidUserIDSave").val($(this).data("userid"));
		  $("#userCode").val($(this).closest("tr").find('td:eq(0)').text());
		  $("#firstName").val($(this).closest("tr").find('td:eq(1)').text());
		  $("#lastName").val($(this).closest("tr").find('td:eq(2)').text());
		  $("#dob").val($(this).closest("tr").find('td:eq(3)').text());
		  $("#gender_M_F").val($(this).closest("tr").find('td:eq(4)').text());
		  $("#email").val($(this).closest("tr").find('td:eq(5)').text());
		  $("#address").val($(this).closest("tr").find('td:eq(6)').text());
		  $("#phone").val($(this).closest("tr").find('td:eq(7)').text());
		  $("#password").val($(this).closest("tr").find('td:eq(8)').text());
		  $("#typeBuyer_T_F").val($(this).closest("tr").find('td:eq(9)').text());
		  $("#typeResearcher_T_F").val($(this).closest("tr").find('td:eq(10)').text());
		  $("#typeFundingBodies_T_F").val($(this).closest("tr").find('td:eq(11)').text());
		 });
 
 
 $(document).on("click", ".btnRemove", function(event)
		 {
		  $.ajax(
		  {
		  url : "UsersAPI",
		  type : "DELETE",
		  data : "uID=" + $(this).data("userid"),
		  dataType : "text",
		  complete : function(response, status)
		  {
		  onUserDeleteComplete(response.responseText, status);
		  }
		  });
		 });
 
 
 function onUserDeleteComplete(response, status)
 {
 if (status == "success")
  {
  var resultSet = JSON.parse(response);
  if (resultSet.status.trim() == "success")
  {
  $("#alertSuccess").text("Successfully deleted.");
  $("#alertSuccess").show();
  $("#divUsersGrid").html(resultSet.data);
  } else if (resultSet.status.trim() == "error")
  {
  $("#alertError").text(resultSet.data);
  $("#alertError").show();
  }
  } else if (status == "error")
  {
  $("#alertError").text("Error while deleting.");
  $("#alertError").show();
  } else
  {
  $("#alertError").text("Unknown error while deleting..");
  $("#alertError").show();
  }
 }
 
 
 
 function validateUserForm() 
 {
 // User ID
 if ($("#uID").val().trim() == "") 
 { 
  return "Insert User ID."; 
 } 
 // User code
 if ($("#userCode").val().trim() == "") 
  { 
  return "Insert User Code."; 
  } 
 // First Name
 if ($("#firstName").val().trim() == "") 
  { 
  return "Insert First Name."; 
  } 
//Last Name-------------------------------
 if ($("#lastName").val().trim() == "") 
  { 
  return "Insert Last Name."; 
  } 
 //Date of Birth-------------------------------
  if ($("#dob").val().trim() == "") 
   { 
   return "Insert Date of Birth."; 
   }
 //Gender-------------------------------
  if ($("#gender_M_F").val().trim() == "") 
   { 
   return "Insert Gender."; 
   }
 //email-------------------------------
  if ($("#email").val().trim() == "") 
   { 
   return "Insert Email."; 
   }
 // address
  if ($("#address").val().trim()== "")
   
  { 
  return "Insert address"; 
  } 
  
//phone
  if ($("#phone").val().trim()== "")
   
  { 
  return "Insert phone Number"; 
  } 
  
//password
  if ($("#password").val().trim()== "")
   
  { 
  return "Insert password"; 
  } 
  
//TypeBuyer
  if ($("#typeBuyer_T_F").val().trim()== "")
   
  { 
  return "Insert whether Buyer Type"; 
  } 
  
//Researcher
  if ($("#typeResearcher_T_F").val().trim()== "")
   
  { 
  return "Insert Researcher Type"; 
  } 
  
//Funder
  if ($("#typeFundingBodies_T_F").val().trim()== "")
   
  { 
  return "Insert whether Funder Type"; 
  } 
  
 return true; 
 }

  