package com.login.teja;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Link.html").include(request, response);
		try 
		{
			if (request.getParameter("fetchAllDetails") != null) 
			{
	            DatabaseLayer.setConnectionAndPerformOperations("1",request, response);
			}
			else if (request.getParameter("stdDetails").equals("Fetch"))
			{	
	            DatabaseLayer.setConnectionAndPerformOperations("2",request, response);
			}
			else if (request.getParameter("stdDetails").equals("Delete"))
			{
				DatabaseLayer.setConnectionAndPerformOperations("3",request, response);
			}
			else if (request.getParameter("stdDetails").equals("Update"))
			{
				DatabaseLayer.setConnectionAndPerformOperations("4",request, response);
			}
			else if (request.getParameter("stdDetails").equals("Insert"))
			{
				DatabaseLayer.setConnectionAndPerformOperations("5",request, response);
			}
		}
		catch (ClassNotFoundException e) 
		{
			out.println("<br/>Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		catch (SQLException e) 
		{
        	out.println("<br/>Invalid Operation!!! Error Occurred!!!  -->  "+e);
        } 
		catch (Exception e) 
		{
			out.println("<br/>Invalid Operation!!! Error Occurred!!!  -->  "+e);
		}
		finally
		{
			out.close();
		}
	}
}

			
			
			
         
