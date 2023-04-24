package com.login.teja;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Link.html").include(request, response);
		
//		Cookie cookies[]=request.getCookies();
//		if(cookies==null)
//		{
			String uName = request.getParameter("userName");
			String uPassword = request.getParameter("userPassword");
			
			if(uPassword.equals("teja0901") && uName.equalsIgnoreCase("Admin"))
			{
				out.println("Successfully Logged In......");
				out.println("<br>Welcome "+" Teja Vardhan");
				Cookie cookie=new Cookie("uName", uName);
				response.addCookie(cookie);
			}
			else
			{
				out.println("Your Name or Password Is Incorrect, Please Try Again with correct Details !!!!!!");
				request.getRequestDispatcher("login.html").include(request, response);
			}
		
//		else 
//		{
//			out.println("\nYou are already Logged-In...");
//			request.getRequestDispatcher("/ProfileServlet").include(request, response);
//		}
//		
		out.close();
	}
}