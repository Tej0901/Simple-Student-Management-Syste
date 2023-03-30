package com.login.teja;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("Link.html").include(request, response);
		
		Cookie cookie = new Cookie("uName", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		out.println("you are Successfully Logged Out.....");
		
		out.close();
		
	}

}
