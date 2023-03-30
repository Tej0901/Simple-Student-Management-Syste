package com.login.teja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Link.html").include(request, response);
		
		Cookie cookies[] = request.getCookies();
		if(cookies!=null)
		{
			String name=cookies[0].getValue();
			if(!name.equals("") || name!=null)
			{
				out.println("Profile Operations Page....");
				out.println("<br>Welcome "+" Teja Vardhan");
				request.getRequestDispatcher("AdminOperations.html").include(request, response);
			}
		}
		else
		{
			out.println("Please login First to Access!!!!!");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}
}
