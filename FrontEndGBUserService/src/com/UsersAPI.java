package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsersAPI
 */
@WebServlet("/UsersAPI")
public class UsersAPI extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	User userObj = new User();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

 // Convert request parameters to a Map
    private static Map getParasMap(HttpServletRequest request)
    {
     Map<String, String> map = new HashMap<String, String>();
    try
     {
     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
     String queryString = scanner.hasNext() ?
     scanner.useDelimiter("\\A").next() : "";
     scanner.close();
     String[] params = queryString.split("&");
     for (String param : params)
     { 
    	 String[] p = param.split("=");
    	 map.put(p[0], p[1]);
    	 }
    	 }
    	catch (Exception e)
    	 {
    	 }
    	return map;
    	}
    	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 String output = userObj.insertUser(
		    request.getParameter("userCode"),
		    request.getParameter("firstName"),
			request.getParameter("lastName"),
			request.getParameter("dob"),
			request.getParameter("gender_M_F"),
			request.getParameter("email"),
			request.getParameter("address"),
			request.getParameter("phone"),
			request.getParameter("password"),
			request.getParameter("typeBuyer_T_F"),
			request.getParameter("typeResearcher_T_F"),
			request.getParameter("typeFundingBodies_T_F"));
			response.getWriter().write(output);
			System.out.println("Got insert");		
			}


	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
		    System.out.println("Got here to update");
			 Map paras = getParasMap(request);
			 String output = userObj.updateUser(paras.get("hidUserIDSave").toString(),
			 paras.get("userCode").toString(),
			 paras.get("firstName").toString(),
			 paras.get("lastName").toString(),
			 paras.get("dob").toString(),
			 paras.get("gender_M_F").toString(),
			 paras.get("email").toString(),
			 paras.get("address").toString(),
			 paras.get("phone").toString(),
			 paras.get("password").toString(),
			 paras.get("typeBuyer_T_F").toString(),
			 paras.get("typeResearcher_T_F").toString(),
			 paras.get("typeFundingBodies_T_F").toString());
			 response.getWriter().write(output);
			 System.out.println("updated");
			}
			protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = userObj.deleteUser(paras.get("uID").toString());
			response.getWriter().write(output);
			System.out.println("Deleted");
			}


}
